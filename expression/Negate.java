package expression;

import java.math.BigDecimal;

public class Negate extends UnaryOperation {

    public Negate(ModifiedExpression a) {
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
    public BigDecimal getOperation(BigDecimal x) {
        return x.negate();
    }
}
