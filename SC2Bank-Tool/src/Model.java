

import java.io.File;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author josh@josho.com
 */
class Model {
	private String rootHome = "";
	private String destHome = "";
	private List<File> filesFound;

	public String getRootHome() {
		return rootHome;
	}

	public synchronized void setRootHome(String rootHome) {
		this.rootHome = rootHome;
	}

	public String getDestHome() {
		return destHome;
	}

	public synchronized void setDestHome(String destHome) {
		this.destHome = destHome;
	}

	public List<File> getFilesFound() {
		return filesFound;
	}

	public synchronized void setFilesFound(List<File> filesFound) {
		this.filesFound = filesFound;
	}

	public List<File> stringToFiles_List(List<String> x){
		Vector<File> result = new Vector<File>();
		for(int i = 0; i< x.size(); i++){
			result.add(new File(x.get(i)));
		}
		return result;
	}


	public Model(){
		filesFound = new Vector<File>();
	}
}
