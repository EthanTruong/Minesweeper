import de.bezier.guido.*;
private MSButton[][] buttons; //2d array of minesweeper buttons

public final static int NUM_ROWS = 20;
public final static int NUM_COLS = 20;

boolean value = false;

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
}

public void draw () {
    background( 0 );
}

public class MSButton {
    private int r, c;
    private float x,y, width, height;
    private boolean populated;
    private String label;
    private int[] markedForDeath;
    private int[] markedForLife;
    
    public MSButton ( int rr, int cc ) {
        width = 400/NUM_COLS;
        height = 400/NUM_ROWS;
        r = rr;
        c = cc; 
        x = c*width;
        y = r*height;
        label = "";
        populated = false;
        for(int i = 0 ; i < NUM_ROWS*2; i++)
            
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
                        buttons[r][c].populated = true;
                    }
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

public boolean isValid(int r, int c){
    if((r < NUM_ROWS && r >= 0) && (c < NUM_COLS && c >= 0))
        return true;
    else
        return false;
}

void keyPressed() {
  if (value == false)
    value = true;
  else
    value = false;
}