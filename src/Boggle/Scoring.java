package src.Boggle;

public class Scoring {

    public static boolean findWordInDict(String word) {
        return true;
    }

    public static int calcScore (String word) {
        int wordLength = word.length();
        int scoreValue = 0;
        if (wordLength < 3)
            return scoreValue;
        switch(wordLength) {
            case 3:
            case 4:
                scoreValue = 1;
                break;
            case 5:
                scoreValue = 2;
                break;
            case 6:
                scoreValue = 3;
                break;
            case 7:
                scoreValue = 5;
                break;
            default:
                scoreValue = 11;
                break;
        }
        return scoreValue;
    }
}
