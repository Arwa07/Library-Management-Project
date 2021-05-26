import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


import javax.swing.SwingConstants;
import java.awt.Color;


import javax.swing.JTable;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import books.author_item;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class issuebook {

	private JFrame frame;
	private JTextField txtname;
	private JTable table;
	private JTextField txtid;
	JComboBox txtbook = new JComboBox();
	ResultSet rs=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					issuebook window = new issuebook();
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
	public issuebook() {
		initialize();
		connect();
		book();
		issuebook_load();

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
		
		public class book_item{
			int id;
			String name;
			public book_item(int id,String name)
			{
				this.id=id;
				this.name=name;
			}
			public String toString()
			{
				return name;
			}
		}
		
		public void book()
		{
			try {
				pst =con.prepareStatement("select * from book");
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {
					txtbook.addItem(new book_item (rs.getInt(1),rs.getString(2)));
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error occur");
			}
			
			
			
		}
		public void issuebook_load() {
			try {
				 
				 Statement stmt=con.createStatement();
				 ResultSet rs=stmt.executeQuery("select i.Id ,m.StudentId,m.StudentName,b.BookName,i.Issuedate date,i.Returndate from issuebook i JOIN student m ON i.StudentId=m.StudentId JOIN book b ON i.book=b.Id   ");
				 table.setModel(DbUtils.resultSetToTableModel(rs));
				 
				 						 				
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
		frame.setBounds(100, 100, 979, 637);
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
		

		
		JLabel lblNewLabel = new JLabel("ISSUE BOOK");
		lblNewLabel.setBounds(55, 24, 234, 33);
		lblNewLabel.setForeground(new Color(128, 128, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Elephant", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel);
		JLabel label = new JLabel("Student Id");
		label.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		label.setBounds(30, 103, 137, 14);
		frame.getContentPane().add(label);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					String mid=txtid.getText();
					try {
						pst = con.prepareStatement("select * from student where StudentId= ?");
						pst.setString(1, mid);
						 rs=pst.executeQuery();
						 if(rs.next() == false) {
							 JOptionPane.showMessageDialog(null, "Student ID no found");
						 }
						 else {
							 String studentname = rs.getString("StudentName");
							 txtname.setText(studentname.trim());
						 }
					} 
					 catch(Exception e3){
						 System.out.print(e3);
				 }
}
			}
		});
		txtid.setBounds(177, 102, 168, 20);
		frame.getContentPane().add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Student Name");
		lblNewLabel_2.setBounds(26, 148, 137, 23);
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtname = new JTextField();
		txtname.setBounds(177, 148, 168, 23);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Book Title");
		lblNewLabel_3.setBounds(13, 196, 130, 23);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_3);
		
		txtbook.setBounds(176, 198, 169, 22);
		frame.getContentPane().add(txtbook);
		
		JDateChooser txtbdate = new JDateChooser();
		txtbdate.setBounds(177, 246, 168, 23);
		frame.getContentPane().add(txtbdate);
		
		JLabel lblNewLabel_4 = new JLabel("Borrow Date");
		lblNewLabel_4.setBounds(26, 246, 128, 23);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_4);
		
		JDateChooser txtrdate = new JDateChooser();
		txtrdate.setBounds(177, 292, 168, 23);
		frame.getContentPane().add(txtrdate);
		
		JLabel lblNewLabel_5 = new JLabel("Return Date");
		lblNewLabel_5.setBounds(26, 292, 130, 23);
		lblNewLabel_5.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnBookissue = new JButton("ISSUE");
		btnBookissue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mid = txtid.getText();
				book_item bitem =(book_item) txtbook.getSelectedItem();
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				String issuedate =date.format(txtbdate.getDate());
				SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
				String returndate =date1.format(txtrdate.getDate());
				try {
					pst=con.prepareStatement("insert into issuebook(StudentId,Book ,Issuedate,Returndate) values(?,?,?,?)");
					pst.setString(1, mid);
					pst.setInt(2, bitem.id);
					pst.setString(3, issuedate);
					pst.setString(4, returndate);

					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(null, "Book Issued!!!!!!!!!!");
						txtname.setText("");
						txtid.setText("");
						txtbook.setSelectedIndex(-1);
						
						issuebook_load();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Error..");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}

				
			
		});
		btnBookissue.setBounds(30, 383, 122, 33);
		btnBookissue.setFont(new Font("Constantia", Font.BOLD, 18));
		frame.getContentPane().add(btnBookissue);
		
		JButton button_update = new JButton("Update");
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1) //to check whether row is selected or not--------
				{
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());

				String mid = txtid.getText();
				book_item bitem =(book_item) txtbook.getSelectedItem();
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				String issuedate =date.format(txtbdate.getDate());
				SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
				String returndate =date1.format(txtrdate.getDate());
				try {
					pst=con.prepareStatement("update issuebook set StudentId = ?,Book = ? ,Issuedate = ?,Returndate =? where Id=?");
					pst.setString(1, mid);
					pst.setInt(2, bitem.id);
					pst.setString(3, issuedate);
					pst.setString(4, returndate);
					pst.setInt(5, id);

					
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(null, "Issued Book Updated!");
						txtname.setText("");
						txtid.setText("");
						txtbook.setSelectedIndex(-1);
						
						issuebook_load();
						btnBookissue.setEnabled(true);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Error..");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
				else {
					 JOptionPane.showMessageDialog(null, "Please select row to update");

				}

				

				
			}
		});
		button_update.setBounds(208, 383, 117, 33);
		button_update.setFont(new Font("Constantia", Font.BOLD, 18));
		frame.getContentPane().add(button_update);
		
		JButton button_cancel = new JButton("Cancel");
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(frame, "gonna close connection");
		        	con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				librarianfunc object= new librarianfunc();
				object.main(null);
				
				frame.setVisible(false);

			}
		});
		button_cancel.setBounds(119, 427, 117, 50);
		button_cancel.setFont(new Font("Constantia", Font.BOLD, 18));
		frame.getContentPane().add(button_cancel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 24, 600, 535);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//to show selected row content------
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());
				txtid.setText(d1.getValueAt(selectedIndex,1).toString());
				txtid.setEditable(false);

				txtname.setText(d1.getValueAt(selectedIndex,2).toString());
				//txtname.setEditable(false);

				txtbook.setSelectedItem(d1.getValueAt(selectedIndex,3).toString());
				
				btnBookissue.setEnabled(false);
				

			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		 JTableHeader tableHeader = table.getTableHeader();
		 Font headerFont = new Font("Calibri", Font.BOLD, 15);
		 tableHeader.setFont(headerFont);
		table.setFont(new Font("Monospaced", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", null, null, null},
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
				"Id", "Student ID", "Student Name", "Book", "Borrow Date", "Return Date"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(2).setPreferredWidth(128);
		table.getColumnModel().getColumn(3).setPreferredWidth(128);
		table.getColumnModel().getColumn(4).setPreferredWidth(107);
		table.getColumnModel().getColumn(5).setPreferredWidth(115);
		table.setBounds(383, 24, 538, 542);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\Sarim\\eclipse-workspace\\Library-Management-Project\\images\\bg2.PNG"));
		lblNewLabel_6.setBounds(0, 0, 965, 600);
		frame.getContentPane().add(lblNewLabel_6);
		
		
		
		
		
		
	}
}