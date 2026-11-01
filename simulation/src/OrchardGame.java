import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class OrchardGame implements Game {

    private static final RandomGenerator RANDOM_GENERATOR = RandomGenerator.getDefault();

    private int piecesForRaven;

    private final int[] trees;

    private boolean gameOver;

    private int amountOfTurns = 0;

    private boolean teamWin;

    public OrchardGame(int piecesForRaven, int fruitsInTree) {
        this.piecesForRaven = piecesForRaven;
        this.trees = new int[]{fruitsInTree, fruitsInTree, fruitsInTree, fruitsInTree};
    }

    public OrchardGame() {
        this(9, 10);
    }

    @Override
    public GameResults play() {
        while (!gameOver) {
            playTurn();
            amountOfTurns++;
        }
        return new GameResults(teamWin, amountOfTurns);
    }

    private void playTurn() {
        var die = Random.from(RANDOM_GENERATOR).nextInt(1, 7);
        switch (die) {
            // Raven gets a piece
            case 5: {
                piecesForRaven--;
                break;
            }
            // The cart! Two pieces from the fullest tree
            case 6: {
                for (int i = 0; i < 2; i++) {
                    int maxIndex = 0;

                    for (int j = 1; j < trees.length; j++) {
                        if (trees[i] > trees[maxIndex]) {
                            maxIndex = j;
                        }
                    }

                    if (trees[maxIndex] > 0) {
                        trees[maxIndex]--;
                    }
                }
                break;
            }
            default: {
                if (trees[die - 1] > 0) {
                    trees[die - 1]--;
                }
                break;
            }
        }

        if (piecesForRaven <= 0) {
            teamWin = false;
            gameOver = true;
        } else if (Arrays.stream(trees).sum() <= 0) {
            teamWin = true;
            gameOver = true;
        }
    }
}
