package application;

import java.math.BigInteger;
import java.util.Random;

public class FermatPrimality
{

    /**
     * 
     * @param n is a BigInteger representation of a number in base 10 (decimal)
     * @param iteration is the number of iterations to perform
     * @return true if n is a prime number, false otherwise
     */
    public boolean isPrime(BigInteger n, int iteration)
    {
        /** base case **/
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) 
            return false;
        /** base case - 2 is prime **/
        if (n.equals(BigInteger.TWO))
            return true;
        /** an even number other than 2 is composite **/
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            return false;
        for (int i = 0; i < iteration; i++)
        {
            BigInteger r = new BigInteger(n.bitLength(), new Random()); // todo : get random big integer?
            BigInteger a = r.mod((n.subtract(BigInteger.ONE)).add(BigInteger.ONE));
            if (!modPow(a, n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE))
                return false;
        }
        return true;
    }
    /** Function to calculate (a ^ b) % c **/
    public BigInteger modPow(BigInteger a, BigInteger b, BigInteger c)
    {
        BigInteger res = new BigInteger("1");
        for (BigInteger i = new BigInteger("0"); b.compareTo(i) < 0; i.add(new BigInteger("1")))
        {
            res = res.multiply(a);
            res = res.mod(c);
        }
        return res.mod(c);
    }

}