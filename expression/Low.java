package expression;

import java.math.BigDecimal;

public class Low extends UnaryOperation{
    public Low(ModifiedExpression a) {
        super(a);
    }

    @Override
    public String getOperation() {
        return "low";
    }

    @Override
    public int getOperation(int x) {
        for(int i=0; i<32; i++){
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
