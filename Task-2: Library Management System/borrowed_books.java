import java.sql.*;
public class borrowed_books 
{
   
    public boolean borrow(String t1)
    {
        try
        {    
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib", "root", "Prakash");
            PreparedStatement smt=con.prepareStatement("select * from  books where title=? and availability_status=?");
            smt.setString(1, t1);
            smt.setString(2, "yes");
            ResultSet rs=smt.executeQuery();
            if(rs.next())
            {
                try
                {
                    PreparedStatement smt1=con.prepareStatement("update books set availability_status=? where title=?");
                    smt1.setString(1, "no");
                    smt1.setString(2, t1);
                    smt1.executeUpdate();
                    return true;
                }
                catch(Exception e)
                {
                    System.out.println("Updating data failed\n"+ e);
                }
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Connection was not established\n"+e);
        }
        return false;       
    }


    public boolean return_books(String r)
    {
        try
        {    
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib", "root", "Prakash");
            PreparedStatement smt=con.prepareStatement("select * from books where title=? and availability_status=?");
            smt.setString(1, r);
            smt.setString(2, "no");
            ResultSet rs=smt.executeQuery();
            if(rs.next())
            {
                try
                {
                    PreparedStatement smt1=con.prepareStatement("update books set availability_status=? where title=?");
                    smt1.setString(1, "yes");
                    smt1.setString(2, r);
                    smt1.executeUpdate();
                    return true;
                }
                catch(Exception e)
                {
                    System.out.println("Updating data failed\n"+ e);
                }
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Connection was not established\n"+e);
        }
        return false;       
    }
}
