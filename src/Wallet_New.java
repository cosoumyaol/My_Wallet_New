import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Wallet_New {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	String uName;
	String uPassword;
	String check="";
	
	private static final String USERNAME = "cosoumyaol";
	private static final String PASSWORD = "soumya5230";
	private static final String CONN_STRING =
			"jdbc:mysql://localhost/world";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException{
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM country where (continent = 'asia')");
			
			rs.last();
			System.out.println(rs.getRow());
			
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wallet_New window = new Wallet_New();
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
	public Wallet_New() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 352, 244);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 11, 317, 182);
		frame.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("SignUp/Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.hide();
				frame.setBounds(100, 100, 352, 244);
				JPanel panel2 = new JPanel();
				panel2.setBounds(10, 11, 317, 182);
				frame.getContentPane().add(panel2);
				panel2.setLayout(null);
				textField_1 = new JTextField();
				textField_1.setBounds(110, 35, 200, 25);
				panel2.add(textField_1);
				textField_1.setColumns(10);
				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(110, 70, 200, 25);
				panel2.add(passwordField_1);
				passwordField_2 = new JPasswordField();
				passwordField_2.setBounds(110, 105, 200, 25);
				panel2.add(passwordField_2);
				JButton btnNewButton_2 = new JButton("Delete");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						uName =textField_1.getText();
						System.out.println(uName);
						String password1 = new String(passwordField_1.getPassword());
						System.out.println(password1);
						String password2 = new String(passwordField_2.getPassword());
						System.out.println(password2);
						if(uName.equals(check)) {
							System.out.println("No User Name");
						}else if(password1.equals(check)||password2.equals(check)){
							System.out.println("No Password");
						}else if(password1.equals(password2)) {
							uPassword=password1;
							System.out.println("User Removed");
							panel2.hide();
							PopWindow p = new PopWindow();
							p.initialize("User Removed",50,15);
							panel1.show();
							frame.setBounds(100, 100, 352, 244);
						}else {
							System.out.println("not equal");			
						}
					}
				});
				btnNewButton_2.setBounds(110, 150, 89, 30);
				panel2.add(btnNewButton_2);
				JButton btnCreate = new JButton("Create");
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						uName =textField_1.getText();
						System.out.println(uName);
						String password1 = new String(passwordField_1.getPassword());
						System.out.println(password1);
						String password2 = new String(passwordField_2.getPassword());
						System.out.println(password2);
						if(uName.equals(check)) {
							System.out.println("No User Name");
						}else if(password1.equals(check)||password2.equals(check)){
							System.out.println("No Password");
						}else if(password1.equals(password2)) {
							uPassword=password1;
							System.out.println("New User Created");
							panel2.hide();
							PopWindow p = new PopWindow();
							p.initialize("User Created",50,15);
							panel1.show();
							frame.setBounds(100, 100, 352, 244);
						}else {
							System.out.println("not equal");			
						}
						
					}
				});
				btnCreate.setBounds(218, 150, 89, 30);
				panel2.add(btnCreate);
				JLabel lblUserName_1 = new JLabel("User Name");
				lblUserName_1.setBounds(20, 35, 89, 25);
				panel2.add(lblUserName_1);
				
				JLabel lblPassword_1 = new JLabel("Password");
				lblPassword_1.setBounds(20, 70, 89, 25);
				panel2.add(lblPassword_1);
				
				JLabel lblRepassword = new JLabel("Re_Password");
				lblRepassword.setBounds(20, 105, 89, 25);
				panel2.add(lblRepassword);
			}
		});
		btnNewButton_1.setBounds(10, 140, 113, 31);
		panel1.add(btnNewButton_1);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				passwordField.setText(null);
			}
		});
		btnReset.setBounds(127, 140, 89, 31);
		panel1.add(btnReset);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel1.hide();
				String userName =textField.getText();
				System.out.println(userName);
				String password = new String(passwordField.getPassword());
				System.out.println(password);
				String name = "hi  "+userName;
				PopWindow p = new PopWindow();
				p.initialize(name,55,15);
				frame.setBounds(10, 11, 321, 362);
				JPanel panel2 = new JPanel();
				panel2.setBounds(10, 11, 321, 362);
				frame.getContentPane().add(panel2);
				panel2.setLayout(null);
				JButton btnNewButton = new JButton("Ok");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						panel2.hide();
						panel1.show();
						frame.setBounds(100, 100, 352, 244);
					}
				});
				btnNewButton.setBounds(115, 280, 56, 23);
				panel2.add(btnNewButton);
				
			}
		});
		btnSignIn.setBounds(218, 140, 89, 31);
		panel1.add(btnSignIn);
		
		textField = new JTextField();
		textField.setBounds(127, 34, 180, 36);
		panel1.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 81, 180, 36);
		panel1.add(passwordField);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(48, 34, 75, 36);
		panel1.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(48, 81, 75, 36);
		panel1.add(lblPassword);
	}
}
