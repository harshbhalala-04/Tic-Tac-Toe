import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char[][] ticTacToeBoard = {{' ', ' ', ' '}, {' ', ' ', ' '},{' ', ' ', ' '}};

        ArrayList<Integer> player1Positions = new ArrayList<>();
        ArrayList<Integer> player2Positions = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter place where you want to put your sign(Player 1): ");

            int player1Pos = scanner.nextInt();

            //Validate player 1 input
            while(player1Positions.contains(player1Pos) || player2Positions.contains(player1Pos)) {
                System.out.println("Please select valid position: ");
                player1Pos = scanner.nextInt();
            }
            player1Positions.add(player1Pos);

            putSign(ticTacToeBoard, player1Pos, "player 1");

            displayTicTacToeBoard(ticTacToeBoard);
            String res = winningCondition(ticTacToeBoard, player1Positions, player2Positions);
            if(res != "") {
                System.out.println(res);
                return;
            }

            System.out.println("Enter place where you want to put your sign(Player 2): ");
            int player2Pos = scanner.nextInt();

            //Validate player 2 input
            while(player1Positions.contains(player2Pos) || player2Positions.contains(player2Pos)) {
                System.out.println("Please select valid position: ");
                player2Pos = scanner.nextInt();
            }

            player2Positions.add(player2Pos);

            putSign(ticTacToeBoard, player2Pos, "player 2");

            displayTicTacToeBoard(ticTacToeBoard);

            res = winningCondition(ticTacToeBoard, player1Positions, player2Positions);
            if(res != "") {
                System.out.println(res);
                return;
            }
        }


    }

    public static void displayTicTacToeBoard(char[][] ticTacToeBoard) {
        System.out.println("Current tic tac toe board");
        for(char[] row: ticTacToeBoard) {
            for(char element: row) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public static String winningCondition(char[][] ticTacToeBoard, ArrayList<Integer> player1Positions, ArrayList<Integer> player2Positions) {

        List<List> winningMovesList = new ArrayList<>();
        
        winningMovesList.add(Arrays.asList(1, 2, 3));
        winningMovesList.add(Arrays.asList(4, 5, 6));
        winningMovesList.add(Arrays.asList(7, 8, 9));
        winningMovesList.add(Arrays.asList(1, 4, 7));
        winningMovesList.add(Arrays.asList(2, 5, 8));
        winningMovesList.add(Arrays.asList(3, 6, 9));
        winningMovesList.add(Arrays.asList(1, 5, 9));
        winningMovesList.add(Arrays.asList(3, 5, 7));

        for(List singleMoveList: winningMovesList) {
            if(player1Positions.containsAll(singleMoveList)) {
                return "Player 1 won the game!!";
            } else if(player2Positions.containsAll(singleMoveList)) {
                return "Player 2 won the game!!";
            } else if (player1Positions.size() + player2Positions.size() == 9) {
                return "Game draw";
            }
        }
        return "";
    }

    public static void putSign(char[][] ticTacToeBoard, int pos, String user) {

        char sign = ' ';

        if(user.equals("player 1")) {
            sign = 'X';
        } else {
            sign = 'O';
        }

        switch (pos) {
            case 1:
                ticTacToeBoard[0][0] = sign;
                break;
            case 2:
                ticTacToeBoard[0][1] = sign;
                break;
            case 3:
                ticTacToeBoard[0][2] = sign;
                break;
            case 4:
                ticTacToeBoard[1][0] = sign;
                break;
            case 5:
                ticTacToeBoard[1][1] = sign;
                break;
            case 6:
                ticTacToeBoard[1][2] = sign;
                break;
            case 7:
                ticTacToeBoard[2][0] = sign;
                break;
            case 8:
                ticTacToeBoard[2][1] = sign;
                break;
            case 9:
                ticTacToeBoard[2][2] = sign;
                break;
        }

    }
}