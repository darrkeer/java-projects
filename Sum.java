public class Sum {
    public static void main(String[] args) {
        int result = 0;
        for (String arg : args) {
            int sign = 1;
            int currentNum = 0;
            String s = arg + ' ';

            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);

                if (!Character.isDigit(c)) {
                    result += sign * currentNum;
                    currentNum = 0;
                    sign = 1;
                }

                if (String.valueOf(c).matches("\\p{javaWhitespace}") || c == 0xc)
                    continue;
                if (c == '+')
                    sign = 1;
                else if(c == '-')
                    sign = -1;
                else
                    currentNum = currentNum * 10 + Character.getNumericValue(c);
            }
        }
        System.out.println(result);
    }
}