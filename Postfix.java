//Audrey Wallach
import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      String s = "35*7+";
      System.out.println(s + " = " + evaluate(s) + "\n");
      System.out.println((s = "354*+7*") + " = " + evaluate(s) + "\n");
      System.out.println((s = "82-") + " = " + evaluate(s) + "\n");
      System.out.println((s = "82/") + " = " + evaluate(s) + "\n");
   }
   public static int evaluate(String x)
   {
     
      MyStack<String> stack = new MyStack<String>();         
      for(int i = 0; i < x.length(); i++)
         {
            if(isOperator(x.charAt(i)))
            {
               String a = stack.pop();
               String b = stack.pop();
               int y = eval(Integer.parseInt(a),Integer.parseInt(b), x.charAt(i));
               stack.push("" + y);
            }
            else
            {
               stack.push(x.substring(i, i+1));
            }
            
         }
      
               /* Write this method for Part I.  Review the existing methods to see 
            what tools are already there for you to use, and how you call them. */
      
      return Integer.parseInt(stack.pop());
   }
   public static int eval(int a, int b, char ch)
   {
      switch(ch) 
      {
         case '*':  
            return a * b;
         case '/': 
            return b / a;
         case '+': 
            return a + b;
         case '-': 
            return b - a;
      }
      return 0;
   }
   public static boolean isOperator(char ch)
   {
      return ch == '*' || ch == '/' || ch == '+' || ch == '-';
   }
}