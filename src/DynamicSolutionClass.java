import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DynamicSolutionClass {
    List<List<String>> dynamicTicTacToeBoard = new ArrayList<>();
    ArrayList<Integer> player1Positions = new ArrayList<>();
    ArrayList<Integer> player2Positions = new ArrayList<>();
    int boardDimensions;
    enum Player {
        PLAYER1,
        PLAYER2
    }

    public void startTicTacToe() {

        System.out.println("Enter in which board do you want to play : (Ex. 3 X 3, 4 X 4)");
        boardDimensions = new Scanner(System.in).nextInt();
        int singleUserGame;
        createBoard(boardDimensions);

        while (true) {

            singleUserGame = playGameForSingleUser(Player.PLAYER1);
            if(singleUserGame == 1) {
                return;
            }
            singleUserGame = playGameForSingleUser(Player.PLAYER2);
            if(singleUserGame == 1) {
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


    public int pickInput() {
        Scanner scanner = new Scanner(System.in);
        int playerPos = scanner.nextInt();
        while (player1Positions.contains(playerPos) || player2Positions.contains(playerPos)) {
            System.out.println("Please select valid position: ");
            playerPos = scanner.nextInt();
        }
        return playerPos;
    }

    public int playGameForSingleUser(Player user) {
        System.out.println("Enter place where you want to put your sign(Player 1): ");
        int playerPos = pickInput();
        if(user == Player.PLAYER1) {
            player1Positions.add(playerPos);
        } else if(user == Player.PLAYER2) {
            player2Positions.add(playerPos);
        }

        putSign(playerPos, user);
        displayTicTacToeBoard();
        String res = winningCondition();
        if (res != null) {
            System.out.println(res);
            return 1;
        }
        return 0;
    }

    public void putSign( int pos,Player user) {

        String sign = " ";

        if(user == Player.PLAYER1) {
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

    public String winningCondition() {

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
        return null;
    }
}
