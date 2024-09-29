package expression;

import java.math.BigDecimal;

public class High extends UnaryOperation{
    public High(ModifiedExpression a) {
        super(a);
    }

    @Override
    public String getOperation() {
        return "high";
    }

    @Override
    public int getOperation(int x) {
        for(int i=31; i>=0; i--){
            if((x & (1 << i)) != 0)
                return (1 << i);
        }
        return 0;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x) {
        return new BigDecimal(getOperation(x.intValue()));
    }
}
