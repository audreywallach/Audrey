//Audrey Wallach
import java.util.*;
import java.io.*;

public class MovieSentiment
{
   /* author: (your name here)   
    *  date: (today's date)
    *  description:  
    *
    */

   public static void main(String[] args)
   {
       //declare a few needed variables for inputing the data
       Scanner in = new Scanner(System.in);
       Scanner file = null;
       String message = " ";
       
      //open input file
      
      //create hash map (what is the key?  what is the value?)

  
      //loop, reading each review (single line) from the file
    
    //Sentiments are now loaded into wordMap
    //repeatedly prompt user for a new movie review until they quit
    while(message.length() > 0)
    {
        System.out.println("enter a review -- Press return to exit: ");
        message = in.nextLine();

        //if a message was entered, process each word, adding to the 
        // score and number of words whenever a word is found in the 
        // pre-loaded HashMap.  
        
        
        //if a message was entered, calculate the rating 
        //  (average score per word) and print it.
        //  Translate the rating into a sentiment and print that
        
        
        
    }//end of while loop
    
  }//end of main method
    
}