public class Stack {

    public DoublyLinkedList stackDDL;

    public Stack(){}

    public void push(DoublyNode node){
        stackDDL.insert(node);
    }

    public DoublyNode POP(){
        DoublyNode tmpToDelete = stackDDL.headOfList;
        stackDDL.delete(tmpToDelete);
        return tmpToDelete;
    }
}
