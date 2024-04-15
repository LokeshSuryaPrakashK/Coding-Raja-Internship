import java.sql.*;
class tables 
{
    public static void main(String[] args) 
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_sys", "root", "Prakash"); 
            Statement smt=con.createStatement();
            String s1= "create table customers(account_id long,name varchar(50),password varchar(50),type varchar(50))";
            if(smt.executeUpdate(s1)==0)
            {
                System.out.println("Customers table created successfully");
                int i=smt.executeUpdate("insert into customers values(100150,'Prakash','1234','savings')");
                if(i==1)
                {
                    System.out.println("Row inserted successfully");
                }
                else
                {
                    System.out.println("Row insertion failed");
                }
            }
            else
            {
                System.out.println("Customers Table is not created");
            }
            String s2="create table accounts(account_id long,name varchar(50),pin int,balance long)";
            if(smt.executeUpdate(s2)==0)
            {
                System.out.println("Accounts table created successfully");
                int i=smt.executeUpdate("insert into accounts values(100150,'Prakash',1234,1000)");
                if(i==1)
                {
                    System.out.println("Row inserted successfully");
                }
                else
                {
                    System.out.println("Row insertion failed");
                }
            }
            else
            {
                System.out.println("Accounts Table is not created");
            } 
            con.close();           
        }
        catch(Exception e)
        {
            System.out.println("Error occured while establishing connection\n "+e);
        }
    }
}
