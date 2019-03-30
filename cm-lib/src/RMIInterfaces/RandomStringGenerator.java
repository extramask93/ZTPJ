package RMIInterfaces;

import java.util.Locale;
import java.util.Random;

public class RandomStringGenerator {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;
    public static String generate(int len) {
        Random rand = new Random();
        String result = new String();
        for(int  i =0; i<len;i++) {
            int offset = rand.nextInt(alphanum.length());
            result += alphanum.charAt(offset);
        }
        return result;
    }
}
