package expression;

import expression.exceptions.*;

public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x;
//        try {
//            x = sc.nextInt();
//        } catch (NumberFormatException e){
//            System.out.println("Incorrect input.");
//            return;
//        }
//        ModifiedExpression ex = new Add(
//                new Subtract(
//                        new Multiply(new Variable("x"), new Variable("x")),
//                        new Multiply(new Const(2), new Variable("x"))
//                ),
//                new Const(1)
//        );
//        System.out.println(ex.evaluate(x));

//        String s = "(-1660441152 - - -1733506207)";
//
        String s = "((0 + -100<ERROR_INSERTED -->-<-- ERROR_INSERTED>))";
        ModifiedExpression ex = (new ExpressionParser()).parse(s);
        System.out.println(ex.toMiniString());
//        System.out.println(ex.evaluate(6));


//        ModifiedExpression ex = new CheckedAdd(new Const(-2147483648), new Const(-2147483648));
//        int x = -5000;
//        try{
//            x = ex.evaluate(1);
//        } catch (Exception e){
//
//        }
//        System.out.println(x);

//        ModifiedExpressiown ex = new BitwiseOR(new Const(1), new BitwiseAND(new Variable("x"), new Const(2)));
//        System.out.println(ex);
    }
}
