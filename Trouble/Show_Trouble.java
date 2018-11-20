package Trouble;

public class Show_Trouble {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater( new Runnable(){
			@Override
			public void run() {
				TroubleGUI gui = new TroubleGUI();//here is the magic
			}
		});
	}

}
