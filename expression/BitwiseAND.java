package expression;

import java.math.BigDecimal;

public class BitwiseAND extends Operation{
    public BitwiseAND(ModifiedExpression a, ModifiedExpression b) {
        super(a, b);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isSymmetrical() {
        return true;
    }

    @Override
    public boolean rightPriority() {
        return false;
    }

    @Override
    public String getOperator() {
        return "&";
    }

    @Override
    public int getOperation(int x, int y) {
        return x & y;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x, BigDecimal y) {
        return new BigDecimal(x.intValue() & y.intValue());
    }
}
