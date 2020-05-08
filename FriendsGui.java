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
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FriendsGui extends JFrame {

	private JPanel contentPane;
	private JTextArea newName;
	private JTextField name;
	private JTextField email;
	private JList Names;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendsGui frame = new FriendsGui();
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
	public FriendsGui(App ap) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 36, 186, 341);
		contentPane.add(scrollPane);
		Friends f=new Friends();
		String friendsNames[]=f.getFriends(ap);
		if(friendsNames==null) {
		JOptionPane.showMessageDialog(null, "You don't have any contacts yet");
		Names=new JList();
		}
		else {
		 Names = new JList(friendsNames);
		}
		Names.setSelectedIndex(0);
		Names.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(Names);
		JButton Rename = new JButton("Rename");
		Rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Names.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "You have no contacts");
				}
				else if(Names.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select a contact");
				}
				else {
				if(newName.getText().trim().equals("Rename With")) {
					JOptionPane.showMessageDialog(null, "Enter the new name");
				}
				else {
				f.rename(newName.getText(),friendsNames[Names.getSelectedIndex()], ap);
				Names=new JList(f.getFriends(ap));
				scrollPane.setViewportView(Names);
				newName.setText("                     Rename With");
				newName.setForeground(Color.gray);
				}
				}
			}
		});
		Rename.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Rename.setBounds(308, 132, 199, 37);
		contentPane.add(Rename);
		
		newName = new JTextArea();
		newName.setBounds(710, 132, 209, 37);
		contentPane.add(newName);
		
		JButton btnAddNewContact = new JButton("Add New Contact");
		btnAddNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().trim().equals("New Friend Name")){
					JOptionPane.showMessageDialog(null, "Enter The new Contact name");
				}
				else if(email.getText().trim().equals("New Friend Email")) {
					JOptionPane.showMessageDialog(null, "Enter The new Contact email");
				}
				else {
				f.setName(name.getText());
				String [] emails=email.getText().split(" ");
				f.setEmails(emails);
				if(!f.addContact(f, ap)) {
					JOptionPane.showMessageDialog(null, "Email Doesn't Exist");
				}
				else {
				Names=new JList(f.getFriends(ap));
				scrollPane.setViewportView(Names);
				email.setText("                  New Friend Email");
				email.setForeground(Color.gray);
				name.setText("                  New Friend Name");
				name.setForeground(Color.gray);
				}
				
				}
			}
		});
		btnAddNewContact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddNewContact.setBounds(308, 35, 199, 37);
		contentPane.add(btnAddNewContact);
		
		JButton btnRemoveContact = new JButton("Remove Contact");
		btnRemoveContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Names.getModel().getSize()==0) {
						JOptionPane.showMessageDialog(null, "You have no Contacts");
				}
			else if(Names.isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "Select a contact");
			}
			else {
			f.deleteContact(Names.getSelectedIndex(), ap);
			if(f.getFriends(ap)==null) {
				Names=new JList();
			}
			else 
			Names=new JList(f.getFriends(ap));
			scrollPane.setViewportView(Names);
			}
			}
		});
		
		btnRemoveContact.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoveContact.setBounds(308, 340, 199, 37);
		contentPane.add(btnRemoveContact);		
		JButton btnNewButton = new JButton("View Emails");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Names.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null,"You have no contacts");
				}
				else if(Names.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select a contact");
				}
				else {
				String [] friendEmails=f.getFriendEmails(Names.getSelectedIndex(), ap);
				dispose();
				FriendEmailsGUI feg=new FriendEmailsGUI(friendEmails,ap,Names.getSelectedIndex());
				feg.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(308, 235, 199, 39);
		contentPane.add(btnNewButton);
		
		name = new JTextField();
		name.setText("                  New Friend Name");
		name.setForeground(Color.gray);
		name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(name.getText().trim().toLowerCase().equals("new friend name")){
			name.setText("");
			name.setForeground(Color.black);
		}
			}
			public void focusLost(FocusEvent e) {
				if(name.getText().trim().equals("")||name.getText().trim().toLowerCase().equals("new friend name")) {
					name.setText("                  New Friend Name");
					name.setForeground(Color.gray);
				}
			}
		});
		
		newName.setText("                     Rename With");
		newName.setForeground(Color.gray);
		newName.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(newName.getText().trim().toLowerCase().equals("rename with")) {
					newName.setText("");
					newName.setForeground(Color.black);
				}
			}
			@Override 
			public void focusLost(FocusEvent e) {
				if(newName.getText().trim().equals("")||newName.getText().trim().toLowerCase().contentEquals("rename with")) {
					newName.setText("                     Rename With");
					newName.setForeground(Color.gray);
				}
			}
		});
		name.setBounds(542, 36, 209, 36);
		contentPane.add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setForeground(Color.gray);
		email.setColumns(10);
		email.setBounds(882, 35, 209, 36);
		contentPane.add(email);
		email.setText("                  New Friend Email");
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
					email.setText("                  New Friend Email");
					email.setForeground(Color.gray);
				}
			}
		});
		this.setVisible(true);
	}
}
