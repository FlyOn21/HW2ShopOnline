package app.utilseshop;
import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordProcessing {
    private static final String SICRET_STRING = "qwertyyuut";
    public static boolean checkPassword (String password, String hash) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        return  Password.check(password, hash)
                .addPepper(SICRET_STRING)
                .with(bcrypt);
    }

    public static String passwordHash (String password) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

        Hash hash = Password.hash(password)
                .addPepper(SICRET_STRING)
                .with(bcrypt);

        return hash.getResult();
    }
// Test case
//    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        String password = "qwerty123";
//        String wrongPassword = " 13456";
//        String hashTest = passwordHash(password);
//        System.out.println("Hash: " + hashTest);
//        boolean checkPassword = checkPassword(password, hashTest);
//        System.out.println("Password is valid: " + checkPassword);
//        boolean checkPasswordWrong = checkPassword(wrongPassword, hashTest);
//        System.out.println("Password is valid: " + checkPasswordWrong);
//    }
}
