public class Stack {

    public DoublyLinkedList stackDDL;

    public Stack(){
        this.stackDDL = new DoublyLinkedList();
    }

    public void push(DoublyNode node){
        stackDDL.insert(node);
    }

    public DoublyNode POP(){
        if (stackDDL.headOfList != null){
            DoublyNode tmpToDelete = stackDDL.headOfList;
            stackDDL.delete(tmpToDelete);
            return tmpToDelete;
        } else {
            return null;
        }
    }
}
