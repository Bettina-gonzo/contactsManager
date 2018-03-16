import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class addContact {
    Scanner input = new Scanner(System.in); // scanning for user input
    UpdateContact update = new UpdateContact();
    ContactsMenu menu = new ContactsMenu();
    Path path = Paths.get("src/contacts.txt");

    public void getAddContact() {
        try {
            if (Files.exists(path)) {
                System.out.println("Please enter a new First, and Last name:"); //prompt user to enter first name
                String contactName = String.format("\"%s %s\",", input.next(), input.next()); // gathering both first and last name into single variable
                List<String> lines = Files.readAllLines(path);
                for(String line: lines){
                    if(line.contains(contactName)){
                        System.out.println("Contact is already in our DB.  Would you like to update or replace it? (y/n)");
                        if(input.next().toLowerCase().startsWith("y")){
                            update.getContactUpdate(contactName);
                        } else menu.getOpenMenu();
                    }
                }
                System.out.println("Please enter a new phone number as: (xxx-xxx-xxxx)");
                String fullContactName = String.format("%s\"%s\"", contactName, input.next());
                List<String> newlines = new ArrayList<>();
                newlines.add(fullContactName);
                System.out.printf("%s was successfully added\n", fullContactName);
                Files.write(path, newlines, StandardOpenOption.APPEND);
            }
        } catch (java.io.IOException IOException) {
            System.err.println("Exception " + IOException); //exception if path is not found (contact cannot be added)
        }
    }
}
