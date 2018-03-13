
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class contactsManagerTest {

    public static Contacts[] contactNew = ContactsArray.findAll();

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int userInput;

        do{
            System.out.println("Welcome to the Contacts Manager book\n" +
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
                    contactDelete():
                case 5:
                    System.out.println("Thank you and please come back again!");
                    System.exit(0);
            }
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
            Path path = Paths.get("/Users/Gonzo 1/IdeaProjects/contactsManager/src/ContactsArray.java");
            if (!Files.exists(path)) {
                Files.createFile(path);
            } else if (Files.exists(path)) {
                System.out.println("Please enter new First name:");
                String userNewFirst = input.next();
                System.out.println("Please enter new Last name:");
                String userNewLast = input.next();
                System.out.println("Please enter new phone number: ");
                String userNewNumber = input.next();
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                int position = 3;
                String extraline = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNewFirst, userNewLast, userNewNumber);
                lines.add(position, extraline);
                Files.write(path, lines, StandardCharsets.UTF_8);
            }
        } catch (IOException IOException) {
            System.err.println("Exception " + IOException);
        }
    }

    public static void contactSearch(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a name to search: ");
        String userName = input.nextLine();
        for(Contacts contact: contactNew)
            if (contact.getFullName().equalsIgnoreCase(userName)){
                System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber());
            }
    }

    public static void contactDelete(){

    }
}
