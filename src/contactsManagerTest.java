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

    public static Contacts[] contactNew = ContactsArray.findAll();

    public static void main(String[] args) {
        openMenu();
    }

    private static void openMenu(){
        Scanner input = new Scanner(System.in);
        int userInput;

        do{
            System.out.println("\nWelcome to the Contacts Manager book!\n\n" +
                    "1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5):\n");

            userInput = input.nextInt();

            switch(userInput){
                case 1:
                    contactNew();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    contactSearch();
                    break;
                case 4:
                    contactDelete();
                    break;
                case 5:
                    System.out.println("Thank you and please come back again!");
                    System.exit(0);
            }
            System.out.println("\nEnter 'y' to continue.");
            if(input.next().toLowerCase().startsWith("y")){
                openMenu();
            } else
                System.exit(0);
        }while(true);
    }

    private static void contactNew() {
        System.out.printf("%-23s|%15s\n", "Name", "Phone number");
        System.out.printf("%s\n", "---------------------------------------");
        for(Contacts contact: contactNew)
            System.out.printf("%-20s   |   %s\n", contact.getFullName(), contact.getPhoneNumber());
    }

    public static void addContact() {
        Scanner input = new Scanner(System.in);
        try {
            Path path = Paths.get("src/ContactsArray.java");
            if (!Files.exists(path)) {
                Files.createFile(path);
            } else if (Files.exists(path)) {
                System.out.println("Please enter new First name:");
                String userNewFirst = input.next();
                System.out.println("Please enter new Last name:");
                String userNewLast = input.next();
                String fullContactName = String.format("%s %s", userNewFirst, userNewLast);

                for(Contacts contact: contactNew) {
                    if (contact.getFullName().equalsIgnoreCase(fullContactName)) {
                        System.out.printf("\n%s %s\n\n", contact.getFullName(), "is already in our DB. Do you want to overwrite it? (y/n)");

                        if(input.next().toLowerCase().startsWith("y")){
                            System.out.println("Overwriting....");
                            contactUpdate(fullContactName);
                            System.out.println("Please enter new phone number: ");
                            String userNewNumber = input.next();
                            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                            int position = 3;
                            String extraline = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNewFirst, userNewLast, userNewNumber);
                            System.out.println(extraline);
                            lines.add(position, extraline);
                            Files.write(path, lines, StandardCharsets.UTF_8);
                            openMenu();
                        }
                        else openMenu();
                    }
                }

                    System.out.println("Please enter new phone number: ");
                    String userNewNumber = input.next();
                    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                    int position = 3;
                    String extraline = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNewFirst, userNewLast, userNewNumber);
                    System.out.println(extraline);
                    lines.add(position, extraline);
                    Files.write(path, lines, StandardCharsets.UTF_8);
            }
        } catch (IOException IOException) {
            System.err.println("Exception " + IOException);
        }
    }

    public static void contactSearch(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a full name to search: ");
        String userName = input.nextLine();
        for(Contacts contact: contactNew)
            if (contact.getFullName().equalsIgnoreCase(userName)){
                System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber());
            }
    }

//    public static void contactFind(String contactName){
//
//    }

    public static void contactDelete(){
        Scanner input = new Scanner(System.in);
        String line;
        String userInput = "";
        try {
            Path path = Paths.get("src/ContactsArray.java");
            BufferedReader file = new BufferedReader(new FileReader("src/ContactsArray.java"));
            if (!Files.exists(path)) {
                System.out.println("Cannot execute your request, file doesn't exist");
            } else if (Files.exists(path)) {
                System.out.println("Please enter a first name to delete:");
                String userNameDelete = input.nextLine();
                System.out.println("Please enter a last name to delete");
                String userLastDelete = input.nextLine();

                for(Contacts contact: contactNew)
                if (contact.getFullName().equalsIgnoreCase(String.format("%s %s",userNameDelete, userLastDelete))){
                    System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber());

                    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                    String lineToDelete = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNameDelete, userLastDelete, contact.getPhoneNumber());

                    while ((line = file.readLine()) != null)
                    {
                        //System.out.println(line);
                        if (line.contains(lineToDelete))
                        {
                            line = "";
                            System.out.println("Line deleted.");
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

