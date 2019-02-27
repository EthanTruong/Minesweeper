import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import de.bezier.guido.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Minesweeper extends PApplet {


private MSButton[][] buttons; //2d array of minesweeper buttons

public final static int NUM_ROWS = 100;
public final static int NUM_COLS = 100;

boolean value = false;

public void setup () {
    
    textAlign(CENTER,CENTER);
    
    // make the manager
    Interactive.make( this );
    
    //your code to initialize buttons goes here
    buttons = new MSButton[NUM_ROWS][NUM_COLS];
    for(int r = 0; r < NUM_ROWS; r++)
        for(int c = 0; c < NUM_COLS; c++)
            buttons[r][c] = new MSButton(r,c);

    // starting populated tiles
    buttons[NUM_ROWS/2-1][NUM_COLS/2].populated = true;
    buttons[NUM_ROWS/2-1][NUM_COLS/2+1].populated = true;
    buttons[NUM_ROWS/2][NUM_COLS/2].populated = true;
    buttons[NUM_ROWS/2][NUM_COLS/2-1].populated = true;
    buttons[NUM_ROWS/2+1][NUM_COLS/2].populated = true;
}

public void draw () {
    background( 0 );
}

public class MSButton {
    private int r, c;
    private float x,y, width, height;
    private boolean populated;
    private String label;
    private ArrayList<Integer> markedForLife = new ArrayList<Integer>();
    private ArrayList<Integer> markedForDeath = new ArrayList<Integer>();
    
    public MSButton ( int rr, int cc ) {
        width = 800/NUM_COLS;
        height = 800/NUM_ROWS;
        r = rr;
        c = cc; 
        x = c*width;
        y = r*height;
        label = "";
        populated = false;
            
        Interactive.add( this ); // register it with the manager
    }

    public boolean isPopulated() {
        return populated;
    }
    
    public void mousePressed () {
        if(!populated)
            populated = true;
        else   
            populated = false;
    }

    public void draw () {    
        if(populated)
            fill( 200 );
        else 
            fill( 100 );

        rect(x, y, width, height);
        fill(0);
        if(value==true) {
            for(int r = 0; r < NUM_ROWS; r++)
                for(int c = 0; c < NUM_COLS; c++)
                    if(!buttons[r][c].isPopulated() && countNeighborPopulated(r, c) == 3) {
                        markedForLife.add(r);
                        markedForLife.add(c);
                    } 
                    else if(buttons[r][c].isPopulated() && (countNeighborPopulated(r, c) >= 4 || countNeighborPopulated(r, c) <= 1)) {
                        markedForDeath.add(r);
                        markedForDeath.add(c);
                    }
            for(int i = 0 ; i < markedForLife.size(); i+=2)
                buttons[markedForLife.get(i)][markedForLife.get(i+1)].populated = true;
            for(int i = 0 ; i < markedForDeath.size(); i+=2)
                buttons[markedForDeath.get(i)][markedForDeath.get(i+1)].populated = false;
            markedForLife.clear();
            markedForDeath.clear();
            value = false;
        }
    }
}

public int countNeighborPopulated(int row, int col){
    int count = 0;
    for(int r = row-1; r <= row+1; r++)
        for(int c = col-1; c <= col+1; c++)
            if(isValid(r, c) && buttons[r][c].isPopulated())
                count++;
    if(buttons[row][col].isPopulated())
        count--;
    return count;
}

public boolean isValid(int r, int c) {
    if((r < NUM_ROWS && r >= 0) && (c < NUM_COLS && c >= 0))
        return true;
    else
        return false;
}

public void keyPressed() {
  if (value == false)
    value = true;
  else
    value = false;
}
  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Minesweeper" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
