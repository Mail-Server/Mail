package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewEmails extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public viewEmails(App C) {
		this.setVisible(true);
		setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(95, 47, 294, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(85, 11, 304, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(" From : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(10, 11, 53, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Subject : ");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSubject.setBounds(10, 47, 66, 25);
		contentPane.add(lblSubject);
		
		JLabel lblPriority = new JLabel("Priority :");
		lblPriority.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPriority.setBounds(10, 83, 66, 25);
		contentPane.add(lblPriority);
		
		JLabel label = new JLabel("");
		label.setBounds(85, 83, 304, 25);
		contentPane.add(label);
		
		JLabel lblBody = new JLabel("Body :");
		lblBody.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblBody.setBounds(10, 119, 66, 25);
		contentPane.add(lblBody);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 155, 408, 199);
		contentPane.add(lblNewLabel_3);
		contentPane.setBackground(Color.white);
		
		JLabel lblAttachments = new JLabel("Attachments");
		lblAttachments.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAttachments.setBounds(500, 11, 99, 25);
		contentPane.add(lblAttachments);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 47, 99, 199);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("View");
		btnNewButton.setBounds(510, 278, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InboxGui test = new InboxGui(C);
				test.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(526, 331, 89, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
