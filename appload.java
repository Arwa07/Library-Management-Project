import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.ImageIcon;

public class appload extends JFrame {
	
	private JPanel contentPane;
	JProgressBar pbar = new JProgressBar();
	JLabel txtpin = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		appload appld= new appload();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					appld.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		homepage hp = new homepage();
	
		
			try {
				for(int i= 0; i<=100;i++)
				{
					Thread.sleep(100); //main thread will sleep for 100 ms
					appld.pbar.setValue(i);
					appld.txtpin.setText(i+"%");   //changed from appld.txtpin.setText(String.valueOf(i)+"%");
					if(i==100)
					{
						appld.setVisible(false);
						appld.dispose();
					}
				}
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp.main(null);
			appld.dispose();
		}////end of main function
		
		
	

	/**
	 * Create the frame.
	 */
	public appload() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 204));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(0, 0, 737, 442);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Loading................................................");
		lblNewLabel.setForeground(new Color(102, 51, 204));
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 24));
		lblNewLabel.setBounds(262, 235, 215, 45);
		panel.add(lblNewLabel);
		pbar.setForeground(new Color(204, 102, 51));
		
		
		pbar.setBounds(74, 291, 587, 29);
		panel.add(pbar);
		
		
		txtpin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpin.setForeground(Color.RED);
		txtpin.setBounds(292, 342, 81, 29);
		panel.add(txtpin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\fabdi\\eclipse-workspace\\Library-Management-Project\\images\\LMSresizedlogo.gif"));
		lblNewLabel_1.setBounds(243, 10, 224, 224);
		panel.add(lblNewLabel_1);
	}
}