import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerXPositions = new ArrayList<>();
    static ArrayList<Integer> playerOPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
        };
        
        printGameBoard(gameBoard);

        Scanner scan = new Scanner(System.in);
        while (true) {
            // Player X's turn
            System.out.println("Enter the position (1-9) for player X:");
            int playerPosition = scan.nextInt();
            placePiece(gameBoard, playerPosition, 'X');
            printGameBoard(gameBoard);
            playerXPositions.add(playerPosition);

            if (checkWinner() != null) {
                System.out.println(checkWinner());
                break;
            }

            // Player O's turn
            Random rand = new Random();
            int randomPosition;
            do {
                randomPosition = rand.nextInt(9) + 1; // Random position between 1 and 9
            } while (playerXPositions.contains(randomPosition) || playerOPositions.contains(randomPosition)); // Ensure the position is not already taken
            
            placePiece(gameBoard, randomPosition, 'O');
            printGameBoard(gameBoard);
            playerOPositions.add(randomPosition);

            if (checkWinner() != null) {
                System.out.println(checkWinner());
                break;
            }
        }
        scan.close();
    }

    public static void placePiece(char[][] gameBoard, int position, char piece) {
        switch (position) {
            case 1: gameBoard[0][0] = piece; break;
            case 2: gameBoard[0][2] = piece; break;
            case 3: gameBoard[0][4] = piece; break;
            case 4: gameBoard[2][0] = piece; break;
            case 5: gameBoard[2][2] = piece; break;
            case 6: gameBoard[2][4] = piece; break;
            case 7: gameBoard[4][0] = piece; break;
            case 8: gameBoard[4][2] = piece; break;
            case 9: gameBoard[4][4] = piece; break;
            default: System.out.println("Invalid position! Please enter a number between 1 and 9.");
        }
    }

    public static void printGameBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static String checkWinner() {
        List<List<Integer>> winningConditions = Arrays.asList(
            Arrays.asList(1, 2, 3), // Top row
            Arrays.asList(4, 5, 6), // Middle row
            Arrays.asList(7, 8, 9), // Bottom row
            Arrays.asList(1, 4, 7), // Left column
            Arrays.asList(2, 5, 8), // Middle column
            Arrays.asList(3, 6, 9), // Right column
            Arrays.asList(1, 5, 9), // Diagonal
            Arrays.asList(3, 5, 7)  // Diagonal
        );

        for (List<Integer> condition : winningConditions) {
            if (playerXPositions.containsAll(condition)) {
                return "Congratulations! Player X wins!";
            } else if (playerOPositions.containsAll(condition)) {
                return "Congratulations! Player O wins!";
            }
        }

        if (playerXPositions.size() + playerOPositions.size() == 9) {
            return "It's a draw!";
        }

        return null; // Game is still ongoing
    }
}
