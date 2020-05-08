package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

public class FriendEmailsGUI extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	private JList emailList;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendEmailsGUI frame = new FriendEmailsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public FriendEmailsGUI(String [] friendEmails,App ap,int FriendNameLine) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1082, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 25, 208, 310);
		contentPane.add(scrollPane);
		
		 emailList = new JList(friendEmails);
		emailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(emailList);
		Friends f=new Friends();
		JButton newEmail = new JButton("Add New Email");
		newEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!f.addNewEmail(email.getText(), FriendNameLine, ap)) {
					JOptionPane.showMessageDialog(null,"Email doesn't exist");
				}
				else {
					emailList=new JList(f.getFriendEmails(FriendNameLine, ap));
					scrollPane.setViewportView(emailList);
					email.setText("                     New Friend Email");
				}
			}
		});
		newEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newEmail.setBounds(424, 54, 244, 41);
		contentPane.add(newEmail);
		
		JButton btnRemoveEmail = new JButton("Remove Email");
		btnRemoveEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(emailList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select an email");
				}
				f.deleteOldEmail(ap, FriendNameLine, emailList.getSelectedIndex());
				emailList=new JList(f.getFriendEmails(FriendNameLine, ap));
				scrollPane.setViewportView(emailList);
			}
		});
		btnRemoveEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoveEmail.setBounds(424, 182, 244, 41);
		contentPane.add(btnRemoveEmail);
		
		email = new JTextField();
		email.setText("                     New Friend Email");
		email.setForeground(Color.gray);
		email.setBounds(788, 54, 222, 41);
		contentPane.add(email);
		email.setColumns(10);
		email.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(email.getText().trim().toLowerCase().equals("new friend email")) {
					email.setText("");
					email.setForeground(Color.BLACK);
				}
			}
			@Override 
			public void focusLost(FocusEvent e) {
				if(email.getText().trim().equals("")||email.getText().trim().toLowerCase().contentEquals("new friend email")) {
					email.setText("                     New Friend Email");
					email.setForeground(Color.gray);
				}
			}
		});
		this.setVisible(true);
	}
}
