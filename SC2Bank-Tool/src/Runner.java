

/**
 *
 * @author josh@josho.com
 */
public class Runner {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		String PROGRAMNAME = "Starcraft2 BankFile Backup";
		String ABOUT = "About:\nThis will find and copy your SC2Bank files to your desired location.\n\n" +
		"To Backup:\n1.Source -> Starcraft 2 Installation\n2.Destination -> Backup Location\n" +
		"3.Select the files to Backup\n4.Click the Backup button\n\nRestore is soon to come.";
		String HEADER = "Author: Josh@Joshho.com";
		String FOOTER = "https://github.com/joshho/SC2Bank-Tool";

		View view = new View(PROGRAMNAME);
		Model model = new Model(); 
		/*Controller controller = */new Controller(view, model);

		view.setjTxt_RootHome_Text(System.getProperty("user.home"));
		model.setRootHome(System.getProperty("user.home"));
		view.setjTxt_DestHome_Text(System.getProperty("user.home"));
		model.setDestHome(System.getProperty("user.home"));

		view.setjTA_HowTo_Text(ABOUT);
		view.setjLbl_Header_Text(HEADER);
		view.setjLbl_Footer_Text(FOOTER);

		view.setVisible(true);
	}
}
