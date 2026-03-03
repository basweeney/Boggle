// found on https://boardgames.stackexchange.com/questions/29264/boggle-what-is-the-dice-configuration-for-boggle-in-various-languages
// it's worth noting that big boggle or whatever version my family owns, it is a 5x5 grid with some other letter pairs
// including in, or th, rather than what normal boggle holds which is only a Qu

import java.util.Arrays;
import java.util.Random;

public class Dice {
    private final String[] faces = new String[6]; // the faces of each die
    private String faceUp; // the current face that is "face up" on a board

    /**
     * Constructor for the Dice class, which is just an array of 6 strings, can't call it die due to java syntax
     */
    public Dice(String die, Random random) {
        for (int i = 0; i < die.length(); i++) {
            char face = die.charAt(i);
            if (face == 'Q') {
                this.faces[i] = "Qu";
                i++;
            } else {
                this.faces[i] = String.valueOf(face);
            }
        }
        this.roll(random);
    }

    /**
     * basic toString method that just prints all the die's faces
     * @return a string representation of the die
     */
    public String toString() {
        return Arrays.toString(this.faces);
    }

    /**
     * Randomly generate a face to make faceUp
     * @param r a Random object to randomly generate the face
     */
    public void roll(Random r) {
        faceUp = faces[r.nextInt(faces.length)];
    }

    /**
     * returns the current letter faceUp on the die
     * @return the die's faceUp letter
     */
    public String getFaceUp() {
        return this.faceUp;
    }
}
