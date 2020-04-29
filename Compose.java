import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Compose extends JFrame {
	enum PRIORITY{
		Urgent,High,Medium,Low;
	}
	private JPanel contentPane;
	private JTextField subject;
	private JTextArea receivers;
	private JTextArea body;
	private SLL filesSLL=new SLL();
	private JList attachList;
	private Email newEmail;
	private CApp c=new CApp();
	DefaultListModel dlm=new DefaultListModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compose frame = new Compose();
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
	public Compose() {
		setTitle("Compose");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension fullscreen=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(100, 100, 1373,864);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		subject = new JTextField();
		subject.setColumns(10);
		subject.setBounds(378, 110, 356, 43);
		contentPane.add(subject);
		
		JLabel lblNewLabel = new JLabel("Receivers");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(139, 31, 137, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubject.setBounds(139, 110, 137, 43);
		contentPane.add(lblSubject);
		
		JComboBox priority = new JComboBox(PRIORITY.values());
		priority.setSelectedIndex(3);
		priority.setFont(new Font("Tahoma", Font.PLAIN, 20));
		priority.setToolTipText("");
		priority.setMaximumRowCount(4);
		
		priority.setBounds(378, 191, 355, 53);
		contentPane.add(priority);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPriority.setBounds(139, 201, 137, 43);
		contentPane.add(lblPriority);
		
		JLabel lblBody = new JLabel("Body");
		lblBody.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBody.setBounds(139, 384, 137, 43);
		contentPane.add(lblBody);
		
		receivers = new JTextArea();
		receivers.setBounds(380, 31, 354, 31);
		contentPane.add(receivers);
		
		 body = new JTextArea();
			body.setBounds(228, 254, 718, 309);
			contentPane.add(body);
			
	
		
		JButton btnNewButton = new JButton("Compose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEmail=new Email();
				String toString =receivers.getText();
				if(toString.trim().length()!=0) {
					String [] toArray =toString.split(" ");
					SLL toSLL= new SLL();
					for(int i=0;i<toArray.length;i++) {
						toSLL.add(toArray[i]);
					}
					newEmail.setTo(toSLL);
				}
				newEmail.setSubject(subject.getText());
				switch( (PRIORITY)priority.getSelectedItem()){
				case Urgent :
					newEmail.setPriority(1);
					break;
				case High:
					newEmail.setPriority(2);
					break;
				case Medium:
					newEmail.setPriority(3);
					break;
				case Low :
					newEmail.setPriority(4);
					break;
				}
				newEmail.setBody(body.getText());
				newEmail.setAttachments(filesSLL);
				DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH,mm,ss");
				LocalDateTime now=LocalDateTime.now();
				newEmail.setDate(dtf.format(now));
				newEmail.setFrom("Abood@mail.com");
				
				
				newEmail.setFolder("Sent");
				if(c.compose(newEmail))
					JOptionPane.showMessageDialog(null,"The email has been sent successfully");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(139, 778, 275, 35);
		contentPane.add(btnNewButton);
		
		JButton draft = new JButton("Save as draft");
		draft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 newEmail=new Email();
				String toString =receivers.getText();
				if(toString.trim().length()!=0) {
					String [] toArray =toString.split(" ");
					SLL toSLL= new SLL();
					for(int i=0;i<toArray.length;i++) {
						toSLL.add(toArray[i]);
					}
					newEmail.setTo(toSLL);
				}
				newEmail.setSubject(subject.getText());
				switch( (PRIORITY)priority.getSelectedItem()){
				case Urgent :
					newEmail.setPriority(1);
					break;
				case High:
					newEmail.setPriority(2);
					break;
				case Medium:
					newEmail.setPriority(3);
					break;
				case Low :
					newEmail.setPriority(4);
					break;
				}
				newEmail.setBody(body.getText());
				newEmail.setAttachments(filesSLL);
				DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH,mm,ss");
				LocalDateTime now=LocalDateTime.now();
				newEmail.setDate(dtf.format(now));
				newEmail.setFrom("Abood@mail.com");
				newEmail.setPath("Accounts\\"+newEmail.getFrom()+"\\"+newEmail.getFrom()+newEmail.getDate());
				newEmail.setFolder("Drafts");
				newEmail.setEmailFolder("Drafts");
				 if (c.compose(newEmail))
					JOptionPane.showMessageDialog(null,"Saved as draft");
				
			}
		});
		
		
		draft.setFont(new Font("Tahoma", Font.PLAIN, 20));
		draft.setBounds(821, 774, 275, 43);
		contentPane.add(draft);
		
		
		
		
		JButton btnNewButton_1 = new JButton("Add Attachments");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser= new JFileChooser();
				fileChooser.setMultiSelectionEnabled(true);
				int r=fileChooser.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION) {
					File[] filesArray=fileChooser.getSelectedFiles();
					for(int i=0;i<filesArray.length;i++) {
						filesSLL.add(filesArray[i].getAbsolutePath().replace("\\", "\\\\"));
					}
					for(int i=0;i<filesArray.length;i++) {
						dlm.addElement(filesArray[i].getName());
					}
				}
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(139, 672, 264, 35);
		contentPane.add(btnNewButton_1);
		
		 attachList = new JList();
		 attachList.setModel(dlm);
		attachList.setBounds(1031, 110, 184, 561);
		contentPane.add(attachList);
		
		JButton btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] indices =attachList.getSelectedIndices();
				for(int i:indices) {
					filesSLL.remove(i);
					dlm.remove(i);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(999, 720, 251, 31);
		contentPane.add(btnNewButton_2);
		
		
	}
}
