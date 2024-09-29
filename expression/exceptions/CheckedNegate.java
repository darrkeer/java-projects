package expression.exceptions;

import expression.ModifiedExpression;

import java.math.BigDecimal;

public class CheckedNegate extends CheckedUnaryOperation {

    public CheckedNegate(ModifiedExpression a) {
        super(a);
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public int getOperation(int x) {
        return -x;
    }

    @Override
    public long getOperation(long x) {
        return -x;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x) {
        return x.negate();
    }
}
