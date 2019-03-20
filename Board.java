import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/** 
*@author  Vlad Diaconu
*/
/*This class represents the window for the game and the checkersboard game and it's mechanics  */

public class Board extends JFrame implements ActionListener
{    
    private JFrame frame= new JFrame("Checkers Game");
    private Square[][] square= new Square[8][8];
    private int x,y;
    private boolean inMove=false;
    private boolean turn=true;
    private int a=-1,b=-1,c=-1,d=-1;
    
    /** 
    *Constructor for a board of checkers.
    *@param x, @param y are the positions a square has to memorize 
    *@param button feeds a JButton type to the square in order for the board to be filled
    */
    
    public Board()
    {
        frame.setSize(800,800);
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(8,8));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for(x=0;x< 8;++x)
       {
         for(y=0;y< 8;++y)
         {  
          JButton button= new JButton();
          square[x][y]= new Square(button,x,y);
          square[x][y].button.addActionListener(this); //Also initialise the starting positions for each piece
          if((x+y)%2!=0)
          { 
            square[x][y].addWhite(button); //call function to add a white square to the board
            if(x>=5 && x<=7)
            
            square[x][y].addWhitePiece(); //add white pieces to the last 3 rows, on white squares only
            
            if(x>=0 && x<=2)
            
            square[x][y].addRedPiece(); //add red pieces to the first 3 rows, on white squares only
          }

          else 
           square[x][y].addBlack(button);//call function to add a black square to the board

          container.add(square[x][y].button);//add all squares to the board
         }
       }   
    }

    /**
    *Show the contents of the created board
     */
    public void display()
    {
      frame.setResizable(false);//dont expose the mistakes
      frame.setVisible(true); //display the board on screen for a game to begin
    }

    private boolean resetTurn(boolean turn)
    {
     if(turn==true)
      return true;

     return false;
    }

   /** 
     *Called function to see the available moves for a piece
    * @param x is the initial position to check for available 
    * @param y is the initial position to check for available 
    * The moveTo() method is called privately within this method]
   */
    
    private void setHighlights(int x, int y)
    {
        if(square[x][y].hasWhitePieceOn(square[x][y].button)==true)
        // only white pieces
        {
        if(x-1>=0 && y-1>=0 && x-1<8 && y-1<8)
        //making sure the bellow if doesn't go out of bounds
          if(square[x-1][y-1].hasWhitePieceOn(square[x-1][y-1].button)==false && square[x-1][y-1].hasRedPieceOn(square[x-1][y-1].button)==false)
          //top left  
            {
              square[x-1][y-1].addSelected(square[x-1][y-1].button);
            }
        else if(x-2>=0 && y-2>=0 && x-2<=7 && y-2<=7)
        //check if the capture-type move that's top left
          {
            if(square[x-2][y-2].hasWhitePieceOn(square[x-2][y-2].button)==false && square[x-2][y-2].hasRedPieceOn(square[x-2][y-2].button)==false)
             {
               if(square[x-1][y-1].hasWhitePieceOn(square[x-1][y-1].button)==false)
                square[x-2][y-2].addSelected(square[x-2][y-2].button);
             }
          } 
        if(x-1>=0 && y+1>=0 && x-1<8 && y+1<8)
        //making sure the bellow if doesn't go out of bounds
          if(square[x-1][y+1].hasWhitePieceOn(square[x-1][y+1].button)==false && square[x-1][y+1].hasRedPieceOn(square[x-1][y+1].button)==false)
          //top right
            {
              square[x-1][y+1].addSelected(square[x-1][y+1].button);
            }
        else if(x-2>=0 && y+2>=0 && x-2<=7 && y+2<=7)
        //making sure the bellow if doesn't go out of bounds
        {
           if(square[x-2][y+2].hasRedPieceOn(square[x-2][y+2].button)==false && square[x-2][y+2].hasWhitePieceOn(square[x-2][y+2].button)==false)
            {
              if(square[x-1][y+1].hasWhitePieceOn(square[x-1][y+1].button)==false)
              square[x-2][y+2].addSelected(square[x-2][y+2].button);
            }
        }
        }
        if(square[x][y].hasRedPieceOn(square[x][y].button)==true)
        //only red pieces
        {
          if(x+1>=0 && y+1>=0 && x+1<8 && y+1<8) 
          //making sure the bellow if doesn't go out of bounds         
            if(square[x+1][y+1].hasWhitePieceOn(square[x+1][y+1].button)==false && square[x+1][y+1].hasRedPieceOn(square[x+1][y+1].button)==false)
            //bottom right
             { 
               square[x+1][y+1].addSelected(square[x+1][y+1].button);
             }
          else if(x+2>=0 && y+2>=0 && x+2<=7 && y+2<=7)
          {
            if(square[x+2][y+2].hasWhitePieceOn(square[x+2][y+2].button)==false && square[x+2][y+2].hasRedPieceOn(square[x+2][y+2].button)==false)
             {
               if(square[x+1][y+1].hasRedPieceOn(square[x+1][y+1].button)==false)
               square[x+2][y+2].addSelected(square[x+2][y+2].button);
             }
          }
          if(x+1>=0 && y-1>=0 && x+1<8 && y-1<8)
          //making sure the bellow if doesn't go out of bounds
            if(square[x+1][y-1].hasWhitePieceOn(square[x+1][y-1].button)==false && square[x+1][y-1].hasRedPieceOn(square[x+1][y-1].button)==false)
            //bottom left
              {
                square[x+1][y-1].addSelected(square[x+1][y-1].button);
              }  
          else if(x+2>=0 && y-2>=0 && x+2<8 && y-2<=7)
          //making sure the bellow if doesn't go out of bounds     
             {
               if(square[x+2][y-2].hasWhitePieceOn(square[x+2][y-2].button)==false && square[x+2][y-2].hasRedPieceOn(square[x+2][y-2].button)==false)
                {
                  if(square[x+1][y-1].hasRedPieceOn(square[x+1][y-1].button)==false)
                  square[x+2][y-2].addSelected(square[x+2][y-2].button);
                }
             }
        }
    }

    /**
    *Called method privately through moveTo() in order to clear all yellow-ed squares on the board for further moves to happen
    */

    private void clearHighlights()
    {
      for(int k=0;k<8;++k)
      {
        for(int z=0;z<8;++z)
        {
          if(square[k][z].hasHighlight(square[k][z].button))
           {
             square[k][z].addWhite(square[k][z].button);
           }
        }
      }
    }

   /**
    *Called method privately through moveTo() in order to allow a legal move to happen
    */

    private boolean canMoveTo(int x, int y, int i, int j)
    {
      if(square[x][y].hasWhitePieceOn(square[x][y].button)==true)
      // Legal moves for a white piece
       {
         if(i==x-1 && j==y-1)
         // top left is legal move
       {
         if(square[i][j].hasWhitePieceOn(square[i][j].button)==false)
         //only if there is no white piece on that square
         {
          if(square[i][j].hasRedPieceOn(square[i][j].button)==false)
          //only if there is no red piece on that square
          {  
            return true;
          }
         }
       }
       if(i==x-1 && j==y+1)
       //top right is legal move
         if(square[i][j].hasWhitePieceOn(square[i][j].button)==false)
         //only if there is no white piece on that square
          {
           if(square[i][j].hasRedPieceOn(square[i][j].button)==false)
           //only if there is no red piece on that square
          return true;
          }
       }
      if(square[x][y].hasRedPieceOn(square[x][y].button)==true)
      // Legal moves for a red piece
      { 
        if(i==x+1 && j==y-1)
        // botton left is legal move
         {
           if(square[i][j].hasRedPieceOn(square[i][j].button)==false)
            // only if there is no red piece on that square
           {
             if(square[i][j].hasWhitePieceOn(square[i][j].button)==false)
              // only if there is no white piece on that square
             return true;
           }
         }
         if(i==x+1 && j==y+1)
         // bottom right is legal move
         {
           if(square[i][j].hasRedPieceOn(square[i][j].button)==false)
           // only if there is no red piece on that square
           {
             if(square[i][j].hasWhitePieceOn(square[i][j].button)==false)
             // only if there is no white piece on that square
             return true;
           }
         }
      }
      //return false if there is an illegal move in play
       return false;

    } 

    /** 
     * Called function to privately check if a piece can capture another
     * @param x and @param y are the initial positions
     * @param i and @param j are the desired positions
     * @return true if a piece can capture another
     * @return false otherwise 
    */

    private boolean canCapture(int x,int y,int i,int j)
    {
      if(square[x][y].hasWhitePieceOn(square[x][y].button)==true)
      {
        if(i==x-2 && j==y-2)
         {
           if(square[i][j].hasRedPieceOn(square[i][j].button)==false && square[i][j].hasWhitePieceOn(square[i][j].button)==false)
            {
              if(square[i+1][j+1].hasRedPieceOn(square[i+1][j+1].button)==true)
               {
                 return true;
               }
            }
         }
        if(i==x-2 && j==y+2)
        {
          if(square[i][j].hasRedPieceOn(square[i][j].button)==false && square[i][j].hasWhitePieceOn(square[i][j].button)==false)
           {
             if(square[i+1][j-1].hasRedPieceOn(square[i+1][j-1].button)==true)
              {
                return true;
              }
           }
        }
      }
    if(square[x][y].hasRedPieceOn(square[x][y].button)==true)
    {
      if(i==x+2 && j==y+2)
      {
       if(square[i][j].hasRedPieceOn(square[i][j].button)==false && square[i][j].hasWhitePieceOn(square[i][j].button)==false)
        {
          if(square[i-1][j-1].hasWhitePieceOn(square[i-1][j-1].button)==true)
           {
             return true;
           }
        }  
      }
      if(i==x+2 && j==y-2)
      {
       if(square[i][j].hasRedPieceOn(square[i][j].button)==false && square[i][j].hasWhitePieceOn(square[i][j].button)==false)
        {
          if(square[i-1][j+1].hasWhitePieceOn(square[i-1][j+1].button)==true)
           {
             return true;
           }
        }
      }
    }
      return false;
    }
    /**
    * Called method privately through actionPerformed in order to make a move on the board
    * @param x, @param y are the initial positions on the board
    * @param i, @param j are the desired *legal* positions on the board
     */
    private void moveTo(int x, int y,int i, int j)
    {
      
      if(square[x][y].hasWhitePieceOn(square[x][y].button)==true && resetTurn(turn)==true) 
      //do a move specific to white pieces
      {
        if(canMoveTo(x,y,i,j)==true)
        //only a legal move is going to end a move combo
        {
          square[x][y].addWhite(square[x][y].button);
          square[i][j].addWhitePiece();
          turn=false; //switch to red's turn
        }
        if(i==x-2 && j==y-2 && canCapture(x,y,x-2,y-2)==true)
         {
          square[x][y].addWhite(square[x][y].button);
          square[x-1][y-1].addWhite(square[x-1][y-1].button);
          square[i][j].addWhitePiece();
          turn=false; //switch to red's turn 
         }
        if(i==x-2 && j==y+2 && canCapture(x,y,x-2,y+2)==true)
         {
          square[x][y].addWhite(square[x][y].button);
          square[x-1][y+1].addWhite(square[x-1][y+1].button);
          square[i][j].addWhitePiece();
          turn=false; //switch to red's turn 
         }
      }     
      if(square[x][y].hasRedPieceOn(square[x][y].button)==true && resetTurn(turn)==false)
      //do a move specific to red pieces
      {
        if(canMoveTo(x,y,i,j)==true)
        //only a legal move is going to end a move combo
        {
        square[x][y].addWhite(square[x][y].button);
        square[i][j].addRedPiece();
        turn= true; //switch to white's turn
        }
        if(i==x+2 && j==y+2 && canCapture(x,y,x+2,y+2)==true)
        {
         square[x][y].addWhite(square[x][y].button);
         square[x+1][y+1].addWhite(square[x+1][y+1].button);
         square[i][j].addRedPiece();
         turn= true; //switch to white's turn 
        }
        if(i==x+2 && j==y-2 && canCapture(x,y,x+2,y-2)==true)
        {
         square[x][y].addWhite(square[x][y].button);
         square[x+1][y-1].addWhite(square[x+1][y-1].button);
         square[i][j].addRedPiece();
         turn= true; //switch to white's turn
        }
      }
      clearHighlights();
      //call clearHighlights() in order to allow another move to happen
    a=-1;b=-1;c=-1;d=-1; 
    //clear the variables for another move to happen
    }


    /**
    * This function remembers the positions of a move.
    * @param inMove keeps track if a move combo is in progress or not, 
     that way @param a,b,c,d store the positions of a move combo respectively (a->Current X b-> Current Y c-> Desired X d-> Desired Y)
    */

    @Override
    public void actionPerformed(ActionEvent e)
    {
       
       JButton source=(JButton)e.getSource(); // get the buttons being pressed
       
       
       for(int i=0;i<8;++i)
      {
         for(int j=0;j<8;++j)
        {
           if(source==square[i][j].button) //find the matching button in the board
           {        

               if(inMove==true)//if a move combo is in progress
               {
                c=square[i][j].getXPosition();
                d=square[i][j].getYPosition();
                inMove=false;
 
               }  
              else  //if it's just the first click of a move combo
               {                   
                a=square[i][j].getXPosition();
                b=square[i][j].getYPosition(); 
                inMove=true;
                setHighlights(a,b); //create highlights for a player to see the legal available moves
               }          
           }
        }
      }

      if(a!=-1 &&b!=-1 &&c!=-1 &&d!=-1) //only call moveTo() if both the current position and desired position have been selected
       moveTo(a,b,c,d);
       
    }
}