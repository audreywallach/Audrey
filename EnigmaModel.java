//Audrey Wallach
/*
 * File: EnigmaModel.java
 * ----------------------
 * This is the starter file for a class that models the German Enigma
 * machine from World War II.
 *
 * // Remember to correct this comment when you implement the class
 */

public class EnigmaModel {
public static void main(String [] args)
{
   EnigmaModel enigma = new EnigmaModel();
   System.out.println(enigma.advanceRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ"));
}

/* Private instance variables */

   // Add any instance variables you need here
   private int rOrder;
   private int[] rSetting;
   private String fastRotor;
   private String midRotor;
   private String slowRotor;

/**
 * Creates a new object that models the operation of an Enigma machine.
 * By default, the rotor order is initialized to 123, which indicates
 * that stock rotors 1, 2, and 3 are used for the slow, medium, and
 * fast rotor positions, respectively.  The rotor setting is initialized
 * to "AAA".
 */

   public EnigmaModel() {
      rOrder = 123;
      rSetting = new int[] {1, 1, 1};

   }

/**
 * Sets the rotor order for the Enigma machine.  The rotor order is
 * specified as a three-digit integer giving the numbers of the stock
 * rotors to use.  For example, calling setRotorOrder(513) uses stock
 * rotor 5 as the slow rotor, stock rotor 1 as the medium rotor, and
 * stock rotor 3 as the fast rotor.  This method returns true if the
 * argument specifies a legal rotor order (three digits in the range
 * 1 to 5 with no duplication) and false otherwise.
 *
 * @param order A three-digit integer specifying the rotor order
 * @return A Boolean value indicating whether the rotor order is legal
 */

   public boolean setRotorOrder(int order) {
      int temp = order;
      int[] used = new int[5];
      while (temp > 0)
      {
         int digit = temp % 10;
         if (digit > 0 && digit < 6)
         {
            if (used[digit - 1] == 0)
            {
               used[digit - 1] = 1;
            }
            else
            {
               return false;  //a number was re-used
            }
         }
         else
         {
            return false;     //a digit out of bounds
         }
         temp = temp / 10;
       }
       rOrder = order;
       
       
       int fast = rOrder%10;
       int medium = (rOrder/10)%10;
       int slow = ((rOrder/10)/10)%10;
       if(fast == 1)
       {
         fastRotor = STOCK_ROTOR_1;
       }
       if(fast == 2)
       {
         fastRotor = STOCK_ROTOR_2;
       }
      if(fast == 3)
       {
         fastRotor = STOCK_ROTOR_3;
       }

      if(fast == 4)
       {
         fastRotor = STOCK_ROTOR_4;
       }

      if(fast == 5)
       {
         fastRotor = STOCK_ROTOR_5;
       }
      if(medium == 1)
       {
         midRotor = STOCK_ROTOR_1;
       }
      if(medium == 2)
       {
         midRotor = STOCK_ROTOR_2;
       }
      if(medium == 3)
       {
         midRotor = STOCK_ROTOR_3;
       }
      if(medium == 4)
       {
         midRotor = STOCK_ROTOR_4;
       }
      if(medium == 5)
       {
         midRotor = STOCK_ROTOR_5;
       }
      if(slow == 1)
       {
         slowRotor = STOCK_ROTOR_1;
       }
       if(slow == 2)
       {
         slowRotor = STOCK_ROTOR_2;
       }
      if(slow == 3)
       {
         slowRotor = STOCK_ROTOR_3;
       }
      if(slow == 4)
       {
         slowRotor = STOCK_ROTOR_4;
       }
      if(slow == 5)
       {
         slowRotor = STOCK_ROTOR_5;
       }

       return true;
   }

/**
 * Establishes the rotor setting for the Enigma machine.  A legal rotor
 * setting must be a string of three uppercase letters.  This method
 * returns true if the argument is a legal setting and false otherwise.
 *
 * @param str The rotor settings
 * @return A Boolean value indicating whether the rotor setting is legal
 */

   public boolean setRotorSetting(String setting) {
      String[] turns = setting.trim().split(" ");
      int[] settings = new int[3];
      if (turns.length != 3)
         return false;     //A legal key must have three separated numbers
      
      for (int i = 0; i < turns.length; i++)
      {
         try {
            int z = Integer.parseInt(turns[i]);
            if (z < 0 || z > 25)
               return false;
            settings[i] = z;
         }
         catch (NumberFormatException e)
         {
            return false;
         }
      }
      rSetting = settings;
      for(int i = 0; i < rSetting[0]; i++)
      {
      slowRotor = advanceRotor(slowRotor);
      }
      for(int i = 0; i < rSetting[1]; i++)
      {
      midRotor = advanceRotor(midRotor);
      }
      for(int i = 0; i < rSetting[2]; i++)
      {
      fastRotor = advanceRotor(fastRotor);
      }


      return true; 
   }

/**
 * Gets the current rotor setting for the Enigma machine.
 *
 * @return The current rotor setting
 */

   public int[] getRotorSetting() 
   {
      return rSetting;   // Replace this line with the actual implementation???
   }

/**
 * Encrypts a string by passing each letter through the various rotors
 * of the Enigma machine.  All letters in the string are converted to
 * uppercase, and the rotors of the Enigma machine are advanced before
 * translating the letter.  If a character in the plaintext string is
 * not a letter, the rotors do not advance and the character is simply
 * copied to the output unchanged.
 *
 * @param plaintext The input plaintext string
 * @return The output ciphertext string
 */

   public String encrypt(String plaintext) 
   {
      plaintext = plaintext.toUpperCase();
      String output = "";
      for(int i = 0; i < plaintext.length(); i++)
      {
         //1
         //doesnt here
         char let = plaintext.charAt(i);
         if(let > 90 || let < 65)
         {
            output+= let;
         }
         else 
         {
         /*
            
         */
         fastRotor = advanceRotor(fastRotor);
         rSetting[2]++;
         if(rSetting[2] == 26)//maybe 26
         {
            rSetting[2] = 0;
            midRotor = advanceRotor(midRotor);
            rSetting[1]++;
            if(rSetting[1] == 26)
            {
               rSetting[1] = 0;
               slowRotor = advanceRotor(slowRotor);//what happens to 676?
            }
            //does here
            
         }
        //2a
        String twoA = LetterSubstitutionCipher.encrypt("" + plaintext.charAt(i), fastRotor);
        //2b
        String twoB = LetterSubstitutionCipher.encrypt(twoA, midRotor);
        //2c
        String twoC = LetterSubstitutionCipher.encrypt(twoB, slowRotor);
        //2d
        String twoD = LetterSubstitutionCipher.encrypt(twoC, REFLECTOR);
        //2e
        String twoE = LetterSubstitutionCipher.encrypt(twoD, LetterSubstitutionCipher.invertKey(slowRotor));
        //2f
        String twoF = LetterSubstitutionCipher.encrypt(twoE, LetterSubstitutionCipher.invertKey(midRotor));
        //2g
        String twoG = LetterSubstitutionCipher.encrypt(twoF, LetterSubstitutionCipher.invertKey(fastRotor));
        output += twoG;
        }

        
         
      }
      return output;
      
        // Replace this line with the actual implementation
   }

/* Private methods */

   // Add any private methods you need here

/* Private constants */

/**
 * The German Enigma machines were supplied with a stock of five rotors,
 * although the required part of the assignment uses only the first three.
 * Each rotor is represented by a string of 26 letters that shows how the
 * letters in the alphabet are mapped to new letters as the current in the
 * Enigma machine flows across the rotor from right to left.  For example,
 * the STOCK_ROTOR_1 string ("EKMFLGDQVZNTOWYHXUSPAIBRCJ") indicates the
 * following mapping when it is in its initial position:
 *
 *    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 *    | | | | | | | | | | | | | | | | | | | | | | | | | |
 *    E K M F L G D Q V Z N T O W Y H X U S P A I B R C J
 *
 * As the rotor advances, the permutation shifts by one position.  For
 * example, after this rotor advances, the bottom line of this transformation
 * is shifted one position to the left, with the E wrapping around to the
 * other side, as follows:
 *
 *    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 *    | | | | | | | | | | | | | | | | | | | | | | | | | |
 *    K M F L G D Q V Z N T O W Y H X U S P A I B R C J E
 *
 * Whenever the rotor setting advances past Z, the next rotor advances
 * one position.
 */

   private static final String STOCK_ROTOR_1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
   private static final String STOCK_ROTOR_2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
   private static final String STOCK_ROTOR_3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
   private static final String STOCK_ROTOR_4 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
   private static final String STOCK_ROTOR_5 = "VZBRGITYUPSDNHLXAWMJQOFECK";

/*
 * The Enigma reflector is also a 26-character string that works just like
 * the rotors except for the fact that it stays in one position and never
 * advances.  The reflector setting of "IXUHFEZDAOMTKQJWNSRLCYPBVG"
 * therefore means that a signal coming into the reflector on the wire
 * shown at the top of the following translation table will go out again
 * on the letter at the bottom:
 *
 *    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 *    | | | | | | | | | | | | | | | | | | | | | | | | | |
 *    I X U H F E Z D A O M T K Q J W N S R L C Y P B V G
 *
 * Note that the reflector is symmetric.  If A is transformed to I, then
 * I is transformed to A.
 */

   private static final String REFLECTOR = "IXUHFEZDAOMTKQJWNSRLCYPBVG";
   private String advanceRotor(String permutation)
   {
      char first = permutation.charAt(0);
      String removed = permutation.substring(1);
      removed += first; 
      //End of step 1
      String result = "";
      for(int i = 0; i < 26; i++)
      {
         char letter = removed.charAt(i);
         letter = (char)(letter - 1);
         if(letter < 65)
         {
            letter = 'Z';
         }
         result += letter;
      }
      return result;
   }
}
