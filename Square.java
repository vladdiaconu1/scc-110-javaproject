import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**  
*@author  Vlad Diaconu
*/
/*This class represents a single square on a board */
public class Square
{
   private int x;
   private int y;
   private ImageIcon emptyWhite= new ImageIcon("empty.png");
   private ImageIcon emptyBlack= new ImageIcon("empty2.png");
   private ImageIcon whitePiece= new ImageIcon("white.png");
   private ImageIcon redPiece= new ImageIcon("red.png");
   private ImageIcon selected= new ImageIcon("selected.png");
   JButton button=new JButton();
  
  /*Constructor class that creates a single,clickable square, which remembers it's position on the board in a 8x8 matrix */
   
   public Square(JButton b,int x, int y)
   {
     this.button=b;
     this.x=x;
     this.y=y;
     b.setSize(100,100);
   }

   /**
    *Called accessors in order to access X position or Y position on board
    * @return x as the position on the oX axys
    * @return y as the position on the Yo axys
    */

   public int getXPosition()
   {
      return x;
   }
     public int getYPosition()
   {
      return y;
   }
 /** 
   * Called function for adding a white square 
   * @param button takes a JButton type in order to add a white square to it as an icon
   */

  public void addWhite(JButton button)
  {
    button.setIcon(emptyWhite);
   // button.setBorder(BorderFactory.createEmptyBorder());
  }

 /** 
   * Called function for adding a black square 
   * @param button takes a JButton type in order to add a black square to it as an icon
   */
  public void addBlack(JButton button)
  {
    button.setIcon(emptyBlack);
    //button.setBorder(BorderFactory.createEmptyBorder());
  } 

/*Called function to add a white piece to a specific square */
  public void addWhitePiece()
  {
    button.setIcon(whitePiece);
  }

  /*Called function for adding a red piece to specific square*/
   public void addRedPiece()
  {
    button.setIcon(redPiece);
  }
  /*
  *Called function for highlighting the available moves a player has during a turn
  */

  public void addSelected(JButton button)
  {
     button.setIcon(selected);
  }

  /** 
   *Called function for checking if a white piece is present on a specific square
   *@return true if a piece is present on the said square or false otherwise
   */


  public boolean hasWhitePieceOn(JButton button)
  {
    if(button.getIcon()==whitePiece)
     return true;
    else
     return false;
  }
    /** 
   *Called function for checking if a red piece is present on a specific square
   *@return true if a piece is present on the said square or false otherwise
   */
   public boolean hasRedPieceOn(JButton button)
  {
    if(button.getIcon()==redPiece)
     return true;
    else
     return false;
  }

   public boolean hasHighlight(JButton button)
  {
    if(button.getIcon()==selected)
     return true;

    return false;
  }
}