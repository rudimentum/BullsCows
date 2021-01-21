package bullscows;

import java.util.Scanner;

public class Main {
    final static int secret = 1234;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int guess = scanner.nextInt();
        getGrade(secret, guess);
        System.out.printf("The secret code is %d.", secret);
    }

    public static void getGrade(int secret, int guess) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            int numDigit = guess % 10;
            for (int j = 0; j < 4; j++) {
                int secDigit = secret % 10;
                if (numDigit == secDigit) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
                secret = secret / 10;
            }
            secret = Main.secret; // restore
            guess = guess / 10;
        }

        if (bulls != 0 || cows != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)", bulls, cows);
        } else {
            System.out.println("Grade: None.");
        }
    }
}
