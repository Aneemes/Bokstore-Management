package cw2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class BookManageDash {
    public static void main(String[] args) {
        Dash log = new Dash();
    }
}
class Dash {
    Dash() {
        JFrame f=new JFrame("Dashboard");
        JButton btnAdd,btnView,btnViewS;
        JLabel lZean;
        
        lZean=new JLabel("Book Store");
        f.add(lZean);
        lZean.setBounds(225,30,3000,100);
        
        
        
        
        btnAdd = new JButton("Add books");
        f.add(btnAdd);
        btnAdd.setBounds(210,150,150,50); 
        btnView = new JButton("View Available Books");
        f.add(btnView);
        btnView.setBounds(200,250,170,50); 
        
        btnViewS = new JButton("View Sold Books");
        f.add(btnViewS);
        btnViewS.setBounds(210,350,150,50); 
        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new bookSt();
                f.dispose();
                
            }
        });
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View();
                f.dispose();
                
            }
        });
        
        btnViewS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SoldBooks();
                f.dispose();
                
            }
        });
        
        
        
        
        f.setLayout(null);
        f.setSize(600,600);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);  
    }
}
