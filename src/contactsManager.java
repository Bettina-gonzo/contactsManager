import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class contactsManager {

    public static void slurp (String filePath, String fileName) throws IOException {
        try{
            List<String> fileContents = Files.readAllLines(Paths.get(filePath,fileName));
            String[] newArray = new String[fileContents.size()];
            for (int i = 0; i < fileContents.size(); ++i) {
//                System.out.printf("%s \n",  fileContents.get(i));
                newArray[i] = fileContents.get(i);
            }
            for (String i: newArray)
                System.out.println(i.toCharArray());
        }catch (RuntimeException RuntimeException){
            System.err.println("Exception " + RuntimeException);
        }
    }

    public void addContact(String filename, List<String> contents) throws IOException {
        try{
            if(!Files.exists(Paths.get(filename))){
                Files.createFile(Paths.get(filename));
                Files.write(Paths.get(filename), contents, StandardOpenOption.APPEND);
            } else if(Files.exists(Paths.get(filename))){
                Files.write(Paths.get(filename), contents, StandardOpenOption.APPEND);
            }

        }catch (RuntimeException RuntimeException){
            System.err.println("Exception " + RuntimeException);
        }
    }


}
