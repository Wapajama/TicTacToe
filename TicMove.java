/**
 * Governs how moves are made in TicTacToe game. Allows for a position
 * (row and column) to be called under one class.
 *
 * @author Jordan Chin
 * @version 1.0
 */
public class TicMove
{
    // instance variables - replace the example below with your own
    public String pos;
    public char row;
    public int col;
    public int arrayRow;
    public int arrayCol;

    /**
     * Initializes the indices of the array to 0.
     */
    public TicMove()
    {
        // initialise instance variables     
        arrayRow = (int) Double.NaN;
        arrayCol = (int) Double.NaN;
    }

    /**
     * Converts part of the positon into the row. Catches a
     * StringIndexOutOfBoundsException if there is no input.
     *
     * @param  game the TicTacToe game board currently in use.
     * @param a a generic TicTacToe player.
     */
    public void rowConvert(TicGame game, ATicPlayer a)
    {
        // put your code here
        try{
            row = pos.charAt(0);
        } catch (StringIndexOutOfBoundsException e){
            
            a.illegalToLegalMove(game,this);
        }
        arrayRow = row - 'A';
        return;
    }

    /**
     * Converts part of the positon into the column. Catches a
     * StringIndexOutOfBoundsException if there is no input.
     *
     * @param  game the TicTacToe game board currently in use.
     * @param a a generic TicTacToe player.
     */
    public void colConvert(TicGame game, ATicPlayer a){
        try{
            col = Character.digit(pos.charAt(1), 10);
        } catch (StringIndexOutOfBoundsException e){
            a.illegalToLegalMove(game,this);
        }
        arrayCol = col - 1;
        return;
    }
}
