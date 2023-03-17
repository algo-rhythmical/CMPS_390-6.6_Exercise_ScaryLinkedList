public class LinkedList{
    public static Node front;

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

    static Node findEnd(){
        Node current = front;
        while(current.next != null){
            current = current.next;
        }
        return current;
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

    static void buildIndex(){

    }

    static void showListOfNames(){
        Node current = front;
        while(current.next != null) {
            System.out.println(current.name);
            current = current.next;
        }
        System.out.println(current.name + "\n");
    }

    static void nameCount(){
        int count = 0;
        Node current = front;
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
            ++count;
            System.out.println("There are " +count+ " names in the list!");
        }

    }

}
