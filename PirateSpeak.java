//Audrey Wallach

import java.util.*;

public class PirateSpeak{
	
   public static void main(String [] args){
   	
   	// Create the hashtable object
      Hashtable<String, String> myPirateDictionary = new Hashtable<String, String>();
      addWordsToDictionary(myPirateDictionary);
      Scanner scan = new Scanner(System.in);
      System.out.println("This program will prompt you through entering an English sentence and will translate it to Pirate Speak for you. Please use the table to enter words that can be translated. Note that capitalization matters! So entering 'hello' is not the same as entering 'Hello'.");
      System.out.println("The size of this dictionary is: " +myPirateDictionary.size());
      System.out.println("Please enter a greeting: hello | pardon me | excuse me:");
      String Greeting = scan.nextLine();
      System.out.println("Please enter a person: sir | madam | miss | stranger | officer :");
      String Person = scan.nextLine();
      System.out.println("Please enter a question: where is | can you help me find | is that | how far is it to :");
      String Question = scan.nextLine();
      System.out.println("Please enter an article: the | a | any | nearby | my | your :");
      String article = scan.nextLine();
      System.out.println("Please enter an adjective: old | attractive | happy :");
      String adjective = scan.nextLine();
      System.out.println("Please enter a place: restaurant | hotel | mall | pub | bank :");
      String Place = scan.nextLine();
      System.out.println("Please enter a desire: I would like to | I desire to | I wish I knew how to | my mother told me to | my companion would like to :");
      String desire = scan.nextLine();
      System.out.println("Please enter an action: find | take a nap | make a withdrawal | have a cocktail:");   
      String action = scan.nextLine();
      String English = (Greeting + " " + Person + ", " + Question + " " + article + " " + adjective + " " + Place + "? " + desire + " " + action + ".");
      System.out.println("You entered:");
      System.out.println(English);
      System.out.println("Here's your sentence translated into Pirate Speak:");
      String PirateSpeak = myPirateDictionary.get(Greeting);
      PirateSpeak += myPirateDictionary.get(Question);
      PirateSpeak +=(English);
      System.out.println(PirateSpeak);
      System.out.println("Thank you for using the English-to-Pirate Speak Translator! Ahoy, have a good day, arrr!");   	
   
   }
	
   public static void addWordsToDictionary(Hashtable<String, String> table)
   {
      // fill the hashtable with words, hard-coded for now
      table.put("hello"," ahoy");
      table.put("pardon me"," avast");
      table.put("excuse me"," arrr");
      table.put("sir"," matey");
      table.put("madam"," proud beauty");
      table.put("miss"," comely wench");
      table.put("stranger"," scurvy dog");
      table.put("officer"," foul blaggart");
      table.put("where is"," whar be ");
      table.put("can you help me find"," know ye");
      table.put("is that"," be that");
      table.put("how far is it to"," how many leagues to");
      table.put("the"," th'");
      table.put("a"," a briny");
      table.put("any"," some forsaken");
      table.put("nearby"," broadside");
      table.put("my"," me");
      table.put("your"," ye");
      table.put("old"," barnacle-covered");
      table.put("attractive"," comely");
      table.put("happy"," grog-filled");
      table.put("restaurant"," galley");
      table.put("hotel"," fleabag inn");
      table.put("mall","market");
      table.put("pub"," Skull & Scuppers");
      table.put("bank"," buried treasure");
      table.put("I would like to"," I be needin' t'");
      table.put("I desire to "," I've a fierce fire in me belly t'");
      table.put("I wish I knew how to"," I be hankerin' t'");
      table.put("my mother told me to"," me dear ol' mum, bless her soul, tol' me t'");
      table.put("my companion would like to"," me mate, ol' Rumpot, wants t'");
      table.put("take a nap", " have a bit of a lie-down");
      table.put("make a withdrawal"," seize all me gold");
      table.put("have a cocktail"," swill a pint or two 'o grog");
   
   }
}