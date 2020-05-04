package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ListCellRenderer;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;

public class InboxGui extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
	public InboxGui(App C) {
		this.setVisible(true);
		setTitle("My Mail");
		Image image = new ImageIcon(this.getClass().getResource("/blue-mail.png")).getImage();
		this.setIconImage(image);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 632, 255);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Priority", "From", "Subject", "Body", "Attachment"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(125);
		scrollPane.setViewportView(table);
		class MyComboBoxRenderer extends JLabel implements ListCellRenderer
	    {
			private String _title;

	        public MyComboBoxRenderer(String title)
	        {
	            _title = title;
	        }

	        @Override
	        public Component getListCellRendererComponent(JList list, Object value,
	                int index, boolean isSelected, boolean hasFocus)
	        {
	            if (index == -1 && value == null) setText(_title);
	            else setText(value.toString());
	            return this;
	        }
	    }
		JComboBox filter = new JComboBox();
		 filter.setRenderer(new MyComboBoxRenderer("Filter By"));
	        filter.setSelectedIndex(-1);
	        contentPane.add(filter);
		filter.setBounds(454, 11, 89, 22);
		contentPane.add(filter);
		
		JComboBox sort = new JComboBox();
		sort.setRenderer(new MyComboBoxRenderer("Sort By"));
        sort.setSelectedIndex(-1); 
        contentPane.add(sort);
		sort.setBounds(553, 11, 89, 22);
		contentPane.add(sort);
		
		JButton Previous = new JButton("Previous");
		Previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Previous.setBounds(454, 310, 89, 23);
		contentPane.add(Previous);
		
		JButton Next = new JButton("Next");
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Next.setBounds(553, 310, 89, 23);
		contentPane.add(Next);
		
		JButton Return = new JButton("Return");
		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserGui test=new UserGui(C);
				test.setVisible(true);
			}
		});
		Return.setBounds(10, 310, 89, 23);
		contentPane.add(Return);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 652, 344);
		contentPane.add(lblNewLabel);
		Image img =new ImageIcon(this.getClass().getResource("/maildesign1.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		/*App ap = new App();
		Folder des = new Folder();
		ap.setViewingOptions(des, null, null);
		des.setPath("Accounts\\"+C.currentUser.getEmail()+"\\Inbox");
		Mail[] emi = new Mail[ap.list.size()];
		emi=(Mail[]) ap.listEmails(1);
		model.addRow(new Object[] {emi[0].getDate()});
		
		
		/*DefaultTableModel model = (DefaultTableModel) table.getModel();
		FileReader Fread = new FileReader("Accounts\\"+C.currentUser.getEmail()+"\\Inbox\\Index.txt");
		BufferedReader read = new BufferedReader(Fread);
		FileReader Fread2 = new FileReader("Accounts\\"+C.currentUser.getEmail()+"\\Inbox\\");  //for body and attachment
		BufferedReader read2 = new BufferedReader(Fread2);
		String line = read.readLine();
		String[] lineArr = line.split("—");
		String date,from,subj,body,attachment,priority;
		date=lineArr[1];
		from=lineArr[2];
		subj=lineArr[4];
		priority=lineArr[5];
		//body=
		//attachment = 
		//model.addRow(rowData);*/
		
		
	}
}
