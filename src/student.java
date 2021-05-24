import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class student {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("Student");
	private JTextField txtname;
	private JTextField txtcontact;
	private JTable table;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					student window = new student();
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
	public student() {
		initialize();
		connect();
		student_load();
			}
	Connection con;
	PreparedStatement pst;
	public void connect() {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/slibrary","root","root");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	}
	
	public void student_load() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","root");
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery("select * from student");
			 table.setModel(DbUtils.resultSetToTableModel(rs));
			 
			 						 con.close();
			
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
		frame.setBounds(100, 100, 976, 626);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Elephant", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(47, 23, 126, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 98, 94, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 180, 94, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contact No");
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 283, 115, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtname = new JTextField();
		txtname.setBounds(132, 101, 136, 22);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		txtcontact = new JTextField();
		txtcontact.setBounds(135, 285, 133, 22);
		frame.getContentPane().add(txtcontact);
		txtcontact.setColumns(10);
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_5.setBounds(20, 326, 115, 22);
		frame.getContentPane().add(lblNewLabel_5);
		
		txtemail = new JTextField();
		txtemail.setBounds(132, 329, 136, 20);
		frame.getContentPane().add(txtemail);
		txtemail.setColumns(10);
		
		JTextArea txtaddress = new JTextArea();
		txtaddress.setBounds(132, 179, 136, 80);
		frame.getContentPane().add(txtaddress);
		
		JButton button_add = new JButton("Add");
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to insert record---
				String name=txtname.getText();
				String address=txtaddress.getText();
				String phoneno=txtcontact.getText();
				String email=txtemail.getText();
								if(name.equals("")||address.equals("")||phoneno.equals("")||email.equals("")){
					JOptionPane.showMessageDialog(null, "SOME FIELDS ARE EMPTY","error",1);
					
					
				}
								else if(!Pattern.matches("^[0-9]+$", txtcontact.getText()))
								{
							       JOptionPane.showMessageDialog(null, "CONTACT SHOULD BE A NUMBER");
							    
								  txtcontact.setText("");
									
								}

				else {
				try {
					pst=con.prepareStatement("insert into student(Name,Address,phoneno,Email) values(?,?,?,?)");
					pst.setString(1, name);
					pst.setString(2, address);
					pst.setString(3, phoneno);
					pst.setString(4, email);

					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(null, "Record Added");
						txtname.setText("");
						txtaddress.setText("");
						txtcontact.setText("");
						txtemail.setText("");
						
						txtname.requestFocus();
						student_load();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Error..");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
				
			}
		});
		button_add.setFont(new Font("Constantia", Font.BOLD, 18));
		button_add.setBounds(23, 378, 102, 36);
		frame.getContentPane().add(button_add);
		
		JButton button_update = new JButton("Update");
		button_update.setFont(new Font("Constantia", Font.BOLD, 18));
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());
				String name=txtname.getText();
				String address=txtaddress.getText();
				String phoneno=txtcontact.getText();
				String email=txtemail.getText();
				
				if(name.equals("")||address.equals("")||phoneno.equals("")||email.equals("")){
					     JOptionPane.showMessageDialog(null, "SOME FIELDS ARE EMPTY","error",1);
					}
				else if(!Pattern.matches("^[0-9]+$", txtcontact.getText()))
				{
			       JOptionPane.showMessageDialog(null, "CONTACT SHOULD BE A NUMBER");
			    
				  txtcontact.setText("");
					
				}
				else {
				try {
					pst=con.prepareStatement("update student set Name =? , Address = ? , phoneno = ? , Email = ?  where Id = ?");
					pst.setString(1, name);
					pst.setString(2, address);
					pst.setString(3, phoneno);
					pst.setString(4, email);
					pst.setInt(5, id);
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(null, "Record Updated");
						txtname.setText("");
						txtaddress.setText("");
						txtcontact.setText("");
						txtemail.setText("");
						
						txtname.requestFocus();
						student_load();

						
						button_add.setEnabled(true);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Error..");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
				
				
				
				
			}
		});
		button_update.setBounds(176, 378, 102, 36);
		frame.getContentPane().add(button_update);
		
		JButton button_delete = new JButton("Delete");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to delete selected row record----
				if(table.getSelectedRowCount()==1) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","root");
					int row=table.getSelectedRow();
					String value=table.getModel().getValueAt(row,0).toString();
					String query="delete from student where Id="+value;
					 PreparedStatement stmt=con.prepareStatement(query);
					 stmt.executeUpdate();
					 DefaultTableModel tbmodel=(DefaultTableModel)table.getModel();
					 tbmodel.setRowCount(0);
					 student_load();
					 JOptionPane.showMessageDialog(null, "Record deleted");
					 txtname.setText("");
						txtaddress.setText("");
						txtcontact.setText("");
						txtemail.setText("");
						
						txtname.requestFocus();

					 button_add.setEnabled(true);

					 
						
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else {
					 JOptionPane.showMessageDialog(null, "Please select row to delete");

				}
				
				
				
			}
		});
		button_delete.setFont(new Font("Constantia", Font.BOLD, 18));
		button_delete.setBounds(23, 447, 102, 36);
		frame.getContentPane().add(button_delete);
		
		JButton button_cancel = new JButton("Cancel");
		button_cancel.setFont(new Font("Constantia", Font.BOLD, 18));
		button_cancel.setBounds(176, 447, 102, 36);
		frame.getContentPane().add(button_cancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(359, 26, 593, 534);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//to show selected row content------
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());
				txtname.setText(d1.getValueAt(selectedIndex,1).toString());
				txtaddress.setText(d1.getValueAt(selectedIndex,2).toString());
				txtcontact.setText(d1.getValueAt(selectedIndex,3).toString());
				txtemail.setText(d1.getValueAt(selectedIndex,4).toString());
								button_add.setEnabled(false);
				
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		JTableHeader tableHeader = table.getTableHeader();
		Font headerFont = new Font("Calibri", Font.BOLD, 15);
		tableHeader.setFont(headerFont);
		table.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Address", "Contact No"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(82);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(151);
		table.getColumnModel().getColumn(3).setPreferredWidth(134);
		table.setBounds(333, 23, 595, 537);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Sarim\\eclipse-workspace\\Library-Management-Project\\images\\bg9.png"));
		lblNewLabel_4.setBounds(0, 0, 962, 589);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		
	}
}
