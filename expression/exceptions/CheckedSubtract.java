package expression.exceptions;

import expression.ModifiedExpression;

import java.math.BigDecimal;

public class CheckedSubtract extends CheckedOperation {
    public CheckedSubtract(ModifiedExpression a, ModifiedExpression b) {
        super(a, b);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getOperator() {
        return "-";
    }

    @Override
    public int getOperation(int x, int y) {
        return x - y;
    }

    @Override
    public long getOperation(long x, long y) {
        return x - y;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x, BigDecimal y) {
        return x.subtract(y);
    }

    @Override
    public int hashCode() {
        if(a == null)
            return (int) 1000000009L * b.hashCode() % 998244353;
        return (int) 1000000009L * (37 * a.hashCode() + b.hashCode()) % 998244353;
    }

    @Override
    public boolean isSymmetrical(){
        return false;
    }

    @Override
    public boolean rightPriority(){
        return false;
    }
}
