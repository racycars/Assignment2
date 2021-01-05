import java.util.Scanner;				// Scanner required for player input

/**
 * The main class for the game Tic-Tac-Toe.
 * Controls the flow of the game, allowing each player to enter an option until the game ends.
 */

public class GameMain {
	private static Scanner scanner = new Scanner(System.in);  // Scanner for input
	
	private Grid grid;					// The game board
	private boolean gameOver;			// Whether game is playing or over
	private Player winner;				// Winner of the game
	private Player currentPlayer;		// Current player (enum)
 
   /**
    * Constructor
    * Sets up the game. Creates the grid and sets the values of the variables before calling the play method.
    */
   public GameMain() {
	   // Create the grid
	   grid = new Grid();
	   // Reset the game variables to their defaults
	   gameOver = false;
	   currentPlayer = Player.X;
	   winner = null;
	   // Begin playing the game
	   play();
	
   }
   
   /**
    * Controls the game play, rotates between player turns until a winner is decided.
    */
   public void play() {
	   do {
	         playerMove(currentPlayer);			// Have the player perform their move
	         grid.display();					// Display the current game board
	         checkForWinner(currentPlayer);		// Checks if the game has been won
	         
	         // Display results if game is over
	         if(gameOver) {
	        	 if(winner == Player.X) {
		        	 System.out.println("Player X wins!");
		         }
	        	 else if (winner == Player.O)
	        	 {
	        		 System.out.println("Player O wins!");
	        	 }
	        	 else if (grid.isDraw() == true)
	        	 {
	        		 System.out.println("It's a Draw!!");
	        	 }
	        	 
	        	 
	         }
	         
	         // Switch turn to the next player
	         if(currentPlayer == Player.X) {
	        	 currentPlayer = Player.O;
	         } else {
	        	 currentPlayer = Player.X;
	         }
	         
	      } while (!gameOver);  // repeat until game-over
   }
 
   /** 
    * Handles the player making their move, checks if the move is valid before making it.
    */
   public void playerMove(Player turnPlayer) {
	   
      boolean validInput = false;
      
      do {
    	  
    	  // Display instructions to the player
         if (turnPlayer == Player.X) {
            System.out.print("Player 'X', enter your move:");
         } else {
            
        	 System.out.print("Player 'O', enter your move: ");
        	 
         }
         
         // Obtains input from the player for both row and column
         System.out.print("\n"+ "Row 1-3:");
         int row = scanner.nextInt();
         System.out.print("Col 1-3:");
         int col = scanner.nextInt();
         
         // Decrease the value entered by 1 to compensate for the array index starting at 0
         row--;
         col--;
         
         // Verify the values the player entered are valid (position is valid and empty)
         if (row >= 0 && row < grid.ROWS && col >= 0 && col < grid.COLUMNS && grid.board[row][col].content == Player.EMPTY) {
        	 grid.board[row][col].content = turnPlayer;
        	 grid.currentRow = row;
        	 grid.currentCol = col;
        	 validInput = true;
         } else {
        	 
        	 System.out.println("Invalid Move Please Try Again!!");
         }
         
      } while (!validInput);   // Repeat until input is valid
   }
 
   /**
    * Checks if the game has ended
    */
   public void checkForWinner(Player turnPlayer) {
	   if(grid.hasWon(turnPlayer)) {
			gameOver = true;
			winner=currentPlayer;
			System.out.println("Congratulations " + winner + ". You Have Won!");
			
		} else if(grid.isDraw()) {
			gameOver = true;
			System.out.println("The game is a draw.");
		}
   }
 
   /**
    * The main() method
    */
   public static void main(String[] args) {
	   char playAgain;
	   while (true) {
			new GameMain();
			System.out.println("Play again? Press Y to play again or N to exit");
			playAgain = scanner.next().charAt(0);
			if (playAgain == 'y') {
				continue;
			}
			else if (playAgain == 'n') {
				System.out.println("Thank You For Playing.");
				System.exit(0);
				break;
			}
	}
}}