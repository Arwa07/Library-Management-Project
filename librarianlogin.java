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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import java.sql.* ;
public class librarianlogin extends JFrame {
	ResultSet rs=null;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					librarianlogin frame = new librarianlogin();
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
	public librarianlogin() {
		
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENTER USERNAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(153, 0, 51));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(224, 129, 187, 17);
		contentPane.add(lblNewLabel);
		
		JTextField txtuname = new JTextField();
		txtuname.setBackground(new Color(204, 204, 255));
		txtuname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtuname.setBounds(238, 157, 136, 20);
		contentPane.add(txtuname);
		txtuname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER PASSWORD");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(153, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(224, 182, 187, 22);
		contentPane.add(lblNewLabel_1);
		JPasswordField txtpwd = new JPasswordField();
		txtpwd.setBackground(new Color(204, 204, 255));
		txtpwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpwd.setEchoChar('*');
		txtpwd.setBounds(238, 206, 136, 20);
		contentPane.add(txtpwd);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setForeground(new Color(153, 0, 51));
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
				}else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","root");
						PreparedStatement stmt=con.prepareStatement("select * from librarian where user_id=? and password=?");
						stmt.setString(1, uname);
						stmt.setString(2, pswd);
						
						
						rs=stmt.executeQuery();
						if(rs.next())
						{
							String s1=rs.getString("user_id");
							String s2=rs.getString("password");
							JOptionPane.showMessageDialog(btnNewButton, "logined successfully");
							txtuname.setText("");
							txtpwd.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(btnNewButton, " user_id or password not matched");
							txtuname.setText("");
							txtpwd.setText("");

						}

						
						
						}catch(Exception e1){
						
					
					}
				}
				
				
				
				
				 
			
			}
		});
		btnNewButton.setBounds(195, 237, 115, 42);
		contentPane.add(btnNewButton);
		
		
		
		JCheckBox ckbox = new JCheckBox("show password\r\n");
		ckbox.setForeground(new Color(153, 0, 51));
		ckbox.setFont(new Font("Times New Roman", Font.BOLD, 11));
		ckbox.setBackground(new Color(255, 204, 255));
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
		ckbox.setBounds(402, 208, 119, 21);
		contentPane.add(ckbox);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setForeground(new Color(153, 0, 51));
		btnNewButton_1.setBackground(new Color(204, 204, 255));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Sarim\\Pictures\\Saved Pictures\\Close-2-icon.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(333, 237, 115, 42);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("LIBRARIAN LOGIN");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(204, 0, 51));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_4.setBounds(147, 67, 374, 51);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(102, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Sarim\\Pictures\\Saved Pictures\\20191219170611-GettyImages-1152794789.jpeg"));
		lblNewLabel_2.setBackground(new Color(204, 102, 204));
		lblNewLabel_2.setBounds(0, 0, 681, 434);
		contentPane.add(lblNewLabel_2);
		
		
	}
}
