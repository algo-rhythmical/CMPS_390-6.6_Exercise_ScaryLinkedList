import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Main extends LinkedList{
    public static void main(String[] args){
        try{
        Scanner listReader = new Scanner(new File("src\\names"));
        String entryName;
        int entryCode;
        init();

        while (listReader.hasNextLine()){
            entryName = listReader.nextLine();
            entryCode = nameCodeCreator(entryName);
            insert(entryName, entryCode);
        }
        System.out.println("Here is the list of names sorted alphabetically:"+"\n");
        showListOfNames();
        nameCount();
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }

    }
}
