import java.util.*;
/**
 * A computer player that choses moves randomly.
 *
 * @author Jordan Chin
 * @version 1.0
 */
public class CpuTicPlayer extends ATicPlayer
{
    // instance variables - replace the example below with your own
    int[] gameMoves;
    int[] bestPath;
    int bestPoint;
    int placePoint;
    int pointMarker;
    int rowOrColumnMarker;
    int size;
    /**
     * Creates the mark for the CPU TicTacToe player.
     */
    public CpuTicPlayer(int size)
    {
        // initialise instance variables
        mark = 'O';
        this.size = size;
        gameMoves = new int[size];
        bestPath = new int[size];
        bestPoint = pointMarker = rowOrColumnMarker = -1;
        placePoint = 0;
    }

    /**
     * Decides moves for computer randomly.
     * @game the TicTacToe game board currently in use.
     */
    public void oldMovePick(TicGame game,TicMove move)
    {
        // put your code here
        move.arrayRow = (int) (Math.random()*game.size);
        move.arrayCol = (int) (Math.random()*game.size);
        return;
    }

    public boolean contains(int[] a, int i){
        boolean contains = false;
        for (int item : a){
            if (item == i){
                contains = true;
                break;
            }
        }
        return contains;
    }
    
    
    public int freq(int[]a, int i){
        int num = 0;
        for (int item : a){
            if (item == i){
                num++;
            }
        }
        return num;
    }

    public void pointAssign(TicGame game, HumanTicPlayer human,
    char type, int i, int j){
        char hMark = human.mark;
        char[][] x = game.x;

        switch (type){
            case 'r':

            if (x[i][j]==mark){
                gameMoves[j] = 1;
                placePoint+=1;
            } else if (x[i][j]==hMark){
                gameMoves[j] = 2;
                placePoint+=2;
            }

            break;
            case 'u':
            case 'c':
            if (x[j][i]==mark){
                gameMoves[j] = 1;
                placePoint+=1;
            } else if (x[j][i]==hMark){
                gameMoves[j] = 2;
                placePoint+=2;
            }
            break;
            case 'd':
            if (x[i][i]==mark){
                gameMoves[i] = 1;
                placePoint+=1;
            } else if (x[i][i]==hMark){
                gameMoves[i] = 2;
                placePoint+=2;
            }
            break;

        }

    }

    public void pointAllocate(TicGame game, int i, char type){
        if ((placePoint >= bestPoint)&&contains(gameMoves,-1)){
            switch (type){
                case 'r':
                pointMarker = 0;
                rowOrColumnMarker = i;
                break;
                case 'c':
                pointMarker = 1;
                rowOrColumnMarker = i;
                break;
                case 'd':
                pointMarker = 2;
                break;
                case 'u':
                pointMarker = 3;
                break;
            }

            if((freq(gameMoves,-1)==1)&&(freq(gameMoves,1)==(size-1)))
                bestPoint = Integer.MAX_VALUE;
            else
                bestPoint = placePoint;
            bestPath = Arrays.copyOfRange(gameMoves,0,size);
        }
    }

    public void rowOrColumnCheck(TicGame game, HumanTicPlayer human,
    char type){
        int j;
        for(int i = 0; i < size; placePoint = 0, i++){
            Arrays.fill(gameMoves, -1);
            for (j = 0; j < size; j++){

                //assigns points for any filled element in board
                pointAssign(game,human,type,i,j);
            }
            pointAllocate(game,i,type);
        }
    }

    public void diagonalCheck(TicGame game, HumanTicPlayer human,char type){
        int start;
        int end;
        placePoint = 0;
        Arrays.fill(gameMoves, -1);
        for (int i = 0, j = size - 1; i < size; i++, j--){

            pointAssign(game,human,type,i,j);
        }
        pointAllocate(game,-1,type);
    }

    /**
     * Assigns points from each filled element in array and fills element that
     * is empty.
     * @param game the TicTacToe game board currently in use.
     */
    public void movePick(TicGame game,TicMove move){
        HumanTicPlayer human = new HumanTicPlayer();
        int size = game.size;
        bestPoint = pointMarker = rowOrColumnMarker = -1;
        //row check
        rowOrColumnCheck(game,human,'r');
        //column check
        rowOrColumnCheck(game,human,'c');
        //downward diagonal check
        diagonalCheck(game,human,'d');
        //upward diagonal check
        diagonalCheck(game,human,'u');
        //position initialization
        for(int i = 0; i < size; i++){
            switch(pointMarker){
                case 0://row select

                if(bestPath[i]<1){
                    move.arrayRow = rowOrColumnMarker;
                    move.arrayCol = i;
                }
                // else{
                // this.oldMovePick(game);
                // }
                break;
                case 1://column select

                if(bestPath[i]<1){
                    move.arrayRow = i;
                    move.arrayCol = rowOrColumnMarker;
                }
                // else{
                // this.oldMovePick(game);
                // }
                break;
                case 2://downward diagonal select
                if(bestPath[i]<1){
                    move.arrayRow = i;
                    move.arrayCol = i;
                }
                // else{
                // this.oldMovePick(game);
                // }
                break;
                case 3:
                if(bestPath[i]<1){
                    move.arrayRow = size -i-1;
                    move.arrayCol = i;
                }
                // else{
                // this.oldMovePick(game);
                // }
                break;
                default://throws wrench
                move.arrayRow = -1;
                move.arrayCol = -1;
                break;
            }
        }
        return;
    }
}
