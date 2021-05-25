import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;

public class category {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("Category");
	private JTextField textField;
	static private JTable table;
	private  JComboBox comboBox;
	//private JButton btnNewButton;
	static Connection con;
	static PreparedStatement pst;
	private static int id;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					category window = new category();
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
	public category() {
		initialize();
		connect();
		load();
	}
	
	
	public static void connect() {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/slibrary","root","rootdikshita");
			
			JOptionPane.showMessageDialog(table, "connection created"); //for testing purpose only (can uncomment to know whats going under the hood)

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//this is to show/display table 
	public static void load() {
		try {
//			 Class.forName("com.mysql.cj.jdbc.Driver"); ///// commented since connection is alrdy created
//			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","rootdikshita");
			
			JOptionPane.showMessageDialog(table, "in load func"); //for testing purpose only (can uncomment to know whats going under the hood)
			
			 //category.connect();---->commented cz connection alrdy created (called in category constructor)
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery("select * from category");
			 table.setModel(DbUtils.resultSetToTableModel(rs));
			 //JOptionPane.showMessageDialog(table, "Gonna close the connection");
			// con.close(); ------>commented cz before doing any operation, connection got closed since it was already called in category constructor beforehand
			
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
		frame.setBounds(100, 100, 1038, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//new Functionality>>>>>>>>>>>>>>>>
		//created window listener so that when window is getting closed, connection get closed too
		//imported Window listener and window adapter classes for this
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
		lblNewLabel.setFont(new Font("Elephant", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(53, 31, 126, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category Name");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 120, 145, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 194, 104, 24);
		frame.getContentPane().add(lblNewLabel_2);
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"active", "deactive"}));
		comboBox.setBounds(165, 197, 131, 22);
		frame.getContentPane().add(comboBox);
		textField = new JTextField();
		textField.setBounds(165, 125, 131, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//to INSERT record---*****************************************************
				String category=textField.getText();
				String status=comboBox.getSelectedItem().toString();
				if(category.equals("")||status.compareToIgnoreCase("")==1){ //compareToIgnoreCase() returns 0 if the string is equal to other string, ignoring case differences.
					JOptionPane.showMessageDialog(addBtn, "SOME FIELDS ARE EMPTY","error",1); 
					textField.setText("");
					comboBox.setSelectedIndex(-1);
					
				}
				else {
					try {
						pst=con.prepareStatement("insert into category(catname,status) values(?,?)");
						pst.setString(1, category);
						pst.setString(2, status);
						int k=pst.executeUpdate();
						
						if(k==1)
						{
							JOptionPane.showMessageDialog(addBtn, "Record Added");
							textField.setText("");
							comboBox.setSelectedIndex(-1);
							textField.requestFocus();
							load();
							
						}
						else {
							JOptionPane.showMessageDialog(addBtn, "Error..");
	
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					finally {
//						try {
//							JOptionPane.showMessageDialog(frame, "gonna close connection");
//							con.close();
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
				}
				
			}
		});
		addBtn.setFont(new Font("Constantia", Font.BOLD, 18));
		addBtn.setBounds(21, 295, 112, 36);
		frame.getContentPane().add(addBtn);

		


				
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//to show selected row content in textField*************************************************
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				
				id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString()); //0 represents the first 0th position i.e. first row 
				
				textField.setText(d1.getValueAt(selectedIndex,1).toString()); //1 represents the 1 position i.e. 2nd column
				comboBox.setSelectedItem(d1.getValueAt(selectedIndex,2).toString());
				addBtn.setEnabled(false); //it will disable add btn cz if the row is selected, the selected row content will be shown in textfield and hence we cant do add operation
				
				
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		JTableHeader tableHeader = table.getTableHeader();
		Font headerFont = new Font("Calibri", Font.BOLD, 15);
		tableHeader.setFont(headerFont);
		table.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, ""},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"ID", "Category Name", "Status"
			}
		) /*{
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class
			}
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}*/
		);
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(255);
		table.getColumnModel().getColumn(2).setPreferredWidth(172);
		table.setBounds(335, 52, 663, 549);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(335, 31, 663, 572);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);

		
		@SuppressWarnings("rawtypes")
		
		
				
				
		JButton delBtn = new JButton("Delete");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to delete selected row record----
				if(table.getSelectedRowCount()==1) {
				try {
					//connect(); ----> no need to create connection again since its alrdy created
//					int row=table.getSelectedRow(); //returns the index of the first selected row
//				String value=table.getModel().getValueAt(row,0).toString();
					
					String query="delete from category where id= " + id;
					 pst = con.prepareStatement(query);
					 
					 pst.executeUpdate();
					 //no need to following 2 lines since without these also, it working 
//					 DefaultTableModel tbmodel=(DefaultTableModel)table.getModel();
//					 tbmodel.setRowCount(0);//-----------------------------------------
					 JOptionPane.showMessageDialog(delBtn, "Record deleted");
					 load();
					 	 
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else {
					 JOptionPane.showMessageDialog(delBtn, "Please select row to delete");

				}
				
			}
		});
		delBtn.setFont(new Font("Constantia", Font.BOLD, 18));
		delBtn.setBounds(21, 376, 112, 36);
		frame.getContentPane().add(delBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//to update selected row record---*************************************************
				//following is to show selected row items in textfields. since its alrdy done on table mouseclicked event...so commented
				
//				DefaultTableModel d1=(DefaultTableModel)table.getModel();
//				int selectedIndex=table.getSelectedRow();
//				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());

				String category=textField.getText();
				String status=comboBox.getSelectedItem().toString();
				
				if(category.equals("")||status.compareToIgnoreCase("")==1){
					JOptionPane.showMessageDialog(addBtn, "SOME FIELDS ARE EMPTY","error",1);
					textField.setText("");
					comboBox.setSelectedIndex(-1);
					
				}
				else {
				try {
					pst=con.prepareStatement("update category set catname = ?,status = ? where id= ?");
					pst.setString(1, category);
					pst.setString(2, status);
					pst.setInt(3, id);
					int k=pst.executeUpdate();
					
					if(k==1)
					{
						JOptionPane.showMessageDialog(addBtn, "Record Updated");
						textField.setText("");
						comboBox.setSelectedIndex(-1);
						textField.requestFocus();
						load();
						addBtn.setEnabled(true);
						
					}
					else {
						JOptionPane.showMessageDialog(addBtn, "Error..");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			}});
		updateBtn.setFont(new Font("Constantia", Font.BOLD, 18));
		updateBtn.setBounds(166, 295, 112, 36);
		frame.getContentPane().add(updateBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					JOptionPane.showMessageDialog(frame, "gonna close connection");
		        	con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				addbooks obj = new addbooks();
				obj.main(null);
				frame.setVisible(false);
				
				
			}
		});
		cancelBtn.setFont(new Font("Constantia", Font.BOLD, 18));
		cancelBtn.setBounds(163, 376, 112, 36);
		frame.getContentPane().add(cancelBtn);
		
				
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\fabdi\\eclipse-workspace\\Library-Management-Project\\images\\bg8.PNG"));
		lblNewLabel_3.setBounds(0, 0, 1024, 635);
		frame.getContentPane().add(lblNewLabel_3);
		
	}
}