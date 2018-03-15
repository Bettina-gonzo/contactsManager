import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DeleteContact {
    public void getContactDelete(){ //method for deleting existing contact
        Scanner input = new Scanner(System.in);
        String line; //variable for individual line
        String userInput = ""; //empty input

        try {
            Path path = Paths.get("src/ContactsArray.java"); // creating path for file and file name

            //java BufferedReading class is used to read the text from a character-based input stream.
            //here we are using it to read data line by line by the readLine() method in the while loop below
            BufferedReader file = new BufferedReader(new FileReader("src/ContactsArray.java"));
            if (!Files.exists(path)) {
                System.out.println("Cannot execute your request, file doesn't exist"); //cannot delete from file if it does not exist
            } else if (Files.exists(path)) {
                System.out.println("Please enter a first name to delete:"); //prompting user to input first name of contact to delete
                String userNameDelete = input.nextLine();
                System.out.println("Please enter a last name to delete"); //prompting user to input last name of contact to delete
                String userLastDelete = input.nextLine();

                for(Contacts contact: ContactsArray.findAll()) // searching through array for contacts
                    if (contact.getFullName().equalsIgnoreCase(String.format("%s %s",userNameDelete, userLastDelete))){ //getting existing contact to see if it matches user input of first and last name
                        System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber()); //displays contact that will be deleted

                        String lineToDelete = String.format("%20s(\"%s %s\",\"%s\"),", "new Contacts", userNameDelete, userLastDelete, contact.getPhoneNumber()); //variable for line to delete with format attached
                        while ((line = file.readLine()) != null) //reading line by line with file.readLine() method
                        {
                            if (line.contains(lineToDelete)) //if the line is equal to the line the user is seeking to delete, this will run then line is equal to empty space below
                            {
                                line = "";
                                System.out.println("Line deleted."); //informing user that line was deleted
                            }
                            userInput += line + '\n'; //creating break in line
                        }
                        FileOutputStream File = new FileOutputStream("src/ContactsArray.java"); //output stream used for writing data to a file
                        File.write(userInput.getBytes()); //writing userInput into a sequence of bytes
                        file.close(); //closing BufferedReader file
                        File.close(); //closing FileOutputStream file
                    }
            }
        } catch (java.io.IOException IOException) {
            System.err.println("Exception " + IOException);
        }
    }
}
