// Stephen Hung
// 1/2/16
// Program that lets the user write in reminders which will then eventually 
// pop up and remind the user.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class PostIt {

	public static void main(String[]args){
		createJFrame();
	}
	
	public static void createJFrame(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame f = new JFrame();
		JPanel p = new JPanel(new BorderLayout());
		JTextArea text = new JTextArea("Write reminders here...");
		JButton post = new JButton("Save Reminder");
		post.addActionListener(new buttonListener(text));
		f.setContentPane(p);
		p.add(text,BorderLayout.CENTER);
		p.add(post,BorderLayout.SOUTH);
		f.setVisible(true);
		f.setSize(300,300);
		int width = (int)(screenSize.getWidth()/2) - 300;
		int height = (int)(screenSize.getHeight()/2) - 300;
		f.setLocation(width,height);
	}
	
	// copied code from
	// http://www.javacodegeeks.com/2012/10/create-new-message-notification-pop-up.html
	public static void createPopup(String message){
		JFrame frame = new JFrame();	
		frame.setUndecorated(true);
		frame.setSize(300,125);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5,5,5,5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel headingLabel = new JLabel("Reminder!");
		headingLabel.setOpaque(false);
		frame.add(headingLabel,constraints);
		constraints.gridx++;
		constraints.weightx = 0f;
		constraints.weighty = 0f;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH; 
		JButton closeButton = new JButton(new AbstractAction("X") {
			public void actionPerformed(final ActionEvent e){
				frame.dispose();
			}
		});
		closeButton.setMargin(new Insets(1,4,1,4));
		closeButton.setFocusable(false);
		frame.add(closeButton,constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5,5,5,5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel messageLabel = new JLabel("<HTML>" + message);
		frame.add(messageLabel,constraints);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
		frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height-toolHeight.bottom-frame.getHeight());
		frame.setAlwaysOnTop(true);
		new Thread(){
			public void run(){
				try{
					Thread.sleep(5000);
					frame.dispose();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public static void runPopup(String reminder){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createPopup(reminder);
	}
	
}

class buttonListener implements ActionListener{
	private static JTextArea text;
	
	public buttonListener(JTextArea temp){
		text = temp;
	}
	
	public void actionPerformed(ActionEvent e){
		PostIt.runPopup(text.getText());
	}
	
}
