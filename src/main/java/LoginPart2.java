/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author Donnell
 */
import java.util.Scanner;
public class LoginPart2 {
    
    //Registers a user by validating their username and password
    public static String registerUser(String username, String password){
        boolean isUsernameValid = checkUsername(username);
        boolean isPasswordValid = checkPasswordComplexity(password);
        
        if (isUsernameValid && isPasswordValid){
            return "Registration is a success";
            
        } else if (!isUsernameValid && !isPasswordValid){
            return "username is not correctly formatted, please ensure that the username contains an underscore and has 5 characters.";
                                
        }else {
            return "Password is not correctly formatted, plewase ensure that the password contains at least 8 characters, a capital letter and a number";
        }
    }

    // Validates the format of the username.
    public static String validateUsername (String username) {
        if (username.contains ("_") && username.length() <= 5){
            return "Username successfully captured";
            
        }else{
            return"username is not correctly formatted, please ensure that the username contains an underscore and has 5 characters.";
        }
    }
   
    // Validates the complexity of the password.
    public static String validatePassword(String password) {
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial= password.matches(".*[^a-zA-Z0-9].*");
        boolean longEnough = password.length() >= 8;
        
        if (hasUppercase && hasDigit && hasSpecial && longEnough) {
            return "Password successfully captured";
        }else {
            return "Password is not correctly formatted, plewase ensure that the password contains at least 8 characters, a capital letter and a number.";
        }
    }
    
    //Checks if the username is valid.
    public static boolean checkUsername (String username){
        return username.contains("_") && username.length() <=5;
    }
    //Checks if the password meets the required complexity.
    public static boolean checkPasswordComplexity (String password){
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial= password.matches(".*[^a-zA-Z0-9].*");
        boolean longEnough = password.length() >= 8;
        return hasUppercase && hasDigit && hasSpecial && longEnough;
    }
     
    // Cell phone number check eg +27638227329
    public static boolean checkCellNumber(String phoneNumber){
        String regex = "^\\+27\\d{1,10}$"; //// South African phone numbers start with +27 followed by 9 digits
        String digitOnly = phoneNumber.replaceAll("^[\\d]", "");
        return phoneNumber.matches(regex) && digitOnly.length() <=13;
    }
 
    //Attempts to log in a user by comparing entered credentials with registered ones.
    public static boolean loginUser(String registeredUsername, String registeredPassword, String enteredUsername, String enteredPassword){
        return registeredUsername.equals(enteredUsername) && registeredPassword.equals(enteredPassword);
    }
    
    //Returns the login status message based on the entered credentials.
    public static String returnLoginStatus(String registeredUsername, String registeredPassword, String enteredUsername, String enteredPassword, String firstname, String lastname){
        if (loginUser(registeredUsername, registeredPassword, enteredUsername, enteredPassword)){
            return "***Login Successful***\nWelcome Back,"+ firstname + " " + lastname + "!";
        }else{
            return "Login failed: Username or password wrong";
        }
    }
    
    //Main method to run the login and registration process.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter your First Name");
        String firstname = input.nextLine();
        
        System.out.println("Enter your Last Name");
        String lastname = input.nextLine();
        
        //Username input with validation
        String username;
        while (true) {
            System.out.println("Enter your username: ");
            username = input.nextLine();
            
            if (checkUsername(username)){
                System.out.println("Username Successfully Captured.");
                break;
                               
            }else{
                System.out.println("username is not correctly formatted, please ensure that the username contains an underscore and has 5 characters");
            }
        }
       
        // Password input with validation
        String password;
       while (true) {
           System.out.println("Enter your Password:");
           password = input.nextLine();
           if (checkPasswordComplexity(password)){
               System.out.println("Password captured successfully.");
               break;
               
           }else{
               System.out.println("Password is not correctly formatted, plewase ensure that the password contains at least 8 characters, a capital letter and a number.");
           }
       } 
       //Cell phone number input with validation
       String phoneNumber;
       while (true){
           System.out.println("Enter your cell number in international code:");
           phoneNumber = input.nextLine();
           if (checkCellNumber(phoneNumber)){
                  System.out.println("Cell phone successfully added");
                  break;                            
           }else {
               System.out.println("Cell phone number incorrectly formatted or doe not contain international code");
           }                       
       }
       
       //Registration confimation
       System.out.println(registerUser(username, password));
       
       System.out.println("\n---LOGIN SECTION");
       
       while (true) {
           System.out.println("ENTER YOUR USERNAME TO LOGIN");
           String enteredUsername = input.nextLine();
          
           System.out.println("ENTER YOUR PASSWORD TO LOGIN");
           String enteredPassword = input.nextLine();  
           
           if (loginUser(username, password, enteredUsername, enteredPassword)){
               System.out.println("----LOGIN SUCCESSFUL----");
               System.out.println("Welcome back, " + firstname + " " + lastname + "!,");
               break; //Exit the login loop
           }else{
               System.out.println("Login failed: username or password incorrect");
               System.out.println("Please try again.\n");
           }
       }
    }
    
}
