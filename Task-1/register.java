import java.sql.*;
import java.util.*;
public class register {
    String name,password,type;
    static long acc_auto_num; 
    register(String name,String password,String type)
    {
        this.name=name;
        this.type=type;
        this.password=password;
        Connection con;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_sys", "root", "Prakash"); 
            try
            {
                PreparedStatement smt1=con.prepareStatement("select * from customers where name=? and password=? and type=?");
                smt1.setString(1,name);
                smt1.setString(2,password);
                smt1.setString(3,type);
                ResultSet r=smt1.executeQuery();  
                if(r.next())
                {
                    System.out.println("\nAccount already exists with similar detail\n Please enter different details");
                }
                else
                {
                    try
                    {
                        Statement smt5=con.createStatement();
                        ResultSet rs = smt5.executeQuery("SELECT MAX(account_id) FROM customers");
                        if(rs.next())
                        {
                            acc_auto_num=rs.getLong(1);
                        }
                        acc_auto_num+=1;
                        PreparedStatement smt=con.prepareStatement("insert into customers values(?,?,?,?)");
                        smt.setLong(1,acc_auto_num);
                        smt.setString(2,name);
                        smt.setString(3,password);
                        smt.setString(4,type);
                        int i=smt.executeUpdate();
                        if(i>=0)
                        {
                        Scanner sc=new Scanner(System.in);
                        System.out.println("Account created Successfully\n");
                        System.out.println("Enter your new PIN");
                        int pin=sc.nextInt();
                        System.out.println("Enter your initial credit of your account");
                        long balance=sc.nextLong();
                        PreparedStatement smt6=con.prepareStatement("insert into accounts values(?,?,?,?)");
                        smt6.setLong(1,acc_auto_num);
                        smt6.setString(2,name);
                        smt6.setInt(3,pin);
                        smt6.setLong(4,balance);
                        int u=smt6.executeUpdate();
                        if(u>=0)
                        {
                            System.out.println("Account created Successfully\n");
                            operations o=new operations(acc_auto_num);
                            o.account_details(acc_auto_num, pin);
                            System.out.println();
                        }
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error occured while generating account number");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("Error occured while fetching the data from database "+e);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error occured while establishing connection\n"+ e);
        }
    }
}
