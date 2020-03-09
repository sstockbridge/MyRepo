import java.util.Scanner;
// asl;jkfa;slkdj
public class ConnectFour {
    public static void printBoard(char[][] array) {
        int length;
        int height;
        height = array.length;
        length = array[0].length;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initializeBoard(char[][] array) { // prints nothing, just sets all array elements to '-'
        int length;
        int height;
        height = array.length;
        length = array[0].length;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = '-';
            }
        }
        printBoard(array);
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i][col] == 'x' || array[i][col] == 'o') {
                --i;
            }
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                return i; // this corresponds with the row that is "open" for a chip to be placed
            }
        }
        return 0;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int vertCount = 0;
        int horCount = 0;
        for (int i = row; i <= array.length - 1; i++) { // checks whether 4 in a row vertically
            if (array[i][col] == chipType)
                vertCount++;
            if (array[i][col] != chipType)
                break;
            if (vertCount == 4) {
                return false;
            }
        }
        for (int j = col; j <= array[0].length - 1; j++) { // checks whether 4 in a row from left to right horizontally
            if (array[row][j] == chipType)
                horCount++;
            if (array[row][j] != chipType) {
                break;
            }
            if (horCount == 4) {
                return false;
            }
        }
        if (col != 0) { // this checks chips horizontally from right to left
            for (int j = col - 1; j >= 0; j--) {
                if (array[row][j] == chipType)
                    ++horCount;
                if (array[row][j] != chipType)
                    break;
                if (horCount == 4) {
                    return false; // false is returned to end the game
                }
            }
        }
        return true;
    }
    public static boolean checkIfTie(char[][] array) { // this method checks if the board is full without any '-' characters
        int count = 0;
        for (char[] boardPiece : array) { // enhanced for loop checks if there are any '-' in the array
            for (char j : boardPiece) {
                if (j == '-') {
                    ++count; // if count ever increments, tie is not reached
                }
            }
        }
        if (count == 0) {
            return false; // returns false to end game
        }
        else {
            return true;
        }
    }
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int boardHeight = 0;
        int boardLength = 0;
        int columnChoice;
        int row;
        char player1 = 'x';
        char player2 = 'o';
        boolean startGame = false; // startGame initialized as false to not start game immediately

        System.out.print("What would you like the height of the board to be? ");
        boardHeight = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        boardLength = scnr.nextInt();
        char[][] gameBoard = new char[boardHeight][boardLength];
        if (boardHeight >= 4 && boardLength >= 4) {
            startGame = true;
            initializeBoard(gameBoard);
            System.out.println();
            System.out.println("Player 1: x");
            System.out.println("Player 2: o");
        }
        while (startGame) { // this loops until someone wins or ties, which would set startGame as false
            System.out.println();
            System.out.print("Player 1: Which column would you like to choose? ");
            columnChoice = scnr.nextInt();
            if (columnChoice >= 0 && columnChoice <= boardLength - 1) {
                row = insertChip(gameBoard, columnChoice, player1);
                gameBoard[row][columnChoice] = 'x';
                printBoard(gameBoard);
                startGame = checkIfWinner(gameBoard, columnChoice, row, player1); // checks every time piece is input
                if (!startGame) {
                    System.out.println("Player 1 won the game!");
                    break;
                }
                startGame = checkIfTie(gameBoard); // checks every time piece is input
                if (!startGame){
                    System.out.println("Draw. Nobody wins.");
                }
            }
            System.out.print("Player 2: Which column would you like to choose? ");
            columnChoice = scnr.nextInt();
            if (columnChoice >= 0 && columnChoice <= boardLength - 1) {
                row = insertChip(gameBoard, columnChoice, player2);
                gameBoard[row][columnChoice] = 'o';
                printBoard(gameBoard);
                startGame = checkIfWinner(gameBoard, columnChoice, row, player2);
                if (!startGame){
                    System.out.println("Player 2 won the game!");
                    break;
                }
                startGame = checkIfTie(gameBoard);
                if (!startGame) {
                    System.out.println("Draw. Nobody wins.");
                }
            }

        }
    }
    }
