public class LinkedList{
    public static Node front;
    public static indexNode[] nameCodeIndex = new indexNode[26];

    static void init(){
        front = null;
    }

    static Node makeNode(String n, int codeEntry){
        Node nBlock = new Node();
        nBlock.name = n;
        nBlock.nameCode = codeEntry;
        nBlock.next = null;
        return nBlock;
    }

    static void insert(String n, int codeEntry){
        Node temp, curr, prev;
        boolean searching;
        if(front == null){
            front = makeNode(n, codeEntry);
        }
        else if(codeEntry < front.nameCode){
            temp = makeNode(n, codeEntry);
            temp.next = front;
            front = temp;
        }
        else{
            searching = true;
            curr = front;
            prev = curr;
            while(searching){
                if (curr.nameCode == codeEntry){
                    searching = false;
                }
                else if(curr.nameCode < codeEntry){
                    if(curr.next == null){
                        curr.next = makeNode(n, codeEntry);
                        searching = false;
                    }
                    else{
                        prev = curr;
                        curr = curr.next;
                    }
                }
                else if(curr.nameCode > codeEntry){
                    temp = makeNode(n, codeEntry);
                    temp.next = curr;
                    prev.next = temp;
                    searching = false;
                }
            }
        }
    }

    static boolean isNameExist(String n){
        Node current;
        current = front;
        while(current != null){
            if(n.equals(current.name)){
                return true;
            }
            else {
                current = current.next;
            }
        }
        return false;
    }

    static boolean isFront(String n){
        return n.equals(front.name);
    }

    static void deleteFront(){
        Node temp;
        temp = front;
        front = front.next;
        temp.next = null;
    }

    static Node findNodeForDelete(String n){
        Node current, previous;
        current = front;
        previous = current;
        while (!n.equals(current.name)) {
            previous = current;
            current = current.next;
        }
        return previous;
    }

    static void deleteFoundNode(Node spot){
        Node temp;
        temp = spot.next;
        spot.next = temp.next;
        temp.next = null;
    }

    static void buildIndexList(){
        for(int i = 0; i < 26; i++){ //i represent current letter
            if(!isSectionEmpty(i)) {
                nameCodeIndex[i] = makeIndexNode(i);
            }
            else{
                nameCodeIndex[i] = makeEmptyIndexNode(i);
            }
        }
    }

    static indexNode makeIndexNode(int n) {
        indexNode iNode = new indexNode();
        Node current, previous;
        int count = 0;
        int lowerBound;

        n += 1; //offset due to nameCode offset: a = 1, b = 2, etc
        lowerBound = n * 456976;

        current = front;
        previous = current;
        while (current != null && count < 1) {
            if(current.nameCode >= lowerBound){
                iNode.firstNode = current;
                count = 1;
            }
            else{
                previous = current;
                current = current.next;
            }
        }
        return iNode;
    }

    static indexNode makeEmptyIndexNode(int n){
        indexNode iNode = new indexNode();
        iNode.emptyIndex = "There are no names in this section!";
        return iNode;
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

    static boolean isSectionEmpty(int n) {
        Node current;
        int lowerBound, upperBound;
        n += 1; //offset due to nameCode offset
        lowerBound = n * 456976;
        upperBound = (n+1) * 456976;
        current = front;
        while(current != null){
            if(current.nameCode >= lowerBound && current.nameCode < upperBound){
                return false;
            }
            else {
                current = current.next;
            }
        }
        return true;
    }

    static void sectionCount(int n){
        int upperBound = (n+2)*456976; //n+2 due to nameCode offset, a = 1, b = 2, etc
        Node current;
        int count=0;
        current = nameCodeIndex[n].firstNode;
        if(nameCodeIndex[n].firstNode == null){
            System.out.println(nameCodeIndex[n].emptyIndex);
            System.out.println();
        }
        else {
            while (current.next != null && current.nameCode < upperBound) {
                ++count;
                current = current.next;
            }
            if (current.next == null) {
                ++count;
            }
            System.out.println("There are " + count + " names that start with " + (char) (n + 97));
        }
    }

    static void showSectionList(int n){
        int upperBound = (n+2)*456976;
        Node current, previous;
        current = nameCodeIndex[n].firstNode;
        previous = current;
        if(nameCodeIndex[n].firstNode == null){
            System.out.println(nameCodeIndex[n].emptyIndex);
            System.out.println();
        }
        else {
            while (current.next != null && current.nameCode < upperBound) {
                previous = current;
                System.out.println(previous.name);
                current = current.next;
            }
            if (current.next == null) {
                System.out.println(current.name);
            }
            System.out.println();
        }
    }

    static void showFullList(){
        Node current, previous;
        current = front;
        previous = current;
        while(current.next != null) {
            previous = current;
            System.out.println(previous.name);
            current = current.next;
        }
        if(current.next == null) {
            System.out.println(current.name + "\n");
        }
    }

    static void showFullListCount(){
        int count = 0;
        Node current;
        current = front;
        if(front == null) {
            System.out.println("The list is empty!");
        }
        else if(front.next == null){
            System.out.println("There is one name in the list!");
        }
        else{
            while(current.next != null) {
                ++count;
                current = current.next;
            }
            if(current.next == null){ //to be able to print last node
                ++count;
            }
            System.out.println("There are " +count+ " names in the list!" +"\n");
        }
    }
}