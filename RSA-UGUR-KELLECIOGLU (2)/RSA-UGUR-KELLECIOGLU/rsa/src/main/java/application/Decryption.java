package application;


public class Decryption {
    /**
     * 
     * @param cipherText is a string representation of a number in base 10 (decimal)
     * @param d is a string representation of a number in base 10 (decimal) 
     * @param n is a string representation of a number in base 10 (decimal)
     * @return a string representation of a number in base 10 (decimal)
     */
    public static String decryptPlainText(Message cipherText, Key d, Key n) {
         return BigIntegerAdapter.String(BigIntegerAdapter.BigInteger(cipherText.getVal()).modPow(BigIntegerAdapter.BigInteger(d.getVal()), BigIntegerAdapter.BigInteger(n.getVal())));
    }
}
