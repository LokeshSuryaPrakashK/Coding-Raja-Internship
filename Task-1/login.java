import java.sql.*;
import java.util.*;
class login
{
    String name;
    String password;
    static int i;
    static long acc_number;
    login(String n,String pass)
    {
        this.name=n;
        this.password=pass;
        Connection con=null;
        try
        {
            Scanner sc=new Scanner(System.in);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_sys", "root", "Prakash");
            PreparedStatement smt=con.prepareStatement("select * from customers where name=? and password=?");
            smt.setString(1,name);
            smt.setString(2,password);
            ResultSet rs=smt.executeQuery();
            if(rs.next())
            {
                System.out.println("Please enter your account number");
                acc_number=sc.nextLong();
                PreparedStatement smt1=con.prepareStatement("select * from customers where account_id=?");
                smt1.setLong(1, acc_number);
                ResultSet rs1=smt1.executeQuery();
                if(rs1.next())
                {
                    System.out.println("Login was Successfull");
                    boolean exitloop=false;
                    while(!exitloop)
                    {
                        System.out.println("\nPlease select below option: \n 1.Credit\n 2.Debit\n 3.Transfer\n 4.Account Details\n 5.Exit");
                        int choice=sc.nextInt();
                        operations o=new operations(acc_number);
                        switch(choice)
                        {
                            case 1:
                                System.out.println("Please enter the amount");
                                Long a1=sc.nextLong();
                                System.out.println("Please enter your PIN");
                                int pin1=sc.nextInt();
                                o.credit_money(a1,pin1);
                                break;
                            case 2:
                                System.out.println("Please enter the amount");
                                Long a2=sc.nextLong();
                                System.out.println("Please enter your PIN");
                                int pin2=sc.nextInt();
                                o.debit_money(a2,pin2);
                                break;
                            case 3:
                                System.out.println("Please enter the account number you want to tranfer the money to:");
                                long nt=sc.nextLong();  
                                System.out.println("Enter the amount:");
                                long a=sc.nextLong();        
                                System.out.println("Please enter your PIN");
                                int pin3=sc.nextInt();
                                o.transfer(nt,acc_number,a,pin3);
                                break;
                            case 4:
                                System.out.println("Please enter your PIN");
                                int pin=sc.nextInt();
                                o.account_details(acc_number,pin);
                                break;
                            case 5:
                                System.out.println("Log out was successful\n---Thank You---");
                                exitloop=true;
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                }
                else
                {
                    System.out.println("Enter account number is not found");
                }

            }
            
            else
            {
                System.out.println("Entered Name or Password is invalid");
            }
            
            con.close();
        } 
        catch(Exception e)
        {
            System.out.println("Connection was not estalished");
        }
   
    }

}


