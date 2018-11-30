/**
    A class of stacks whose entries are stored in a chain of nodes.
    Implement all methods in SimpleLinkedStack class using 
    the inner Node class. 

    Main Reference : text book or class notes
    Do not change or add data fields 
    Do not add new methods
    You may access Node object fields directly, i.e. data and next 
*/

package PJ2;

public class SimpleLinkedStack<T> implements StackInterface<T>
{
   
   // Data fields
   private Node topNode;    // references the first node in the chain
   private int count;  	    // number of data in this stack
  
   public SimpleLinkedStack()
   {
      // add stataments
       	   count = 0;
	   topNode = null;
	   
   } // end default constructor
  
   public void push(T newData)
   {
      // add stataments
          Node temp = new Node (newData, topNode);
	   topNode = temp;
	   count++;
	   
   } // end push

   public T peek()
   {
      // add stataments
        T first = null;
	   
	   if (topNode != null)
		   first = topNode.data;
      return first;
   } // end peek

   public T pop()
   {
      // add stataments
         T first = peek();
	   
	   if (topNode != null)
	   {
		   topNode = topNode.next;
		   count--;
	  
	   }
            return first;
   } // end pop

   public boolean empty()
   {
      // add stataments
            return (topNode == null);
   } // end empty

   public int size()
   {
      // add stataments
      return count;
   } // end isEmpty

   public void clear()
   {
      // add stataments
          count = 0;
	   topNode = null;
   } // end clear

   public String toString()
   {
      // add stataments
      // note: data class in stack must implement toString() method
      //       return a list of data in Stack, separate them with ','
       
          String result = "[";
       Node currentNode = topNode;
       while (currentNode != null) {
           result = result + currentNode.data + ", ";
           currentNode = currentNode.next;
       }
       
           result = result + "]";
       
           return result;
   }


   /****************************************************
	private inner node class
        Do not modify this class!!
        you may access data and next directly
    ***************************************************/

	private class Node
	{
	  private T data; // entry in list
	  private Node next; // link to next node
	  private Node (T dataPortion)
	  {
	    data = dataPortion;
	    next = null; // set next to NULL
	  } // end constructor

	  private Node (T dataPortion, Node nextNode)
	  {
	    data = dataPortion;
	    next = nextNode; // set next to refer to nextNode
	  } // end constructor
	} // end Node


   /****************************************************
      Do not modify: Stack test
   ****************************************************/
   public static void main (String args[])
   {

        System.out.println("\n"+
	"*******************************************************\n"+
        "Sample Expected output:\n"+
	"\n"+
        "OK: stack is empty\n"+
        "Push 3 data: 10, 30, 50\n"+
        "Print stack [50,30,10,]\n"+
        "OK: stack size is 3\n"+
        "OK: peek stack top is 50\n"+
        "OK: stack is not empty\n"+
        "Pop 2 data: 50, 30\n"+
        "Print stack [30,10,]\n"+
        "Print stack [10,]\n"+
        "OK: stack pop data is 30\n"+
        "Clear stack\n"+
        "Print stack []\n"+
	"\n"+
	"*******************************************************");

        System.out.println("\nYour Test output:\n");
	StackInterface<Integer> s = new SimpleLinkedStack<Integer>();
	if (s.empty()) 
            System.out.println("OK: stack is empty");
	else
            System.out.println("Error: stack is not empty");

	s.push(10);
	s.push(30);
	s.push(50);
        System.out.println("Push 3 data: 10, 30, 50");
        System.out.println("Print stack " + s);

	if (s.size() == 3) 
            System.out.println("OK: stack size is 3");
	else
            System.out.println("Error: stack size is " + s.size());

	if (s.peek() == 50) 
            System.out.println("OK: peek stack top is 50");
	else
            System.out.println("Error: peek stack top is " + s.size());

	if (!s.empty()) 
            System.out.println("OK: stack is not empty");
	else
            System.out.println("Error: stack is empty");

        System.out.println("Pop 2 data: 50, 30");
        s.pop();
        System.out.println("Print stack " + s);
	int data=s.pop();
        System.out.println("Print stack " + s);
	if (data == 30) 
            System.out.println("OK: stack pop data is 30");
	else
            System.out.println("Error: stack pop data is " + data);

        System.out.println("Clear stack");
        s.clear();
        System.out.println("Print stack " + s);
   }

} // end Stack
