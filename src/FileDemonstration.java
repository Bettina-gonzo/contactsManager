import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileDemonstration extends JFrame{
    private JTextArea outputArea; // used for output
    private JScrollPane scrollPane; // uset to provide scrolling to output

    //setup GUI
    public FileDemonstration(){
        super( "Testing class file");

        outputArea = new JTextArea();
        scrollPane = new JScrollPane(outputArea); // add output are to scrollpane

        add(scrollPane, BorderLayout.CENTER); // add scrollPane to GUI
        setSize(600,600); // set GUI Size
        setVisible(true); // display GUI
        analyzePath(); // create and analyze file object
    }

    // to allow users to specify which file name
    private File getFile(){
        //display file dialog, so user can choose file to open
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);

        //if user clicked cancel button on dialog, then return
        if (result == JFileChooser.CANCEL_OPTION){
            System.exit(1);
        }
        File filename = fileChooser.getSelectedFile(); // get Selected file

        // display error if invalid
        if((filename == null) || (filename.getName().equals(""))){
        JOptionPane.showMessageDialog(this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
        }// end if

        return filename;
    }// end method getFile

    // display information about file user input
    public void analyzePath(){
        // create File object based on user input
        File name = getFile();

        if(name.exists()) {  // if exists then output information about it
            outputArea.setText(String.format(
                    "%s%s\n%s\n%s\n%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s",
                    name.getName(), " exists",
                    (name.isFile() ? "is a file" : "is not a file"),
                    (name.isDirectory() ? "is a directory" : "is not a directory"),
                    (name.isAbsolute() ? "is absolute path" : "is not absolute path"), "Last modified: ",
                    name.lastModified(), "Length: ", name.length(), "Path: ", name.getPath(), "Absolute path: ",
                    name.getAbsolutePath(), "Parent: ", name.getParent()));

            if(name.isDirectory()){  // output directory listing
             String directory[] = name.list();
             outputArea.append( "\n\nDirectory contents:\n");
                for(String directoryName : directory)
                    outputArea.append(directoryName + "\n");
            } // end else
        } // end outer if
        else{
            JOptionPane.showMessageDialog(this, name + " does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } // end of else
    } // end method analyzePath
}


