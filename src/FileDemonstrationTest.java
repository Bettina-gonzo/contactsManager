import javax.swing.*;
import java.awt.*;

public class FileDemonstrationTest {
    public static void main(String[] args) throws Exception{
        EventQueue.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                FileDemonstration application = new FileDemonstration();
                application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    } //end main
} // end class FileDemonstrationTest
