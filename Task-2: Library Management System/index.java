import java.util.*;
class index
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("---Welcome to my Library Management System");        
        System.out.println("Enter user name");
        String n=sc.nextLine();
        System.out.println("Enter user password");
        String pass=sc.nextLine();
        operations o=new operations(n,pass);
        if(o.check_details())
        {
            boolean exitloop=false;
            while(!exitloop)
            {
                System.out.println("\nPlease select the options\n 1.Books available\n 2.Borrow Books\n 3.Return Books\n 4. Exit");
                int choice=sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1:
                        System.out.println("\nEnter the title of book you like to read");
                        String t=sc.nextLine();
                        if(!o.books_available(t))                       
                        {
                            System.out.println("Sorry! Books are not available");
                        }
                        break;
                    case 2:
                        System.out.println("\nEnter the title of book you like to borrow : ");
                        String t1=sc.nextLine();
                        borrowed_books bb=new borrowed_books();
                        if(bb.borrow(t1))
                        {
                            System.out.println("Your desired is available please collect it");
                        } 
                        else
                        {
                            System.out.println("\nSorry! Your desired book is not available");
                        }
                        break;
                    case 3:
                        System.out.println("\nEnter the title of book you like to return : ");
                        String r=sc.nextLine();
                        borrowed_books bb1=new borrowed_books();
                        if(bb1.return_books(r))
                        {
                            System.out.println("\n   Thank You for returning book");
                        } 
                        else
                        {
                            System.out.println("\nBook updation failed");
                        }
                        break;
                    case 4:      
                        System.out.println("\n    Visit Again\n ----Thank You----");             
                        exitloop=true;
                        break;
                    default:
                        System.out.println("\nInvalid choice");
                }
            }

        }
    }
}