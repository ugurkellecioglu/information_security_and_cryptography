package application;

import java.math.BigInteger;
import java.util.Random;

public class KeyGeneration {
    private BigInteger p;
    private BigInteger q;
    private BigInteger e;
    private BigInteger d;
    private BigInteger n;
    private BigInteger toitent;

    /**
     * summary: pick two prime numbers and compute the q and p
     */
    private void pickPandQ() {
       setQ(BigInteger.probablePrime(1024, new Random()));
       setP(BigInteger.probablePrime(1024, new Random()));
    }

    private  void findN() {
        setN(p.multiply(q));
    }

    private void findToitent() {
        setToitent(p
                .subtract(new BigInteger("1"))
                .multiply(q.subtract(new BigInteger("1"))));
    }

    /**
     * summary: find e such that gcd(e, toitent) = 1
     */
    private void findE() {
        BigInteger e = BigInteger.probablePrime(1024, new Random());
        while(bigIntegerRelativelyPrime(e) == false) {
            e = BigInteger.probablePrime(1024, new Random());
        }
        setE(e);
    }
    /**
     * summary: find d such that e * d = 1 (mod toitent)
     */
    private  void findD() {
        setD(e.modInverse(toitent));
    }
    private boolean bigIntegerRelativelyPrime(BigInteger e) {
        return e.gcd(toitent).equals(BigInteger.ONE);
    }

    public void getConstants() {
        pickPandQ();
        findN();
        findToitent();
    }
    public BigInteger getE() {
        findE();
        return e;
    }
    public BigInteger getD() {
        findD();
        return d;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }


    public void setE(BigInteger e) {
        this.e = e;
    }


    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getToitent() {
        return toitent;
    }

    public void setToitent(BigInteger toitent) {
        this.toitent = toitent;
    }
}
