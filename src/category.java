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

public class category {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("Category");
	private JTextField textField;
	static private JTable table;
	private  JComboBox comboBox;
	//private JButton btnNewButton;


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
	
	public void load() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","root");
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery("select * from category");
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
		frame.setBounds(100, 100, 1038, 672);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"active", "deactive"}));
		comboBox.setBounds(165, 197, 131, 22);
		frame.getContentPane().add(comboBox);
		textField = new JTextField();
		textField.setBounds(165, 125, 131, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to insert record---
				String category=textField.getText();
				String status=comboBox.getSelectedItem().toString();
				if(category.equals("")||status.compareToIgnoreCase("")==1){
					JOptionPane.showMessageDialog(btnNewButton, "SOME FIELDS ARE EMPTY","error",1);
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
						JOptionPane.showMessageDialog(btnNewButton, "Record Added");
						textField.setText("");
						comboBox.setSelectedIndex(-1);
						textField.requestFocus();
						load();
						
					}
					else {
						JOptionPane.showMessageDialog(btnNewButton, "Error..");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		btnNewButton.setFont(new Font("Constantia", Font.BOLD, 18));
		btnNewButton.setBounds(21, 295, 112, 36);
		frame.getContentPane().add(btnNewButton);

		


				
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//to show selected row content------
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());
				textField.setText(d1.getValueAt(selectedIndex,1).toString());
				comboBox.setSelectedItem(d1.getValueAt(selectedIndex,1).toString());
				btnNewButton.setEnabled(false);
				
				
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
		
		
				
				
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to delete selected row record----
				if(table.getSelectedRowCount()==1) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slibrary","root","root");
					int row=table.getSelectedRow();
					String value=table.getModel().getValueAt(row,0).toString();
					String query="delete from category where id="+value;
					 PreparedStatement stmt=con.prepareStatement(query);
					 stmt.executeUpdate();
					 DefaultTableModel tbmodel=(DefaultTableModel)table.getModel();
					 tbmodel.setRowCount(0);
					 load();
					 JOptionPane.showMessageDialog(btnNewButton_1, "Record deleted");
					 
						
					 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
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
		btnNewButton_1.setBounds(21, 376, 112, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to update selected row record---
				DefaultTableModel d1=(DefaultTableModel)table.getModel();
				int selectedIndex=table.getSelectedRow();
				int id=Integer.parseInt(d1.getValueAt(selectedIndex,0).toString());

				String category=textField.getText();
				String status=comboBox.getSelectedItem().toString();
				
				if(category.equals("")||status.compareToIgnoreCase("")==1){
					JOptionPane.showMessageDialog(btnNewButton, "SOME FIELDS ARE EMPTY","error",1);
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
						JOptionPane.showMessageDialog(btnNewButton, "Record Updated");
						textField.setText("");
						comboBox.setSelectedIndex(-1);
						textField.requestFocus();
						load();
						btnNewButton.setEnabled(true);
						
					}
					else {
						JOptionPane.showMessageDialog(btnNewButton, "Error..");

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			}});
		btnNewButton_2.setFont(new Font("Constantia", Font.BOLD, 18));
		btnNewButton_2.setBounds(166, 295, 112, 36);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.setFont(new Font("Constantia", Font.BOLD, 18));
		btnNewButton_3.setBounds(163, 376, 112, 36);
		frame.getContentPane().add(btnNewButton_3);
		
				
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Sarim\\eclipse-workspace\\Library-Management-Project\\images\\bg8.PNG"));
		lblNewLabel_3.setBounds(0, 0, 1024, 635);
		frame.getContentPane().add(lblNewLabel_3);
		
	}
}
