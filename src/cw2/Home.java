package cw2;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class Home {
    public static void main(String[] args) {
        Login log = new Login();
        
    }
}
class Login {
	
	ConnectionC db= new ConnectionC();
    Login() {
    	
    	
    	
    	JFrame f = new JFrame("DS Bookstore");
        JLabel lUser, lpsw,lhead,lneed,limage;
        JTextField tfUser;
        JPasswordField tfpsw;
        JButton btnLogin,btnSignUp;
        
        limage = new JLabel(new ImageIcon("C:\\Users\\aneem\\Downloads\\download.png"));
        f.add(limage);
        limage.setBounds(500,100,400,500);
        
        
        //user label
        lUser = new JLabel("Username");
        f.add(lUser);
        lUser.setBounds(125, 115, 100, 100);
        
       
        
        lpsw = new JLabel("Password");
        f.add(lpsw);
        lpsw.setBounds(125, 165, 100, 100);
        
        lhead = new JLabel("Book Store Login");
        f.add(lhead);
        lhead.setBounds(250, 25, 150, 150);
        
        lneed = new JLabel("Don't Have An Account?");
        f.add(lneed);
        lneed.setBounds(130, 400, 150, 150);
        
        
        tfUser = new JTextField(30);
        f.add(tfUser);
        tfUser.setBounds(200, 150,200,30);
        
        
        tfpsw = new JPasswordField(30);
        f.add(tfpsw);
        tfpsw.setBounds(200, 200,200,30);
        
        
        
        
        btnLogin = new JButton("Login");
        f.add(btnLogin);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.ORANGE);
        btnLogin.setBounds(250,310,100,40);
        
        btnSignUp = new JButton("SignUp");
        f.add(btnSignUp);
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(Color.GREEN);
        btnSignUp.setBounds(290,460,100,40);
        
        
        btnLogin.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
//            	String query="Select Username from signup";
//            	String query1="Select Password from signup";
                String user = tfUser.getText();
                String psw = tfpsw.getText();
                int value=userLogin(user,psw);
                
            	
               
					if (value==1) {
						new Dash();
						f.dispose();
					}else if (value==2){
						JOptionPane.showMessageDialog(f, "Password Not Matched ");
						
					}else {
						JOptionPane.showMessageDialog(f, "Username/Password Not Valid ");
					}
           
                
            
            }
            
        });
        
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reg();
                f.dispose();
                
            }
        });
        
        
        
        
        
        
        
        
        f.setLayout(null);
        f.setSize(1000, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    
    public int userLogin(String username, String psw) {
    	String query="Select * from signup where user='"+username+"'";
    	
        try {
			ResultSet result=  db.connection().executeQuery(query);
	 		
			
			
		
			if (result.next()) {
				if (result.getString(4).equals(psw)) {
				return 1;
			}else if (!result.getString(4).equals(psw)){
				return 2;
				
			}
		}
	
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		return 0;
    	
    }
    
    
}
