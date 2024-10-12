package mycart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;

public class ShoppingCartDB extends ShoppingCart {

    private boolean login = false;
    private ArrayList<String> cart = new ArrayList<String>();

    public boolean isLogin() {return login;}

    public ShoppingCartDB() {}
    public ShoppingCartDB(String user) {
        setUser(user);
        setFile(user + ".txt");
        setCart(cart);
    }

    public void login(String name) throws FileNotFoundException, IOException {

        login = true;

        // Check database to see if existing customer
        // If exist read file, if not write file
        Path p = Paths.get("cartdb/" + name + ".txt");

        if (p.toFile().exists()) {
            System.out.printf("File %s exists\n", p.toFile());
            Reader loaded = new FileReader(p.toFile());
            BufferedReader brl = new BufferedReader(loaded);

            // Check if file is empty
            if (isEmpty(brl))
                System.out.printf("%s, your cart is empty\n", name);
            else {
                System.out.printf("%s, your cart contains the following items\n", name);
                BufferedReader brt = new BufferedReader(new FileReader(p.toFile()));
                String item = "";
                int idx = 0;
                while (null != item) {
                    item = brt.readLine();
                    if (null == item)
                        break;
                    idx++;
                    if (idx > 1) {
                        // Update cart of user
                        cart.add(item);
                        System.out.printf("%d. %s\n", idx-1, item);
                    }
                }
                brt.close();
            }
        } else {
            System.out.printf("%s, your cart is empty\n", name);
            Writer writer = new FileWriter("cartdb/" + name + ".txt");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write("cartdb/" + name + ".db");
            bw.close();
        }
    }

    private boolean isEmpty(BufferedReader br) throws IOException{
        String cart = "";
        int lines = 0;
        while (null != cart) {
            cart = br.readLine();
            if(null == cart)
                break;
            lines++;
        }
        br.close();
        if (lines > 1)
            return false;
        return true;
    }

    public void save(String name, ArrayList<String> cart) {

        if(!login) 
            System.out.println("Please login before saving");
        else {
            try {
                // Overwrite previous file to avoid repetition
                Writer saved = new FileWriter("cartdb/" + name + ".txt");
                BufferedWriter bSaved = new BufferedWriter(saved);  
                bSaved.write("cartdb/" + name + ".db");
                for(String item : cart) {
                    bSaved.write("\n" + item);
                }
                bSaved.close();
                System.out.println("Your cart has been saved");
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

    public void users() {
        System.out.println("The following users are registered");
        File db = new File("cartdb/");
        File[] dbs = db.listFiles();
        int idx = 0;
        for(File d : dbs) {
            idx++;
            System.out.printf("%d. %s", idx, d);
        }
    }
}
