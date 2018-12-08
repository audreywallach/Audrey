//Audrey Wallach
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Racko extends JPanel implements MouseListener, ActionListener{
   final static int MAX_CARD_VALUE = 60;
   private static LinkedList<Card> deck;
   private static LinkedList<Card> discard;
   private static Scanner input = new Scanner(System.in);
   public static boolean playing = false;
   private static LinkedList<Card> humanHand;
   private static LinkedList<Card> compHand;
   private static int selected = 0;
   JButton b1, b2, b3, b4, b5;
   Card takeCard = null;
   private BufferedImage img;
   public final String img_file = "backcard.png";
   private static boolean deckPopped = false;
   private JLabel status;
   
   
   public static void main(String[] args)
   {
      deck = new LinkedList<Card>();
      load_deck(deck);
      deck = shuffle_deck(deck);
      print_top_to_bottom(deck);
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      humanHand = new LinkedList<Card>();
      compHand = new LinkedList<Card>();
      deal_initial_hands();
      humanHand = shuffle_deck(humanHand);
      print_top_to_bottom(humanHand);
      System.out.println();
      System.out.println();
      System.out.println();
      print_top_to_bottom(compHand);
      
   }
   
   public Racko() {
      deck = new LinkedList<Card>();
      discard = new LinkedList<Card>();
      humanHand = new LinkedList<Card>();
      compHand = new LinkedList<Card>();
      playing = true;
      deckPopped = false;
      status = new JLabel();
      status.setBorder(BorderFactory.createLineBorder(Color.RED));
   	
   	// Load deck and distribute cards
      load_deck(deck);
      shuffle();
      boolean userStarts = does_user_begin();
      deal_initial_hands();
   	
      add_card_to_discard(deal_card());
   	// Determine who makes the first move
      if (!userStarts) {
         status.setText("The computer went first.");
         System.out.println("The computer went first.");
      	  // Check that the deck size isn't 0 before the computer makes a move.
         if (deck.size() == 0) {
            shuffle();
         }
         compHand = computer_play(compHand);
         print_top_to_bottom(compHand);
      }
      else {
         status.setText("You go first.");
         System.out.println("You go first.");
      }
      //Set the location for all cards in the human Hand (for UI drawing)
      for (int i = 0; i < 10; i++) {
         humanHand.set(i, new Card(humanHand.get(i).getValue(), 350, 350 - (30 * i)));
      }
   
      makeUI();
   
   }
	
	

	/*
	 * Shuffle the deck. If the deck size is 0, shuffle the discard pile
	 * and put it back in the deck.  Call the shuffle_deck helper method 
	 * below this one to shuffle a deck that is passed.
	 */
   public static void shuffle() 
   { 
      if(deck.size() == 60)
      {
         deck = shuffle_deck(deck);
      }
      if(deck.size() == 0)
      {
         discard = shuffle_deck(discard);
      }
      
   }

   /*
    *  shuffle_deck is a helper method that takes a linked list of Cards and 
    *  re-arranges it in random order.  It then returns the shuffled deck.
    *  HINT:  You can destroy the passed sDeck list since you need to return
    *  the shuffled version of the deck as a separate LinkedList.
    */
   public static LinkedList<Card> shuffle_deck(LinkedList<Card> sDeck)
   {
      LinkedList<Card> shuffled = new LinkedList<Card>();
      while(sDeck.isEmpty() != true)
        //for(int i = 0; i < 20; i++)
      {
         int m = (int)(Math.random()*sDeck.size());
         Card removed = sDeck.remove(m);
           //System.out.println(m + " " + removed);
         shuffled.addFirst(removed);
            
      }
      return shuffled;
   }

	/*
	 * Create the deck of cards with values 1 through MAX_CARD_VALUE
	 * The Card class constructor requires a position - you
	 * can send it 0,0.  The code that is already written 
	 * above resets all the positions later.
	 */
   public static void load_deck(LinkedList<Card> deck) 
   {
      for(int i = 1; i <= MAX_CARD_VALUE; i++)
      {
         Card c = new Card (i,0,0);
         deck.addLast(c);
      }
   }
   
         
  
	/*
	 * Check whether the player has racko, meaning that the cards are in
	 * ascending order from index 0 to 9.
	 */
   public static boolean check_racko(LinkedList<Card> hand) {
   	      
      int check = hand.getFirst().getValue();
      for(int i = 1; i<10; i++)
         {
            if(hand.get(i).getValue() < check)
            {
               return true;
            }
            else
               check = hand.get(i).getValue();
         }
      return false;		//placeholder
   }

	/*
	 * Deal the card by removing the top (first) card in the deck and returning it.
	 */
   public static Card deal_card() 
   {
      Card a = deck.removeFirst();
      return a;
      
   
   }

	/*
	 * Deal the initial hands by alternating between the two hands 
	 * (the computer's and user's) and distributing cards from the deck.
	 */
   public static void deal_initial_hands() 
   {
      for(int i = 0; i < 10; i++)
      {
         Card H = deal_card();
         humanHand.addFirst(H);
         Card C = deal_card();
         compHand.addFirst(C);
      
      }
   	
   }

	/*
	 * Decide whether the user or computer should begin by using a random number.
	 */
   public static boolean does_user_begin() {
      if((int)(Math.random()*100) >= 50)
      {
         return true;
      }	
      return false;	
   }

	/*
	 * Print card values in the passed hand.  (You may need this for debugging, but not otherwise.)
	 */
   public static void print_top_to_bottom(LinkedList<Card> hand) 
   {
      for(int i = hand.size()-1; i >=0; i--)
      {
         System.out.println(hand.get(i));
      }
   
   }

	/*
	 * Search through the player's hand and replace the new card with the
	 * selected card. Then put the selected card to the discard pile.
    * (Call the next method, add_card_to_discard.)
    * HINT: numberOfCard is the number on the card (the value), not the index of the card.
	 */
   public static boolean find_and_replace(Card newCard, int numberOfCard, LinkedList<Card> hand) 
   {
      Card temp = hand.getFirst();
      int index = 0;
      while(temp != null)
      {
         if(temp.getValue() == numberOfCard)
         {  
            
            hand.set(index, newCard);
            add_card_to_discard(temp);   
            return true;
         }
               
          index++;      
         temp = hand.get(index);
      }
      return false;	//placeholder         
   }

	/*
	 * Put the card in the discard pile.  (At the front of the discard list.)
	 */
   public static void add_card_to_discard(Card card) 
   {
      discard.addLast(card);
   
   }
	
	/*
	 * ------------------------------ COMPUTER STRATEGY ----------------------
	 */
	

	/*
	 * Determine the computer's strategy. Return the computer's hand after it
	 * makes its move.
	 */
   public static LinkedList<Card> computer_play(LinkedList<Card> hand) {
   	// Check that the deck size isn't 0 before the computer makes a move.
      if (deck.isEmpty()) {
         shuffle();
      }
   
      if (!discard.isEmpty())
      {
         System.out.println("Discard Card: " + discard.getFirst().getValue());
      }
      int[] bestAscendingHand = new int[10]; // Used to keep track of cards to keep
   
           // Marks the cards that make it impossible to win for replacement; otherwise,
           // keep the card value the same in the array
      for (int i = 0; i < 10; i++) {
         if ((i >= 0 && i < (9 - (MAX_CARD_VALUE - hand.get(i).getValue()))) || 
                       (i <= 9 && hand.get(i).getValue() < i + 1)) {
            bestAscendingHand[i] = -1;
         }
         else {
            bestAscendingHand[i] = hand.get(i).getValue();
         }
      }
   
   
           // An array with cards to be kept and cards to be replaced
      bestAscendingHand = find_ascending_hand(bestAscendingHand);
   
           // Determine whether card on top of the discard pile can be placed into
           // ascending hand; otherwise, draw a card from the deck and determine
           // whether to discard it or use it
      if (!discard.isEmpty() && determine_use(discard.getFirst().getValue(), bestAscendingHand)) {
         System.out.println("The computer chose from the discard pile.");
         Card discardedCard = discard.removeFirst();
         int newPos = choose_position(discardedCard.getValue(), bestAscendingHand);
         bestAscendingHand[newPos] = discardedCard.getValue();
         find_and_replace(discardedCard, hand.get(newPos).getValue(), hand);
      }
      else {
               // Take a card from the deck and determine its use
         Card drawCard = deck.removeFirst();
         System.out.println("The computer chose from the deck.");
         if (determine_use(drawCard.getValue(), bestAscendingHand)) {
            int newPos = choose_position(drawCard.getValue(), bestAscendingHand);
            bestAscendingHand[newPos] = drawCard.getValue();
            find_and_replace(drawCard, hand.get(newPos).getValue(), hand);
         }
         else {
            add_card_to_discard(drawCard);
         }
      }
      return hand;
   }

	    /**
	     * @param hi - index of the second card
	     * @param lo - index of the first card
	     * @param higherVal - value of the higher card
	     * @param lowerVal - value of the lower card
	     * @return true if there are enough cards that can go between the two cards 
	     */
   private static boolean check_if_possible(int hi, int lo, int higherVal, int lowerVal) {
           // Checks if there are enough possible cards to put between the
           // lower valued card and higher valued card
      if (hi - lo > higherVal - lowerVal) {
         return false;
      }
   
      return true;
   }

	    /*
	     * Determine which cards in the current hand should be kept. Mark the cards
	     * to be replaced with -1 and return the hand. The cards are compared by which
	     * card has the greater range of values.
	     */
   private static int[] find_ascending_hand(int[] hand) {
      int[] ascendingHand = new int[10];
      int loIndex = 0;    // Index of the lowest valued card kept at a time
      int lower = 0;      // loIndex + 1 to remain noninclusive of that index
      int lowerVal = 0;   // Value of the card at the loIndex but incremented by one to remain noninclusive
   
           // Make the array take in all the values
      for (int a = 0; a < hand.length; a++) {
         ascendingHand[a] = hand[a];
      }
   
           // Go through every card except the last card and compare it with all other cards
           // after it that are lower
      for (int i = 0; i < 9; i++) {
               // If the card is already marked for removal, don't check it
         if (ascendingHand[i] == -1) {
            continue;
         }
         else {
                   // Determine whether the card can be kept based on what the previous
                   // card's value and position
            if (!check_if_possible(i, lower, hand[i], lowerVal)) {
               ascendingHand[i] = -1;
               continue;
            }
            for (int j = i + 1; j < 10; j++) {
                       // Check cards that have lower values than the current card
               if (hand[j] < hand[i] && ascendingHand[j] != -1) {
                           // Check if there are more spaces between the card and the end
                           // or more spaces between the card and the last card that was kept
                           // This comparison is done with both the current card and the card
                           // it is being compared with
                  if (j - lower >= 9 - j) { // More cards between the card and the last KEPT card
                     if (i - lower >= 9 - i) { // More cards between the card and the last KEPT card
                                   // Check if the current card or the other card has a
                                   // higher range; if the other card is better, make the
                                   // current card -1, otherwise keep the current card
                                   // and mark the other card -1
                        if (hand[j] - hand[loIndex] > hand[i] - hand[loIndex]) {
                                       //Check whether other card has a possible place in the hand
                           if (check_if_possible(j, lower, hand[j], lowerVal)) {
                              ascendingHand[i] = -1;
                              break;
                           }
                           else {
                              ascendingHand[i] = hand[i];
                              ascendingHand[j] = -1;
                           }
                        }
                        else {
                           ascendingHand[i] = hand[i];
                           ascendingHand[j] = -1;
                        }
                     }
                     else if (i - lower < 9 - i) { // More cards between the card and the last card
                        if (hand[j] - hand[loIndex] > MAX_CARD_VALUE - hand[i]) {
                                       //Check whether other card has a possible place in the hand
                           if (check_if_possible(j, lower, hand[j], lowerVal)) {
                              ascendingHand[i] = -1;
                              break;
                           }
                           else {
                              ascendingHand[i] = hand[i];
                              ascendingHand[j] = -1;
                           }
                        }
                        else {
                           ascendingHand[i] = hand[i];
                           ascendingHand[j] = -1;
                        }
                     }
                  }
                  else if (j - lower < 9 - j) { // More cards between the card and the last card
                     if (i - lower >= 9 - i) { // More cards between the card and the last KEPT card
                        if (MAX_CARD_VALUE - hand[j] > hand[i] - hand[loIndex]) {
                                       //Check whether other card has a possible place in the hand
                           if (check_if_possible(j, lower, hand[j], lowerVal)) {
                              ascendingHand[i] = -1;
                              break;
                           }
                           else {
                              ascendingHand[i] = hand[i];
                              ascendingHand[j] = -1;
                           }
                        }
                        else {
                           ascendingHand[i] = hand[i];
                           ascendingHand[j] = -1;
                        }
                     }
                     else if (i - lower < 9 - i) { // More cards between the card and the last card
                        if (MAX_CARD_VALUE - hand[j] > MAX_CARD_VALUE - hand[i]) {
                                       //Check whether other card has a possible place in the hand
                           if (check_if_possible(j, lower, hand[j], lowerVal)) {
                              ascendingHand[i] = -1;
                              break;
                           }
                           else {
                              ascendingHand[i] = hand[i];
                              ascendingHand[j] = -1;
                           }
                        }
                        else {
                           ascendingHand[i] = hand[i];
                           ascendingHand[j] = -1;
                        }
                     }
                  }
               }
            }
         
                   // If the current card was not marked for replacement, increment the lower
                   // index variables to account for the current card being the latest addition
                   // to the best ascending hand
            if (ascendingHand[i] != -1) {
               loIndex = i;
               lower = i + 1;
               lowerVal = ascendingHand[i] + 1;
            }
         }
      }
           // Make sure that the last card in the hand can be used
      if (!check_if_possible(9, lower, hand[9], lowerVal)) {
         ascendingHand[9] = -1;
      }
      return ascendingHand;
   }

	    /*
	     * Check that the card is within range.
	     */
   private static boolean within_range(int drawCard, int hi, int lo) {
      if (drawCard < hi && drawCard > lo) {
         return true;
      }
      return false;
   }

	    /*
	     * Determine the use of the card by determining whether the current card's value
	     * can go between any two cards in the hand that have cards to be replaced between
	     * them.
	     */
   private static boolean determine_use(int value, int[] ascHand) {
      int i = 0;
      int lo = 0;
      int lowerIndex = 0;
      int hi = 0;
      while (i < 10) {
               // Look for cards that are to be kept and determine whether there are
               // cards that need to be replaced between the two
         int numOfCards = 0;
         if (ascHand[i] != -1) {
            hi = i;
            numOfCards = hi - lowerIndex;
            lowerIndex = i + 1;
         }
         else {
            if (i == 9) {
               hi = 9;
               numOfCards = hi + 1 - lowerIndex;
            }
         }
               // Determine whether there are cards that need to be replaced between the
               // two cards and if the current card's value falls between the two cards'
         if (numOfCards != 0) {
            if (ascHand[lo] != -1 && ascHand[hi] != -1) {
               if (within_range(value, ascHand[hi], ascHand[lo])) {
                  return true;
               }
            }
                   // Check looking at the cards from 0 to the current card's value or the
                   // current card's value to MAX_CARD_VALUE
            else if ((ascHand[lo] == -1 && within_range(value, ascHand[hi], 0)) || 
                           (ascHand[hi] == -1 && within_range(value, MAX_CARD_VALUE+1, ascHand[lo]))) {
               return true;
            }
         }
      
               // Update to check for the next range
         if (ascHand[i] != -1) lo = i;
         i++;
      }
      return false;
   }

	    /*
	     * Choose a position for the card to be placed. This is assuming that it has
	     * already been determined that the card can be used. Look for the range it falls
	     * in and place the card next to the card with the closer value.
	     */
   private static int choose_position(int card, int[] bestAscendingHand) {
      int i = 0;
      int lo = 0;
      int hi = 0;
      while (i < 10) {
         if (bestAscendingHand[i] != -1) {
            hi = i;
            if (lo == 0 && bestAscendingHand[lo] == -1) {
               if (within_range(card, bestAscendingHand[hi], 0)) {
                           // If the card is closer in value to the higher card,
                           // return the index of the card behind the higher card
                  if (bestAscendingHand[hi] - card < card) {
                     bestAscendingHand[hi - 1] = card;
                     return hi - 1;
                  }
                  else {
                     bestAscendingHand[0] = card;
                     return 0;
                  }
               }
            }
            else {
               if (within_range(card, bestAscendingHand[hi], bestAscendingHand[lo])) {
                           // If the card is closer in value to the lower card,
                           // return the index of the card in front the lower card
                  if (bestAscendingHand[hi] - card < 
                                   card - bestAscendingHand[lo]) {
                     bestAscendingHand[hi - 1] = card;
                     return hi - 1;
                  }
                  else {
                     bestAscendingHand[lo + 1] = card;
                     return lo + 1;
                  }
               }
            }
         }
               // Determine whether the card should be in the last slot or closer to the
               // lower card
         else if (i == 9 && bestAscendingHand[9] == -1) {
            if (within_range(card, MAX_CARD_VALUE+1, bestAscendingHand[lo])) {
            
               if (MAX_CARD_VALUE - card < card - bestAscendingHand[lo]) {
                  bestAscendingHand[9] = card;
                  return 9;
               }
               else {
                  bestAscendingHand[lo + 1] = card;
                  return lo + 1;
               }
            }
         }
      
               // Update to check for the next range
         if (bestAscendingHand[i] != -1) lo = i;
      
         i++;
      }
      return -1;
   }


	
	/**
	 *  USER INTERFACE METHODS
	 */
	
   public void makeUI() {
      addMouseListener(this);
        
      b1 = new JButton("Choose Card from Discard Pile");
      b2 = new JButton("Choose Card from Deck");
      b3 = new JButton("Replace with your Selected Card");
      b4 = new JButton("Keep");
      b5 = new JButton("Discard");
        
        //Listen for actions on buttons 1, 2, 3, 4, and 5.
      b1.addActionListener(this);
      b2.addActionListener(this);
      b3.addActionListener(this);
      b4.addActionListener(this);
      b5.addActionListener(this);
        
      add(b1);
      add(status);
      add(b2);
      add(b3);
      add(b4);
      add(b5);
      b3.setVisible(false);
      b4.setVisible(false);
      b5.setVisible(false);
      b1.setActionCommand("selectFromDis");
      b2.setActionCommand("selectFromDec");
      b3.setActionCommand("switch");
      b4.setActionCommand("keep");
      b5.setActionCommand("discard");
        
      setFocusable(true);
        
      try {
         if (img == null) {
            img = ImageIO.read(new File(img_file));
         }
      } catch (IOException e) {
         System.out.println("Internal Error:" + e.getMessage());
      }
    
   }

	/*
	 * Give buttons actions
	 */
   public void actionPerformed(ActionEvent e) {
   	// Select button to choose card from discard pile
      if ("selectFromDis".equals(e.getActionCommand())) {
         b1.setVisible(false);
         b2.setVisible(false);
         b3.setVisible(true);
         takeCard = discard.removeFirst();
         status.setText("You chose from the discard pile. Select a card to replace.");
      }
      // Select button to choose card from deck
      else if ("selectFromDec".equals(e.getActionCommand())) {
         b1.setVisible(false);
         b2.setVisible(false);
         b4.setVisible(true);
         b5.setVisible(true);
         if (deck.size() == 0) {
            shuffle();
         }
         deckPopped = true;
         status.setText("You are selecting from the deck.");
         takeCard = deal_card();
      }
      // Select button to replace a card in the hand with the selected card
      else if ("switch".equals(e.getActionCommand())) {
         if (selected != 0) {
            b1.setVisible(true);
            b2.setVisible(true);
            b3.setVisible(false);
            status.setText("Your card was replaced.");
            System.out.println("Replacing with card " + takeCard.getValue());
            find_and_replace(takeCard, selected, humanHand);
            selected = 0;
            //SEH - reset card locations to normal
            for (int i = 0; i < 10; i++) {
               humanHand.set(i, new Card(humanHand.get(i).getValue(), 350, 350 - (30 * i)));
            }
         
            if (deck.size() == 0) {
               shuffle();
            }				
            if (check_racko(humanHand)) {
               b1.setVisible(false);
               b2.setVisible(false);
               status.setText("You win!");
               status.setForeground(Color.RED);
               status.setFont(new Font("Arial", Font.BOLD, 20));
            }
            else if (check_racko(compHand)) {
               b1.setVisible(false);
               b2.setVisible(false);
               status.setText("Computer wins!");
               status.setForeground(Color.RED);
               status.setFont(new Font("Arial", Font.BOLD, 20));
            }
            compHand = computer_play(compHand);
         }
      }
      	// Select button to keep card drawn from deck
      else if ("keep".equals(e.getActionCommand())) {
         b4.setVisible(false);
         b5.setVisible(false);
         b3.setVisible(true);
         deckPopped = false;
            //SEH - Fix #1
      		//takeCard = deck.removeFirst();
         status.setText("Card kept. Select a card to replace with your new card.");
         System.out.println("Card kept");
      }
      	// Select button to discard card drawn from deck
      else if ("discard".equals(e.getActionCommand())) {
         b1.setVisible(true);
         b2.setVisible(true);
         b4.setVisible(false);
         b5.setVisible(false);
         deckPopped = false;
            //SEH - Fix #2
      		//takeCard = deck.removeFirst();
         System.out.println("Card discarded: " + takeCard.getValue());
         status.setText("Card discarded: " + takeCard.getValue());
         add_card_to_discard(takeCard);
         compHand = computer_play(compHand);
         int discardCard = discard.getFirst().getValue();
         System.out.println("Discard Card: " + discardCard);
         if (deck.size() == 0) {
            shuffle();
         }
      }
      System.out.println("Deck has " + deck.size() + " cards.");
      System.out.println("Discard has " + discard.size() + " cards.");
      repaint();
   }

	/*
	 * Draw images.
	 */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
   	// Draw cards descending down from last slot to first
      for (int i = 9; i >= 0; i--) {
         humanHand.get(i).draw(g);
         g.drawString(String.valueOf(humanHand.get(i).getValue()), 
            	humanHand.get(i).getX() + 20 + (4 * humanHand.get(i).getValue()), 
            	humanHand.get(i).getY() + 25);
      }
   	// Draw discard pile with top card showing
      g.drawString("Discard Pile", 30, 320);
      if (discard.size() > 0) {
         for (int i = discard.size() - 1; i >= 0; i--) {
            discard.get(i).setX(20);
            discard.get(i).setY(330 + (i * 2));
            discard.get(i).draw(g);
            g.drawString(String.valueOf(discard.get(i).getValue()), 
               	discard.get(i).getX() + 20 + (4 * discard.get(i).getValue()), 
               	discard.get(i).getY() + 25);
         }
      }
   	// Draw deck with top card showing when player has chosen to draw a card
      g.drawString("Deck Pile", 30, 90);
      if (deck.size() > 0) {
         g.drawImage(img, 5, 70, 340, 250, null);
      }
      if (deckPopped == true) {
         //SEH - Fix #3
      	//Card popped = deck.getFirst();
         Card popped = takeCard;
         popped.setX(20);
         popped.setY(100);
         popped.draw(g);
         g.drawString(String.valueOf(popped.getValue()), popped.getX() + 
            	20 + (4 * popped.getValue()),popped.getY() + 25);
      }
   }

	/*
	 * Allow user to click on a card to select it. Selected card will move up to indicate it
	 * has been selected and move back down to indicate it has been unselected.
	 */
   @Override
   public void mouseClicked(MouseEvent e) {
   	// TODO Auto-generated method stub
      for (int i = 9; i >= 0; i--) {
         if (selected == 0) {
            if (e.getX() >= humanHand.get(i).getX() && e.getX() <= humanHand.get(i).getX() + 300
            		&& e.getY() >= humanHand.get(i).getY() && e.getY() <= humanHand.get(i).getY() + 30) {
               humanHand.get(i).setY(humanHand.get(i).getY() - 30);
               selected = humanHand.get(i).getValue();
               repaint();
            }
         }
         else if (selected == humanHand.get(i).getValue()) {
            if (e.getX() > humanHand.get(i).getX() && e.getX() < humanHand.get(i).getX() + 300
            		&& e.getY() > humanHand.get(i).getY() && e.getY() < humanHand.get(i).getY() + MAX_CARD_VALUE) {
               humanHand.get(i).setY(humanHand.get(i).getY() + 30);
               selected = 0;
               repaint();
            }
         }
      }
      /*
   	if (selected == 0) {
   
   		if (e.getX() > humanHand.get(0).getX() && e.getX() < humanHand.get(0).getX() + 300
   				&& e.getY() > humanHand.get(0).getY() && e.getY() < humanHand.get(0).getY() + 201) {
   			humanHand.get(0).setY(humanHand.get(0).getY() - 30);
   			selected = humanHand.get(0).getValue();
   			repaint();
   		}
   	}
   	else if (selected == humanHand.get(0).getValue()) {
   		if (e.getX() > humanHand.get(0).getX() && e.getX() < humanHand.get(0).getX() + 300
   				&& e.getY() > humanHand.get(0).getY() && e.getY() < humanHand.get(0).getY() + 201) {
   			humanHand.get(0).setY(humanHand.get(0).getY() + 30);
   			selected = 0;
   			repaint();
   		}
   	}
      */
   }
   @Override
   public void mouseEntered(MouseEvent e) {
   	// TODO Auto-generated method stub
   
   }
   @Override
   public void mouseExited(MouseEvent e) {
   	// TODO Auto-generated method stub
   
   }
   @Override
   public void mousePressed(MouseEvent e) {
   	// TODO Auto-generated method stub
   
   }
   @Override
   public void mouseReleased(MouseEvent e) {
   	// TODO Auto-generated method stub
   
   }
	
   public void callTimer() {
   	
   }

}
