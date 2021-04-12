/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictictactoe;
import java.util.*;
/**
 *
 * @author jrsullins
 */
public class TicTicTacToe {

    /********************** YOUR CODE GOES HERE **********************/
 
    /**
    * Computes the heuristic value of the board for a given player.
    * @param board A 4x4 array of characters, either 'X', 'O', or ' '.
    * @param ai Which character the computer is, either 'X' or 'O'.
    * @return A positive integer giving the value of the board for the character 
    * the AI is playing, between 0 and 1000.
    */
    
public static float evaluate(char[][] board, char ai , char Fplayer,char [][] Fboard){
        int row,col;
        int value =0; 
        char comp; 
        if (Fplayer == 'X') comp = 'O'; else comp = 'X'; 
        
       
        
        
        int cons3PlayerMovesROW =countThrees(Fboard,Fplayer); 
        
        boolean VictoryAI =fourInARow(board,comp);
        boolean playerVictory = fourInARow(board,Fplayer); 
                
        //if the game confirm ai victory then choose it 
        if (VictoryAI==true){
            value =1000;
        }
        //if the game confirm player victory then avoid it 
        else if (playerVictory== true){
            value = 0;
        }
        /*if player about get 3 consecutive moves in same row then it call the function which chooses the board in which 
        the ai disturb the player from winning by choosing their next move 
        in that remaining row*/
        else if (cons3PlayerMovesROW >=1){ 
            value = cons3InROW(board,comp,Fplayer,Fboard);          
        }
      
        else{
            
            /*  Here if the player make two consecutive row move then ai disturb by
            making their move in third remaining row*/
            for (int i = 0; i < 4; i++) {
                if ( (board[i][0] ==Fplayer) &&  (board[i] [1] == Fplayer)  && (board[i][2]==comp) && (Fboard[i][2] == ' ')){
                    value=1000; 
                    break;
                }else if ((board[i][1] ==Fplayer) &&  (board[i] [2] == Fplayer)  && (board[i][3]==comp) && (Fboard[i][3] ==' ')){
                    value =1000; 
                    break; 
                }else if ((board[i][3] ==Fplayer) &&  (board[i] [2] == Fplayer)  && (board[i][1]==comp) && (Fboard[i][1] == ' ')){
                    value =1000; 
                    break; 
                }else if((board[i][2] ==Fplayer) &&  (board[i] [1] == Fplayer)  && (board[i][0]==comp) && (Fboard[i][0] == ' ')){
                    value =1000; 
                    break; 
                }
            } 
            /*    Here if the player make two consecutive columns move then ai disturb by
            making their move in third remaining columns */
            for (int i = 0; i < 4; i++) {
                    if ( (board[0][i] ==Fplayer) &&  (board[1] [i] == Fplayer)  && (board[2][i]==comp) && (Fboard[2][i] == ' ')){
                        value=1000; 
                        break; 
                    }else if ((board[1][i] ==Fplayer) &&  (board[2] [i] == Fplayer)  && (board[3][i]==comp) && (Fboard[3][i] ==' ')){
                        value =1000; 
                        break;
                    }else if ((board[3][i] ==Fplayer) &&  (board[2] [i] == Fplayer)  && (board[1][i]==comp) && (Fboard[1][i] == ' ')){
                        value =1000; 
                        break;
                    }else if((board[2][i] ==Fplayer) &&  (board[1] [i] == Fplayer)  && (board[0][1]==comp) && (Fboard[0][i] == ' ')){
                        value =1000; 
                        break;
                    }
            } 
        
            /*    Here if the player make two consecutive diagonal move then ai disturb by
            making their move in thir remaining diagonal */
            for (int i = 0; i < 4; i++) {
                if (i <2){
                    if ( (board[i][i] ==Fplayer) &&  (board[i+1] [i+1] == Fplayer)  && (board[i+2][i+2]==comp) && (Fboard[i+2][i+2] == ' ') ){
                        value = 1000; 
                        break;
                    }
                }else if (i >=2){
                    if ( (board[i][i] ==Fplayer) &&  (board[i-1] [i-1] == Fplayer)  && (board[i-2][i-2]==comp) && (Fboard[i-2][i-2] == ' ') ){
                        value = 1000; 
                        break;
                    }    
                }
            }

            if ( (board[3][0] ==Fplayer) &&  (board[2] [1] == Fplayer)  && (board[1][2]==comp) && (Fboard[1][2] == ' ')){
                value=1000; 
           }else if ((board[2][1] ==Fplayer) &&  (board[1] [2] == Fplayer)  && (board[0][3]==comp) && (Fboard[0][3] ==' ')){
                value =1000; 
            }else if ((board[0][3] ==Fplayer) &&  (board[1] [2] == Fplayer)  && (board[2][1]==comp) && (Fboard[2][1] == ' ')){
                value =1000; 
            }else if((board[2][1] ==Fplayer) &&  (board[1] [2] == Fplayer)  && (board[3][0]==comp) && (Fboard[3][0] == ' ')){
                value =1000; 
            }

            // Here  prohibits 3 consecutive player moves in any diagonal. 
            for (row = 0; row < 2; row++)
                for (col = 0; col < 2; col++)
                    if (board[row][col] == Fplayer &&
                        board[row+1][col+1] == Fplayer &&
                        board[row+2][col+2] == comp && Fboard[row+2][col+2] ==' ') {
                        value =1000; 
                        break; 
                    }  
            for (row = 0; row < 2; row++)
                for (col = 2; col < 4; col++)
                    if (board[row][col] == Fplayer &&
                        board[row+1][col-1] == Fplayer &&
                        board[row+2][col-2] == comp && Fboard[row+2][col-2]==' ') {
                            value =1000;
                            break; 
                } 
               
        }
         
       return value;    
                
       
}   
  /*  This function returns the total number of “3 in a row” that who has over all rows, columns, or diagonals  */
public static int cons3InROW(char[][]board, char ai , char initialPlayer,char [][] initialboard) {
      int value=0; 
      int row, col;
      int X, O;
      
      /* Here it check all rows */
     
      for (row = 0; row < 4; row++)
        for (col = 0; col < 2; col++)
          if (board[row][col] == initialPlayer &&
              board[row][col+1] == initialPlayer &&
              board[row][col+2] == initialPlayer) {
              if (col==0 && board[row][col+3] ==ai && initialboard[row][col+3]==' '){
                  value = 1000; 
                  break; 
              }else if (col==1 && board[row][0]==ai && initialboard[row][0]==' '){
                  value=1000;
                  break; 
              }
          }

      /* Here it check all columns */

      for (row = 0; row < 2; row++)
        for (col = 0; col < 4; col++)
          if (board[row][col] == initialPlayer &&
              board[row+1][col] == initialPlayer &&
              board[row+2][col] == initialPlayer){
              if (row==0 && board[row+3][col] ==ai && initialboard[row+3][col]==' '){
                  value = 1000; 
                  break; 
              }else if (row==1 && board[0][col]==ai && initialboard[0][col]==' '){
                  value=1000;
                  break;
              }
          }

      /* here it check all diagonals */

      for (row = 0; row < 2; row++)
        for (col = 0; col < 2; col++)
          if (board[row][col] == initialPlayer &&
              board[row+1][col+1] == initialPlayer &&
              board[row+2][col+2] == initialPlayer) {
               if (row==0 && board[row+3][col+3] ==ai && initialboard[row+3][col+3]==' '){
                  value = 1000; 
                  break;
              }else if (row==1 && board[0][0]==ai && initialboard[0][0]==' '){
                  value=1000;
                  break; 
              }
          }
      if ( (board[3][0] ==initialPlayer) &&  (board[2] [1] == initialPlayer)  && (board[1][2]==initialPlayer) && (board[0][3] == ai) && (initialboard[0][3] == ' ')){
                value=1000; 
        }else if ((board[0][3] ==initialPlayer) &&  (board[2] [1] == initialPlayer)  && (board[1][2]==initialPlayer) && (board[3][0] == ai) && (initialboard[3][0] == ' ')){
                value =1000;    
        } 
        
      
      return value;
      }
   
	
  
    
        
    





/***************** END OF YOUR CODE ********************************/

     
    /**
     * Determines the player in question (X or 0) a winner by having 4 in a row.
     * @param board The game board
     * @param who Which character we are checking (ether 'X' or 'O')
     * @return true if who has 4 in a row, false otherwise
     */
    public static boolean fourInARow(char[][] board, char who) {
        for (int i = 0; i < 4; i++) {
            if (countRow(board, i, who) == 4) {
                //cout << "Win in row " << i << "\n";
                return true;
            }
        }
        for (int i = 0; i < 4; i++) {            
            if (countCol(board, i, who) == 4) {            
                //cout << "Win in column " << i << "\n";
                return true;
            }   
        }
        if (countDiagonal(board, 0, 0, true, who) == 4) {
            return true;
            }
        if (countDiagonal(board, 0, 3, false, who) == 4) {
            return true;
            }

        return false;
    }

 
    
    /**
     * Exhaustively searches the current board to count the sets of 3 this character has in a row.
     * @param board The game board
     * @param who Which character we are checking (ether 'X' or 'O')
     * @return The number of 3 in a row that character has.
     */
    public static int countThrees(char[][] board, char who) {
      int row, col;
      int X, O;
      int score = 0;

      /* check all rows */
      for (row = 0; row < 4; row++)
        score += threeRow(board, row, who);

      /* check all columns */
      for (col = 0; col < 4; col++)
        score += threeCol(board, col, who);

      /* check all diagonals */
      score += threeDiagonal(board, true, who);      
      score += threeDiagonal(board, false, who);
      
      return score;
      }
   
    /**
     * Searches for whether who has three in a row in the given row
     * @param board
     * @param row
     * @param who
     * @return 
     */
    public static int threeRow(char[][] board, int row, char who) { 
        int count = 0;
        for (int col = 0; col < 2; col++) {
          if (board[row][col] == who &&
              board[row][col+1] == who &&
              board[row][col+2] == who) count++;            
        }
        return count;
    }
    
    /**
     * Searches for whether who has three in a row in the given column
     * @param board
     * @param row
     * @param who
     * @return 
     */
    public static int threeCol(char[][] board, int col, char who) {  
        int count = 0;
        for (int row = 0; row < 2; row++) {
          if (board[row][col] == who &&
              board[row+1][col] == who &&
              board[row+2][col] == who) count++;            
        }
        return count;
    }
    
    /**
     * Returns number of threes in a row along a diagonal
     * @param board
     * @param up true if up from right, false if up from left
     * @param who
     * @return 
     */
    public static int threeDiagonal(char[][] board, boolean right, char who) {
        int count = 0;
        if (right) {
            for (int row = 0; row < 2; row++)
                for (int col = 0; col < 2; col++)
                    if (board[row][col] == who &&
                        board[row+1][col+1] == who &&
                        board[row+2][col+2] == who) {
                        count++;
                    }
            return count;
        }    
        else {
            for (int row = 0; row < 2; row++)
                for (int col = 2; col < 4; col++)
                    if (board[row][col] == who &&
                        board[row+1][col-1] == who &&
                        board[row+2][col-2] == who) {
                        count++;
                    }
            return count;
        }
    }
    
    /**
     * Returns the number of who in a given row
     * @param board
     * @param row
     * @param who
     * @return 
     */
    public static int countRow(char[][] board, int row, char who) {
        int count = 0;
        for (int col = 0; col < 4; col++) {
            if (board[row][col] == who) count++;
        }
        return count;
    }
    
    /**
     * Returns the number of who in a given column
     * @param board
     * @param col
     * @param who
     * @return 
     */
    public static int countCol(char[][] board, int col, char who) {
        int count = 0;
        for (int row = 0; row < 4; row++) {
            if (board[row][col] == who) count++;
        }
        return count;
    }
    
    /**
     * Number of who on diagonal
     * @param board
     * @param row Lowest row value
     * @param col Lowest col value if right diagonal, highest if left diagonal
     * @param right Does diagonal increase in right or left direction
     * @param who
     * @return 
     */
    public static int countDiagonal(char[][] board, int row, int col, boolean right, char who) {
        int count = 0;
        if (right) {
            while (row < 4 && col < 4) {
                if (board[row][col] == who) {
                    count++;
                }
                row++;
                col++;
            }  
        }
        else {
            while (row < 4 && col >= 0) {
                if (board[row][col] == who) {
                    count++;
                }
                row++;
                col--;
            }  
        }
        return count;
    }
    
    static final int MAXLEVEL = 2;
    
 
/* This is the main function for playing the game. It alternatively
   prompts the user for a move, and uses the minmax algorithm in 
   conjunction with the given evaluation function to determine the
   opposing move. This continues until the board is full. It returns
   the number scored by X minus the number scored by O. */

    public static boolean run(int[] scores, char who ,char initialPlayer) { 
        int i, j;
        char current, other;
        int playerrow, playercol;
        int[] location = new int[2];  // Allows us to pass row, col by reference
        int move = 1;

        /* Initialize the board */
        char[][] board = new char[4][4];
        for (i = 0; i < 4; i++) { 
            for (j = 0; j < 4; j++) {
                board[i][j] = ' ';
            }
        }

        if (who == 'O') display(board);

        while (move <= 16) {
            if (move % 2 == 1) {
                current = 'X';
                other = 'O';
            }
            else {                
                current = 'O';
                other = 'X';
            }

            if (current == who) {        /* The computer's move */
                choose(location, board, who,initialPlayer);  /* Call function to compute move */
                System.out.println("Computer chooses " + (location[0]+1) + ", " + (location[1]+1));
                if (board[location[0]][location[1]] == ' ') 
                    board[location[0]][location[1]] = current;
                else {
                    System.out.println("BUG! " + (location[0]+1) + ", " + (location[1]+1) + " OCCUPIED!!!");
                    System.exit(0);
                }
                if (fourInARow(board, who)) {
                    System.out.println("Computer has 4 in a row! Computer wins!");
                    display(board);
                    return true;
                }
            }

            else {                       /* Ask for player's move */
                Scanner in = new Scanner(System.in);
                System.out.print("Player " + current + ", enter row: ");
                playerrow = in.nextInt();
                System.out.print("Player " + current + ", enter column: ");
                playercol = in.nextInt();
                while (board[playerrow-1][playercol-1] != ' ' ||
                    playerrow < 1 || playerrow > 4 ||
                    playercol < 1 || playercol > 4) {
                        System.out.println("Illegal move! You cannot use that square!");
                
                        System.out.print("Player " + current + ", enter row: ");
                        playerrow = in.nextInt();
                        System.out.print("Player " + current + ", enter column: ");
                        playercol = in.nextInt();
                }
                playercol--; playerrow--;
                board[playerrow][playercol] = current;
                if (fourInARow(board, current)) {
                    System.out.println("Player has 4 in a row! Player wins!");
                    display(board);
                    return true;            
                }
            }
 
            display(board);    /* Redisplay board to show the move */

            move++; /* Increment the move number and do next move. */
        }
        scores[0] = countThrees(board, 'X');
        scores[1] = countThrees(board, 'O');
        return false;
    }


    
/* This displays the current configuration of the board. */

    public static void display(char[][] board) {
        int row, col;  
        int scores[] = new int[2];
        System.out.print("\n");
        for (row = 3; row >= 0; row--) {
            System.out.print("  +-+-+-+-+\n");
            System.out.print((row+1) + " ");
            for (col = 0; col < 4; col++) {
            if (board[row][col] == 'X')  /* if contents are 0, print space */
                System.out.print("|X");
            else if (board[row][col] == 'O')
                System.out.print("|0");
            else System.out.print("| ");
            }
            System.out.print("|\n");
        }
        System.out.print("  +-+-+-+-+\n");  /* print base, and indices */
        System.out.print("   1 2 3 4\n");
        scores[0] = countThrees(board, 'X');
        scores[1] = countThrees(board, 'O');
        System.out.println("X: " + scores[0]);
        System.out.println("O: " + scores[1]);
    }
   
/* Basic function for choosing the computer's move. It essentially
   initiates the first level of the MINMAX algorithm, and returns
   the column number it chooses. */

    public static void choose(int[] location, char[][] board, char who, char initialPlayer) {
        int move; 
        float value;
          char[][] initialboard = new char[4][4];
        copy(initialboard, board); 
        getmax(location, board, 1, who, initialPlayer, initialboard);
    }


/* This handles any MAX level of a MINMAX tree. This essentially handles moves for the computer. */

    public static float getmax(int[] location, char[][] board, int level, char who, char initialPlayer,char[][] initialboard) {
        char[][] tempboard = new char[4][4];
        int r,c = 0;
        float max = -1001;
        float val;
        int[] tempLocation = new int[2];
        for (r = 0; r < 4; r++)
            for (c = 0; c < 4; c++) {  /* Try each row and column in board */
                if (board[r][c] == ' ') {     /* Make sure square not full */

                /* To avoid changing original board  during tests, make a copy */
                copy(tempboard, board); 

                /* Find out what would happen if we chose this column */
                tempboard[r][c] = who;

                /* If this is the bottom of the search tree (that is, a leaf) we need
                    to use the evaluation function to decide how good the move is */
                if (level == MAXLEVEL) 
                    val = evaluate(tempboard, who, initialPlayer,initialboard);

                /* Otherwise, this move is only as good as the worst thing our
                    opponent can do to us. */
                else
                    val = getmin(tempLocation, tempboard, level+1, who  , initialPlayer,initialboard);

                /* Return the highest evaluation, and set call by ref. parameter
                    "move" to the corresponding column */
                if (val > max) {
                    max = val;
                    if (level==1) {location[0] = r; location[1] = c;}
                 }

            }
        }
        return max;
    }

/* This handles any MIN level of a MINMAX tree. This essentially handles moves for the player. */

    public static float getmin(int[] location, char[][] board, int level, char who,char initialPlayer,char [] [] initialboard) {
        char[][] tempboard = new char[4][4];
        int r,c = 0;   
        int[] tempLocation = new int[2];
        float min = 10001;
        float val;

        /* Since this is opponent's move, we need to figure out which they are */
        char other;
        if (who == 'X') other = 'O'; else other = 'X'; 

        for (r = 0; r < 4; r++)
            for (c = 0; c < 4; c++) {  /* Try each row and column in board */
                if (board[r][c] == ' ') {     /* Make sure square not full */

                    /* To avoid changing original board  during tests, make a copy */
                    copy(tempboard, board);

                    /* Find out what would happen if opponent chose this column */
                    tempboard[r][c] = other;

                    /* If this is the bottom of the search tree (that is, a leaf) we need
                    to use the evaluation function to decide how good the move is */
                    if (level == MAXLEVEL)  
                        val = evaluate(tempboard, who, initialPlayer,initialboard);

                    /* Otherwise, find the best thing that we can do if opponent
                        chooses this move. */
                    else
                        val = getmax(tempLocation, tempboard, level+1, who,initialPlayer,initialboard);

                    /* Return the lowest evaluation (which we will assume will be 
                        chosen by opponent, and set call by ref. parameter
                        "move" to the corresponding column */
                    if (val < min) {
                        min = val;
                        // *move = col;
                    }
                }
            }
        return min;
   }


/* This function makes a copy of a given board. This is necessary to be
   able to "try out" the effects of different moves without messing up
   the actual current board. */

    public static void copy(char[][] a, char[][] b) {
        int i, j;
        for (i = 0; i < 4; i++) { 
            for (j = 0; j < 4; j++) {
                a[i][j] = b[i][j];  
            }
        }
    }
   


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        char player, computer;
        int[] scores = new int[2];
        /* Decide who goes first */
        System.out.print("Do you want to play X or O: ");
        Scanner in = new Scanner(System.in);
        player = in.nextLine().charAt(0);
        if (player == 'X') computer = 'O';
        else computer = 'X';
        boolean win = false;
        win = run(scores, computer, player);
        if (!win)
            System.out.println("\nFinal score: \nX: " + scores[0] + "\nO: " + scores[1] + "\n");
        }
}