package by.bsu.finalproject.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptographer {

    private static final String HASHING_TYPE = "SHA-1";

    public String encrypt(String password){

        byte[] bytes = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_TYPE);
            messageDigest.update(password.getBytes("utf-8"));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1,bytes);
        String encryptedPassword = bigInteger.toString(16);
        return encryptedPassword;
}
}
