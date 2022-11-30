package cw2;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
public class SignUp {
    public static void main(String[] args) {
        Reg Log= new Reg();
    
}
}
class Reg{
    Reg(){
        JFrame j =new JFrame("Sign Up");
        
        JLabel lname,lpsw,lcpass,lUser,lemail;
        JTextField tfuser,tfname,tfemail;
        JPasswordField tfpsw, tfcpass;
        JButton btnreg;
        
        
        
        
        
        //LABEL
        lname=new JLabel("Name : ");
        j.add(lname);
        lname.setBounds(100,100,100,30);
        
        
        lUser=new JLabel("Username : ");
        j.add(lUser);
        lUser.setBounds(100,140,100,30);
        
        lpsw=new JLabel("Password :");
        j.add(lpsw);
        lpsw.setBounds(100,220,100,30);
        
        lpsw=new JLabel("Confirm Password:");
        j.add(lpsw);
        lpsw.setBounds(100,260,200,30);
        
        
        lemail=new JLabel("Email : ");
        j.add(lemail);
        lemail.setBounds(100,180,100,30);
        
        
        
        ///text fields
        
        tfname = new JTextField(30);
        j.add(tfname);
        tfname.setBounds(230, 110, 160, 20);
        
        
        
        tfuser= new JTextField(10);
        j.add(tfuser);
        tfuser.setBounds(230, 150, 160, 20);
        
        tfemail = new JTextField(30);
        j.add(tfemail);
        tfemail.setBounds(230, 185, 160, 20);
        
        tfpsw = new JPasswordField(30);
        j.add(tfpsw);
        tfpsw.setBounds(230, 225, 160, 20);
        
        tfcpass = new JPasswordField(30);
        j.add(tfcpass);
        tfcpass.setBounds(230, 265, 160, 20);
        
        
        
        
        btnreg=new JButton("SignUp");
        j.add(btnreg);
        btnreg.setBounds(200, 340, 150, 30);
        
        
        
        //save action
        btnreg.addActionListener(e->{
            String name=tfname.getText();
            String user=tfuser.getText();
            String psw=tfpsw.getText();
            String email=tfemail.getText();
            String cpsw=tfcpass.getText();
            
            
                try {
                    ConnectionC db=new ConnectionC();
                    
                    String query1= "Select * from SignUp where user='"+user+"'";
                    ResultSet result1=  db.connection().executeQuery(query1);
                    System.out.println(psw);
                    System.out.println(cpsw);
                    System.out.println(psw.equals(""));
                    if(name.equals("")|| user.equals("")|| psw.equals("") || email.equals("")|| cpsw.equals("")){
                        JOptionPane.showMessageDialog(btnreg, " Fields must not be left empty ");
                    
                    }else if (!result1.next() && psw.equals(cpsw)) { 
                        String query= "insert into Signup values('"+name+"','"+user+"','"+email+"','"+psw+"')";
                            
                        
                        int result=  db.connection().executeUpdate(query);
                        if(result>0) {
                            JOptionPane.showMessageDialog(btnreg, " You Have Successfully Signed Up!");
                            new Login();
                            j.dispose();
                        }
                        
                    }else if (!psw.equals(cpsw)) {
                        JOptionPane.showMessageDialog(btnreg, "Confirmation Password doesn't match!");
                        
                        
                    }else {
                        JOptionPane.showMessageDialog(btnreg, "Username already exist!");
                    }
                    
                  
                  
                  
                    //System.out.println(query);
                    
                   // System.out.println(con);
                } catch (SQLException e1) {
                    
                    e1.printStackTrace();
                }
            
            
        });
        
        
        
        
    
       j.setLayout(null);  
        j.setSize(600,600);
        j.setVisible(true);
        j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
    }
}
