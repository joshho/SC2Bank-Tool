import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author josh@josho.com
 */
public class View extends javax.swing.JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form View
	 */
	public View(String programName) {

		initComponents();
		this.setTitle(programName);

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;

		// Move the window
		this.setLocation(x, y);
	}

	private void initComponents() {

		jTxt_RootHome = new javax.swing.JTextField();
		jTxt_DestHome = new javax.swing.JTextField();
		jBtn_RootHome = new javax.swing.JButton();
		jBtn_DestHome = new javax.swing.JButton();
		jBtn_Find = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTA_FilesFound = new javax.swing.JList<String>();
		jBtn_Backup = new javax.swing.JButton();
		jLbl_Header = new javax.swing.JLabel();
		jLbl_Footer = new javax.swing.JLabel();
		jLbl_RootHome = new javax.swing.JLabel();
		jLbl_DestHome = new javax.swing.JLabel();
		jBtn_Restore = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTA_HowTo = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTxt_RootHome.setText("rootHome");


		jTxt_DestHome.setText("destHome");


		jBtn_RootHome.setText("Change");


		jBtn_DestHome.setText("Change");

		jBtn_Find.setText("Lookup files in Destination Home");

		jTA_FilesFound.setSize(20,5);//, height).setColumns(20);
		jScrollPane1.setViewportView(jTA_FilesFound);

		jBtn_Backup.setText("Copy files Destination");

		jLbl_Header.setText("Description of Program");

		jLbl_Footer.setText("Author name");

		jLbl_RootHome.setText("Source Path (Starcraft 2 folder)");

		jLbl_DestHome.setText("Backup Location");

		jBtn_Restore.setText("Restore to files Found ^");

		jTA_HowTo.setColumns(20);
		jTA_HowTo.setRows(5);
		jTA_HowTo.setLineWrap(true);
		jTA_HowTo.setWrapStyleWord(true);
		jTA_HowTo.setEditable(false);
		jScrollPane2.setViewportView(jTA_HowTo);
		jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLbl_Footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(jBtn_Backup)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jBtn_Restore))
														.addGroup(layout.createSequentialGroup()
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jLbl_RootHome)
																		.addGroup(layout.createSequentialGroup()
																				.addGap(10, 10, 10)
																				.addComponent(jTxt_RootHome, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(jBtn_RootHome))
																				.addComponent(jLbl_Header, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(layout.createSequentialGroup()
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(jLbl_DestHome)
																								.addGroup(layout.createSequentialGroup()
																										.addGap(10, 10, 10)
																										.addComponent(jTxt_DestHome, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
																										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(jBtn_DestHome))
																										.addGroup(layout.createSequentialGroup()
																												.addGap(55, 55, 55)
																												.addComponent(jBtn_Find))
																												.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addGap(18, 18, 18)
																												.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addContainerGap())))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLbl_Header, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jLbl_RootHome)
										.addGap(3, 3, 3)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTxt_RootHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jBtn_RootHome))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jLbl_DestHome)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jTxt_DestHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jBtn_DestHome))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jBtn_Find)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jBtn_Backup)
																.addComponent(jBtn_Restore)))
																.addGroup(layout.createSequentialGroup()
																		.addGap(23, 23, 23)
																		.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
																		.addComponent(jLbl_Footer)
																		.addContainerGap())
		);

		pack();
		setResizable(false);
		//setSize(600,400);
	}// </editor-fold>


	void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}  

	int showYN(String prompt) {
		return JOptionPane.showConfirmDialog(null, prompt, "Prompt",
				JOptionPane.YES_NO_OPTION);
	}  

	void showInfo(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}     


	public void addBtn_RootHomeListener(ActionListener mal) {
		jBtn_RootHome.addActionListener(mal);
	}

	public void addBtn_DestHomeListener(ActionListener mal) {
		jBtn_DestHome.addActionListener(mal);
	}

	public void addBtn_FindListener(ActionListener mal) {
		jBtn_Find.addActionListener(mal);
	}

	public void addBtn_RestoreListener(ActionListener mal) {
		jBtn_Restore.addActionListener(mal);
	}

	public void addBtn_BackupListener(ActionListener mal) {
		jBtn_Backup.addActionListener(mal);
	}

	public void addTA_FilesFound_SelectedListener(ListSelectionListener  mal) {
		jTA_FilesFound.addListSelectionListener(mal);
	}

	// Variables declaration - do not modify
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTA_HowTo;
	private javax.swing.JButton jBtn_Restore;
	private javax.swing.JButton jBtn_Backup;
	private javax.swing.JButton jBtn_DestHome;
	private javax.swing.JButton jBtn_Find;
	private javax.swing.JButton jBtn_RootHome;
	private javax.swing.JLabel jLbl_Footer;
	private javax.swing.JLabel jLbl_Header;
	private javax.swing.JLabel jLbl_RootHome;
	private javax.swing.JLabel jLbl_DestHome;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList<String> jTA_FilesFound;
	private javax.swing.JTextField jTxt_DestHome;
	private javax.swing.JTextField jTxt_RootHome;
	// End of variables declaration

	public void getjTxt_RootHome_Text(String x){
		jTxt_RootHome.setText(x);
	}

	public String getjTxt_DestHome_Text() {
		return jTxt_DestHome.getText();
	}

	public void setjTxt_DestHome_Text(String x) {
		this.jTxt_DestHome.setText(x);
	}

	public String getjTxt_RootHome_Text() {
		return jTxt_RootHome.getText();
	}

	public void setjTxt_RootHome_Text(String x) {
		this.jTxt_RootHome.setText(x);
	}


	//List of files...
	public List<String> getjTA_FilesFound_List() {
		return jTA_FilesFound.getSelectedValuesList();
	}

	public void setjTA_FilesFound_Text(Vector<String> x) {
		this.jTA_FilesFound.setListData(x);
	}

	//Descrips

	public void setjLbl_Footer_Text(String x) {
		this.jLbl_Footer.setText(x);
	}

	public void setjLbl_Header_Text(String x) {
		this.jLbl_Header.setText(x);
	}

	public String getjTA_HowTo_Text() {
		return jTA_HowTo.getText();
	}

	public void setjTA_HowTo_Text(String x) {
		this.jTA_HowTo.setText(x);
	}

}
