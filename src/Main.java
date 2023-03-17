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

    static int nameCodeCreator(String n){
        int code;
        if(n.length() == 3){                                                                                            //for names that are 3 letters long-
            code = ((n.charAt(0) - 96) * 456976) + ((n.charAt(1) - 96) * 17576) + ((n.charAt(2) - 96) * 676);           //char - 96, not 97('a') due to names-
            return code;                                                                                                //that matches other names except for the last letter being 'a', aka karl vs karla
        }
        else if(n.length() == 4){                                                                                       //for names that are 4 letters long
            code = ((n.charAt(0) - 96) * 456976) + ((n.charAt(1) - 96) * 17576) + ((n.charAt(2) - 96) * 676) +
                    ((n.charAt(3) - 96) * 26);
            return code;
        }
        else{                                                                                                           //for names that are atleast 5 letters long.-
            code = ((n.charAt(0) - 96) * 456976) + ((n.charAt(1) - 96) * 17576) + ((n.charAt(2) - 96) * 676) +
                    ((n.charAt(3) - 96) * 26) + (n.charAt(4) - 96);
            return code;
        }
    }
}
