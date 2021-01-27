package bullscows;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String secret = getSecret();
        System.out.println("Okay, let's start a game!");
        int turn = 1;
        while (true) {
            System.out.printf("Turn %d:%n", turn);
            String guess = scanner.next();
            int bulls = getGrade(secret, guess);
            if (bulls == secret.length()) {
                break;
            }
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static String getSecret() {
        int length = 0;
        int charsRange = 0;
        while (true) {
            try {
                System.out.println("Input the length of the secret code:");
                length = scanner.nextInt();
                System.out.println("Input the number of possible symbols in the code:");
                charsRange = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! Use integers from 1 to 10");
                scanner.next();
            }
        }
        if (length > 36 || length < 0 || charsRange < length) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.%n", length);
            return getSecret();
        } else {
            return generateRandom(length, charsRange);
        }
    }

    public static String generateRandom(int length, int charsRange) {
        Random random = new Random();
        while (true) {
            StringBuilder buffer = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int n = random.nextInt(charsRange);
                // ASCII CODE 48 - '0'
                // ASCII CODE 97 - 'a'
                n = n < 10 ? n + 48 : n + 87;
                char c = (char) n;
                buffer.append(c);
            }
            String generatedString = buffer.toString();
            String secret = generatedString;
            System.out.println(secret);
            if (secret.length() == length && checkUniqueDigits(secret)) {
                System.out.printf("The secret is prepared: %s %s%n", prepareAsterisks(length), prepareRanges(charsRange));
                return secret;
            }
        }
    }

    public static String prepareAsterisks(int length) {
        return "*".repeat(length);
    }

    public static String prepareRanges(int charsRange) {
        int d = charsRange < 9 ? charsRange - 1 : 9;
        char c;
        if (charsRange < 10) {
            return String.format("(0-%d)", d);
        } else {
            c = (char) (87 + charsRange - 1);
        }
        return String.format("(0-%d, a-%c)", d, c);
    }

    public static boolean checkUniqueDigits(String str) {
        boolean isUnique = true;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    isUnique = false;
                    break;
                }
            }
        }
        return isUnique;
    }

    public static int getGrade(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (secret.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }

        if (bulls != 0 || cows != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)%n", bulls, cows);
        } else {
            System.out.println("Grade: None.");
        }
        return bulls;
    }
}
