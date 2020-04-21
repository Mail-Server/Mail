import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartupGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartupGUI window = new StartupGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartupGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1027, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome To Mail Server");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(292, 35, 438, 92);
		frame.getContentPane().add(lblNewLabel);
		
		JButton createNewAccount = new JButton("Create new account");
		createNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SignupGUI SUG=new SignupGUI();
				SUG.setVisible(true);
			}
		});
		createNewAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		createNewAccount.setBounds(206, 216, 227, 83);
		frame.getContentPane().add(createNewAccount);
		
		JButton signin = new JButton("Signin");
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginGui LIG=new LoginGui();
				LIG.setVisible(true);
			}
		});
		signin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		signin.setBounds(618, 216, 227, 83);
		frame.getContentPane().add(signin);
	}

}
