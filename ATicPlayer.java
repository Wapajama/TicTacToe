
/**
 * A generic TicTacToe player that makes moves in the game.
 *
 * @author Jordan Chin
 * @version 1.0
 */
public class ATicPlayer
{
    // instance variables - replace the example below with your own
    public char mark;

    /**
     * Constructor for objects of class ATicPlayer
     */
    public ATicPlayer()
    {
        // initialise instance variables

    }

    /**
     * Makes move on game board after user decides pick.
     *
     * @param game the TicTacToe game board currently in use.
     */
    public void movePick(TicGame game,TicMove move)
    {
        // put your code here
        return;
    }

    /**
     * Receives user input.
     */
    public void getPos(TicMove move){return;}

    /**
     * Checks if a position to be made is legal.
     * @param game the TicTacToe game board currently in use.
     * @return true if the element of an array is empty (a space) when a
     * position is called; false otherwise.
     */
    private boolean isPosLegal(TicGame game, TicMove move){
        return game.x[move.arrayRow][move.arrayCol] == ' ';
    }

    /**
     * Prints an error message and instructs player to make and execute
     * another move.
     */
    public void illegalToLegalMove(TicGame game,TicMove move){
        System.out.println(TicGame.error);
        getPos(move);
        movePick(game,move);
        return;
    }

    /**
     * Executes move chosen by player. Catches an
     * ArrayIndexOutOfBoundsException if move is illegal. Iterates until
     * legal move is chosen.
     * @param game the TicTacToe game board currently in use.
     */
    public void makeMove(TicGame game,TicMove move){
        do{
            try{
                if(!isPosLegal(game,move)){
                    if(this instanceof HumanTicPlayer){
                        System.out.println("Changing moves not allowed.");
                        illegalToLegalMove(game,move);
                    }
                    else{
                        getPos(move);
                        movePick(game,move);
                    }   
                    continue;
                }
                game.x[move.arrayRow][move.arrayCol] = this.mark;
                break;
            } catch (ArrayIndexOutOfBoundsException e){
                illegalToLegalMove(game,move);
            }
        } while (true);
        return;
    }
}
