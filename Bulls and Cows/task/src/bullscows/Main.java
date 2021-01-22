package bullscows;

import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int secret = generateRandom();
        System.out.println("Okay, let's start a game!");
        int turn = 1;
        while (true) {
            System.out.printf("Turn %d:%n", turn);
            int guess = scanner.nextInt();
            int bulls = getGrade(secret, guess);
            if (bulls == String.valueOf(secret).length()) {
                break;
            }
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static int generateRandom() {
        int length = scanner.nextInt();
        if (length < 11 && length > 0) {
            while (true) {
                long pseudoRandomNumber = Long.reverse(System.nanoTime());
                try {
                    String temp = String.valueOf(pseudoRandomNumber).substring(0, length);
                    if (checkUniqueDigits(temp)) {
                        return Integer.parseInt(temp);
                    }
                } catch (Exception e) {
                }
            }
        } else {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
            return generateRandom();
        }
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

    public static int getGrade(int secret, int guess) {
        int length = String.valueOf(secret).length();
        int tempSecret = secret;
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < length; i++) {
            int numDigit = guess % 10;
            for (int j = 0; j < length; j++) {
                int secDigit = tempSecret % 10;
                if (numDigit == secDigit) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
                tempSecret = tempSecret / 10;
            }
            tempSecret = secret; // restore
            guess = guess / 10;
        }

        if (bulls != 0 || cows != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)%n", bulls, cows);
        } else {
            System.out.println("Grade: None.");
        }
        return bulls;
    }
}
