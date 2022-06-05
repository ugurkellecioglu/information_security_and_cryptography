package application;

import java.math.BigInteger;

public class BigIntegerAdapter {
    /**
     * 
     * @param param
     * @return BigInteger object from string param (param is a string representation of a number) and base 10 (decimal)
     */
    public static BigInteger BigInteger(Object param) {
        if (param instanceof BigInteger) return (BigInteger) param;
        if (param instanceof String) return new BigInteger((String) param);
        return null;
    }
    /**
     * 
     * @param param
     * @return String from BigInteger param (param is a BigInteger representation of a number) and 
     */
    public static String String(Object param) {
        if (param instanceof BigInteger) return ((BigInteger) param).toString();
        if (param instanceof String) return (String) param;
        return null;
    }
}
