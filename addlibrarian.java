import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class addlibrarian {

	private JFrame frame;
private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private static JTable table;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_3;

	/**
	 * Launch the application.
	 */

	public static void main(String args[]) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				addlibrarian window = new addlibrarian();
				window.frame.setVisible(true);
				try {
					
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addlibrarian() {
		initialize();
		connect();
		librarian_load();
		
	}
	Connection con;
	PreparedStatement pst;
	public void connect() {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/slibrary","root","root");
			//JOptionPane.showMessageDialog(table, "connection created"); //for testing purpose only (can uncomment to know what is going under the hood)

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	}
	
	public void librarian_load() {
		try {
			
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery("select * from librarian");
			 table.setModel(DbUtils.resultSetToTableModel(rs));
			 
			//con.close();
			
		 }
		 catch(Exception e3){
			 System.out.print(e3);
	 }

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	frame.setBounds(100, 100, 1085, 707);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Librarian");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Elephant", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(43, 20, 165, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("firstName");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 84, 103, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("LastName");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 138, 103, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 193, 103, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PhoneNO");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_4.setBounds(10, 256, 103, 23);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("create Password");
		lblNewLabel_6.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(0, 306, 177, 23);
		frame.getContentPane().add(lblNewLabel_6);
		
		JTextField textField = new JTextField();
		textField.setBounds(123, 87, 166, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(128, 141, 161, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(128, 196, 161, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(128, 259, 161, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		 
		 JLabel lblNewLabel_5 = new JLabel("confirmPassword");
		 lblNewLabel_5.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		 lblNewLabel_5.setBounds(10, 350, 158, 26);
		 frame.getContentPane().add(lblNewLabel_5);

		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname=textField.getText();
				String lastname=textField_1.getText();
				String email=textField_2.getText();
				String phoneno=textField_3.getText();
				String pswd=passwordField_2.getText();
				
				String password=passwordField_3.getText();
				if(firstname.equals("")||lastname.equals("")||email.equals("")||phoneno.equals("")||password.equals(""))
				{
					JOptionPane.showMessageDialog(btnNewButton, "SOME FIELDS ARE EMPTY","error",1);
					
				}
				else if(!pswd.equals(password)) {
					JOptionPane.showMessageDialog(btnNewButton, "password does not macthed","error",1);
					passwordField_3.setText("");
					passwordField_2.setText("");
				}
				else if(!Pattern.matches("^[0-9]+$", textField_3.getText()))
				{
			       JOptionPane.showMessageDialog(btnNewButton, "PHONENO SHOULD BE A NUMBER");
					
					textField_3.setText("");
					
				}
				

				else {
				try {
					//Class.forName("com.mysql.cj.jdbc.Driver");
					//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","root");
					PreparedStatement stmt=con.prepareStatement("insert into librarian(firstname,lastname,email,phoneno,password) values(?,?,?,?,?)");
					stmt.setString(1, firstname);
					stmt.setString(2, lastname);
					stmt.setString(3, email);
					stmt.setString(4, phoneno);
					stmt.setString(5, password);
					
					int i=stmt.executeUpdate();
					if(i !=0)
					{
						JOptionPane.showMessageDialog(btnNewButton, "inserted");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						passwordField_3.setText("");
						passwordField_2.setText("");
						librarian_load();

					}


					
					
					}catch(Exception e1){
					
						JOptionPane.showMessageDialog(btnNewButton,"Error");
				}
				
				}

			}
		});
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setEchoChar('*');
		passwordField_2.setBounds(170, 309, 128, 20);
		frame.getContentPane().add(passwordField_2);
		
		passwordField_3 = new JPasswordField();
		passwordField_3.setBackground(Color.WHITE);
		passwordField_3.setEchoChar('*');
		passwordField_3.setBounds(178, 356, 120, 20);
		frame.getContentPane().add(passwordField_3);
		btnNewButton.setFont(new Font("Constantia", Font.BOLD, 18));
		btnNewButton.setBounds(10, 426, 103, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1) {
				try {
										int row=table.getSelectedRow();
					String value=table.getModel().getValueAt(row,0).toString();
					String query="delete from librarian where user_id ="+value;
					 PreparedStatement stmt=con.prepareStatement(query);
					 stmt.executeUpdate();
					 DefaultTableModel tbmodel=(DefaultTableModel)table.getModel();
					 tbmodel.setRowCount(0);
					 
					 JOptionPane.showMessageDialog(btnNewButton_1, "Record deleted");
					 librarian_load();
					 
						
					 
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				else {
					 JOptionPane.showMessageDialog(btnNewButton_1, "Please select row to delete");

				}


			}
		});
		btnNewButton_1.setFont(new Font("Constantia", Font.BOLD, 18));
		btnNewButton_1.setBounds(10, 486, 103, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRowCount()==1) //to check whether row is selected or not--------
					{
					DefaultTableModel d1=(DefaultTableModel)table.getModel();
					int selectedIndex=table.getSelectedRow();
					int user_id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());

				String firstname=textField.getText();
				String lastname=textField_1.getText();
				String email=textField_2.getText();
				String phoneno=textField_3.getText();
				String pswd=passwordField_2.getText();
				
				String password=passwordField_3.getText();
				if(firstname.equals("")||lastname.equals("")||email.equals("")||phoneno.equals("")||password.equals(""))
				{
					JOptionPane.showMessageDialog(btnNewButton, "SOME FIELDS ARE EMPTY","error",1);
					
				}
				else if(!pswd.equals(password)) {
					JOptionPane.showMessageDialog(btnNewButton, "password does not macthed","error",1);
					;
					passwordField_3.setText("");
					passwordField_2.setText("");
				}
				else if(!Pattern.matches("^[0-9]+$", textField_3.getText()))
				{
			       JOptionPane.showMessageDialog(btnNewButton, "PHONENO SHOULD BE A NUMBER");
			    
				  textField_3.setText("");
					
				}
				

				else {
				try {
					
					PreparedStatement stmt=con.prepareStatement(" update librarian set firstname = ? ,lastname = ? ,email = ?,phoneno =? ,password =? where user_id= ?");
					stmt.setString(1, firstname);
					stmt.setString(2, lastname);
					stmt.setString(3, email);
					stmt.setString(4, phoneno);
					stmt.setString(5, password);
					stmt.setInt(6, user_id);

					int i=stmt.executeUpdate();
					if(i !=0)
					{
						JOptionPane.showMessageDialog(btnNewButton, "Record Updated");
						librarian_load();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						passwordField_3.setText("");
						passwordField_2.setText("");
						btnNewButton.setEnabled(true);


					}


					
					
					}catch(Exception e1){
					
						JOptionPane.showMessageDialog(btnNewButton,"Error");
				}
								}
					}
					else {
						 JOptionPane.showMessageDialog(btnNewButton_1, "Please select row to update");

					}


			}
		});
		btnNewButton_2.setFont(new Font("Constantia", Font.BOLD, 18));
		btnNewButton_2.setBounds(151, 426, 103, 33);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(frame, "gonna close connection");
		        	con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				homepage object= new homepage();
				object.main(null);
				
				frame.setVisible(false);

				
			}
		});
		btnNewButton_3.setFont(new Font("Constantia", Font.BOLD, 18));
		btnNewButton_3.setBounds(151, 488, 103, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 37, 724, 592);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int user_id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());
				textField.setText(d1.getValueAt(selectedIndex,1).toString());
				textField_1.setText(d1.getValueAt(selectedIndex,2).toString());
				textField_2.setText(d1.getValueAt(selectedIndex,3).toString());
				textField_3.setText(d1.getValueAt(selectedIndex,4).toString());

				passwordField_2.setText(d1.getValueAt(selectedIndex,5).toString());
				
								btnNewButton.setEnabled(false);
				

			}
		});
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		JTableHeader tableHeader = table.getTableHeader();
		 Font headerFont = new Font("Calibri", Font.BOLD, 15);
		 tableHeader.setFont(headerFont);
		table.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"UserId", "FirstName", "LastName", "Email", "PhoneNO", "Password"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(119);
		table.getColumnModel().getColumn(4).setPreferredWidth(116);
		table.getColumnModel().getColumn(5).setPreferredWidth(83);
		table.setBounds(308, 37, 724, 592);
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\Sarim\\eclipse-workspace\\Library-Management-Project\\images\\librarian2.PNG"));
		lblNewLabel_7.setBounds(0, 0, 1069, 668);
		frame.getContentPane().add(lblNewLabel_7);
		
	}
}
