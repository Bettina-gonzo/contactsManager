
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class contactsManagerTest {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Category contacts = new Category();
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
        }

    }
}
