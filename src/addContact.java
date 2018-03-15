import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class addContact {
    Scanner input = new Scanner(System.in); // scanning for user input
    UpdateContact update = new UpdateContact();
    ContactsMenu menu = new ContactsMenu();
    Path path = Paths.get("src/ContactsArray.java");
    public void getAddContact() {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path); //if file does not exist, then we will create a file
            } else if (Files.exists(path)) {
                System.out.println("Please enter new First name:"); //prompt user to enter first name
                String userNewFirst = input.next();
                System.out.println("Please enter new Last name:"); //prompt user to enter last name
                String userNewLast = input.next();
                String fullContactName = String.format("%s %s", userNewFirst, userNewLast); // gathering both first and last name into single variable
                CheckContact(fullContactName,userNewFirst,userNewLast);
                System.out.println("Please enter new phone number: "); //prompting user for new phone number
                String userNewNumber = input.next();
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8); //reading all lines in file
                int position = 3;
                String extraline = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNewFirst, userNewLast, userNewNumber);
                System.out.println(extraline);
                lines.add(position, extraline);
                Files.write(path, lines, StandardCharsets.UTF_8);
//                    showContact();
//                    System.exit(0) ; //exiting program
            }
        } catch (java.io.IOException IOException) {
            System.err.println("Exception " + IOException); //exception if path is not found (contact cannot be added)
        }
    }


    public void CheckContact(String findthiscontact, String userNewFirst, String userNewLast) throws IOException {
        //checking if contact already exists logic
        for(Contacts contact: ContactsArray.findAll()) {
            if (contact.getFullName().equalsIgnoreCase(findthiscontact)) { // if the contact the user inserted already exists, then this will run
                System.out.printf("\n%s %s\n\n", contact.getFullName(), "is already in our DB. Do you want to overwrite it? (y/n)"); // will prompt user to see if they want to overwrite the contact
                if(input.next().toLowerCase().startsWith("y")){ // if yes run this
                    System.out.println("Overwriting....");
                    update.getContactUpdate(findthiscontact); // calling contactUpdate method using fullContactName variable as a parameter
                    //the contactUpdate method will delete user name instance from the file
                    //once deleted, we will add the updated user to file list
                    System.out.println("Please enter new phone number: ");
                    String userNewNumber = input.next();
                    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8); //reading all lines from file
                    int position = 3; // default placement position for new user
                    String extraline = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNewFirst, userNewLast, userNewNumber); //inserting new line on the third line
                    System.out.println(extraline);
                    lines.add(position, extraline); //adding new contact in default line and format
                    Files.write(path, lines, StandardCharsets.UTF_8); //writing new contact to file
                    menu.getOpenMenu(); //display main menu
                }
                else menu.getOpenMenu();//if user doesn't want to overwrite, go back to main menu
            }
        }
    }
}
