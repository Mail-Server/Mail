package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.datastructure.mailServer.ComposeGui.PRIORITY;

public class EmailViewGUI  extends JFrame{


	enum PRIORITY{
		Urgent,High,Medium,Low;
	}
	private JPanel contentPane;
	private JTextField subject;
	private JTextArea receivers;
	private JTextArea body;
	private SLL filesSLL=new SLL();
	private JList attachList;
	private Mail newEmail;
	IndexFile ind=new IndexFile();
	private App c=new App();
	private JComboBox priority;
	private JScrollPane scrollPane;
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
	public EmailViewGUI(App C,Mail email) {
		this.setVisible(true);
		String [] attachments=new String [email.getAttachments().size()];
		for(int i=0;i<email.getAttachments().size();i++)
			attachments[i]=(String)email.getAttachments().get(i);
		String [] names=new String[attachments.length];
		for (int i=0;i<names.length;i++)
			names[i]=(attachments[i]).substring(attachments[i].lastIndexOf('\\')+1, attachments[i].length()-1);
		attachList=new JList(names);
		setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	Dimension fullscreen=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(100, 100, 750,568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		subject = new JTextField();
		subject.setText(email.getSubject());
		subject.setColumns(10);
		subject.setBounds(136, 265, 354, 31);
		contentPane.add(subject);
		
		JLabel lblNewLabel = new JLabel("Sender :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 205, 120, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblSubject.setBounds(27, 260, 99, 43);
		contentPane.add(lblSubject);
		switch (email.getPriority()) {
		case 1:
			priority = new JComboBox(new String []{"Urgent"});
			break;
		case 2:
			priority = new JComboBox(new String []{"High"});
			break;
		case 3:
			priority = new JComboBox(new String []{"Medium"});
			break;
		case 4:
			priority = new JComboBox(new String []{"Low"});
			break;
		}
		 
		priority.setFont(new Font("Tahoma", Font.BOLD, 12));
		priority.setToolTipText("");
		priority.setMaximumRowCount(4);
		
		priority.setBounds(302, 451, 74, 23);
		contentPane.add(priority);
		
		JLabel lblPriority = new JLabel("Priority :");
		lblPriority.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPriority.setBounds(212, 440, 80, 43);
		contentPane.add(lblPriority);
		
		JLabel lblBody = new JLabel("Body : ");
		lblBody.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblBody.setBounds(48, 315, 74, 43);
		contentPane.add(lblBody);
		
		receivers = new JTextArea();
		receivers.setBounds(136, 210, 354, 31);
		receivers.setText(email.getFrom());
		contentPane.add(receivers);
		
		 body = new JTextArea();
			body.setBounds(136, 320, 354, 120);
			body.setText(email.getBody());
			contentPane.add(body);
		
		
		
		
		JButton btnNewButton_1 = new JButton("View Attachments");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0, 100, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(attachList.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "No attachments to show");
				}
				else if(attachList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select an attachment");
				}
				else {
					ind.viewAttachment(attachments[attachList.getSelectedIndex()]);
				}
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(547, 490, 163, 31);
		contentPane.add(btnNewButton_1);
		
		attachList.setBounds(580, 205, 99, 232);
		contentPane.add(attachList);
		
		JButton btnNewButton_2 = new JButton("Delete Attachment");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(139, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(attachList.getModel().getSize()==0) {
					JOptionPane.showMessageDialog(null, "You have no attachments");
				}
				else if(attachList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Select an attachment");
				}
				else {
					ind.deleteAttachment(attachments[attachList.getSelectedIndex()]);
					SLL newAttachments=ind.getAttachments(email.getPath());
					String[]attachments=new String[newAttachments.size()];
					for(int i=0;i<attachments.length;i++) {
						attachments[i]=(String)newAttachments.get(i);
					}
					String [] names=new String[attachments.length];
					for(int i=0;i<attachments.length;i++)
						names[i]=(attachments[i]).substring(attachments[i].lastIndexOf('\\')+1, attachments[i].length()-1);
					attachList=new JList(names);
					contentPane.add(attachList);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_2.setBounds(547, 448, 163, 31);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Discard");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(Color.BLACK);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserGui test = new UserGui(C);
				test.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(10, 493, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel background = new JLabel("");
		Image imeg = new ImageIcon(this.getClass().getResource("/mailbirds1.png")).getImage();
		background.setIcon(new ImageIcon(imeg));
		background.setBounds(0, 0, 528, 179);
		contentPane.add(background);
		
		JLabel lblNewLabel_1 = new JLabel("__________________________________________________________________________________________________________");
		lblNewLabel_1.setBounds(-14, 178, 762, 14);
		contentPane.add(lblNewLabel_1);
		Image imege = new ImageIcon(this.getClass().getResource("/gos.jpg")).getImage();
		
		
	}
}


