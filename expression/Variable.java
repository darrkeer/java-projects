package expression;

import java.math.BigDecimal;

public class Variable extends ModifiedExpression {
    private final String name;

    public Variable(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void toString(StringBuilder sb){
        sb.append(name);
    }

    @Override
    public void toMiniString(StringBuilder sb) {
        sb.append(name);
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case ("x") -> x;
            case ("y") -> y;
            case ("z") -> z;
            default -> throw new IllegalArgumentException("Variable '" + name + "' is not allowed");
        };
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return x;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x, BigDecimal y, BigDecimal z) {
        return switch (name) {
            case ("x") -> x;
            case ("y") -> y;
            case ("z") -> z;
            default -> throw new IllegalArgumentException("Variable '" + name + "' is not allowed");
        };
    }

    @Override
    public boolean equals(Object e) {
        if (e instanceof Variable x)
            return getName().equals(x.getName());
        return false;
    }

    @Override
    public int getPriority(){
        return 4;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public boolean isSymmetrical() {
        return true;
    }

    @Override
    public boolean rightPriority(){
        return false;
    }

    @Override
    public boolean isSingle(){
        return true;
    }
}
