package librarymanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sarim\\Pictures\\Saved Pictures\\62863-books-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 449);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENTER USERNAME");
		lblNewLabel.setForeground(new Color(51, 0, 102));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(185, 76, 187, 17);
		contentPane.add(lblNewLabel);
		
		JTextField txtuname = new JTextField();
		txtuname.setBackground(new Color(204, 204, 255));
		txtuname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtuname.setBounds(185, 104, 187, 20);
		contentPane.add(txtuname);
		txtuname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER PASSWORD");
		lblNewLabel_1.setForeground(new Color(51, 0, 102));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(185, 135, 187, 22);
		contentPane.add(lblNewLabel_1);
		JPasswordField txtpwd = new JPasswordField();
		txtpwd.setBackground(new Color(204, 204, 255));
		txtpwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpwd.setEchoChar('*');
		txtpwd.setBounds(185, 166, 187, 20);
		contentPane.add(txtpwd);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setForeground(new Color(51, 0, 102));
		btnNewButton.setBackground(new Color(204, 204, 255));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Sarim\\Pictures\\Saved Pictures\\preferences-system-login-icon.png"));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname=txtuname.getText();
				String pswd=txtpwd.getText();
				
				if(uname.equals("")||pswd.equals(""))
				{
					JOptionPane.showMessageDialog(btnNewButton, "some fields are empty","error",1);
				}
				
				
				
				
				else if(uname.equalsIgnoreCase("admin")&&pswd.equals("admin") )
				{
					JOptionPane.showMessageDialog(btnNewButton,"LOGIN SUCESSFULLY");
					txtuname.setText("");
					txtpwd.setText("");
				}
				
				else 
				{
					JOptionPane.showMessageDialog(btnNewButton,"please provide correct details");
					txtuname.setText("");
					txtpwd.setText("");
				}
				
				
				 
			
			}
		});
		btnNewButton.setBounds(131, 213, 139, 42);
		contentPane.add(btnNewButton);
		
		
		
		JCheckBox ckbox = new JCheckBox("show password\r\n");
		ckbox.setForeground(new Color(51, 0, 102));
		ckbox.setFont(new Font("Times New Roman", Font.BOLD, 11));
		ckbox.setBackground(new Color(204, 204, 204));
		ckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ckbox.isSelected())
				{
					txtpwd.setEchoChar((char)0);	
				
				}
				else
				{
					txtpwd.setEchoChar('*');
				}
			}
		});
		ckbox.setBounds(389, 168, 119, 21);
		contentPane.add(ckbox);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setForeground(new Color(51, 0, 102));
		btnNewButton_1.setBackground(new Color(204, 204, 255));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Sarim\\Pictures\\Saved Pictures\\Close-2-icon.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBounds(313, 213, 139, 42);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("ADMIN LOGIN");
		lblNewLabel_3.setForeground(new Color(51, 0, 102));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 0, 681, 55);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Sarim\\Pictures\\Saved Pictures\\Still-life-books-magnifier-scissors-alarm-clock-red-apple-blue-background_1920x1440.jpg"));
		lblNewLabel_2.setForeground(new Color(204, 0, 0));
		lblNewLabel_2.setBackground(new Color(255, 102, 153));
		lblNewLabel_2.setBounds(0, 0, 681, 410);
		contentPane.add(lblNewLabel_2);
		
		
	}
}
