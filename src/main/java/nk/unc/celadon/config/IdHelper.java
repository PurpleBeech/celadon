package nk.unc.celadon.config;

import java.util.Base64;
import java.security.SecureRandom;

public class IdHelper {
    public static final String VALUES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    private static final char[] ID_VALUES = VALUES.toCharArray();
    private static int ID_SIZE = 10;
    
    public static String generateHash() {
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder(ID_SIZE);
        for (int i=0; i < ID_SIZE; i++) {
            int index = random.nextInt(ID_VALUES.length);
            builder.append(ID_VALUES[index]);
        }
        return builder.toString();
    }
}
