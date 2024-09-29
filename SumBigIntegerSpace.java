import java.math.BigInteger;

public class SumBigIntegerSpace {
    public static void main(String[] args) {
        BigInteger result = BigInteger.ZERO;

        String s = String.join(" ", args);
        s = String.join("", s, " ");

        int sign = 1;
        BigInteger currentNum = BigInteger.ZERO;

        int skip = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < skip)
                continue;

            char c = s.charAt(i);

            if (!Character.isDigit(c)) {
                result = result.add(currentNum.multiply(BigInteger.valueOf(sign)));
                currentNum = BigInteger.ZERO;
                sign = 1;
            }

            if (Character.getType(c) == Character.SPACE_SEPARATOR) {
                continue;
            }
            if (c == '+') {
                sign = 1;
            }
            else if(c == '-') {
                sign = -1;
            }
            else {
                skip = i;
                while (Character.isDigit(s.charAt(skip)))
                    skip++;
                currentNum = new BigInteger(s.substring(i, skip));
            }
        }
        System.out.println(result);
    }
}