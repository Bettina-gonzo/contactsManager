import java.util.Scanner;

public class SearchContact {
    public void getContactSearch() {  //method for finding the contact in file
        String foundContact= null;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a full name to search: "); //prompting user to enter full name for search
        String userName = input.next();
        for (Contacts contact : ContactsArray.findAll()) {
            if (contact.getFullName().startsWith(userName)) { //if the contact exists in the array then it will run
                foundContact = contact.getFullName(); // defining a foundContact variable to gather value of search result.  If null, then it will be sent to if statement outside loop.
                System.out.printf("%-20s   |   %s\n", contact.getFullName(), contact.getPhoneNumber()); //will print out all contact info in correct format
            }
        }
        if(foundContact == null){  // if contact is not found, then it will let the user know the contact is not in our DB.
            System.out.println("Contact not found in our database.");
        }
    }
}
