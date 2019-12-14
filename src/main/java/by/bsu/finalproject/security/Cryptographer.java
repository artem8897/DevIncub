package by.bsu.finalproject.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptographer {

    private static final String HASHING_TYPE = "SHA-1";
    private static final String ENCODING = "utf-8";
    private static final Logger logger = LogManager.getLogger(Cryptographer.class);

    public String encrypt(String password){

        byte[] bytes = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_TYPE);
            messageDigest.update(password.getBytes(ENCODING));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error(e);
        }
        BigInteger bigInteger = new BigInteger(1,bytes);
        return bigInteger.toString(16);
}
}
