import java.util.Scanner;
/**
 * A user that can decide and execute moves.
 *
 * @author Jordan Chin
 * @version 1.0
 */
public class HumanTicPlayer extends ATicPlayer
{
    // instance variables - replace the example below with your own
    /**
     * Creates the mark for the human TicTacToe player.
     */
    public HumanTicPlayer()
    {
        // initialise instance variables
        mark = 'X';
    }

    /**
     * Makes move on game board after user decides pick.
     *
     * @param game the TicTacToe game board currently in use.
     */
    public void movePick(TicGame game,TicMove move){
        move.rowConvert(game, this);
        move.colConvert(game, this);
        return;
    }

    /**
     * Checks to see if the position chosen by user is invalid.
     * @return true if the position is empty or its length is invalid, and
     * that it is not "QUIT"; false otherwise.
     */
    public boolean isPosInvalid(TicMove move){
        boolean isPosEmpty = move.pos.equals("");
        boolean isPosNotQuit = !move.pos.equals("QUIT");
        boolean isPosLengthInvalid = move.pos.length() > 2;
        return isPosEmpty || isPosLengthInvalid && isPosNotQuit;
    }

    /**
     * Receives user input and repeats if input is invalid.
     */
    public void getPos(TicMove move){
        Scanner s = new Scanner(System.in);
        do{
            move.pos = s.nextLine().toUpperCase();
            if (!isPosInvalid(move))
                break;
            System.out.println(TicGame.error);
        } while (isPosInvalid(move));
        return;
    }
}
