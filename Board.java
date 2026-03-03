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
import java.util.Map;

public class Board {
    static final String[] classicBoard = new String[]{ // the "classic" set of dice according to the link above
            "AACIOT", "ABILTY", "ABJMOQu", "ACDEMP",
            "ACELRS", "ADENVZ", "AHMORS", "BIFORX",
            "DENOSW", "DKNOTU", "EEFHIY", "EGKLUY",
            "EGINTV", "EHINPS", "ELPSTU", "GILRUW"
    };
    static final String[] newBoard = new String[] { // the "new" set of dice found above
            "AAEEGN", "ABBJOO", "ACHOPS", "AFFKPS",
            "AOOTTW", "CIMOTU", "DEILRX", "DELRVY",
            "DISTTY", "EEGHNW", "EEINSU", "EHRTVW",
            "EIOSST", "ELRTTY", "HIMNUQu", "HLNNRZ",
    };
    static String[] randDiceArr = new String[16]; // for if I ever want to make a random set of dice
}
