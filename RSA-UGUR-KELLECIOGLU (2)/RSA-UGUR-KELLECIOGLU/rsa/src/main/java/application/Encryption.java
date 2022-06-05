package application;


public class Encryption {
  /**
   * 
   * @param msg is a string representation of a number in base 10 (decimal)
   * @param e is a string representation of a number in base 10 (decimal)
   * @param n is a string representation of a number in base 10 (decimal)
   * @return a string representation of a number in base 10 (decimal)
   */
  public static String encrypt(Message msg, Key e, Key n) {
      return BigIntegerAdapter.String(BigIntegerAdapter.BigInteger(msg.getVal()).modPow(BigIntegerAdapter.BigInteger(e.getVal()), BigIntegerAdapter.BigInteger(n.getVal())));
    }

}
