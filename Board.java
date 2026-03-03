/**
 * High level goals
 *  1. create boggle backend
 *  2. create boggle webapp
 *  3. create boggle bot solver to "play against the bot"
 */

/**
 * Creating Boggle backend
 * 1. create board based on boggle rules with sets of dice, letters, map nodes to connect them, etc.
 * 2. set up input and point counter using word dict and DFS on map
 * https://www.bananagrammer.com/2013/10/the-boggle-cube-redesign-and-its-effect.html - used to hardcode some boards
 */

import java.util.*;

public class Board {
    private Random random; // the board's random generator for its dice and shuffling
    static final String[] classicBoard = new String[]{ // the "classic" set of dice according to the link above
            "AACIOT", "ABILTY", "ABJMOQu", "ACDEMP",
            "ACELRS", "ADENVZ", "AHMORS", "BIFORX",
            "DENOSW", "DKNOTU", "EEFHIY", "EGKLUY",
            "EGINTV", "EHINPS", "ELPSTU", "GILRUW"
    };
    static final String[] modernBoard = new String[] { // the "new" set of dice found above
            "AAEEGN", "ABBJOO", "ACHOPS", "AFFKPS",
            "AOOTTW", "CIMOTU", "DEILRX", "DELRVY",
            "DISTTY", "EEGHNW", "EEINSU", "EHRTVW",
            "EIOSST", "ELRTTY", "HIMNUQu", "HLNNRZ",
    };
    static String[] randDiceArr = new String[16]; // for if I ever want to make a random set of dice
    private Dice[][] board = new Dice[4][4]; // the board as a 2D array of dice where 0,0 is the bottom left die and 3,3 is top right
    private Map<Dice, List<Dice>> adjList;
    private Dice[] flatBoard = new Dice[16]; // flatBoard, useful for a few operations
    private final int BOARDSIZE = 4;

     /**
     * Constructor for a boggle board with different option for available dice
     * @param boardType - flag for what type of dice to be used in board
     */
    public Board(String boardType) {
        this.random = new Random();
        if (boardType.contains("classic")) {
            for (int row = 0; row < BOARDSIZE; row++) {
                for (int col = 0; col < BOARDSIZE; col++) {
                    Dice die = new Dice(classicBoard[(BOARDSIZE*row)+col], random);
                    this.board[row][col] = die;
                    this.flatBoard[(BOARDSIZE*row)+col] = die;
                }
            }
        } else if (boardType.contains("modern")) {
            for (int row = 0; row < BOARDSIZE; row++) {
                for (int col = 0; col < BOARDSIZE; col++) {
                    Dice die = new Dice(modernBoard[(BOARDSIZE*row)+col], random);
                    this.board[row][col] = die;
                    this.flatBoard[(BOARDSIZE*row)+col] = die;
                }
            }
        } else { // random
            // generateRandomBoard
        }
        System.out.println("Before shuffling board");
        shuffleBoard(); // note that creating the adj list happens in shuffleBoard
        System.out.println("Shuffled Board");
    }

    /**
     * Shuffles the board, used online info about Fisher-Yates algorithm
     */
    private void shuffleBoard() {
        Dice[] flatBoard = this.flatBoard;
        // shuffle flat array using Fisher-Yates algorithm
        for (int i = flatBoard.length-1; i > 0; i--) {
            int r = random.nextInt(i+1);
            Dice temp = flatBoard[i];
            flatBoard[i] = flatBoard[r];
            flatBoard[r] = temp;
        }

        // map flatBoard into 2D board
        for (int i = 0; i < flatBoard.length; i++) {
            this.board[i / this.BOARDSIZE][i%this.BOARDSIZE] = flatBoard[i];
        }
        createAdjList(); // create new adjList for post-shuffle board

    }

    /**
     * Creates an adjacency list for each die on the board, Claude helped create it though I have made these myself lol
     */
    private void createAdjList() {
        adjList = new HashMap<>();
        for (int row = 0; row < 4; row++) { // iterate through each row
            for (int col = 0; col < 4; col++) { // iterate through each cell in row
                List<Dice> neighbors = new ArrayList<>(); // current list of neighbors for current cell
                for (int deltaRow = -1; deltaRow <= 1; deltaRow++) { // cells to above and below current cell
                    for (int deltaColumn = -1; deltaColumn <= 1; deltaColumn++) { // cells to left and right of current
                        if (deltaRow == 0 && deltaColumn == 0) continue; // current cell
                        int neighborRow = row + deltaRow;
                        int neighborCol = col + deltaColumn;
                        if (neighborRow >= 0 && neighborRow < 4 && neighborCol >= 0 && neighborCol < 4) {
                            neighbors.add(board[neighborRow][neighborCol]);
                        }
                    }
                }
                this.adjList.put(board[row][col], neighbors);
            }
        }
    }

    /**
     * Simple toString method that prints out the board as a 2D grid
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[row].length; col++) {
                String letter = this.board[row][col].getFaceUp();
                sb.append(letter);
                if (letter.contains("Q"))
                    sb.append(" ");
                else
                    sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Board board = new Board("classic");
        System.out.println(board.toString());
    }
}
