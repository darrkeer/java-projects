package expression;

import java.math.BigDecimal;

public class L1 extends UnaryOperation{
    public L1(ModifiedExpression a) {
        super(a);
    }

    @Override
    public String getOperation() {
        return "l1";
    }

    @Override
    public int getOperation(int x) {
        int cnt = 0;
        for(int i=31; i>=0; i--){
            if(((x >> i) & 1) == 1)
                ++cnt;
            else
                break;
        }
        return cnt;
    }

    @Override
    public BigDecimal getOperation(BigDecimal x) {
        return new BigDecimal(getOperation(x.intValue()));
    }
}
