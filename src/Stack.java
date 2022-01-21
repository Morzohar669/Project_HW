public class Stack {

    public DoublyLinkedList stackDDL;

    public Stack(){
        this.stackDDL = new DoublyLinkedList();
    }

    public void push(DoublyNode node){
        stackDDL.insert(node);
    }

    public DoublyNode POP(){
        DoublyNode tmpToDelete = stackDDL.headOfList;
        stackDDL.delete(tmpToDelete);
        return tmpToDelete;
    }
}
