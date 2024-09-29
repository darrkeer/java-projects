package expression.exceptions;

import expression.ModifiedExpression;

import java.math.BigDecimal;

public class CheckedMultiply extends CheckedOperation {
    public CheckedMultiply(ModifiedExpression a, ModifiedExpression b) {
        super(a, b);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getOperator() {
        return "*";
    }

    @Override
    public int getOperation(int x, int y) {
        return x * y;
    }

    @Override
    public long getOperation(long x, long y) {
        return x * y;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x, BigDecimal y) {
        return x.multiply(y);
    }

    @Override
    public int hashCode() {
        if(a == null)
            return (int) 1000000021L * b.hashCode() % 998244353;
        return (int) 1000000021L * (41 * a.hashCode() + b.hashCode()) % 998244353;
    }

    @Override
    public boolean isSymmetrical(){
        return true;
    }

    @Override
    public boolean rightPriority(){
        return false;
    }
}
