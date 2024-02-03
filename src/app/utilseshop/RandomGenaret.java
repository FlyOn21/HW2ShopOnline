package app.utilseshop;

import java.util.Random;

public class RandomGenaret {

    private static final Random random = new Random();
    public static String generate6DigitString() {
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }

    public static void main(String[] args) {
        String random6DigitString = generate6DigitString();
        System.out.println(random6DigitString);
    }
}
