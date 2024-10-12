package mycart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShoppingCartDB myCart = new ShoppingCartDB();
        String ip = "";
        String user = "";
        System.out.println("Welcome to your shopping cart!");
        
        while (!ip.equals("end")) {
            Scanner scan = new Scanner(System.in);
            String command = scan.next();
            ip = command.toLowerCase();
            String input = scan.nextLine().trim();

            switch (command) {
                case "list":
                    myCart.list(myCart.getCart());
                    break;
                    
                case "add":
                    myCart.add(myCart.getCart(), input);
                    break;
                    
                case "delete":
                    int idx = Integer.parseInt(input.trim());
                    myCart.delete(myCart.getCart(), idx);
                    break;

                case "login":
                    try {
                        myCart = new ShoppingCartDB(input);
                        System.out.printf("my cart filename: %s\n", myCart.getFile());
                        myCart.login(input);
                        user = input;
                        break;
                    } catch(FileNotFoundException e) {
                        System.out.println("Login unsuccessful, file not found");
                        break;
                    }
                    catch(IOException e) {
                        System.out.println("Login unsuccessful, input / output error");
                        break;
                    }
                
                case "save":
                    myCart.save(user, myCart.getCart());
                    break;
                
                case "users":
                    myCart.users();
                    break;
                    
                default:
                    System.out.println("Invalid command\nPlease reenter");
                    scan.close();
            }
        }
    }
}