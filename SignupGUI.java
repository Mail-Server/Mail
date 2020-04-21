import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignupGUI extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField Email;
	private JPasswordField ConfirmPassword;
	private JPasswordField Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupGUI frame = new SignupGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignupGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Name = new JTextField();
		Name.setBounds(364, 44, 311, 54);
		contentPane.add(Name);
		Name.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(364, 136, 311, 54);
		contentPane.add(Email);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(167, 44, 115, 54);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(167, 136, 115, 54);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(167, 227, 115, 54);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(167, 317, 165, 54);
		contentPane.add(lblConfirmPassword);
		
		ConfirmPassword = new JPasswordField();
		ConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ConfirmPassword.setBounds(364, 317, 311, 54);
		contentPane.add(ConfirmPassword);
		
		Password = new JPasswordField();//TODO Make the eye button which when clicked show the password
		Password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Password.setBounds(364, 227, 311, 54);
		contentPane.add(Password);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CApp C=new CApp();
				UserAccount acc=new UserAccount();
				acc.setEmail(Email.getText());
				acc.setName(Name.getText());
				acc.setPassword(Password.getText());
				if(ConfirmPassword.getText().equals(Password.getText())) {
					JOptionPane.showMessageDialog(null, "Password and confirm password fields are different");	
				}
				else if(C.signup(acc)) {
					JOptionPane.showMessageDialog(null, "You have created your account successfully");
				}
				else {
					JOptionPane.showMessageDialog(null,"");//TODO Show a message that includes the rules of the email address of the user or show that the email exists
				}
				
			}
		});
		btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateAccount.setBounds(212, 395, 398, 92);
		contentPane.add(btnCreateAccount);
	}
}
