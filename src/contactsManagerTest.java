
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

public class contactsManagerTest {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        contactsManager contacts = new contactsManager();
        int userInput;
        String userNewContact;
        String userNewNumber;

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
                contacts.slurp("src", "newContacts.csv");
                break;
            case 2:
                System.out.println("Please enter First and last name, and phone number: ");
                userNewContact = input.next();
//                System.out.println("Please enter phone number: ");
//                userNewNumber = input.nextLine();
                input.next();
                List<String> newContact = new ArrayList<>();
                newContact.add(0, userNewContact);
                contacts.addContact("src/newContacts.csv", newContact);
                break;
            case 3:
//                Search contact by name
                System.out.println("Please enter name of contact: ");
                break;
            case 4:
//                delete an existing contact

                break;

        }



    }
}
