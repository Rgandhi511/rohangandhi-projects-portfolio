import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class ExpenseTracker {
    public static void main(String[] args) {

        ArrayList<Expense> Expenses = new ArrayList<>();
        
        try(Scanner scanner = new Scanner(System.in)){
        
        System.out.println("            ");
        System.out.println("Welcome to your Expense Tracker");
        System.out.println("Wanna add an expense?");
        System.out.println("y/n?   ");
        String choice = scanner.nextLine();
        

        while(choice.equalsIgnoreCase("y")) {
            //adding just the braces allows the code to not run "add another" so it looks better
            addExpense(scanner, Expenses);//this takes the addExpense from below and allows the code to let you track a new expense
        System.out.println("Add another?");
        System.out.println("y/n?   ");
            choice = scanner.nextLine();
        }

        try{
            PrintWriter savedExpenses = new PrintWriter(new FileWriter("saved_expenses.txt", false));
            savedExpenses.write("This is the start of your saved expenses!");
            savedExpenses.write("\n");
            for(int i = 0; i < Expenses.size(); i++){//allows us to print all the expenses used. 
                savedExpenses.println(Expenses.get(i));
            }
            savedExpenses.write("This is the end of your saved expenses!");
            savedExpenses.close();
        }catch(IOException e){
            e.printStackTrace();//get a friendly error
        }

        System.out.println("All Expenses:");
        for(int i = 0; i < Expenses.size(); i++){//allows us to print all the expenses used. 
            System.out.println(Expenses.get(i));

        }
        System.out.println("        ");




        scanner.close();
    }
    

    }

    static class Expense{

        Integer id;//creating the variables
        String description;
        String category;
        BigDecimal amt;
        LocalDate date;
    
        public Expense(int id, String description, String category, BigDecimal amt, LocalDate date){
            this.id = id;//helps label this into the correct place 
            this.description = description;
            this.category = category;
            this.amt = amt;
            this.date = date;
        }

        @Override //allows me to replace the unique that is printed with my own print
        public String toString(){
            return "{Expense: " + id + "|\"" + description + "\"|" + category + "|$" + amt + "|" + date + "|}";
        }

        
    }
        
private static void addExpense(Scanner scanner, ArrayList<Expense> expenses){
    //we are passing the existing scanners and arraylist so that it can rerun the code if they ask to track another expense

        int id = expenses.size() + 1;
        System.out.println("Expense #" + id);
        


        String description;
        do { 
            System.out.println("What did you buy or do?   ");
            description = scanner.nextLine();
        if (!description.matches("[a-zA-Z ]+")){
            System.out.println("Sorry that response is invalid.");
            System.out.println("Please use a valid response");
        }
        } while (!description.matches("[a-zA-Z ]+"));
        //when doing the doing the description command line if the input of the description includes numbers it will say invalid
        //then will make you try again till it works


        String category;
        do{
        System.out.println("What category would you like to place it in?   ");
        category = scanner.nextLine();

        if(!category.matches("[a-zA-Z ]+")){
            System.out.println("Sorry that response is invalid.");
            System.out.println("Please use a valid response");
        }
        } while (!category.matches("[a-zA-Z ]+"));
        
        System.out.println("How much was it?");
        BigDecimal amt = scanner.nextBigDecimal();
        scanner.nextLine();

        
        System.out.println("When did you purchase it?   ");
        String dateInput = scanner.nextLine();//scanner cannot take the values of the date 
        LocalDate date;//its declared so we dont keep declaring it 
        if(dateInput.equals("today") || dateInput.equals("Today")){
            date = LocalDate.now();//assigns value 
            //if I did local date date = LocalDate.now() it would be assigned within the if statement
            System.out.println(LocalDate.now());
        }else if (dateInput.equals("yesterday") || dateInput.equals("Yesterday")){
            date = LocalDate.now().minusDays(1);
            System.out.println(LocalDate.now().minusDays(1));
        }else{
            date = LocalDate.parse(dateInput);
            
        }

        Expense e = new Expense(id, description, category, amt, date);//this constructs an object
        expenses.add(e);//this STORES the data into the arraylist we created
        System.out.println(e);
    
    }
   
    }



