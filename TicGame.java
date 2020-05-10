import java.util.Scanner;
/**
 * Where the TicTacToe game is created, modified and checked when it
 * is finished. Also where the main method is located.
 * @author Jordan Chin
 * @version 1.0
 */
public class TicGame
{
    private char[][] m_Grid;        // KRIS: More descriptive value names, prefix with "m_" to clarify that it's a [m]ember variable. 
    private const int m_GridSize;   // KRIS: Making m_GridSize const so that no one can change it(and so that we know for sure that no one can, either 
    private static String m_Error;  // intentionally or accidentaly) during the game. Setting it to const is a good  
    private int m_HumanWins;        // idea here as >currently< we are unable to switch size during the game, however in another
    private int m_NumberOfTies;     // implementation you could obv make it non-const. 
// KRIS: Always make member variables private(VERY few exceptions, though there are some),
// and only edit them using so called "Get" and "Set" functions as shown below,
// it makes it easier to know who changes the values (e.g by setting a breakpoint in the function)
// and you can make sure that it's always correctly accesses (note the "InsideBounds" check I added).           
    private const char m_ErrorValue = -1;
    
    public char GetNode(int x, int y)
    {
        if( InsideBounds(x,y))
        {
            return m_Grid[x][y];
        }
        return errorValue;
    }
    public void SetNode(int x, int y, char value)
    {
        if (InsideBounds(x,y))
        {
            m_Grid[x][y] = value;
        }
        else
        (
            // Handle the error in any way you seem fit, e.g throw an exception
            throw new OutOfBoundsException("DAFUQ");
        )
    }
    public int GetGridSize()const { return m_GridSize;} // The trailing "const" means that the function can't change the member variables
                                                        // i.e the code won't compile if you try to edit anything. 
    public void HumanWins()
    {
        print("CONGRATS BITCH");
        ++m_HumanWins;
        
        // other type of proccesing
    }
    public void AIWins()
    {
        print("HOLY SHIT U SUK");
        
        // ....
    }
    public void ItsATie()
    {
        print("OH FECK NO ONE WINS")
        ++m_NumberOfTies;
    }
    
    /**
     * Creates a TicTacToe game of an inputed size.
     * 
     * @param s the size of the game
     */
    // KRIS: 
    public TicGame(int size) : m_Size(size) 
    {
        // initialise instance variables
        hWin = 0;
        tie = 0;
        error = "Please enter valid move.";
        x = new char[size][size];
        for(int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                x[i][j] = ' '; 
    }

    /**
     * A string representation of the TicTacToe game.
     *
     * @return The full TicTacToe board.
     */
    public String toString()
    {
        // put your code here
        String s = "";
        String horizlines = "---";
        String vertlines = "|";
        char a = 'A';
        int colnum = 1;
        s += "   " + colnum + "   ";
        for (int m = 0; m < (size - 1); m++){
            colnum++;
            s += colnum + "   ";
        }
        s += "\n";
        for (int i = 0; i < (size - 1); i++){
            s += a + " ";
            a++;
            for (int j = 0; j < (size - 1); j++){
                s += " " + x[i][j] + " " + vertlines;
            }
            s += " " + x[i][(size - 1)] + " \n";
            s += "  ";
            for (int k = 0; k < (size - 1); k++){
                s += horizlines + vertlines;
            }
            s += horizlines + "\n";
        }
        s += a + " ";
        for (int j = 0; j < (size - 1); j++){
            s += " " + x[(size - 1)][j] + " " + vertlines;
        }
        s += " " + x[(size - 1)][(size - 1)] + " \n";
        return s; 
    }

    /**
     * Creates error messages for invalid board sizes and prints them to the
     * screen.
     */
    public static void invalidBoardSize(){
        String invalidBoardSizeError="Board size invalid. ";
        String print3x3BoardMess = "Printing 3x3 board.";
        System.out.println(invalidBoardSizeError + print3x3BoardMess);
        return;
    }

    /**
     * Checks if the element in an array does not have a space.
     * @param i the first index in the array.
     * @param j the second index in the array.
     * @return true if the element is occupied by a player; false otherwise.
     */
    public boolean isPosFull(int i, int j){
        return x[i][j] != ' ';
    }

    /**
     * Checks to see if the game is over.
     * @return true if a player won or if there is a tie.
     */
    private boolean isGameOver(HumanTicPlayer h){
        char hMark = h.mark;
        boolean gameDone = false;
        //row check
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size - 1; j++)
                if ((x[i][j] == x[i][j+1])&&isPosFull(i,j))
                {//if condition is true
                    //if statement for if loop is at the first iteration and
                    //it is true. If not at first iteration, if previous
                    //iteration is false, loop breaks.
                    if (j == 0)
                        gameDone = true;
                    else if (!gameDone) 
                        break;
                }
                else //if condition is false
                //if statement for if previous iteration returned true
                if(gameDone){
                    gameDone = false; 
                    break;
                } 
            if (gameDone){
                if (x[i][0] == hMark)
                    hWin = 1;
                return gameDone;
            }
        }
        //column check
        for(int j = 0; j < size; j++){
            for (int i = 0; i < size - 1; i++)
                if ((x[i][j] == x[i+1][j])&&isPosFull(i,j))
                {//if condition is true
                    //if statement for if loop is at the first iteration and
                    //it is true. If not at first iteration, if previous
                    //iteration is false, loop breaks.
                    if (i == 0)
                        gameDone = true;
                    else if (!gameDone) 
                        break;
                }
                else //if condition is false
                //if statement for if previous iteration returned true
                if(gameDone){
                    gameDone = false; 
                    break;
                } 
            if (gameDone){
                if (x[0][j] == hMark)
                    hWin = 1;
                return gameDone;
            }
        }
        //downward diagonal check
        for (int i = 0; i < size - 1; i++)
            if ((x[i][i] == x[i+1][i+1])&&isPosFull(i,i))
            {//if condition is true
                //if statement for if loop is at the first iteration and
                //it is true. If not at first iteration, if previous
                //iteration is false, loop breaks.
                if (i == 0)
                    gameDone = true;
                else if (!gameDone) 
                    break;
            }
            else //if condition is false
            //if statement for if previous iteration returned true
            if(gameDone){
                gameDone = false; 
                break;
            } 
        if (gameDone){
            if (x[0][0] == hMark)
                hWin = 1;
            return gameDone;
        }
        //upward diagonal check
        for(int i = size - 1, j = 0; i > 0; i--, j++)
            if ((x[i][j] == x[i-1][j+1])&&isPosFull(i,j))
            {//if condition is true
                //if statement for if loop is at the first iteration and
                //it is true. If not at first iteration, if previous
                //iteration is false, loop breaks.
                if (j == 0)
                    gameDone = true;
                else if (!gameDone) 
                    break;
            }
            else //if condition is false
            //if statement for if previous iteration returned true
            if(gameDone){
                gameDone = false; 
                break;
            }
        if (gameDone){
            if (x[size-1][0] == hMark)
                hWin = 1;
            return gameDone;
        }
        //tie check
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++)
                if (isPosFull(i,j))
                {//if condition is true
                    //if statement for if previous iteration is false, 
                    //loop breaks.
                    if (j == 0 && i == 0)
                        gameDone = true;
                    else if (!gameDone) 
                        break;
                }
                else //if condition is false
                //if statement for if previous iteration returned true
                if(gameDone){
                    gameDone = false; 
                    break;
                }
        }
        if (gameDone){
                hWin = 0;
                tie = 1;
        }
        return gameDone;
    }

    /**
     * Runs the entire game. Controls the user input and the seqences within
     * the game.
     * @param args
     */
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String print3x3BoardMess = "Printing 3x3 board.";
        System.out.println("Welcome to the TicTacToe Game! \n");
        System.out.println("It will be randomly decided who plays first.\n");
        System.out.println("Enter a board size or the Enter key to create");
        System.out.println("a 3x3 board. (Invalid entries will create this");
        System.out.println("automatically.)");
        String in = scan.nextLine();
        int s = (int) Double.NaN;
        int gamesPlayed = 0;
        int hWins = 0;
        if (!in.equals(""))
            try{
                s = Integer.parseInt(in);
                if (s < 2 || s > 9){
                    invalidBoardSize();
                    s = 3;
                }
            } catch (NumberFormatException e){
                invalidBoardSize();
                s = 3;
            }
        else{ 
            System.out.println(print3x3BoardMess);
            s = 3;
        }
        TicGame g = new TicGame(s);
        TicMove m;
        HumanTicPlayer h = new HumanTicPlayer();
        CpuTicPlayer cpu = new CpuTicPlayer(g.size);
        int coinFlip = (int) (Math.random()*2);
        int counter = 0;
        if (coinFlip == 1)
            System.out.println(g);
        while(coinFlip != -1){
            if (g.isGameOver(h)){
                hWins += g.hWin;
                gamesPlayed++;
                switch(g.hWin){
                    case 1:
                    System.out.println("You Win\n");
                    break;

                    case 0:
                    if (g.tie==1)
                        System.out.println("It's a tie!\n");
                     else
                        System.out.println("CPU Wins\n");
                    break;

                    default:
                   
                    break;
                }
                System.out.println("New Game\n");
                g = new TicGame(s);
                coinFlip = (int) (Math.random()*2);
                if(coinFlip == 1)
                    System.out.println(g);
            }
            switch (coinFlip){
                case 0: //CPU starts first 
                m = new TicMove();
                System.out.println("CPU's move:");
                if (counter==0){
                    cpu.oldMovePick(g,m);
                    counter++;
                }
                else{
                    cpu.movePick(g,m);
                }
                //  cpu.movePickAiTest(g);
                cpu.makeMove(g,m);
                System.out.println(g);
                if (g.isGameOver(h))//runs loop to top if game is over
                    continue;
                
                case 1: //Human starts first
                m = new TicMove();
                coinFlip = 0;
                System.out.println("Your move:");
                h.getPos(m);
                if (m.pos.equals("QUIT")){
                    System.out.print("You have played " + gamesPlayed);
                    System.out.println(" games.");
                    System.out.print("You've won " + hWins + " out of ");
                    System.out.println(gamesPlayed + " games.");
                    coinFlip = -1;
                    continue;
                }
                h.movePick(g,m);
                h.makeMove(g,m);
                System.out.println(g);
                if (g.isGameOver(h))//runs loop to top if game is over
                    continue;
                break;

                default:
                break;
            }
        }
        return;
    }
}
