public class ShowContact {

    public void getContact(){
        try {
            System.out.printf("%-23s|%15s\n", "Name", "Phone number"); // displaying tittle menu
            System.out.printf("%s\n", "---------------------------------------");
//            Path path = Paths.get("src/ContactsArray.java");
//            Files.exists(path);
//            FileOutputStream File = new FileOutputStream("src/ContactsArray.java");
            for (Contacts contact : ContactsArray.findAll())
            System.out.printf("%-20s   |   %s\n", contact.getFullName(), contact.getPhoneNumber()); // pulling specific contact from array
        } catch (NoSuchFieldError  NosuchFile){
            System.err.println("Exception " + NosuchFile); // exception message for file not found
        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
