import java.sql.*;
class operations 
{
    int choice;
    long acc_number;
    static long balance;
    static long balance_own;
    static long balance_to;
    operations(long acc_number)
    {
        this.acc_number=acc_number;
    }

    public void credit_money(long a,int pin)
    {  
        try
        { 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_sys", "root", "Prakash");        
        PreparedStatement smt1=con.prepareStatement("select balance from accounts where account_id=? and pin=?");
        smt1.setLong(1,acc_number);
        smt1.setInt(2,pin);
        ResultSet rs1=smt1.executeQuery();
        if(rs1.next())
        {
            balance=rs1.getLong("balance");
        }
            PreparedStatement smt2=con.prepareStatement("update accounts set balance=? where account_id=? and pin=?");
            smt2.setLong(1,a+balance);
            smt2.setLong(2,acc_number);
            smt2.setInt(3,pin);
            int i=smt2.executeUpdate();
            if(i!=0)
            {
                System.out.println("Amount Credited successfully");
                System.out.println("Available Balance in your account is : "+(balance+a));
            }
            con.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }


    public void debit_money(long a,int pin)
    {  
        try
        { 
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_sys", "root", "Prakash");        
        PreparedStatement smt1=con.prepareStatement("select balance from accounts where account_id=? and pin=?");
        smt1.setLong(1,acc_number);
        smt1.setInt(2,pin);
        ResultSet rs1=smt1.executeQuery();
        if(rs1.next())
        {
            balance=rs1.getLong("balance");
        }
        if(balance>a)
        {
            PreparedStatement smt2=con.prepareStatement("update accounts set balance=? where account_id=? and pin=?");
            smt2.setLong(1,balance-a);
            smt2.setLong(2,acc_number);
            smt2.setInt(3,pin);
            int i=smt2.executeUpdate();
            if(i!=0)
            {
                System.out.println("Amount Credited successfully");
                System.out.println("Available Balance in your account is : "+(balance-a));

            }
        }
        else
        {
            System.out.println("\nInsufficient Balance");
        }
        con.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }


    public void transfer(long nt,long nf,long a,int pin)
    {
        try
        {    
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_sys", "root", "Prakash");        
            PreparedStatement smt1=con.prepareStatement("select * from customers where account_id=?");
            smt1.setLong(1, nt);
            ResultSet rs1=smt1.executeQuery();
            if(rs1.next())
                {
                    PreparedStatement smt5=con.prepareStatement("select * from accounts where account_id=? and pin=?");
                    smt5.setLong(1, nf);
                    smt5.setInt(2, pin);
                    ResultSet rs5=smt5.executeQuery();
                    if(rs5.next())
                    {
                        balance_own=rs5.getLong(4);
                    }
                    if(balance_own>a)
                    {

                        PreparedStatement smt=con.prepareStatement("select * from accounts where account_id=?");
                        smt.setLong(1, nt);
                        ResultSet rs=smt.executeQuery();
                        if(rs.next())
                        {
                            balance_to=rs.getLong(4);
                        }
                        PreparedStatement smt4=con.prepareStatement("update accounts set balance=? where account_id=?");
                        smt4.setLong(1, balance_to+a);
                        smt4.setLong(2, nt);
                        int j=smt4.executeUpdate();
                        if(j!=0)
                        {
                            System.out.println("\n---Transfer of amount was Successful---");
                            PreparedStatement smt6=con.prepareStatement("update accounts set balance=? where account_id=? and pin=?");
                            smt6.setLong(1, balance_own-a);
                            smt6.setLong(2, nf);
                            smt6.setInt(3, pin);                    
                            int k=smt6.executeUpdate();
                            if(k!=0)
                            {

                                System.out.println("\nAvailable balance in your account after tranfer : "+(balance_own-a));
                            }
                        }
                    }
                    else
                    {
                        System.out.println("\nInsufficient Balance");
                    }

                }
            else
            {
                System.out.println("Entered Account number does not exist");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public void account_details(long n,int pin)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_sys", "root", "Prakash");        
            System.out.println("Please enter your account number");
            PreparedStatement smt1=con.prepareStatement("select * from accounts where account_id=? and pin=?");
            smt1.setLong(1, n);
            smt1.setInt(2, pin);
            ResultSet rs1=smt1.executeQuery();
            while(rs1.next())
            {
                System.out.println("\nAccount number : "+rs1.getLong(1) + "\nAccount Holder Name : "+rs1.getString(2) + "\nBalance Amount Available : "+rs1.getLong(4));
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Connection does not establish");
        }
    }


   

}

    
