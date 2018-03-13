import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class contactsManagerTest {

    public static Contacts[] contactNew = ContactsArray.findAll(); // calling array object of contacts

    public static void main(String[] args) { // main method
        openMenu();
    }

    private static void openMenu(){ // menu items method
        Scanner input = new Scanner(System.in);
        int userInput;

        do{
            System.out.println("\nWelcome to the Contacts Manager book!\n\n" + //display menu
                    "1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5):\n");

            userInput = input.nextInt(); //input for user option

            switch(userInput){ //switch case for user options
                case 1:
                    showContact(); //user entered 1, will display all contacts from array
                    break;
                case 2:
                    addContact(); //user entered 2, will allow user to add contact to file. Will inform user if contact is already created.
                    break;
                case 3:
                    contactSearch(); //user entered 3, will allow user to search for contact already listed.
                    break;
                case 4:
                    contactDelete(); //user entered 4, will allow user to delete a contact
                    break;
                case 5:
                    System.out.println("Thank you and please come back again!"); //user entered 5, will exit program
                    System.exit(0);
            }
            System.out.println("\nEnter 'y' to continue.");
            if(input.next().toLowerCase().startsWith("y")){  // if user decides to continue, go back to main menu, if not exit.
                openMenu();
            } else
                System.exit(0);
        }while(true);
    }

    private static void showContact(){
        try {
            System.out.printf("%-23s|%15s\n", "Name", "Phone number"); // displaying tittle menu
            System.out.printf("%s\n", "---------------------------------------");
            for (Contacts contact : contactNew)
                System.out.printf("%-20s   |   %s\n", contact.getFullName(), contact.getPhoneNumber()); // pulling specific contact from array
        } catch (NoSuchFieldError  NosuchFile){
            System.err.println("Exception " + NosuchFile); // exception message for file not found
        }
    }

    public static void addContact() {
        Scanner input = new Scanner(System.in); // scanning for user input
        try {
            Path path = Paths.get("src/ContactsArray.java"); //defining path for file and file name
            if (!Files.exists(path)) {
                Files.createFile(path); //if file does not exist, then we will create a file
            } else if (Files.exists(path)) {
                System.out.println("Please enter new First name:"); //prompt user to enter first name
                String userNewFirst = input.next();
                System.out.println("Please enter new Last name:"); //prompt user to enter last name
                String userNewLast = input.next();
                String fullContactName = String.format("%s %s", userNewFirst, userNewLast); // gathering both first and last name into single variable

//                checking if contact already exists logic
                for(Contacts contact: contactNew) {
                    if (contact.getFullName().equalsIgnoreCase(fullContactName)) { // if the contact the user inserted already exists, then this will run
                        System.out.printf("\n%s %s\n\n", contact.getFullName(), "is already in our DB. Do you want to overwrite it? (y/n)"); // will prompt user to see if they want to overwrite the contact

                        if(input.next().toLowerCase().startsWith("y")){ // if yes run this
                            System.out.println("Overwriting....");
                            contactUpdate(fullContactName); // calling contactUpdate method using fullContactName variable as a parameter
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
                            openMenu(); //display main menu
                        }
                        else openMenu();//if user doesn't want to overwrite, go back to main menu
                    }
                }
                    System.out.println("Please enter new phone number: "); //prompting user for new phone number
                    String userNewNumber = input.next();
                    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8); //reading all lines in file
                    int position = 3;
                    String extraline = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNewFirst, userNewLast, userNewNumber);
                    System.out.println(extraline);
                    lines.add(position, extraline);
                    Files.write(path, lines, StandardCharsets.UTF_8);
                    System.exit(0); //exiting program
            }
        } catch (IOException IOException) {
            System.err.println("Exception " + IOException); //exception if path is not found (contact cannot be added)
        }
    }

    public static void contactSearch(){  //method for finding the contact in file
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a full name to search: "); //prompting user to enter full name for search
        String userName = input.nextLine();
        for(Contacts contact: contactNew)
            if (contact.getFullName().equalsIgnoreCase(userName)){ //if the contact exists in the array then it will run
                System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber()); //will print out all contact info in correct format
            }
    }


    public static void contactDelete(){ //method for deleting existing contact
        Scanner input = new Scanner(System.in);
        String line; //variable for individual line
        String userInput = ""; //empty input
        try {
            Path path = Paths.get("src/ContactsArray.java"); // creating path for file and file name
            BufferedReader file = new BufferedReader(new FileReader("src/ContactsArray.java")); //
            if (!Files.exists(path)) {
                System.out.println("Cannot execute your request, file doesn't exist"); //cannot delete from file if it does not exist
            } else if (Files.exists(path)) {
                System.out.println("Please enter a first name to delete:"); //prompting user to input first name of contact to delete
                String userNameDelete = input.nextLine();
                System.out.println("Please enter a last name to delete"); //prompting user to input last name of contact to delete
                String userLastDelete = input.nextLine();

                for(Contacts contact: contactNew) // searching through array for contacts
                if (contact.getFullName().equalsIgnoreCase(String.format("%s %s",userNameDelete, userLastDelete))){ //getting existing contact to see if it matches user input of first and last name
                    System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber()); //displays contact that will be deleted

                    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8); //
                    String lineToDelete = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNameDelete, userLastDelete, contact.getPhoneNumber()); //variable for line to delete with format attached

                    while ((line = file.readLine()) != null) //
                    {

                        if (line.contains(lineToDelete)) //if the line is equal to the line the user is seeking to delete, this will run
                        {
                            line = "";
                            System.out.println("Line deleted."); //informing user that line was deleted
                        }
                        userInput += line + '\n'; //creating break in line
                    }
                    FileOutputStream File = new FileOutputStream("src/ContactsArray.java");
                    File.write(userInput.getBytes());
                    file.close();
                    File.close();
                }
            }
        } catch (IOException IOException) {
            System.err.println("Exception " + IOException);
        }
    }
 // EOF -> End of file

    public static void contactUpdate(String updateContact){
        String line;
        String userInput = "";
        try {
            Path path = Paths.get("src/ContactsArray.java");
            BufferedReader file = new BufferedReader(new FileReader("src/ContactsArray.java"));
            if (!Files.exists(path)) {
                System.out.println("Cannot execute your request, file doesn't exist");
            } else if (Files.exists(path)) {
                for(Contacts contact: contactNew)
                    if (contact.getFullName().equalsIgnoreCase(updateContact)){
                        System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber());

                        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                        String lineToDelete = String.format("%20s(\"%s\",\"%s\"),", "new Contacts", updateContact, contact.getPhoneNumber());

                        while ((line = file.readLine()) != null)
                        {
                            //System.out.println(line);
                            if (line.contains(lineToDelete))
                            {
                                line = "";
                            }
                            userInput += line + '\n';
                        }
                        FileOutputStream File = new FileOutputStream("src/ContactsArray.java");
                        File.write(userInput.getBytes());
                        file.close();
                        File.close();
                    }
            }
        } catch (IOException IOException) {
            System.err.println("Exception " + IOException);
        }
    }
}

