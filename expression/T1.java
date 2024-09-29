package expression;

import java.math.BigDecimal;

public class T1 extends UnaryOperation {
    public T1(ModifiedExpression a) {
        super(a);
    }

    @Override
    public String getOperation() {
        return "t1";
    }

    @Override
    public int getOperation(int x) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if (((x >> i) & 1) == 1)
                cnt++;
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
