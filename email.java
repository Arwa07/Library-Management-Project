import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import com.mysql.cj.Session;
//import com.sun.net.httpserver.Authenticator;

import java.awt.Font;
//import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;///////////////
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;///////////
import javax.mail.Session;///////////////
import javax.mail.Transport;///////////////
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;/////////////////
import javax.mail.internet.MimeMessage;	/////
import javax.mail.Address;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class email {

	private JFrame frame;
	private JTextField textField_sub;
	private JLabel text_to;
	private JTextField sid;
	JTextArea textArea_msg;
	Connection con;
	PreparedStatement pst;
	String to_mail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					email window = new email();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public email() {
		initialize();
		connect();
	}
	
	public void connect() {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/slibrary","root","rootdikshita");
			//JOptionPane.showMessageDialog(table, "connection created"); //for testing purpose only (can uncomment to know what is going under the hood)

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 678, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		    	super.windowClosing(e);
		        try {
					JOptionPane.showMessageDialog(frame, "gonna close connection");
		        	con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		JLabel txtmail = new JLabel("Send Email");
		txtmail.setForeground(new Color(153, 0, 102));
		txtmail.setFont(new Font("Elephant", Font.BOLD, 26));
		txtmail.setBounds(244, 27, 202, 31);
		frame.getContentPane().add(txtmail);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		lblNewLabel_1.setBounds(75, 93, 189, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		sid = new JTextField();
		sid.setFont(new Font("Tahoma", Font.BOLD, 18));
		sid.setBounds(355, 97, 209, 30);
		frame.getContentPane().add(sid);
		sid.setColumns(10);
		
		JLabel Label_to = new JLabel("To");
		Label_to.setFont(new Font("Bookman Old Style", Font.BOLD, 23));
		Label_to.setBounds(75, 220, 89, 36);
		frame.getContentPane().add(Label_to);
		
		JTextField txt_name = new JTextField();
		txt_name.setFont(new Font("Tahoma", Font.BOLD, 18));
		txt_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					String sname = txt_name.getText();
					String studentid = sid.getText();
					try
					{
						pst = con.prepareStatement("select Email, StudentId from student where StudentName = ? and StudentId = ?");
						pst.setString(1, sname);
						pst.setString(2, studentid);
						ResultSet rs = pst.executeQuery();
						if(rs.next()== false) {
							JOptionPane.showMessageDialog(frame, "Student not found");
						}
						else {
							to_mail = rs.getString("Email");
							text_to.setText(to_mail.trim());
						}
					}
					catch(Exception e3) {
						
					}
				}
			}
		});
		txt_name.setBounds(355, 163, 209, 30);
		frame.getContentPane().add(txt_name);
		txt_name.setColumns(10);
		
		JLabel txt_sub = new JLabel("Subject");
		txt_sub.setFont(new Font("Bookman Old Style", Font.BOLD, 23));
		txt_sub.setBounds(75, 278, 96, 36);
		frame.getContentPane().add(txt_sub);
		
		JLabel txt_msg = new JLabel("Message");
		txt_msg.setFont(new Font("Bookman Old Style", Font.BOLD, 23));
		txt_msg.setBounds(75, 358, 118, 36);
		frame.getContentPane().add(txt_msg);
		
		textField_sub = new JTextField();
		textField_sub.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_sub.setBounds(355, 278, 209, 30);
		frame.getContentPane().add(textField_sub);
		textField_sub.setColumns(10);
		
		JTextArea textArea_msg = new JTextArea();
		textArea_msg.setFont(new Font("Tahoma", Font.BOLD, 18));
		textArea_msg.setBounds(355, 358, 209, 110);
		frame.getContentPane().add(textArea_msg);
		
		JButton sendBtn = new JButton("Send");
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				String from_mail = "lmslibrarian110@gmail.com";
				String from_pwd = "librarianlms625";
				String sub = textField_sub.getText();
				
				Properties p = new Properties();
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.port", "587");

				Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from_mail,from_pwd);
					}
				});
				
				try {
					MimeMessage m = new MimeMessage(s);
					m.setFrom(new InternetAddress(from_mail));
					m.addRecipient(Message.RecipientType.TO, new InternetAddress(to_mail));
					m.setSubject(sub);
					m.setText(textArea_msg.getText());
					Transport.send(m);
				}
				catch(MessagingException ex) {
					ex.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(frame, "E-mail sent successfully!");
				
			   }
			
		});
		sendBtn.setFont(new Font("Elephant", Font.BOLD, 18));
		sendBtn.setBounds(146, 517, 118, 45);
		frame.getContentPane().add(sendBtn);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(frame, "gonna close connection");
		        	con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				librarianfunc.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Elephant", Font.BOLD, 18));
		btnNewButton.setBounds(366, 517, 118, 45);
		frame.getContentPane().add(btnNewButton);
		
		text_to = new JLabel("email");
		text_to.setForeground(Color.DARK_GRAY);
		text_to.setFont(new Font("Tahoma", Font.BOLD, 18));
		text_to.setBounds(356, 227, 179, 24);
		frame.getContentPane().add(text_to);
		
		JLabel std_name = new JLabel("Student Name");
		std_name.setFont(new Font("Bookman Old Style", Font.BOLD, 23));
		std_name.setBounds(75, 158, 249, 52);
		frame.getContentPane().add(std_name);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\fabdi\\eclipse-workspace\\Library-Management-Project\\images\\bg2.PNG"));
		lblNewLabel.setBounds(0, 0, 664, 593);
		frame.getContentPane().add(lblNewLabel);
	}
}
