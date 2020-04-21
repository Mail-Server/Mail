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

public class LoginGui extends JFrame {

	private JPanel contentPane;
	private JTextField Email;
	private JPasswordField Password;
	private JPasswordField ConfirmPassword;
	private JLabel lblConfirmPassword;
	private JButton login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
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
	public LoginGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Email = new JTextField();
		Email.setBounds(340, 15, 292, 66);
		contentPane.add(Email);
		Email.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(140, 10, 76, 66);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(140, 120, 110, 66);
		contentPane.add(lblPassword);
		
		Password = new JPasswordField();
		Password.setBounds(340, 125, 292, 66);
		contentPane.add(Password);
		
		ConfirmPassword = new JPasswordField();
		ConfirmPassword.setBounds(340, 243, 292, 66);
		contentPane.add(ConfirmPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(140, 238, 183, 66);
		contentPane.add(lblConfirmPassword);
		
		login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CApp C=new CApp();
				if(ConfirmPassword.getText().equals(Password.getText())) {
					
					if(C.signin(Email.getText(), Password.getText())) {
						JOptionPane.showMessageDialog(null, "You have signed in successfully");
				}
					else {
						JOptionPane.showMessageDialog(null,"");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Password and Confirm Password fields are different");
				}
			}
		});
		login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		login.setBounds(340, 363, 284, 75);
		contentPane.add(login);
	}
}
