import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ShowContact {

    public void getContact(){
        try {
            System.out.printf("%-23s|%15s\n", "Name", "Phone number");
            System.out.printf("%s\n", "---------------------------------------");
            Path path = Paths.get("src/contacts.txt");
            if(Files.exists(path)){
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                for(int i = 0; i < lines.size(); i ++){
                    System.out.printf("%s\n", lines.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
