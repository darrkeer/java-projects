package expression;

import java.math.BigDecimal;

public abstract class UnaryOperation extends ModifiedExpression {

    private final ModifiedExpression a;

    public UnaryOperation(ModifiedExpression a) {
        this.a = a;
    }

    public abstract String getOperation();

    @Override
    public void toMiniString(StringBuilder sb) {
        if (a.isSingle()) {
            sb.append(getOperation()).append(' ');
            a.toMiniString(sb);
        } else {
            sb.append(getOperation()).append('(');
            a.toMiniString(sb);
            sb.append(')');
        }
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append(getOperation()).append('(');
        a.toString(sb);
        sb.append(')');
    }

    @Override
    public int evaluate(int x) {
        return getOperation(a.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getOperation(a.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return getOperation(a.evaluate(x));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x, BigDecimal y, BigDecimal z) {
        return getOperation(a.evaluate(x, y, z));
    }

    public abstract int getOperation(int x);

    public abstract BigDecimal getOperation(BigDecimal x);

    @Override
    public boolean isSymmetrical() {
        return true;
    }

    @Override
    public boolean rightPriority() {
        return true;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public int getPriority() {
        return 5;
    }
}
