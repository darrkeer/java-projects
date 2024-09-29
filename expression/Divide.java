package expression;

import java.math.BigDecimal;

public class Divide extends Operation {
    public Divide(ModifiedExpression a, ModifiedExpression b) {
        super(a, b);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getOperator() {
        return "/";
    }

    @Override
    public int getOperation(int x, int y) {
        if(y == 0)
            throw new ArithmeticException("Zero division");
        return x / y;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x, BigDecimal y) {
        return x.divide(y);
    }

    @Override
    public int hashCode() {
        if (a == null)
            return (int) 1000000033L * b.hashCode() % 998244353;
        return (int) 1000000033L * (43 * a.hashCode() + b.hashCode()) % 998244353;
    }

    @Override
    public boolean isSymmetrical() {
        return false;
    }

    @Override
    public boolean rightPriority() {
        return true;
    }
}
