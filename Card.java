//Audrey Wallach
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 *  The Card class models a card with data to allow the display of the card
 *  in a specific position on the screen.  The code to manage the x, y, pos_x,
 *  and pos_y values (display locations)is already written into the Racko class.
 */
 
public class Card {
	private int value;
	private BufferedImage img;
	public final String img_file = "card.png";
	private int pos_x = 30;
	private int pos_y = 30;
	private int x;
	private int y;
	
   //The Card constructor takes a card value and also an x and a y value for the 
   //  initial location of the card on the screen.  
	public Card(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		this.pos_x = x;
		this.pos_y = y;
		
		try {
			if (img == null) {
				img = ImageIO.read(new File(img_file));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}
	
   // Returns the number on the card
	public int getValue() {
		return value;
	}
	
   // Changes the card to a different card value. (like exchanging two cards in the rack)
	public void setValue(int newVal) {
		value = newVal;
	}
	
   //Retrieves the card's horizontal position
	public int getX() {
		return pos_x;
	}
	
   //Retrieves the card's vertical position
	public int getY() {
		return pos_y;
	}

   //Sets the card's horizontal position
	public void setX(int newX) {
		pos_x = newX;
	}
	
   //Sets the card's vertical position
	public void setY(int newY) {
		pos_y = newY;
	}
	
   //Draws the card onto the user interface (UI)
	public void draw(Graphics g) {
		g.drawImage(img, pos_x, pos_y, 300, 201, null);
	}
   
   //Description of the card.
   public String toString()
   {
      return "Card: value " + value + " at position(x,y)=(" + x + "," + y + ")";
   }

}
