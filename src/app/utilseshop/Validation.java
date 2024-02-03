package app.utilseshop;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validation {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    // standard E.164
    private static final String PHONE_REGEX = "\\(?\\+[0-9]{1,3}\\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})?";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

//    public static void main(String[] args) {
//        String email = "zhogolevpv@gmail.com";
//        String phoneNumber = "+380504121644";
//
//        boolean isEmailValid = isValidEmail(email);
//        boolean isPhoneNumberValid = isValidPhoneNumber(phoneNumber);
//
//        System.out.println("Email is valid: " + isEmailValid);
//        System.out.println("Phone number is valid: " + isPhoneNumberValid);
//    }
}
