

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author josh@josho.com
 */
public class Controller{
	Model model;
	View view;
	final String CONFIGFILE = "config.properties";
	final String BANKFILE = "BANKFILE";

	public Controller(View view, Model model) {
		this.model = model;
		this.view = view;

		view.addBtn_RestoreListener(new RestoreFileListener());
		view.addBtn_BackupListener(new BackupFileListener());
		view.addBtn_FindListener(new FindFileListener());
		view.addBtn_RootHomeListener(new FileChangeListenerRoot());
		view.addBtn_DestHomeListener(new FileChangeListenerDest());
		view.addTA_FilesFound_SelectedListener(new SelectedListListener());
	}

	class RestoreFileListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			view.showError("Not ready yet, you can follow us here: https://github.com/joshho/SC2Bank-Tool");
			int i = 0;
			if(i == 0)
				return;

			//NO files to be found...
			List<File> filesFound = model.getFilesFound();
			if(filesFound.size() == 0){
				view.showError("No files were found to be copied");
				return;
			}

			File destRoot = new File(model.getDestHome());

			//Check for Config File 
			File iniFile = new File(destRoot.getAbsoluteFile() + File.separator + CONFIGFILE);
			if(!iniFile.exists()){
				view.showError("Configuration File could not be found for restoring Information");
				return;
			}
		}
	}

	class SelectedListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			model.setFilesFound(model.stringToFiles_List(view.getjTA_FilesFound_List()));
		}
	}

	class BackupFileListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {

			//NO files to be found...
			List<File> filesFound = model.getFilesFound();
			if(filesFound.size() == 0){
				view.showError("No files were found to be copied");
				return;
			}

			File destRoot = new File(model.getDestHome());
			if(!destRoot.exists()){ 
				//view.showError("Destination Directory does not exist");
				if(0==view.showYN(destRoot.getAbsolutePath() + " was not found.\nCreate New?"))
					destRoot.mkdirs();
				else
					return;
			}

			//Check for Config File 
			File iniFile = new File(destRoot.getAbsoluteFile() + File.separator + CONFIGFILE);
			if(!iniFile.exists()){
				try {
					iniFile.createNewFile();
				} catch (IOException e1) {
					view.showError("Configuration File could not created");
					return;
				}
			}

			//Load the Config File
			Properties defaultProps = new Properties();
			try {
				FileInputStream in = new FileInputStream(destRoot.getAbsoluteFile() + File.separator  + CONFIGFILE);
				defaultProps.load(in);
				in.close();
			} catch (IOException e1) {
				view.showError("Configuration File could not be loaded");
				return;
			}

			long timestamp = System.currentTimeMillis();
			File destinationForFiles = new File(destRoot.getAbsolutePath() + File.separator + timestamp);
			destinationForFiles.mkdirs();

			//Copy the files (or try to)
			boolean success = true;
			for(int i =0; i<filesFound.size(); i++){
				File source = filesFound.get(i);
				File dest = new File(destinationForFiles.getAbsolutePath(), source.getName());
				try {
					copyDirectory(source, dest);
				} catch (IOException e1) {
					success = false;
					view.showError("Error with copying: "+dest.getAbsolutePath());
				}
				defaultProps.setProperty(BANKFILE+"."+source.getName()+"."+timestamp, timestamp+File.separator+dest.getName());
			}

			try {
				FileOutputStream fo = new FileOutputStream(destRoot.getAbsoluteFile() + File.separator + CONFIGFILE);
				defaultProps.store(fo, null);
				fo.close();
			} catch (IOException e) {
				view.showError("Config file could not be written to, re-back up to a new directory is recommended");
				return;
			}

			if(success){
				view.showInfo("All files were copied", "Success");
			}
		}

		public void copyDirectory(File sourceLocation , File targetLocation) throws IOException {
			if (sourceLocation.isDirectory()) {
				if (!targetLocation.exists()) {
					targetLocation.mkdir();
				}

				String[] children = sourceLocation.list();
				for (int i=0; i<children.length; i++) {
					copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
				}
			} else {

				InputStream in = new FileInputStream(sourceLocation);
				OutputStream out = new FileOutputStream(targetLocation);

				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			}
		}
	}

	class FindFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File root = new File(model.getRootHome());
			if(!root.exists()){ 
				view.showError("Root Directory does not exist");
				return;
			}
			List<File> filesFound = fileFinder(root, "SC2Bank".toLowerCase());
			//model.setFilesFound(filesFound);

			if(filesFound.size() == 0){
				view.showError("No files were found.");
				return;
			}

			Vector<String> filesString = new Vector<String>(filesFound.size());
			for(int i =0; i<filesFound.size(); i++){
				filesString.add(filesFound.get(i).getAbsolutePath());
			}
			view.setjTA_FilesFound_Text(filesString);
		}

		private List<File> fileFinder(File current, String extension){
			Vector<File> result = new Vector<File>();
			File[] x = current.listFiles();
			if(x != null){
				for(int i = 0; i< x.length; i++){
					if(x[i].isDirectory()){
						result.addAll(fileFinder(x[i], extension));
					}else if(x[i].isFile()){
						if(x[i].getAbsolutePath().toLowerCase().endsWith(extension)){
							result.add(x[i].getParentFile());
						}
					}
				}
			}
			return result;
		}
	}


	class FileChangeListenerRoot implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int rVal = c.showOpenDialog(view);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				model.setRootHome(c.getSelectedFile().getAbsolutePath());
				view.setjTxt_RootHome_Text(model.getRootHome());
			}
		}
	}

	class FileChangeListenerDest implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int rVal = c.showOpenDialog(view);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				model.setDestHome(c.getSelectedFile().getAbsolutePath());
				view.setjTxt_DestHome_Text(model.getDestHome());
			}
		}
	}
}
