package expression.exceptions;

import expression.ModifiedExpression;

import java.math.BigDecimal;

public class CheckedAdd extends CheckedOperation {
    public CheckedAdd(ModifiedExpression a, ModifiedExpression b) {
        super(a, b);
    }

    @Override
    public int getOperation(int x, int y) {
        return x + y;
    }

    @Override
    public long getOperation(long x, long y) {
        return x + y;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x, BigDecimal y) {
        return x.add(y);
    }

    public static CheckedAdd makeInstance(ModifiedExpression a, ModifiedExpression b) {
        return new CheckedAdd(a, b);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getOperator() {
        return "+";
    }

    @Override
    public int hashCode() {
        if (a == null)
            return (int) 1000000007L * b.hashCode() % 998244353;
        return (int) 1000000007L * (31 * a.hashCode() + b.hashCode()) % 998244353;
    }

    @Override
    public boolean isSymmetrical() {
        return true;
    }

    @Override
    public boolean rightPriority() {
        return false;
    }
}
