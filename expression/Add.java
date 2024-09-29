package expression;

import java.math.BigDecimal;

public class Add extends Operation {
    public Add(ModifiedExpression a, ModifiedExpression b) {
        super(a, b);
    }

    @Override
    public int getOperation(int x, int y) {
        return x + y;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x, BigDecimal y) {
        return x.add(y);
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
