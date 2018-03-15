import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UpdateContact {
    public void getContactUpdate(String updateContact){ //method for updating contact provided by user
        String line;
        String userInput = "";
        try {
            Path path = Paths.get("src/ContactsArray.java");
            BufferedReader file = new BufferedReader(new FileReader("src/ContactsArray.java"));
            if (!Files.exists(path)) {
                System.out.println("Cannot execute your request, file doesn't exist");
            } else if (Files.exists(path)) {
                for(Contacts contact: ContactsArray.findAll())
                    if (contact.getFullName().equalsIgnoreCase(updateContact)){
                        System.out.printf("%-20s   |   %s\n\n",contact.getFullName(),contact.getPhoneNumber());

                        String lineToDelete = String.format("%20s(\"%s\",\"%s\"),", "new Contacts", updateContact, contact.getPhoneNumber());

                        while ((line = file.readLine()) != null)
                        {
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
        } catch (java.io.IOException IOException) {
            System.err.println("Exception " + IOException);
        }
    }
}
