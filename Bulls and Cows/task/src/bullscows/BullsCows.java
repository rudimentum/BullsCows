package bullscows;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BullsCows {
    final static Scanner scanner = new Scanner(System.in);

    public String getSecret() {
        int length;
        int charsRange;
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
        if (length > 36 || length <= 0 || charsRange < length) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.%n", length);
        } else if (charsRange > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z)");
        } else {
            return generateRandom(length, charsRange);
        }
        return getSecret();
    }

    public String inputGuess() {
        return scanner.next();
    }

    /**
     * Generate a secret code containing numbers 0-9 and lowercase Latin characters a-z
     * Print the secret code using * characters
     * Print which characters were used to generate the secret code
     * @return randomly generated secret code
     */
    public String generateRandom(int length, int charsRange) {
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
            String secret = buffer.toString();
            if (secret.length() == length && checkUniqueDigits(secret)) {
                System.out.printf("The secret is prepared: %s %s%n", prepareAsterisks(length), prepareRanges(charsRange));
                return secret;
            }
        }
    }

    public String prepareAsterisks(int length) {
        return "*".repeat(length);
    }

    /**
     * Check the number of possible characters in the code to notify the user about allowed ranges
     * @return characters were used to generate the secret code
     */
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

    public boolean checkUniqueDigits(String str) {
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

    public int getGrade(String secret, String guess) {
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
