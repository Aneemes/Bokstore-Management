package cw2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.TableModel;
public class View {
	ConnectionC db= new ConnectionC();
    public static void main(String[] args) {
        View vm =new View();
        
    }
    View(){
        
        
        JFrame f=new JFrame("Bookstore Inventory Management System");
        JButton btnBack,btnDelete,btnUpdate,btnSell,btnSearch ;
        JLabel lNo,lAb,lSh;
        String column[]= {"Book Number","Book Name","Author Name","Date Published","Cost","Quantity Available"};
         
        lAb = new JLabel("Available Books");
        f.add(lAb);
        lAb.setBounds(400, 30, 300, 100);
        
        String query="Select * from addbooks";
        ConnectionC db= new ConnectionC();
        ArrayList<Books> book= new ArrayList<Books>();
        
        
        
        
        try {
            ResultSet result=  db.connection().executeQuery(query);
            
            
            while(result.next()) {
                String BookName=result.getString("BookName");
                String AuthorName=result.getString("PublisherName");
                String date=result.getString("Date");
                int Cost=Integer.parseInt(result.getString("Cost"));
                int bookNumber=Integer.parseInt(result.getString("BookCode"));
                int Quantity=Integer.parseInt(result.getString("QuantitySold"));
                Books stff =new Books(BookName, AuthorName, date, Cost,bookNumber, Quantity);
                book.add(stff);
                
//               System.out.println(book.get(0).bookNumber);
////              System.out.println("..............");
                
            }
            
            
            
            
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        lNo = new JLabel("No of books available:"+ book.size());
        f.add(lNo);
        lNo.setBounds(50, 360, 300, 100);
        
        Object data[][]= new Object[book.size()] [column.length];
        
        for(int i=0; i<book.size(); i++) {
            data[i][0]=book.get(i).bookNumber;
            data[i][1]=book.get(i).BookName;
            data[i][2]=book.get(i).AuthorName;
            data[i][3]=book.get(i).date;
            data[i][4]=book.get(i).Cost;
            data[i][5]=book.get(i).Quantity;
        }

        
        JTable jt =new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        sp.setBounds(50,100,800,300);
        
        
        //button
        
        btnBack=new JButton("Back");
        f.add(btnBack);
        btnBack.setBounds(10, 10, 70, 30);
        
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dash();
                f.dispose();
            }
        });
        
        
        btnDelete=new JButton("Delete");
        f.add(btnDelete);
        btnDelete.setBounds(500, 550, 150, 30);
        
        
        btnDelete.addActionListener(e->{
            int row= jt.getSelectedRow();
            if(row>=0) {
            
                TableModel model=jt.getModel();
                 
                 String code=Integer.toString((int)model.getValueAt(row,0));
                 
                 String dquery = "Delete from addbooks WHERE `BookCode` = '"+ code +"'";
                 try {
                    int dresult = db.connection().executeUpdate(dquery);
                    if(dresult>=1) {
                        JOptionPane.showMessageDialog(sp,"Book Deleted");
                        new View();
                        f.dispose();
                    }
                   
                } catch (SQLException e1) {
                    
                    e1.printStackTrace();
                }
                 
            }else {
                JOptionPane.showMessageDialog(sp, "please select row");
            }
                
                
            
        });
        
        
        
        
        
        btnUpdate=new JButton("Update");
        f.add(btnUpdate);
        btnUpdate.setBounds(300, 550, 150, 30);
        
        btnUpdate.addActionListener(e->{
            int row = jt.getSelectedRow();
            if(row>=0) {
                
                JLabel lbName= new JLabel("Book Name");
                f.add(lbName);
               
                lbName.setBounds(10,600,900,30);
                
                JTextField tfbName = new JTextField();
                f.add(tfbName);
                tfbName.setBounds(80, 600, 120,30);
                
                TableModel model = jt.getModel();
                String BookName=(String)model.getValueAt(row,1);
                
                tfbName.setText(BookName);
                
                
                JLabel laName= new JLabel("Publisher Name");
                f.add(laName);
                
                laName.setBounds(210,600,300,30);
                
                JTextField tfaName = new JTextField();
                f.add(tfaName);
                tfaName.setBounds(310, 600, 120,30);
                
                TableModel model1 = jt.getModel();
                String AuthorName=(String)model1.getValueAt(row,2);
                
                tfaName.setText(AuthorName);
                
                JLabel lpDate= new JLabel("Date");
                f.add(lpDate);
            
                lpDate.setBounds(470,600,300,30);
                
                JTextField tfpDate = new JTextField();
                f.add(tfpDate);
                tfpDate.setBounds(530, 600, 150,30);
                
                TableModel model2 = jt.getModel(); 
                
                String date=(String)model2.getValueAt(row,3);
                tfpDate.setText(date);
                System.out.println(date);
                
                JLabel laddBy= new JLabel("Cost");
                f.add(laddBy);
           
                laddBy.setBounds(690,600,300,30);
                
                JTextField tfaddBy = new JTextField();
                f.add(tfaddBy);
                tfaddBy.setBounds(740, 600, 150,30);
                
                TableModel model3 = jt.getModel(); 
                
                String Cost=Integer.toString((int)model3.getValueAt(row,4));
                tfaddBy.setText(Cost);
                
                
                
                
                JButton btnChange = new JButton("Make Change");
                f.add(btnChange);
                btnChange.setBounds(430, 650, 150, 50);
                
                JButton btnCancel = new JButton("Cancel");
                f.add(btnCancel);
                btnCancel.setBounds(430, 720, 150, 50);
               
           
                // update action
                
                btnChange.addActionListener(e3->{
                    
                
                    String tbName = tfbName.getText();
                    String taName= tfaName.getText();
                    String tpDate = tfpDate.getText();
                    String taddBy = tfaddBy.getText();
                    
                    
                    TableModel model4 = jt.getModel(); 
                    String Code=Integer.toString((int)model4.getValueAt(row,0));
                    String BookName1=(String)model4.getValueAt(row, 1);
                    String uquery ="update `addbooks` set `BookName` = '" + tbName + "',`PublisherName` = '" + taName + "', `Cost` = '" + taddBy + "',  `Date` = '"+ tpDate +"' WHERE `BookCode` = '"+ Code +"'";
                    String squery ="update `soldbooks` set `BookName` = '" + tbName + "',`PublisherName` = '" + taName + "', `Cost` = '" + taddBy + "',  `Date` = '"+ tpDate +"' WHERE `BookName` = '"+ BookName1 +"'";
                    System.out.println(uquery);
                     try {
                        int uresult = db.connection().executeUpdate(uquery);
                        int sresult = db.connection().executeUpdate(squery);
                        if(uresult>=1 && sresult>=1) {
                            JOptionPane.showMessageDialog(sp,"Book Updated");
                            new View();
                            f.dispose();
                        }
                        
                    } catch (SQLException e1) {
                       
                        e1.printStackTrace();
                    }
    
                  
                });
                
                
                
                //cancel action
                
                btnCancel.addActionListener(e2->{
                    
                    new View();
                    f.dispose();
                    
                });
                
                
                
                
                
                
            }else {
                JOptionPane.showMessageDialog(sp, "selected Row");
            }
            
       
        
        
        
        
        });
        
  
        
        JTextField tfbSearch = new JTextField();
        f.add(tfbSearch);
        tfbSearch.setBounds(50, 70, 170,30);
        
        btnSearch = new JButton("Search");
        f.add(btnSearch);
        btnSearch.setBounds(230, 70, 100, 30);
        
        btnSearch.addActionListener(e3->{
        	String Search=tfbSearch.getText();
        	
        	try {
				ResultSet value = bookAvailable(Search);
				if(value.next()) {
					int bookNumber=Integer.parseInt(value.getString("BookCode"));
//					System.out.println(bookNumber);
					BinarySearch se= new BinarySearch();
		        	ArrayList<Integer> Quan= new ArrayList<Integer>();
		        	for(int i=0; i<book.size(); i++) {
		                int quann=book.get(i).bookNumber;
		                Quan.add(quann);
		               
		                
		        	
		        	}
//		        	System.out.println(se.binarySearch(Quan, bookNumber));
		        	int Index=se.binarySearch(Quan, bookNumber);
		        	Object data1[][]= new Object[1] [column.length];
		            JTable jt1 =new JTable(data1,column);
		            JScrollPane sp1=new JScrollPane(jt1);
		            f.add(sp1);
		            sp1.setBounds(50,450,800,38);
		            
		        	System.out.println(book.get(Index).bookNumber);
		        	System.out.println(book.get(Index).BookName);
		        	System.out.println(book.get(Index).AuthorName);
		        	System.out.println(book.get(Index).date);
		        	System.out.println(book.get(Index).Cost);
		        	System.out.println(book.get(Index).Quantity);
		        	data1[0][0]=book.get(Index).bookNumber;
		            data1[0][1]=book.get(Index).BookName;
		            data1[0][2]=book.get(Index).AuthorName;
		            data1[0][3]=book.get(Index).date;
		            data1[0][4]=book.get(Index).Cost;
		            data1[0][5]=book.get(Index).Quantity;
				}
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
        	
        });
        
        btnSell=new JButton("Sell");
        f.add(btnSell);
        btnSell.setBounds(870, 200, 100, 30);
        
        btnSell.addActionListener(e->{
            int row= jt.getSelectedRow();
            
            if(row>=0) {
            
                TableModel model=jt.getModel();
                 
                 String code=Integer.toString((int)model.getValueAt(row,0));
                 
                 String BookName=(String)model.getValueAt(row,1);
                 String AuthorName=(String)model.getValueAt(row,2);
                 String date=(String)model.getValueAt(row,3);
                 String Cost=Integer.toString((int)model.getValueAt(row,4));   
              
                 int Quan=1;
                 int Quantity=((int)model.getValueAt(row,5)-1);	
                 int QuantityC=(int)model.getValueAt(row,5); 
                 System.out.println(!(QuantityC<=0));
                 String query2= "Select * from sold where BookName='"+BookName+"'";
                 if (!(QuantityC<=0)) {
                	 
                	 String query1= "Select * from soldbooks where BookName='"+BookName+"'";
                     try {
    					ResultSet result3 = db.connection().executeQuery(query1);
    					
    					if(result3.next()) {
    						int prevQuan=Integer.parseInt(result3.getString("QuantitySold"));
    						
    						System.out.println(prevQuan++);
    						String uquery = "update `addbooks` set `Quantitysold` = '" + Quantity + "' WHERE `BookCode` = '"+ code +"'";
    						String urquery = "update `soldbooks` set `Quantitysold` = '" + prevQuan++ + "' WHERE `BookName` = '"+ BookName +"'";
    						int uresult = db.connection().executeUpdate(uquery);
    						int uaresult = db.connection().executeUpdate(urquery);
    						if(uresult>=1 && uaresult>=1) {
    	                        JOptionPane.showMessageDialog(sp,"Book Sold");
    	                        new View();
    	                        f.dispose();
    	                    }
    					}else {
    						String dquery = "update `addbooks` set `Quantitysold` = '" + Quantity + "' WHERE `BookCode` = '"+ code +"'";
    		                 String aquery = "insert into soldbooks(BookName,PublisherName,Date,Cost,Quantitysold) values('"+BookName+"','"+AuthorName+"','"+date+"','"+Cost+"','"+Quan+"') ";
    		                 try {
    		                    int dresult = db.connection().executeUpdate(dquery);
    		                    int aresult = db.connection().executeUpdate(aquery);
    		                    if(aresult>=1 && dresult>=1 ) {
    		                        JOptionPane.showMessageDialog(sp,"Book Sold");
    		                        new View();
    		                        f.dispose();
    		                    }
    		                   
    		                } catch (SQLException e1) {
    		                    
    		                    e1.printStackTrace();
    		                }
    					}
    				} catch (SQLException e2) {
    					
    					e2.printStackTrace();
    				}
                     
                	 
                 }else {
                     JOptionPane.showMessageDialog(sp,"No more books available");
                 }
                 
                 
                 
                 
            }else {
                JOptionPane.showMessageDialog(sp, "please select row");
            }
                
                
            
        });
        
        
        
        
        
        
        
        
        
         
        f.setLayout(null);  
        f.setSize(1000,900);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        
        
    }
    public ResultSet bookAvailable(String Search) throws SQLException {
        String query2= "Select * from addbooks where BookName='"+Search+"'";
        
        ResultSet result = db.connection().executeQuery(query2);
        return result;
}
}

