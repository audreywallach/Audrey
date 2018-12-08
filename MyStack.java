//Audrey Wallach
import java.util.*;
public class MyStack<anyType> implements StackInterface<anyType>
{
   private ArrayList<anyType> list;
   private int numElements;
   
   public MyStack()
   {
      list = new ArrayList();
      numElements = 0;
   }
   public void push(anyType x)
   {
      list.add(x);
      
   }
   	//add x to the top of the stack

   public anyType pop()
   {
      if(list.size() == 0)
      {
         return null;
      }
      else
         return list.remove(0);
   }			//remove and return the element on the top
        										//return null if empty
   public anyType peek()
   {
      if(list.size() == 0)
      {
         return null;
      }
      else
         return list.get(0);
   }			//return the element on the top
     											//return null if empty
   public boolean isEmpty()
   {
      if(list.size() == 0)
         return true;
      else
         return false;
   }		//true if empty, false otherwise
}