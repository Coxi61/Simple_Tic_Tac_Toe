package tictactoe;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {


    // tested for board 4x4 as well : x____x____x____x
    static final int gameDimension = 3;
    static boolean isGameFinished;
    static int numberOfHitX;
    static int numberOfHitO;
    static GameState gameState;
    static final String emptySquareTokenPrinted =" ";
    static final String emptySquareTokenInternal ="_";



    static int numberOfCells = gameDimension * gameDimension;
    static String[][] board = new String[gameDimension][gameDimension];
    static String boardString;
    static String moveRowString;
    static String moveColumnString;
    static int moveRow;
    static int moveColumn;
    static String whoseTurn;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String temp;
        String showGameState;


        emptyBoard();
        drawGrid(emptySquareTokenPrinted);
        whoseTurn = "X";

        //Creates a game loop
        while (true) {
            //update board with one new move
            inputNextMove();

            //judge
            judgeBoardState();
            drawGrid(emptySquareTokenPrinted);
            switchWhoseTurn();
            if (gameState != GameState.GAME_NOT_FINISHED) {
                temp = gameState.name().toLowerCase().replace('_', ' ');
                showGameState = temp.substring(0, 1).toUpperCase(Locale.ROOT) + temp.substring(1);
                System.out.println(showGameState);
            }

            //Ends the game when someone wins or there is a draw.
            if (gameState == GameState.DRAW || gameState == GameState.X_WINS || gameState == GameState.O_WINS ||
                    gameState == GameState.IMPOSSIBLE) {
                break;
            }
        }

    }

    public static void switchWhoseTurn() {
        if (whoseTurn.equals("X")) {
            whoseTurn = "O";
        } else {
            whoseTurn = "X";
        }
    }

    public static void emptyBoard() {
        //fill board array with position
        for (int i = 0; i < gameDimension; i++) {
            for (int j = 0; j < gameDimension; j++) {
                board[i][j] = "_";
            }
        }
        boardString = Arrays.deepToString(board);
    }


    public static void inputNextMove() {
        String temp;
        //input new move
        boolean checkInput = false;
        while (!checkInput) {
            System.out.print("Enter the coordinates: ");
            if (checkMoveIsTwoNumber()) {
                    if (checkMoveRange(moveRow)) {
                        if (checkMoveRange(moveColumn)) {
                            if (checkMoveIsValid(moveRow, moveColumn)) {
                                checkInput = true;
                            }
                        }
                    }
                }

            //delete previous inputs rubbish
            // temp = sc.nextLine();
        }

        //upgrade board position
        board[moveRow - 1][moveColumn - 1] = whoseTurn;
        boardString = Arrays.deepToString(board);
    }

    public static boolean checkMoveIsTwoNumber() {
        if (!sc.hasNextInt()) {
            System.out.println("You should enter numbers!");
            sc.next(); //read the wrong input , not to process again
            if (sc.hasNext()) {
                sc.next();
            }
            return false;
        } else {
            moveRow = sc.nextInt();
            if (!sc.hasNextInt()) {
                System.out.println("You should enter numbers!");
                sc.next();
                return false;
            } else {
                moveColumn = sc.nextInt();
            }
        }
        return true;
    }

    public static void printEmptyGrid() {
        System.out.println("---------");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("---------");
    }

    public static boolean checkMoveIsValid(int moveRow, int moveColumn) {
        boolean checkResult = true;
        if (!board[moveRow -1][moveColumn - 1].equals("_")) {
            checkResult = false;
            System.out.println("This cell is occupied! Choose another one!");
            //System.out.printf("row %d col %d token ***%s***", moveRow - 1, moveColumn - 1, board[moveRow -1][moveColumn - 1]);
        }
        return checkResult;
    }

    public static boolean checkMoveIsNumber(String moveCoordinate) {
        boolean checkResult = true;
        try {
            //System.out.printf("moveCoordinate = %s", moveCoordinate);
            Integer.parseInt(moveCoordinate);
        } catch (NumberFormatException nfe) {
            checkResult = false;
            System.out.println("You should enter numbers!");
        }
        return checkResult;
    }

    public static boolean checkMoveRange(int moveCoordinate) {
        boolean checkResult = true;
        if (moveCoordinate < 1 || 3 < moveCoordinate) {
            checkResult = false;
            System.out.println("Coordinates should be from 1 to 3!");
        }
        return checkResult;
    }


    public static int countNumberX() {
        //System.out.println("countNumberX = " + boardString.length() - boardString.replace("X", "").length();
        return boardString.length() - boardString.replace("X", "").length();
    }

    public static int countNumberO() {
        //System.out.println("countNumberO = " + boardString.length() - boardString.replace('O', "").length());
        return boardString.length() - boardString.replace("O", "").length();
    }


    public static void judgeBoardState() {
        //intelligence
        numberOfHitX = searchHit("XXX");
        numberOfHitO = searchHit("OOO");
        isGameFinished = isFinished();

        //judge
        if (numberOfHitX > 0 && numberOfHitO == 0) {
            gameState = GameState.X_WINS;
        } else if (numberOfHitO > 0 && numberOfHitX == 0) {
            gameState = GameState.O_WINS;
        } else if (numberOfHitO > 0 && numberOfHitX > 0) {
            gameState = GameState.IMPOSSIBLE;
        } else if (Math.abs(countNumberX() - countNumberO()) > 1) {
            gameState = GameState.IMPOSSIBLE;
        } else if (numberOfHitX == 0 && numberOfHitO == 0 && isGameFinished) {
            gameState = GameState.DRAW;
        } else if (numberOfHitX == 0 && numberOfHitO == 0 && !isGameFinished) {
            gameState = GameState.GAME_NOT_FINISHED;
        }
    }

    static public void drawGrid(String emptySquareTokenPrinted) {

        String upperFrame = "-".repeat(numberOfCells);
        System.out.println(upperFrame);
        for (int i = 0; i < gameDimension; i++) {
            System.out.print("| ");
            for (int j = 0; j < gameDimension; j++) {
                System.out.print(board[i][j].replace("_", emptySquareTokenPrinted) + " ");
            }
            System.out.print("|\n");
        }
        System.out.println(upperFrame);
    }

    static public boolean isFinished() {
        if (boardString.indexOf(emptySquareTokenInternal) == -1) {
            return true;
        } else {
            return false;
        }
    }

    static public int searchHit(String hitDefinition) {
        int hitCount = 0;

        //rows
        //System.out.println();
        for (int i = 0; i < gameDimension; i++) {
            String row = "";
            for (int j = 0; j < gameDimension; j++) {
                row += board[i][j];
            }
            if (row.equals(hitDefinition)) {
                hitCount++;
            }
            //System.out.printf(" row %d , %s", i, row);
        }

        //columns
        //System.out.println();
        for (int i = 0; i < gameDimension; i++) {
            String column = "";
            for (int j = 0; j < gameDimension; j++) {
                column += board[j][i];
            }
            if (column.equals(hitDefinition)) {
                hitCount++;
            }
            //System.out.printf(" col %d , %s", i, column);
        }
        //System.out.println();
        //System.out.printf("hitCount = %d", hitCount);

        //diagonal (left top - right bottom)
        //System.out.println();
        String diagonal = "";
        for (int i = 0; i < gameDimension; i++) {
            diagonal += board[i][i];
        }
        if (diagonal.equals(hitDefinition)) {
            hitCount++;
        }
        //System.out.printf(" diagonal " + diagonal);
        //System.out.println();
        //System.out.printf("hitCount = %d", hitCount);

        //diagonal (left bottom - right top)
        //System.out.println();
        diagonal = "";
        for (int i = 0; i < gameDimension; i++) {
            diagonal += board[i][gameDimension - 1 - i];
        }
        if (diagonal.equals(hitDefinition)) {
            hitCount++;
        }
        //System.out.printf(" diagonal (left bottom - right top) " + diagonal);
        //System.out.println();
        //System.out.printf("hitCount = %d", hitCount);

        return hitCount;
    }
}

/*
Game not finished when neither side has three in a row but the grid still has empty cells.
Draw when no side has a three in a row and the grid has no empty cells.
X wins when the grid has three X’s in a row.
O wins when the grid has three O’s in a row.
Impossible when the grid has three X’s in a row as well as three O’s in a row,
    or there are a lot more X's than O's or vice versa (the difference should be 1 or 0;
    if the difference is 2 or more, then the game state is impossible).
 */
enum GameState {
    GAME_NOT_FINISHED,
    DRAW,
    X_WINS,
    O_WINS,
    IMPOSSIBLE
}
