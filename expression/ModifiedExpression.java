package expression;

import java.math.BigDecimal;

public abstract class ModifiedExpression implements TripleExpression, expression.Expression, BigDecimalExpression {
    public String toString(){
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    public abstract void toString(StringBuilder sb);

    public String toMiniString(){
        StringBuilder sb = new StringBuilder();
        toMiniString(sb);
        return sb.toString();
    }

    public abstract void toMiniString(StringBuilder sb);

//    public abstract int evaluate(int x);

//    public abstract int evaluate(int x, int y, int z);

//    public abstract BigDecimal evaluate(BigDecimal x);

//    public abstract Long evaluate(Long x);
//
//    public abstract Long evaluate(Long x, Long y, Long z);

    public abstract BigDecimal evaluate(BigDecimal x, BigDecimal y, BigDecimal z);

    public abstract int getPriority();

    public abstract boolean isSymmetrical();

    public abstract boolean rightPriority();

    public abstract boolean isSingle();
}
