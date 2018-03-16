import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateContact {
    Scanner input = new Scanner(System.in);
    ContactsMenu menu = new ContactsMenu();
    Path path = Paths.get("src/contacts.txt");

    public void getContactUpdate(String updateContact) { //method for updating contact provided by user
        try {
            List<String> lines = Files.readAllLines(path);
            List<String> newlines = new ArrayList<>();
            for (String line : lines) {
                if (line.contains(updateContact)) {
                        System.out.println("Please enter full name and number:");
                        String contactName = String.format("\"%s %s\",\"%s\"", input.next(), input.next(), input.next());
                        newlines.add(contactName);
                        System.out.printf("%s was successfully updated\n", contactName);
                        continue;
                    }
                newlines.add(line);
            }
            Files.write(path, newlines); //writing new contact to file
            menu.getOpenMenu(); //display main menu
        } catch (java.io.IOException IOException) {
            System.err.println("Exception " + IOException); //exception if path is not found (contact cannot be added)
        }
    }
}
