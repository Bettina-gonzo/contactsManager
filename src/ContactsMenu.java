import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactsMenu {
    public void getOpenMenu(){ // menu items method
        Scanner input = new Scanner(System.in);
        ShowContact showContact = new ShowContact();
        addContact addcontact = new addContact();
        SearchContact search = new SearchContact();
        DeleteContact delete = new DeleteContact();

        int userInput;
        try{
            do{
                System.out.println("\nWelcome to the Contacts Manager book!\n\n" + //display menu
                        "1. View contacts.\n" +
                        "2. Add a new contact.\n" +
                        "3. Search a contact by name.\n" +
                        "4. Delete an existing contact.\n" +
                        "5. Exit.\n" +
                        "Enter an option (1, 2, 3, 4 or 5):\n");
                userInput = input.nextInt(); //input for user option

                if(userInput > 5 || userInput < 1){
                    System.out.println("Please enter an option from 1 to 5!\n");
                    getOpenMenu();
                }

                switch(userInput){ //switch case for user options
                    case 1:
                        showContact.getContact(); //user entered 1, will display all contacts from array
                        break;
                    case 2:
                         addcontact.getAddContact(); //user entered 2, will allow user to add contact to file. Will inform user if contact is already created.
                        break;
                    case 3:
                        search.getContactSearch(); //user entered 3, will allow user to search for contact already listed.
                        break;
                    case 4:
                        delete.getContactDelete(); //user entered 4, will allow user to delete a contact
                        break;
                    case 5:
                        System.out.println("Thank you and please come back again!"); //user entered 5, will exit program
                        System.exit(0);
                }

                System.out.println("\nEnter 'y' to continue.");
                if(input.next().toLowerCase().startsWith("y")){  // if user decides to continue, go back to main menu, if not exit.
                    getOpenMenu();
                } else if(input.next().toLowerCase().startsWith("n")){
                    System.exit(0);
                }
                else
                    getOpenMenu();
            }while(true);
        } catch (InputMismatchException wrongInput){
            System.out.printf("%s %s, please enter an integer" , "Exception", wrongInput);
            getOpenMenu();
        }
    }
}
