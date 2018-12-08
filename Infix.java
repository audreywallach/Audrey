//Audrey Wallach
import java.util.*;
public class Infix
{
   public static void main(String[] args)
   {
      String s = "3*5+7";
      System.out.println(s + " = " + Postfix.evaluate(trans(s)) + "\n");
      System.out.println((s = "(3+5*4)*7") + " = " + 
                           Postfix.evaluate(trans(s)) + "\n");
      System.out.println((s = "8-2") + " = " + 
                           Postfix.evaluate(trans(s)) + "\n");
      System.out.println((s = "8/2") + " = " + 
                           Postfix.evaluate(trans(s)) + "\n");
   }
   public static String trans(String x)
   {
       MyStack<String> Stacks = new MyStack<String>();
       String result = "";
       String temp = x;
       int i = 0;
       while(i < temp.length())
       {
       if(Character.isDigit(x.charAt(i)))
       {
         
         result += x.charAt(i);
       }
       else if(x.charAt(i)== '(')
         {
            Stacks.push("(");
         }
        else if(x.charAt(i) == ')')
        {
            while(!Stacks.peek().equals("("))
            {
               result += Stacks.pop();
            }
            Stacks.pop();
        }
        else if(Postfix.isOperator(x.charAt(i)))
        {
            while(!Stacks.isEmpty()&& !Stacks.peek().equals("(") && !isLower(Stacks.peek().charAt(0), x.charAt(i)))
            {
               result += Stacks.pop();
            }
            Stacks.push("" + x.charAt(i));
        }
       else 
       {
         result += x.charAt(i);

       }
       i++;
              
       }
       while(!Stacks.isEmpty())
       {
         result += Stacks.pop();
       }
                /* Write this method for Part 2.  Use available methods
           as appropriate. */
      System.out.println(result);   
      return result;
   }
   	//returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      if(c1 == '*' || c1 == '/')
         return false;
      return c2 == '*' || c2 == '/';
   }
}
