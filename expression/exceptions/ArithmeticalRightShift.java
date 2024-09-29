package expression.exceptions;

import expression.ModifiedExpression;
import expression.Operation;

import java.math.BigDecimal;

public class ArithmeticalRightShift extends Operation {
    public ArithmeticalRightShift(ModifiedExpression a, ModifiedExpression b) {
        super(a, b);
    }

    @Override
    public int getPriority() {
        return -10;
    }

    @Override
    public boolean isSymmetrical() {
        return false;
    }

    @Override
    public boolean rightPriority() {
        return false;
    }

    @Override
    public String getOperator() {
        return ">>>";
    }

    @Override
    public int getOperation(int x, int y) {
        return x >>> y;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x, BigDecimal y) {
        return new BigDecimal(x.intValue() >>> y.intValue());
    }
}
