//Audrey Wallach
/**
        this is student code, in particular the solve method
        in this lab, it just reads the data and populates the 1D array.
        results are displayed for the student by calling myView.updateView()
 */
import java.util.Scanner;
import java.io.File;
import java.io.*;
public class ShelbyModel
{
    private int monthData[];
    private ShelbyView myView;
    private String dataFile;
        
    public ShelbyModel ( String filename, ShelbyView view )
    {
        myView = view;
        dataFile = filename;
        monthData = new int[13];    //The 0 index element is unused so that Jan will be at 1
    }
    public static void readFile(String[] words, String fileName)throws IOException
      {
         Scanner input = new Scanner(new FileReader("ShelbyList.dat"));
         int i=0;
         String line;
         while (input.hasNextLine())
            {
               line=input.nextLine(); 
               words[i]= line;
               i++;
               input.close();
            }
  }
//Found on Blackboard notes sheet
   
    public void solve()throws IOException
    {    
      Scanner input = new Scanner(new File("ShelbyList.dat"));
      int i = 0;
      String line;
      
      while (input.hasNextLine())
      {
         line = input.nextLine();
         int bslash = line.indexOf("/");
         if(bslash>0)
         {
         int month = Integer.parseInt(line.substring(bslash+1,bslash+3));
         monthData[month]++;
         }
      }
      input.close();
      
    //... student code here to load the monthData array...
    // think about what you have to begin with and
    //  what the end result needs to be here.
    // Next figure out how you will read the file using tools at hand
    // If you get code or ideas from somewhere else be sure to put
    //  a comment in identifying the resource you used.
    myView.updateView(monthData);               // for each month
    }
}
