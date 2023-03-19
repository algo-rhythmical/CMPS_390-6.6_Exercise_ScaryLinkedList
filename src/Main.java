import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Main extends LinkedList{
    public static void main(String[] args){
        try{
        Scanner keyboard = new Scanner(System.in);
        Scanner listReader;
        File file;
        String fileName;
        String entryName;
        String deleteEntry;
        int entryCode;
        int switchChoice;
        int sectionNum;
        String choiceToModify = "y";
        String modChoice;
        String sectionChoice;
        Node nodeForDelete;

        System.out.print("Enter the file name: ");
        fileName = keyboard.nextLine();
        file = new File("src\\"+fileName);
        listReader = new Scanner(file);

        init();
        while (listReader.hasNextLine()){
            entryName = listReader.nextLine();
            entryCode = nameCodeCreator(entryName);
            insert(entryName, entryCode);
        }
        System.out.println("\n"+ "The list of names has been created!"+"\n");

        System.out.println("What would you like to do? Pick an integer to confirm your choice!" +"\n");
        while(choiceToModify.equals("y")){
            System.out.println("Full list display: enter '1'" +"\n"+
                             "Full list count: enter '2'" +"\n"+
                             "Delete a specific name: enter '3'" +"\n"+
                             "Section count(by starting letter): enter '4'" +"\n"+
                             "Section display(by starting letter): enter '5'");
            modChoice = keyboard.next(); System.out.println();
            while((int)modChoice.charAt(0) < 49 || (int)modChoice.charAt(0) > 53 || modChoice.length()>1){
                System.out.println("Please pick an appropriate number for your menu choice!" +"\n");
                System.out.println("Full list display: enter '1'" +"\n"+
                                    "Full list count: enter '2'" +"\n"+
                                    "Delete a specific name: enter '3'" +"\n"+
                                    "Section count(by starting letter): enter '4'" +"\n"+
                                    "Section display(by starting letter): enter '5'");
                modChoice = keyboard.next(); System.out.println();
            }
            switchChoice = (int)modChoice.charAt(0) - 48;
            switch(switchChoice) {
                case 1: //print out all names in the list
                    System.out.println("Full list of names:");
                    showFullList();
                    break;
                case 2: //print out number of names in the list
                    showFullListCount();
                    break;
                case 3: //deletes a name on the list
                    keyboard.reset();
                    System.out.print("Which name would you like to delete?(or enter \"stop\" due to choice regret): ");
                    deleteEntry = keyboard.next();
                    if(deleteEntry.equals("stop")){
                        break;
                    }
                    while(!isNameExist(deleteEntry)){
                        System.out.print("Name not in list, please enter a valid name(enter 'stop' if regret): ");
                        deleteEntry = keyboard.next();
                        if(deleteEntry.equals("stop")){
                            break;
                        }
                    }
                    if(deleteEntry.equals("stop")){
                        break;
                    }
                    else if(isFront(deleteEntry)){
                        deleteFront();
                    }
                    else {
                    nodeForDelete = findNodeForDelete(deleteEntry);
                    deleteFoundNode(nodeForDelete);
                    }
                    System.out.println("The name \"" +deleteEntry+ "\" has been deleted from the list.");
                    break;
                case 4: //print out number of names in a specific section
                    buildIndexList();
                    System.out.print("Which section would you like to count?(Choose a letter a-z): ");
                    sectionChoice = keyboard.next();
                    while((int)sectionChoice.charAt(0) < 97||(int)sectionChoice.charAt(0) > 122||sectionChoice.length()>1){
                        System.out.print("Stop trying to break my code and pick a single letter a-z please:");
                        sectionChoice = keyboard.next();
                    }
                    sectionNum = sectionChoice.charAt(0) - 'a'; System.out.println();
                    sectionCount(sectionNum);
                    break;
                case 5: //print out names in a specific section
                    buildIndexList();
                    System.out.print("Which section of names do you want to print out?(Choose a letter a-z): ");
                    sectionChoice = keyboard.next();
                    while((int)sectionChoice.charAt(0) < 97 || (int)sectionChoice.charAt(0) > 122||sectionChoice.length()>1){
                        System.out.print("Stop trying to break my code and pick a single letter a-z please:");
                        sectionChoice = keyboard.next();
                    }
                    sectionNum = sectionChoice.charAt(0) - 'a'; System.out.println();
                    showSectionList(sectionNum);
                    break;
            }
            System.out.print("Would you like to go back to the menu options?(Enter y/n): ");
            choiceToModify = keyboard.next(); System.out.println();
            while(!choiceToModify.equals("y") && !choiceToModify.equals("n")){
                System.out.print("Please enter y or n to choose whether to go back to menu or not: ");
                choiceToModify = keyboard.next(); System.out.println();
            }
            if(choiceToModify.equals("n")) {
                System.out.println("Sayonara!");
                System.exit(0);
            }
        }
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
}
