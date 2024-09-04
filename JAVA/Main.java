package org.example;

import java.util.*;
import java.util.Scanner;
import java.time.*;


// 1. TIC TAC TOE GAME using fundamentals.

class TicTacToe
{
    static char[][] board;
    public TicTacToe()
    {
        board=new char[3][3];
        initBoard();
    }
    void initBoard(){
        for(int i=0;i< board.length;i++)
        {
            for(int j=0; j<board[i].length;j++)
            {
                board[i][j]=' ';

            }
        }
    }
    static  void displayBoard()
    {
        System.out.println("-------------");
        for (char[] chars : board) {
            System.out.print("| ");
            for (int j = 0; j < chars.length; j++) {
                System.out.print(chars[j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    static void placeMark(int row,int col,char mark){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            board[row][col]= mark;
        }
        else{
            System.out.println("Invalid Position!!");

        }
    }
    static boolean checkColumnWin()
    {
        for(int j=0;j<=2;j++)
        {
            if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j]== board[2][j])
            {
                return true;
            }
        }
        return false;
    }
    static boolean checkDaigonalWin(){
        if(board[0][0]!= ' ' && board[0][0] == board[1][1] && board[1][1]== board[2][2]
                || board[0] [2] !=' ' && board[0][2] == board[1][1] && board[1][1]== board[2][0] )
        {
            return true;
        }
        else {
            return false;
        }
    }
    static boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }
    static boolean isDraw(){
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }
}
abstract class Player{
    String name;
    char mark;
    abstract void makeMove();

    static boolean isValidMove(int row,int col)
    {
        if(row>=0 && row<=2 && col>=0 && col<=2)
        {
            return TicTacToe.board[row][col] == ' ';
        }
        return false;
    }
}
class HumanPlayer extends Player{

    HumanPlayer(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner sc= new Scanner(System.in);
        int row;
        int col;
        do{
            System.out.println("Enter the row and Column");
            row =  sc.nextInt();
            col = sc.nextInt();
        }while (!isValidMove(row, col));
        TicTacToe.placeMark(row,col,mark);
    }
}

class AIPlayer extends Player{
    AIPlayer(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        Scanner sc= new Scanner(System.in);
        int row;
        int col;
        do{
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);

        }while (!isValidMove(row, col));
        TicTacToe.placeMark(row,col,mark);
    }
}

public class Main {
    public static void main(String[] args) {
        TicTacToe t= new TicTacToe();

        HumanPlayer p1 = new HumanPlayer("Demon",'X');
        AIPlayer p2 = new AIPlayer("AI",'O');
        Player cp;
        cp=p1;

        while(true)
        {
            System.out.println(cp.name + "'s Turn !!");
            cp.makeMove();
            TicTacToe.displayBoard();
            if(TicTacToe.checkColumnWin() || TicTacToe.checkDaigonalWin() || TicTacToe.checkRowWin())
            {
                System.out.println(cp.name +" has won!!");
                break;
            } else if (TicTacToe.isDraw()) {
                System.out.println("DRAW!!");
                break;
            } else {
                if(cp==p1){
                    cp = p2;
                }
                else{
                    cp = p1;
                }
            }
        }

    }

}





// 2. ATM SIMULATION using oops.


class ATMSimulation{
    float balance; // variable to store balance.
    float pin=1234; // variable to store pin.
    Scanner s=new Scanner(System.in);
    List<String> transaction=new ArrayList<>(); // arraylist is type of collection available in java collections to store dynamically the elements in an array format.


    // method to verify the pin and give access.
    public void checkPin(int pin){
        if(this.pin!=pin){
            System.out.println("Please enter a valid pin!! ");
            int pin1=s.nextInt();
            checkPin(pin1);
        }
        else{
            menu(); // method calling
        }

    }
    // method menu to provide options.
    public void menu(){
        System.out.println("The menu is as follows:");
        System.out.println("1. Balance Enquiry.");
        System.out.println("2. Amount Withdraw.");
        System.out.println("3. Amount Deposit");
        System.out.println("4. Transaction History");
        System.out.println("5. Change Pin");
        System.out.println("6. Exit");
        System.out.println("Enter your choice:");
        int choice=s.nextInt();
        // to call required options using switch case.
        switch (choice){
            case 1: balanceEnquiry();
                break;
            case 2: amountWithdraw();
                break;
            case 3: amountDeposit();
                break;
            case 4: transactionHistory();
                break;
            case 5: changePin();
                break;
            case 6: exits();
                break;
            default: System.out.println("Enter a valid choice.."); // if input doesn't match any then default will execute.
        }
    }



    // method to view the current balance.
    private void balanceEnquiry(){
        System.out.println("Current balance is:"+balance);
        addTransaction("Balance Enquiry",balance);
        menu();
    }


    // method to withdraw amount.
    private void amountWithdraw(){
        // check for the current amount if withdraw is valid or not.
        if(balance<=0){
            System.out.println("Please add balance to make withdrawal.");
        }
        else {
            System.out.println("Enter the required amount:");
            float withdraw = s.nextInt();
            if(withdraw<balance){
                balance = balance - withdraw;// withdrawing amount.
                System.out.println("Withdraw is successful.");
                addTransaction("Withdraw",withdraw);
                System.out.println("Do you want to display your balance yes/no!");
                String s1 = s.next();
                if (Objects.equals(s1, "yes")) {
                    balanceEnquiry();
                }
            }
            else{
                System.out.println("Insufficient balance to make this withdrawal!!");
            }
        }
        menu();// again display the menu.
    }


    // method to deposit money.
    private void amountDeposit(){
        System.out.println("Enter the amount:");
        float deposit=s.nextInt();
        balance=balance+deposit;
        System.out.println("Deposit is successful.");
        addTransaction("Deposit",deposit);
        System.out.println("Do you want to display your balance yes/no!");
        String s1=s.next();
        if(Objects.equals(s1, "yes")){
            balanceEnquiry();
        }
        menu();
    }


    // to view transaction history i.e, all previous transactions.
    private void transactionHistory(){
        System.out.println("Your Transaction History:");
        for( String string: transaction){
            System.out.println(string);
        }

    }


    // method to change pin.
    // if initial pin is matched then  only one can change the pin
    // new pin should be entered correctly for two times to modify the existing pin!!
    private void changePin() {
        System.out.println("Enter the current password:");
        int pass = s.nextInt();
        if (pass == pin) {
            System.out.println("Enter new pin:");
            int newpass = s.nextInt();
            System.out.println("Enter new pin again:");
            int newpass2 = s.nextInt();
            if (newpass == newpass2) {
                pin=newpass2;
                System.out.println("Pin changed successfully.");
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now().withNano(0);
                transaction.add("Last PIN Changed on:" + date + " at " + time);
                menu();
            } else {
                System.out.println("Both are not matching!!!");
                System.out.println("Would you like to try again?");
                String s2 = s.nextLine();
                if (s2.equals("yes")) {
                    changePin();
                }
            }
        }
        else
        {
            System.out.println("Both are not matching!!!");
            System.out.println("Would you like to try again?");
            String s2 = s.nextLine();
            if (s2.equals("yes")) {
                changePin();
            }
        }
    }

    // exit from the application.
    private void exits () {
        System.out.println("Exited Successfully. Have a nice day..");
    }


    // method to add transactions to the list one by one after calling of this method from above methods.
    private void addTransaction (String type,float amount){
        LocalDate date = LocalDate.now(); // it is used to create an instance of date at that particular call.
        LocalTime time = LocalTime.now().withNano(0); // it is used to create an instance of time at that particular call.
        transaction.add(type + " on " + date + " at " + time + ": " + amount); // adding the elements to list
    }

}

public class ATM {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);  // Scanner object to take the input from user.
        ATMSimulation a = new ATMSimulation(); // object creation for accessing the class members.
        System.out.println("Enter your Pin:"); // message to ask user to enter pin.
        int pin = s.nextInt(); // variable to store the user entered pin;
        a.checkPin(pin); // calling of class method using object reference.
    }
}