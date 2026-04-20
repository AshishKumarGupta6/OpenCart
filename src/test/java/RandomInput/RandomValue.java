package RandomInput;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomValue {

    public static String getAllAlphabetic(){
        String generateString = RandomStringUtils.randomAlphabetic(5);
        return generateString;
    }

    public static String getAllNumeric(){
        String getNumeric=RandomStringUtils.randomNumeric(5);
        return getNumeric;
    }
}
