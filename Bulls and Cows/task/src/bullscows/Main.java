package bullscows;

public class Main {

    public static void main(String[] args) {
        String secret = BullsCows.getSecret();
        System.out.println("Okay, let's start a game!");
        int turn = 1;
        while (true) {
            System.out.printf("Turn %d:%n", turn);
            String guess = BullsCows.inputGuess();
            int bulls = BullsCows.getGrade(secret, guess);
            if (bulls == secret.length()) {
                break;
            }
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
