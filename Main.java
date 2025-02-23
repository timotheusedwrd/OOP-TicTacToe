import java.util.Scanner;

public class Main {
    public static boolean gameEnd = false;
    public static Scanner scanner = new Scanner(System.in);
    public static char board[] = {
        '_', '_', '_',
        '_', '_', '_', 
        '_', '_', '_'
    };

    public static int winningMoves[][] = {
        // vertically
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
        // horizontally
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
        // diagonally
        {0, 4, 8}, {2, 4, 6}
    };

    public static int turn = 0; 
    // 0 = player 1 (O) 
    // 1 = player 2 (X) 
    public static void main(String args[]){
        startGame();
    }

    public static void printBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(board[i * 3 + j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static char getPlayerSign(){
        return turn == 0 ? 'O' : 'X';
    }

    public static String getPlayerName(){
        return turn == 0 ? "Player 1" : "Player 2";
    }

    public static boolean checkWin(char playerSign){
        boolean win = false;
        for(int i = 0; i < 8; i++){
            boolean valid = true;
            for(int j = 0; j < 3; j++){
                if(board[winningMoves[i][j]] != playerSign) valid = false;
            }
            if(valid){
                win = true;
                break;
            }
        }
        return win;
    }

    public static boolean placeMove(int x, int y){
        if(x < 0 || x > 2 || y < 0 || y > 2) return false;
        if(board[y * 3 + x] != '_') return false;
        board[y * 3 + x] = getPlayerSign();
        return true;
    }

    public static boolean isTie(){
        int length = 9;
        for(int i = 0; i < 9; i++){
            if(board[i] != '_') length -= 1;
        }
        if(length <= 0 && !checkWin('O') && !checkWin('X')) return true;
        return false;
    }

    public static void play(){
        int x;
        int y;
        boolean validMove;
        // place move
        do {        
            System.out.print(getPlayerName() + " move: ");
            x = scanner.nextInt();
            y = scanner.nextInt();
            validMove = placeMove(x, y);
            if(!validMove){
                System.out.println("You cannot place in this tile!");
            }
        } while (validMove == false);

        // check win
        if(checkWin(getPlayerSign())){
            System.out.println(getPlayerName() + "WIN");
            printBoard();
            gameEnd = true;
            return;
        }
        // check tie
        if(isTie()){
            System.out.println("Game is Tie");
            printBoard();
            gameEnd = true;
            return;
        }
        // print board
        printBoard();
        // swap turn
        if(turn == 0) turn = 1;
        else turn = 0;
    }

    public static void startGame(){
        System.out.println("Welcome to Tictactoe!");
        while(!gameEnd){
            play();
        }
    }
}