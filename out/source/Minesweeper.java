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

public final static int NUM_ROWS = 20;
public final static int NUM_COLS = 20;

public void setup () {
    
    textAlign(CENTER,CENTER);
    
    // make the manager
    Interactive.make( this );
    
    //your code to initialize buttons goes here
    buttons = new MSButton[NUM_ROWS][NUM_COLS];
    for(int r = 0; r < NUM_ROWS; r++)
        for(int c = 0; c < NUM_COLS; c++)
            buttons[r][c] = new MSButton(r,c);
}

public void draw () {
    background( 0 );
}

public class MSButton {
    private int r, c;
    private float x,y, width, height;
    private boolean clicked;
    private String label;
    
    public MSButton ( int rr, int cc ) {
        width = 400/NUM_COLS;
        height = 400/NUM_ROWS;
        r = rr;
        c = cc; 
        x = c*width;
        y = r*height;
        label = "";
        clicked = false;
        Interactive.add( this ); // register it with the manager
    }

    public boolean isClicked() {
        return clicked;
    }
    
    public void mousePressed () {
        if(!clicked)
            clicked = true;
        else   
            clicked = false;
    }

    public void draw () {    
        if(clicked)
            fill( 200 );
        else 
            fill( 100 );

        rect(x, y, width, height);
        fill(0);

        for(int r = 0; r < NUM_ROWS; r++)
            for(int c = 0; c < NUM_COLS; c++)
                if(buttons[r][c].isClicked());
                    buttons[r][c].clicked = false;
    }

    public boolean isValid(int r, int c){
        if((r < NUM_ROWS && r >= 0) && (c < NUM_COLS && c >= 0))
            return true;
        else
            return false;
    }
}


/*
import de.bezier.guido.*;
//Declare and initialize NUM_ROWS and NUM_COLS = 20
private MSButton[][] buttons; //2d array of minesweeper buttons

public final static int NUM_ROWS = 40;
public final static int NUM_COLS = 40;

private ArrayList <MSButton> bombs; //ArrayList of just the minesweeper buttons that are mined

void setup () {
    size(400, 400);
    textAlign(CENTER,CENTER);
    
    // make the manager
    Interactive.make( this );
    
    //your code to initialize buttons goes here
    buttons = new MSButton[NUM_ROWS][NUM_COLS];
    for(int r = 0; r < NUM_ROWS; r++)
        for(int c = 0; c < NUM_COLS; c++)
            buttons[r][c] = new MSButton(r,c);

    setBombs();
}

public void draw () {
    background( 0 );
    if(isWon())
        displayWinningMessage();
}

public boolean isWon() {
    //your code here
    return false;
}

public void displayLosingMessage() {
    //your code here
}

public void displayWinningMessage() {
    //your code here
}

public class MSButton {
    private int r, c;
    private float x,y, width, height;
    private boolean clicked, marked;
    private String label;
    
    public MSButton ( int rr, int cc ) {
        width = 400/NUM_COLS;
        height = 400/NUM_ROWS;
        r = rr;
        c = cc; 
        x = c*width;
        y = r*height;
        label = "";
        marked = clicked = false;
        Interactive.add( this ); // register it with the manager
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isClicked() {
        return clicked;
    }
    // called by manager
    
    public void mousePressed () {
        if(!clicked)
            clicked = true;
        else   
            clicked = false;
        //your code here
    }

    public void draw () {    
        if (marked)
            fill(0);
        // else if( clicked && bombs.contains(this) ) 
        //     fill(255,0,0);
        else if(clicked)
            fill( 200 );
        else 
            fill( 100 );

        rect(x, y, width, height);
        fill(0);
        text(label,x+width/2,y+height/2);
    }

    public void setLabel(String newLabel) {
        label = newLabel;
    }

    public boolean isValid(int r, int c) {
        //your code here
        return false;
    }
    
    public int countBombs(int row, int col) {
        int numBombs = 0;
        //your code here
        return numBombs;
    }
}
*/
  public void settings() {  size(400, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Minesweeper" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
