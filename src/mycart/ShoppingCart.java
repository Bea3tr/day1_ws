package mycart;

import java.util.*;

public class ShoppingCart {

    private String user;
    private String file;
    private ArrayList<String> cart = new ArrayList<String>();

    public String getFile() {return this.file;}
    public void setFile(String file) {this.file = file;}

    
    public ArrayList<String> getCart() {return this.cart;}
    public void setCart(ArrayList<String> cart) {this.cart = cart;}

    public void setUser(String user) {this.user = user;}
    public String getUser() {return this.user;}

    public ShoppingCart() {}
    public ShoppingCart(String user) {
        this.user = user;
        this.file = user + ".txt";
    }
    
    public void list(ArrayList<String> cart) {
        if (cart.isEmpty())
                System.out.println("Your cart is empty");
        
        else {
            for (int i = 0; i < cart.size(); i++){
                System.out.printf("%d. %s\n", i+1, cart.get(i));
            }
        }
    }
    
    public void add(ArrayList<String> cart, String items) {
        // separate items into an array
        String[] arr = items.replaceAll("\\s", "").split(",");
        // convert array to list
        ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));
        for (String item : list){
            String it = item.toLowerCase();
            if (cart.contains(item)){
                System.out.printf("You have %s in your cart\n", it);
            }
            else{
                cart.add(it);
                System.out.printf("%s added to cart\n", it);
            }
        }
    }
    
    public void delete(ArrayList<String> cart, int index){
        if (index > cart.size()){
            System.out.println("Incorrect item index");
        }
        else{
            for (int i=0; i < cart.size(); i++) {
                if (i == index-1){
                    String item = cart.get(i);
                    cart.remove(item);
                    System.out.printf("%s removed from cart\n", item);
                }   
            }
        }
    }
}
