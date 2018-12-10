//Audrey Wallach
public class WordSentiment
{
   private String word;
   private int totalScore;
   private int counter;
   
   public WordSentiment(String w, int t)
   {
      word = w;
      totalScore = t;
      counter = 1;
   }
   
   public String getWord()
   {
      return word;
   }
   public int getTotalScore()
   {
      return totalScore;
   }
   public int getCounter()
   {
      return counter;
   }
   public double getAverageScore()
   {
      return (double)totalScore/counter;
   }
   
   public void setTotalScore(int x)
   {
      totalScore += x;
   }
   public void increaseCounter()
   {
      counter++;
   }
   public String toString()
   {
      return word + " " + counter + " " + totalScore;
   } 
}