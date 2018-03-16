import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DeleteContact {
    Path path = Paths.get("src/contacts.txt");

    public void getContactDelete(){ //method for deleting existing contact
        Scanner input = new Scanner(System.in);
        String line; //variable for individual line
        String userInput = ""; //empty input
        try {
            BufferedReader file = new BufferedReader(new FileReader("src/contacts.txt"));
            Scanner scannedFile = new Scanner(new File("src/contacts.txt"));
            PrintWriter writer = new PrintWriter("src/contacts_tmp.txt");

            System.out.println("Please enter a contact full name to delete");
            String Contact2Delete = input.next();
            while ((line = file.readLine()) != null) //reading line by line with file.readLine() method
                {
                    if (line.contains(Contact2Delete)) //if the line is equal to the line the user is seeking to delete, this will run then line is equal to empty space below
                    {
                        line = "";
                        System.out.println("Contact deleted."); //informing user that line was deleted
                    }
                    userInput += line + '\n'; //creating break in line
                }
                FileOutputStream File = new FileOutputStream("src/contacts.txt"); //output stream used for writing data to a file
                File.write(userInput.getBytes()); //writing userInput into a sequence of bytes
                file.close(); //closing BufferedReader file
                File.close(); //closing FileOutputStream file

            while (scannedFile.hasNext()) {
                String lines = scannedFile.nextLine();
                if (!lines.isEmpty()) {
                    writer.write(lines);
                    writer.write("\n");
                }
            }
            file.close();
            writer.close();

            File file1 = new File("src/contacts.txt");
            File file2 = new File("src/contacts_tmp.txt");

            file1.delete();
            file2.renameTo(file1);

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
