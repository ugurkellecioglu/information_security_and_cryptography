package application;

public class StringToBinary {
    /**
     * 
     * @param s is a string representation of a number in base 10 (decimal)
     * @return a string representation of a number in base 2 (binary)
     */
    public static  String  convert(String s) {
        char [] charArray = s.toCharArray();
        StringBuilder str = new StringBuilder();
        for(char c : charArray) {
            String binaryString = Integer.toBinaryString(c);
            int round = 8 - binaryString.length();
            for(int i = 0; i < round; i++) str.append("0");
            str.append( binaryString + " ");
        }
        return str.toString();
    }

}
