import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class books {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtpages;
	private JTextField txtedition;
	private JTextField txtcopies;
	private JTable table;
	JComboBox txtauthor = new JComboBox();
	JComboBox txtpublisher = new JComboBox();
	JComboBox txtcategory = new JComboBox();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					books window = new books();
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
	public books() {
		initialize();
		connect();
		category();
		author();
		publisher();
		book_load();
	}
	
	public class category_item{
		int id;
		String name;
		public category_item(int id, String name) {
			this.id= id;
			this.name=name;
		}
		
		public String toString() {
			return name;
		}
	}
	public class author_item{
		int id;
		String name;
		public author_item(int id, String name) {
			this.id= id;
			this.name=name;
		}
		
		public String toString() {
			return name;
		}
	}
	public class publisher_item{
		int id;
		String name;
		public publisher_item(int id, String name) {
			this.id= id;
			this.name=name;
		}
		
		public String toString() {
			return name;
		}
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
	public void category() {
		try {
			pst =con.prepareStatement("select * from category");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				txtcategory.addItem(new category_item (rs.getInt(1),rs.getString(2)));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error occur");
		}
		 
	}
	
		public void author() {
		try {
			pst =con.prepareStatement("select * from author");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				txtauthor.addItem(new author_item (rs.getInt(1),rs.getString(2)));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error occur");
		}
		 
	}
	public void publisher() {
		try {
			pst =con.prepareStatement("select * from publisher");
			ResultSet rs=pst.executeQuery();
			txtpublisher.removeAllItems();
			while(rs.next()) {
				txtpublisher.addItem(new publisher_item (rs.getInt(1),rs.getString(2)));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error occur");
		}
		 
	}

	
	public void book_load() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","root");
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery("select b.Id,b.BookName,c.catname,a.Name,p.Name,b.pages,b.Edition,b.Copies from book b JOIN category c on b.Category =c.id JOIN author a on b.Author = a.Id JOIN publisher p on b.Publisher = p.Id  ");
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
		frame.setBounds(100, 100, 1085, 707);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Elephant", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(43, 20, 165, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 84, 103, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Category");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 138, 103, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Author");
		lblNewLabel_3.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 193, 103, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Publisher");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_4.setBounds(10, 256, 103, 23);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("No. of Pages");
		lblNewLabel_5.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 318, 116, 23);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Edition");
		lblNewLabel_6.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 376, 103, 23);
		frame.getContentPane().add(lblNewLabel_6);
		
		txtname = new JTextField();
		txtname.setBounds(136, 87, 140, 19);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		//JComboBox txtcategory = new JComboBox();
		txtcategory.setBounds(137, 140, 139, 22);
		frame.getContentPane().add(txtcategory);
		
		//JComboBox txtauthor = new JComboBox();
		txtauthor.setBounds(136, 195, 140, 22);
		frame.getContentPane().add(txtauthor);
		//JComboBox txtpublisher = new JComboBox();
		txtpublisher.setBounds(136, 245, 140, 22);
		frame.getContentPane().add(txtpublisher);
		
		
		txtpages = new JTextField();
		txtpages.setBounds(136, 321, 140, 19);
		frame.getContentPane().add(txtpages);
		txtpages.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setBounds(136, 380, 140, 19);
		frame.getContentPane().add(txtedition);
		txtedition.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("No. of Copies");
		lblNewLabel_7.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(10, 434, 143, 19);
		frame.getContentPane().add(lblNewLabel_7);
		
		txtcopies = new JTextField();
		txtcopies.setBounds(158, 436, 96, 19);
		frame.getContentPane().add(txtcopies);
		txtcopies.setColumns(10);
		
		JButton button_add = new JButton("Add");
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to insert record---
				String name=txtname.getText();
				category_item citem= (category_item) txtcategory.getSelectedItem();
				author_item aitem= (author_item) txtauthor.getSelectedItem();
				publisher_item pitem= (publisher_item) txtpublisher.getSelectedItem();
				String pages=txtpages.getText();
				String edition=txtedition.getText();
				String copies=txtcopies.getText();
				
				
				
				if(name.equals("")||citem.equals("")||aitem.equals("")||pitem.equals("")||pages.equals("")||edition.equals("")||copies.equals("")){
					JOptionPane.showMessageDialog(null, "SOME FIELDS ARE EMPTY","error",1);
					
					
				}
								
				else {
				try {
					pst=con.prepareStatement("insert into book(BookName,Category,Author,Publisher,pages,Edition,Copies) values(?,?,?,?,?,?,?)");
					pst.setString(1, name);
					pst.setInt(2,citem.id );
					pst.setInt(3,aitem.id );
					pst.setInt(4,pitem.id );
					pst.setString(5, pages);
					pst.setString(6, edition);
					pst.setString(7, copies);

					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(null, "Record Added");
						txtname.setText("");
						txtcategory.setSelectedIndex(-1);
						txtauthor.setSelectedIndex(-1);

						txtpublisher.setSelectedIndex(-1);
						txtpages.setText("");
						txtedition.setText("");
						txtcopies.setText("");

						txtname.requestFocus();
						book_load();
						
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
		button_add.setBounds(10, 489, 103, 33);
		frame.getContentPane().add(button_add);
		
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
				String query="delete from book where Id="+value;
				 PreparedStatement stmt=con.prepareStatement(query);
				 stmt.executeUpdate();
				 DefaultTableModel tbmodel=(DefaultTableModel)table.getModel();
				 tbmodel.setRowCount(0);
				 book_load();
				 JOptionPane.showMessageDialog(null, "Record deleted");
				 txtname.setText("");
				  txtcategory.setSelectedIndex(-1);
				   txtauthor.setSelectedIndex(-1);
				    txtpublisher.setSelectedIndex(-1);
					txtpages.setText("");
					txtedition.setText("");
					txtcopies.setText("");	
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

			}});
		button_delete.setFont(new Font("Constantia", Font.BOLD, 18));
		button_delete.setBounds(10, 551, 103, 33);
		frame.getContentPane().add(button_delete);
		
		JButton button_update = new JButton("Update");
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());
				String name=txtname.getText();
				category_item citem= (category_item) txtcategory.getSelectedItem();
				author_item aitem= (author_item) txtauthor.getSelectedItem();
				publisher_item pitem= (publisher_item) txtpublisher.getSelectedItem();
				String pages=txtpages.getText();
				String edition=txtedition.getText();
				String copies=txtcopies.getText();
				
				
				
				if(name.equals("")||citem.equals("")||aitem.equals("")||pitem.equals("")||pages.equals("")||edition.equals("")||copies.equals("")){
					JOptionPane.showMessageDialog(null, "SOME FIELDS ARE EMPTY","error",1);
					
					
				}
								
				else {
				try {
					pst=con.prepareStatement("update book set BookName = ?,Category = ?,Author = ?,Publisher = ?,pages = ?,Edition = ?,Copies =?  where Id = ?");
					pst.setString(1, name);
					pst.setInt(2,citem.id );
					pst.setInt(3,aitem.id );
					pst.setInt(4,pitem.id );
					pst.setString(5, pages);
					pst.setString(6, edition);
					pst.setString(7, copies);
					pst.setInt(8, id);

					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(null, "Record Updated");
						txtname.setText("");
						txtcategory.setSelectedIndex(-1);
						txtauthor.setSelectedIndex(-1);

						txtpublisher.setSelectedIndex(-1);
						txtpages.setText("");
						txtedition.setText("");
						txtcopies.setText("");

						txtname.requestFocus();
						book_load();
						
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
		button_update.setFont(new Font("Constantia", Font.BOLD, 18));
		button_update.setBounds(136, 489, 103, 33);
		frame.getContentPane().add(button_update);
		
		JButton button_cancel = new JButton("Cancel");
		button_cancel.setFont(new Font("Constantia", Font.BOLD, 18));
		button_cancel.setBounds(136, 555, 103, 29);
		frame.getContentPane().add(button_cancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 37, 724, 592);
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
				txtcategory.setSelectedItem(d1.getValueAt(selectedIndex,2).toString());
				txtauthor.setSelectedItem(d1.getValueAt(selectedIndex,3).toString());
				txtpublisher.setSelectedItem(d1.getValueAt(selectedIndex,4).toString());
				txtpages.setText(d1.getValueAt(selectedIndex,5).toString());
				txtedition.setText(d1.getValueAt(selectedIndex,6).toString());
				txtcopies.setText(d1.getValueAt(selectedIndex,7).toString());
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
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Name", "Category", "Author", "Publisher", "No. of Pages", "Edition", "No. of Copies"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(119);
		table.getColumnModel().getColumn(4).setPreferredWidth(116);
		table.getColumnModel().getColumn(5).setPreferredWidth(113);
		table.getColumnModel().getColumn(6).setPreferredWidth(83);
		table.getColumnModel().getColumn(7).setPreferredWidth(77);
		table.setBounds(308, 37, 724, 592);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\Sarim\\eclipse-workspace\\Library-Management-Project\\images\\bg5.PNG"));
		lblNewLabel_8.setBounds(0, 0, 1061, 670);
		frame.getContentPane().add(lblNewLabel_8);
		
		
		
		
		
		
		
	}
}
