//Audrey Wallach
public interface StackInterface<anyType>
{
   public void push(anyType x);	//add x to the top of the stack

   public anyType pop();			//remove and return the element on the top
        										//return null if empty
   public anyType peek();			//return the element on the top
     											//return null if empty
   public boolean isEmpty();		//true if empty, false otherwise
}