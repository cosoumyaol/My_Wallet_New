import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.*;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Wallet_New {

	private JFrame frmMywallet;
	String uName;
	String uPassword;
	String check="";
	String name;
	int id=0;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rsData = null;
	ResultSet rsUser = null;
	ResultSet rsUser_1 = null;
	
	String userName12=null;
	String password12=null;
	String date=null;
	String time=null;
	String product=null;
	double price=0;
	int i=0;
	
	private static final String USERNAME = "cosoumyaol";
	private static final String PASSWORD = "soumya5230";
	private static final String CONN_STRING =
			"jdbc:mysql://localhost/mywallet";
	
	public static void main(String[] args) throws SQLException{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wallet_New window = new Wallet_New();
					window.frmMywallet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Wallet_New() throws SQLException {
		initialize();
	}
	
	private void initialize() throws SQLException{
		frmMywallet = new JFrame();
		frmMywallet.setTitle("My_Wallet");
		frmMywallet.setBounds(500, 100, 352, 244);
		frmMywallet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMywallet.getContentPane().setLayout(null);
		PopWindow p = new PopWindow();
		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			
			

/*------------------------------Login Window------------------------------*/
		
	JPanel panel1 = new JPanel();
	panel1.setBounds(10, 11, 317, 182);
	frmMywallet.getContentPane().add(panel1);
	panel1.setLayout(null);
	
	JLabel lblUserName = new JLabel("User Name");
	lblUserName.setBounds(48, 34, 75, 36);
	panel1.add(lblUserName);
	
	JTextField textField = new JTextField();
	textField.setBounds(127, 34, 180, 36);
	panel1.add(textField);
	textField.setColumns(10);
	
	//		UtilDateModel model = new UtilDateModel();
	//		JDatePanelImpl datePanel = new JDatePanelImpl(model);
	//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
	 
	//		frame.add(datePicker);
	
	JLabel lblPassword = new JLabel("Password");
	lblPassword.setBounds(48, 81, 75, 36);
	panel1.add(lblPassword);
	
	JPasswordField passwordField = new JPasswordField();
	passwordField.setBounds(127, 81, 180, 36);
	panel1.add(passwordField);
			
	JButton btnReset = new JButton("Reset");
	btnReset.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			textField.setText(null);
			passwordField.setText(null);
		}
	});
	btnReset.setBounds(127, 140, 89, 31);
	panel1.add(btnReset);
		
/*------------------------------Information Window------------------------------*/	
	JPanel panel2 = new JPanel();
	panel2.setBounds(10, 11, 600, 300);
	frmMywallet.getContentPane().add(panel2);
	panel2.setLayout(null);
	panel2.hide();
	
	JButton btnSignIn = new JButton("Sign In");
	btnSignIn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
						
			userName12 =textField.getText();
			String userName = "'"+userName12+"'";
			password12 = new String(passwordField.getPassword());
			String password = "'"+password12+"'";
			if(userName12.equals(check)) {				
				name = "Enter User Name";				
				p.initialize(name,35,15);
			}else if(password12.equals(check)) {				
				name = "Enter password";				
				p.initialize(name,35,15);
			}else {
			textField.setText(null);
			passwordField.setText(null);
			panel1.hide();
			panel2.show();
			try {
				
				rsUser = stmt.executeQuery("SELECT id FROM loginfo where "
						+ "user_name= "+userName+" and password="+password+"");
				rsUser.next();					
				id =rsUser.getInt("id");
				//System.out.println(id);
				name = "hello  "+userName;				
				p.initialize(name,55,15);																
				frmMywallet.setBounds(400, 100, 600, 350);				
				frmMywallet.setTitle("Data");
				JTable table = new JTable(); 
				Object[] columns = {"Date", "Time", "Product", "Price"};
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columns);
				table.setModel(model);
		     
				JScrollPane pane =new JScrollPane(table);
				pane.setBounds(10, 11, 550, 250);
				panel2.add(pane);
		     			     
				table.setBackground(Color.LIGHT_GRAY);
				table.setForeground(Color.BLACK);
				Font font = new Font("",5,15);
				table.setFont(font);
				table.setRowHeight(30);
				Object[] row = new Object[4];					     
				try {
				rsData = stmt.executeQuery("SELECT * FROM data where id="+id+"");				
				while (rsData.next()) {				
					Date d = rsData.getDate("p_date");
					Time t =rsData.getTime("p_time");
					String s=rsData.getString("product");
					double price = rsData.getDouble("price");					
					//System.out.println(d +" "+t+" "+s+" "+price);
					row[0]=d;
					row[1]=t;
					row[2]=s;
					row[3]=price;
			        model.addRow(row);
			        table.setModel(model);
				}
			} catch (SQLException e1) {
				
				name = "No Data Available";				
				p.initialize(name,35,15);
			}					     
			JButton btnNewButton = new JButton("Close");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panel2.hide();
					panel1.show();
					frmMywallet.setBounds(500, 100, 352, 244);
					frmMywallet.setTitle("My_Wallet");
				}
			});
			btnNewButton.setBounds(469, 264, 89, 33);
			panel2.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Add Data");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panel2.hide();
					frmMywallet.setBounds(500, 100, 352, 244);
					frmMywallet.setTitle("Add data");
					JPanel panel3 = new JPanel();
					panel3.setBounds(10, 11, 317, 182);
					frmMywallet.getContentPane().add(panel3);
					panel3.setLayout(null);
					
					JLabel lblUserName = new JLabel("Date");
					lblUserName.setBounds(50, 0, 89, 25);
					panel3.add(lblUserName);
					
					JTextField textField = new JTextField();
					textField.setBounds(110, 0, 200, 25);
					panel3.add(textField);
					textField.setColumns(10);
					
					JLabel lblUserName_1 = new JLabel("Time");
					lblUserName_1.setBounds(50, 35, 89, 25);
					panel3.add(lblUserName_1);
					
					JTextField textField_1 = new JTextField();
					textField_1.setBounds(110, 35, 200, 25);
					panel3.add(textField_1);
					textField_1.setColumns(10);
					
					JLabel lblUserName_2 = new JLabel("Product");
					lblUserName_2.setBounds(50, 70, 89, 25);
					panel3.add(lblUserName_2);
					
					JTextField textField_2 = new JTextField();
					textField_2.setBounds(110, 70, 200, 25);
					panel3.add(textField_2);
					textField_2.setColumns(10);
					
					JLabel lblUserName_3 = new JLabel("price");
					lblUserName_3.setBounds(50, 105, 89, 25);
					panel3.add(lblUserName_3);
					
					JTextField textField_3 = new JTextField();
					textField_3.setBounds(110, 105, 200, 25);
					panel3.add(textField_3);
					textField_3.setColumns(10);
					
					JButton btnNewButton_5 = new JButton("Close");
					btnNewButton_5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							panel3.hide();
							
							frmMywallet.setBounds(400, 100, 600, 350);
							frmMywallet.setTitle("Data");
							panel2.show();
						}
					});
					btnNewButton_5.setBounds(18, 150, 89, 30);
					panel3.add(btnNewButton_5);
					
					JButton btnNewButton = new JButton("Add");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {															
							
							try {	
								 date =textField.getText();
								String date1="'"+date+"'";
								 time =textField_1.getText();
								String time1="'"+time+"'";
								 product =textField_2.getText();
								String product1="'"+product+"'";
								String prc =textField_3.getText();
								 price =Double.parseDouble(prc);
							
								stmt.executeUpdate("insert into data "
										+ "(id,p_date,p_time,product,price) "
										+ "values ("+id+","+date1+","+time1+","+product1+","+price+")");
								panel3.hide();
								i=1;
								PopWindow p = new PopWindow();
								p.initialize("Data Added",55,15);
								frmMywallet.setBounds(400, 100, 600, 350);
								frmMywallet.setTitle("Data");
								panel2.show();												
										
							} catch (SQLException e) {						
								name = "Incorrect Data";				
								p.initialize(name,40,15);
							}catch (Exception e1) {						
								name = "Incorrect Data";				
								p.initialize(name,40,15);
							}
							if(i==1) {
							String d1 = date;
							String t1 =time;
							String s1=product;
							double price1 =price;					
							System.out.println(d1 +" "+t1+" "+s1+" "+price1);
							row[0]=d1;
							row[1]=t1;
							row[2]=s1;
							row[3]=price1;
					        model.addRow(row);
					        table.setModel(model);
					        i=0;
							}else {
								date=null;
								time=null;
								product=null;
								price=0;
							}
						}
					});
					btnNewButton.setBounds(218, 150, 89, 30);
					panel3.add(btnNewButton);
					
					JButton btnNewButton_1 = new JButton("Reset");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							textField.setText(null);
							textField_1.setText(null);
							textField_2.setText(null);
							textField_3.setText(null);
						}
					});
					btnNewButton_1.setBounds(110, 150, 89, 30);
					panel3.add(btnNewButton_1);
				}
			});
			btnNewButton_1.setBounds(10, 264, 89, 33);
			panel2.add(btnNewButton_1);
			} catch (SQLException e2) {
				panel2.hide();
				name = "User Not Found";				
					p.initialize(name,40,15);
					panel1.show();
				}
			}
		}			
	});
	btnSignIn.setBounds(218, 140, 89, 31);
	panel1.add(btnSignIn);
		
		
		
/*------------------------------Delete/Create Window------------------------------*/
		
	JButton btnNewButton_1 = new JButton("SignUp/Delete");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel1.hide();				
			frmMywallet.setBounds(500, 100, 352, 244);
			frmMywallet.setTitle("Mannage user");
			JPanel panel2 = new JPanel();
			panel2.setBounds(10, 11, 317, 182);
			frmMywallet.getContentPane().add(panel2);
			panel2.setLayout(null);
			
			JLabel lblUserName_1 = new JLabel("User Name");
			lblUserName_1.setBounds(20, 35, 89, 25);
			panel2.add(lblUserName_1);
			
			JTextField textField_1 = new JTextField();
			textField_1.setBounds(110, 35, 200, 25);
			panel2.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblPassword_1 = new JLabel("Password");
			lblPassword_1.setBounds(20, 70, 89, 25);
			panel2.add(lblPassword_1);
			
			JPasswordField passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(110, 70, 200, 25);
			panel2.add(passwordField_1);
			
			JLabel lblRepassword = new JLabel("Re_Password");
			lblRepassword.setBounds(20, 105, 89, 25);
			panel2.add(lblRepassword);
			
			JPasswordField passwordField_2 = new JPasswordField();
			passwordField_2.setBounds(110, 105, 200, 25);
			panel2.add(passwordField_2);
			
			JButton btnNewButton_5 = new JButton("Close");
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panel2.hide();
					
					frmMywallet.setBounds(500, 100, 352, 244);
					frmMywallet.setTitle("My_Wallet");
					panel1.show();
				}
			});
			btnNewButton_5.setBounds(10, 150, 89, 30);
			panel2.add(btnNewButton_5);
			
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
						name = "No User Name";				
						p.initialize(name,55,15);
					}else if(password1.equals(check)||password2.equals(check)){
						name = "No Password";				
						p.initialize(name,55,15);
					}else if(password1.equals(password2)) {							
						uPassword=password1;
						uName="'"+uName+"'";
						uPassword="'"+uPassword+"'";
						try {
							rsUser = stmt.executeQuery("SELECT id FROM loginfo where "
									+ "user_name= "+uName+" and password="+uPassword+"");
							rsUser.next();					
							id =rsUser.getInt("id");
							System.out.println(id);
							stmt.executeUpdate("delete from data where id="+id);	
							stmt.executeUpdate("delete from loginfo where id="+id);	
							System.out.println("User Removed");
							panel2.hide();
							PopWindow p = new PopWindow();
							p.initialize("User Removed",50,15);
							panel1.show();
							frmMywallet.setBounds(500, 100, 352, 244);
							frmMywallet.setTitle("My_Wallet");
						} catch (SQLException e1) {								
							name = "User Doesnot Exist";				
							p.initialize(name,35,15);
						}							
					}else {
						name = "Password Mismatch";				
						p.initialize(name,40,15);			
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
						name = "No User Name";				
						p.initialize(name,40,15);
					}else if(password1.equals(check)||password2.equals(check)){
						name = "No Password";				
						p.initialize(name,40,15);
					}else if(password1.equals(password2)) {
						uPassword=password1;
						uName="'"+uName+"'";
						uPassword="'"+uPassword+"'";
						try {
							stmt.executeUpdate("insert into loginfo "
									+ "(user_name,password) "
									+ "values ("+uName+","+uPassword+")");
							System.out.println("New User Created");
							panel2.hide();
							PopWindow p = new PopWindow();
							p.initialize("User Created",50,15);
							panel1.show();
							frmMywallet.setBounds(500, 100, 352, 244);
							frmMywallet.setTitle("My_Wallet");
						} catch (SQLException e1) {
							name = "User Name Already Exists";				
							p.initialize(name,20,15);
						}														
					}else {
						name = "Password Mismatch";				
							p.initialize(name,55,15);			
						}						
					}
				});
				btnCreate.setBounds(218, 150, 89, 30);
				panel2.add(btnCreate);				
			}
		});
		btnNewButton_1.setBounds(10, 140, 113, 31);
		panel1.add(btnNewButton_1);
	
	} catch (SQLException e) {
		name ="System Error";
			p.initialize(name,55,15);
		}
	}
	}
