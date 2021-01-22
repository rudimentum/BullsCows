package bullscows;

import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int secret = generateRandom();
        int guess = scanner.nextInt();
        getGrade(secret, guess);
        System.out.printf("The secret code is %d.", secret);
    }

    public static int generateRandom() {
        int length = scanner.nextInt();
        if (length < 11 && length > 0) {
            while (true) {
                long pseudoRandomNumber = Long.reverse(System.nanoTime());
                try {
                    String temp = (pseudoRandomNumber + "").substring(0, length);
                    if (checkUniqueDigits(temp)) {
                        System.out.println("The random secret number is " + temp);
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

    public static void getGrade(int secret, int guess) {
        int tempSecret = secret;
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            int numDigit = guess % 10;
            for (int j = 0; j < 4; j++) {
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
            System.out.printf("Grade: %d bull(s) and %d cow(s)", bulls, cows);
        } else {
            System.out.println("Grade: None.");
        }
    }
}
