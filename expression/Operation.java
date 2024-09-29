package expression;

import java.math.BigDecimal;

public abstract class Operation extends ModifiedExpression {
    protected final ModifiedExpression a;
    protected final ModifiedExpression b;

    public Operation(ModifiedExpression a, ModifiedExpression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append('(');
        a.toString(sb);
        sb.append(' ').append(getOperator()).append(' ');
        b.toString(sb);
        sb.append(')');
    }

    @Override
    public void toMiniString(StringBuilder sb) {
        if (a.getPriority() >= getPriority()) {
            a.toMiniString(sb);
        } else {
            sb.append('(');
            a.toMiniString(sb);
            sb.append(')');
        }

        sb.append(' ').append(getOperator()).append(' ');

        if (b.getPriority() > getPriority() ||
                (b.getPriority() == getPriority() && isSymmetrical() && !b.rightPriority())) {
            b.toMiniString(sb);
        } else {
            sb.append('(');
            b.toMiniString(sb);
            sb.append(')');
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Operation x)
            return x.a.equals(a) && x.b.equals(b)
                    && x.getOperator().equals(getOperator());
        return false;
    }

    @Override
    public int evaluate(int x) {
        return getOperation(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getOperation(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return getOperation(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x, BigDecimal y, BigDecimal z) {
        return getOperation(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    public abstract String getOperator();

    public abstract int getOperation(int x, int y);

    public abstract BigDecimal getOperation(BigDecimal x, BigDecimal y);

    @Override
    public boolean isSingle() {
        return false;
    }
}
