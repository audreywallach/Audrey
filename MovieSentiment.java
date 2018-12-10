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
      Map<String, WordSentiment> map = new HashMap <String, WordSentiment>();
       
      try{
       
      //open input file
         file = new Scanner(new File("movieReviews.txt"));
      //create hash map (what is the key?  what is the value?)
         while(file.hasNext())
         {
            String line = file.nextLine();
            char score = line.charAt(0);//get character
            String scoreString = score + "";//convert to string
            int realScore = Integer.parseInt(scoreString);//convert to int
            String[] wordReview = line.split(" ");
            for(String wr : wordReview)
            {
               if(!map.containsKey(wr))
               {
                  WordSentiment ws = new WordSentiment(wr, realScore);
                  map.put(wr, ws);
               }
               else 
               {
                  map.get(wr).setTotalScore(realScore);
                  map.get(wr).increaseCounter();
               }
            }
         }
      //System.out.print(map);
      //loop, reading each review (single line) from the file
      
      }catch(IOException e)
      {
         System.out.println("This file cannot be read");
      }
    //Sentiments are now loaded into wordMap
    //repeatedly prompt user for a new movie review until they quit
      while(message.length() > 0)
      {
         System.out.println("enter a review -- Press return to exit: ");
         message = in.nextLine();
      
        //if a message was entered, process each word, adding to the 
        // score and number of words whenever a word is found in the 
        // pre-loaded HashMap.  
         String[] userReview = message.split(" ");
         //System.out.print(Arrays.toString(userReview));
         double userScore = 0;
         int wordCount = 0;
         for(String ur : userReview)
         {
            if(map.containsKey(ur))
            {
               //System.out.print("if");
               userScore += map.get(ur).getAverageScore();
               wordCount ++;
            }
            else 
            { 
               //System.out.print("else"); ignore 
            }
         }
      
        
        //if a message was entered, calculate the rating 
        //  (average score per word) and print it.
        //  Translate the rating into a sentiment and print that
        double calculatedWordRating = (double)userScore/wordCount;
        System.out.print("The reivew has a average of: " + calculatedWordRating+ " ");
        int wholeNumber = (int)Math.round(calculatedWordRating);
        //System.out.print(wholeNumber);
        if(wholeNumber == 0)
        {
         System.out.print("Negative Sentiment\n");
        }
        if(wholeNumber == 1)
        {
         System.out.print("Somewhat Negative Sentiment\n");
        }
        if(wholeNumber == 2)
        {
         System.out.print("Neutral Sentiment\n");
        }
        if(wholeNumber == 3)
        {
         System.out.print("Somewhat Positive Sentiment\n");
        }
        if(wholeNumber == 4)
        {
         System.out.print("Positive Sentiment\n");
        }

 
        
        
      }//end of while loop
    
   }//end of main method
    
}