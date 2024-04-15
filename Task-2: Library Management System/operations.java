import java.sql.*;
public class operations
{
    String name,password;
    operations(String n,String pass)
    {
        this.name=n;
        this.password=pass;

    } 
    public boolean check_details()
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib", "root", "Prakash");
            PreparedStatement smt=con.prepareStatement("select * from user where name=? and password=?");
            smt.setString(1, name);
            smt.setString(2, password);
            ResultSet rs=smt.executeQuery();
            if(rs.next())
            {
                System.out.println(" ---Login was successful---\n");
                return true;
            }
            else
            {
                System.out.println("Entered name or password is incorrect");
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Connection was not established\n"+e);
        }
        return false;
    }  

    public boolean books_available(String t)
    {
        int z=0;
        String title=t;
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib", "root", "Prakash");
            PreparedStatement smt=con.prepareStatement("select * from  books where title=?");
            smt.setString(1, title);
            ResultSet rs=smt.executeQuery();
            while(rs.next())
            {
                System.out.println("Title : "+rs.getString(1)+"\nAuthor : "+rs.getString(2)+"\nGenre of the book : "+rs.getString(3)+"\nAvailability status of the book : "+rs.getString(4));
                System.out.println();
                z++;
            }
        }
        catch(Exception e)
        {
            System.out.println("Connection was not established\n"+e);
        }
        return (z!=0);   
    }
    
}
