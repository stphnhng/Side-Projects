import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextEditor {
	
	public static void main(String[]args){
		createJFrame();		
	}
	
	public static void createJFrame(){
		JFrame frame = new JFrame("Text Editor");
		JPanel panel = new JPanel(new BorderLayout());
		JMenuBar bar = new JMenuBar();
		JMenu firstOption = new JMenu("File");
		JMenuItem newFile = new JMenuItem("New File");
		JTextArea text = new JTextArea("Type text here...");
		JMenuItem saveFile = new JMenuItem("Save File");
		newFile.addActionListener(new MenuActionListener());
		saveFile.addActionListener(new MenuActionListener(text.getText()));
		bar.add(firstOption);
		firstOption.add(newFile);
		firstOption.add(saveFile);
		frame.setContentPane(panel);
		panel.add(text);
		frame.setSize(500,500);
		frame.setLocation(500,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(bar);
		frame.setVisible(true);
	}
	
}

class MenuActionListener implements ActionListener{
	String saveText;
	private static PrintStream output;
	
	public MenuActionListener(String text){
		saveText = text;
		try {
			output = new PrintStream(new File("sample.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MenuActionListener(){
		try {
			output = new PrintStream(new File("sample.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("New File")){
			createTextFile();
		}else if(e.getActionCommand().equals("Save File")){
			try{
				output = new PrintStream(new File("sample.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			output.print(saveText);
		}
	}
	
	public static void createTextFile(){
		String fileName = JOptionPane.showInputDialog("What would you like to name the file?");
		try {
			output = new PrintStream(new File(fileName + ".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}


