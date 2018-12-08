//Audrey Wallach
import java.util.Scanner;
import java.io.*;

public class AddThree
{

   public static void main(String[] args)
   {
      String[] filenames = {"1Kints.txt", "1Kints.txt","1Kints.txt", "2Kints.txt", "4Kints.txt", "8Kints.txt", "16Kints.txt", "32Kints.txt", "1Mints.txt"};
      int[] filecounts = {250, 500, 1000, 2000, 4000, 8000, 16000, 32000, 1000000};
      
      System.out.println("Number of integers\tMilliseconds to run\tNumber of triples");
      for (int i = 0; i < filenames.length; i++)
      {
         int[] nums = loadIntFile(filenames[i], filecounts[i]);   //The array containing the ints to use
         int trioCt = 0;                                          //Update this count each time you find
                                                                  // a triplet that sums to zero.
      
         long start = System.currentTimeMillis();
         //Insert code to solve the problem here
        for(int y = 0; y < nums.length; i++)
            for(int z = 0; z < nums.length-1; z++)
               for(int w = 0; w < nums.length-2; w++)
                  if(nums[y] + nums[z] + nums[w] == 0)
                     trioCt++;   
                  
         
         //End of code to solve the problem
         long end = System.currentTimeMillis();
         System.out.println(filecounts[i] + "\t\t\t" + (end - start)+"\t\t\t" + trioCt);
      }
   }
   
   public static int[] loadIntFile(String filename, int numOfNums)
   {
      int[] numbers = new int[numOfNums];
      try
      {
         Scanner input = new Scanner(new File(filename));
   
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
      
      //Read numOfNums integers from the file called filename
      //  I recommend reading the file with Scanner and using nextInt
      //  Use a try catch block for exception handling.  You may return
      //  null if there is an error reading the file.
      return numbers;
   }  
   
}