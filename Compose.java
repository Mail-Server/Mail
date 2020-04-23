import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Compose extends JFrame {
	enum PRIORITY{
		Urgent,High,Medium,Low;
	}
	private JPanel contentPane;
	private JTextField Receivers;
	private JTextField Subject;
	private JTextField textField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1275, 825);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Receivers = new JTextField();
		Receivers.setBounds(378, 31, 356, 43);
		contentPane.add(Receivers);
		Receivers.setColumns(10);
		
		Subject = new JTextField();
		Subject.setColumns(10);
		Subject.setBounds(378, 110, 356, 43);
		contentPane.add(Subject);
		
		JLabel lblNewLabel = new JLabel("Receivers");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(139, 31, 137, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubject.setBounds(139, 110, 137, 43);
		contentPane.add(lblSubject);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(4);
		comboBox.setBounds(378, 191, 355, 53);
		contentPane.add(comboBox);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPriority.setBounds(139, 201, 137, 43);
		contentPane.add(lblPriority);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(378, 296, 838, 319);
		contentPane.add(textField);
		
		JLabel lblBody = new JLabel("Body");
		lblBody.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBody.setBounds(139, 399, 137, 43);
		contentPane.add(lblBody);
		
		JButton btnNewButton = new JButton("Compose");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(228, 700, 275, 78);
		contentPane.add(btnNewButton);
		
		JButton draft = new JButton("Save as draft");
		draft.setFont(new Font("Tahoma", Font.PLAIN, 20));
		draft.setBounds(726, 700, 275, 78);
		contentPane.add(draft);
		contentPane.add(textField);
	}
}
