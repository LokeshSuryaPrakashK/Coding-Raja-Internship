import java.sql.*;
class tables 
{
    public static void main(String[] args) 
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdeo", "root", "Prakash"); 
            Statement smt=con.createStatement();
            String s1= "create table user(name varchar(50),password varchar(50),phone varchar(50))";
            if(smt.executeUpdate(s1)==0)
            {
                System.out.println("User table created successfully");
                int i=smt.executeUpdate("insert into user values('prakash','1234','9652659966')");
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
                System.out.println("User Table is not created");
            }
            String s2="create table books(title varchar(50), author varchar(50), genre varchar(50), availability_status varchar(50))";
            if(smt.executeUpdate(s2)==0)
            {
                System.out.println("Books table created successfully");
                int i=smt.executeUpdate("insert into books values('Wings Of Fire','Abdul Kalam','Auto Biography','yes')");
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
                System.out.println("Bookss Table is not created");
            } 
            con.close();           
        }
        catch(Exception e)
        {
            System.out.println("Error occured while establishing connection\n "+e);
        }
    }
}

