import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DynamicSolutionClass {
    List<List<String>> dynamicTicTacToeBoard = new ArrayList<>();

    public void startTicTacToe() {

        ArrayList<Integer> player1Positions = new ArrayList<>();
        ArrayList<Integer> player2Positions = new ArrayList<>();

        System.out.println("Enter in which board do you want to play : (Ex. 3 X 3, 4 X 4)");
        int boardDimensions = new Scanner(System.in).nextInt();

        createBoard(boardDimensions);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter place where you want to put your sign(Player 1): ");

            int player1Pos = scanner.nextInt();

            //Validate player 1 input
            while (player1Positions.contains(player1Pos) || player2Positions.contains(player1Pos)) {
                System.out.println("Please select valid position: ");
                player1Pos = scanner.nextInt();
            }
            player1Positions.add(player1Pos);

            putSign(boardDimensions, player1Pos, "player 1");

            displayTicTacToeBoard();
            String res = winningCondition(boardDimensions, player1Positions, player2Positions);
            if (res != "") {
                System.out.println(res);
                return;
            }

            System.out.println("Enter place where you want to put your sign(Player 2): ");
            int player2Pos = scanner.nextInt();

            //Validate player 2 input
            while (player1Positions.contains(player2Pos) || player2Positions.contains(player2Pos)) {
                System.out.println("Please select valid position: ");
                player2Pos = scanner.nextInt();
            }

            player2Positions.add(player2Pos);

            putSign(boardDimensions, player2Pos, "player 2");

            displayTicTacToeBoard();

            res = winningCondition(boardDimensions, player1Positions, player2Positions);
            if (res != "") {
                System.out.println(res);
                return;
            }
        }
    }

    public void createBoard(int boardDimensions) {
        for(int row = 0; row < boardDimensions; row++) {
            List<String> tempList = new ArrayList<>();
            for(int col = 0; col < boardDimensions; col++) {
                tempList.add(" ");
            }
            dynamicTicTacToeBoard.add(tempList);
        }
    }

    public void putSign(int boardDimensions, int pos, String user) {

        String sign = " ";

        if(user.equals("player 1")) {
            sign = "X";
        } else {
            sign = "O";
        }

        dynamicTicTacToeBoard.get((pos - 1) / boardDimensions).set((pos - 1) % boardDimensions, sign);

    }

    public void displayTicTacToeBoard() {
        System.out.println("Current tic tac toe board");
        for(List<String> row: dynamicTicTacToeBoard) {
            for(String element: row) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public String winningCondition(int boardDimensions, ArrayList<Integer> player1Positions, ArrayList<Integer> player2Positions) {

        List<List> winningMovesList = new ArrayList<>();


        //Adding row
        for(int row = 0; row < boardDimensions; row++) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for(int col = 0; col < boardDimensions; col++) {
                tempList.add(row * boardDimensions + col + 1);
            }
            winningMovesList.add(tempList);
        }

        //Adding Col
        for(int col = 0; col < boardDimensions; col++) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for(int row = 0; row < boardDimensions; row++) {
                tempList.add(row * boardDimensions + col + 1);
            }
            winningMovesList.add(tempList);
        }

        //Adding left diagonal
        ArrayList<Integer> leftDiagonal = new ArrayList<>();
        leftDiagonal.add(1);
        for(int row = 1; row < boardDimensions; row++) {
            leftDiagonal.add(leftDiagonal.get(row - 1) + boardDimensions + 1);
        }

        winningMovesList.add(leftDiagonal);

        //Adding right diagonal
        ArrayList<Integer> rightDiagonal = new ArrayList<>();
        rightDiagonal.add(boardDimensions);
        for(int row = 1; row < boardDimensions; row++) {
            rightDiagonal.add(rightDiagonal.get(row - 1) + boardDimensions - 1);
        }

        winningMovesList.add(rightDiagonal);
        

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
}
