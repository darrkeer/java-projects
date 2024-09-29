package expression.exceptions;

import expression.ModifiedExpression;

import java.math.BigDecimal;

public abstract class CheckedUnaryOperation extends ModifiedExpression {

    private final ModifiedExpression a;

    public CheckedUnaryOperation(ModifiedExpression a) {
        this.a = a;
    }

    public abstract String getOperation();

    @Override
    public void toMiniString(StringBuilder sb) {
        if (a.isSingle()) {
            sb.append(getOperation()).append(' ');
            a.toMiniString(sb);
        } else {
            sb.append(getOperation()).append('(');
            a.toMiniString(sb);
            sb.append(')');
        }
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append(getOperation()).append('(');
        a.toString(sb);
        sb.append(')');
    }

    @Override
    public int evaluate(int x) {
        int eval = a.evaluate(x);
        int intRes = getOperation(eval);
        long longRes = getOperation((long) eval);
        if(intRes != longRes){
            throw new ArithmeticException("Integer overflow");
        }
        return intRes;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int eval = a.evaluate(x, y, z);
        int intRes = getOperation(eval);
        long longRes = getOperation((long) eval);
        if(intRes != longRes){
            throw new ArithmeticException("Integer overflow");
        }
        return intRes;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return getOperation(a.evaluate(x));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x, BigDecimal y, BigDecimal z) {
        return getOperation(a.evaluate(x, y, z));
    }

    public abstract int getOperation(int x);

    public abstract long getOperation(long x);

    public abstract BigDecimal getOperation(BigDecimal x);

    @Override
    public boolean isSymmetrical() {
        return true;
    }

    @Override
    public boolean rightPriority() {
        return true;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public int getPriority() {
        return 5;
    }
}
