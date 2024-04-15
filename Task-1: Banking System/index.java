import java.util.*;
class index
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("----Welcome to the Banking system----");
        boolean exitloop=false;
        while(!exitloop)
        {
            System.out.println("Please select from below options : \n\n 1.Login \n 2.Register \n 3.Exit\n");
            System.out.println("Please enter the option number");
            int opt=sc.nextInt();
            sc.nextLine();
            switch(opt)
            {
                case 1:
                    System.out.println("Please enter your username");
                    String n=sc.nextLine();
                    System.out.println("Please enter your password");
                    String pass=sc.nextLine();
                    login l=new login(n,pass);
                    break;
                case 2:
                    
                    System.out.println("Please enter your username");
                    String n1=sc.nextLine();
                    System.out.println("Please enter your password");
                    String pass1=sc.nextLine();
                    System.out.println("Please enter the type of account you need");
                    String type=sc.nextLine();
                    register r=new register(n1,pass1,type);
                    break;
                case 3:
                    System.out.println("Visit Again\n---Thank You---");
                    exitloop=true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}