package expression;

import java.math.BigDecimal;

public class Const extends ModifiedExpression {
    private final int intValue;
    private final BigDecimal decimalValue;

    private final boolean isDecimal;

    public Const(int val){
        this.intValue = val;
        this.decimalValue = null;
        this.isDecimal = false;
    }

    public Const(BigDecimal val){
        this.intValue = val.intValue();
        this.decimalValue = val;
        this.isDecimal = true;
    }

    public boolean getIsDecimal(){
        return isDecimal;
    }

    public int getIntValue(){
        return intValue;
    }

    public BigDecimal getDecimalValue(){
        return decimalValue;
    }

    @Override
    public void toString(StringBuilder sb){
        if(isDecimal){
            sb.append(decimalValue.toString());
        } else{
            sb.append(intValue);
        }
    }

    @Override
    public void toMiniString(StringBuilder sb) {
        toString(sb);
    }

    @Override
    public int evaluate(int x) {
        if(isDecimal)
            throw new IllegalArgumentException("You cant use int and decimal types at the same time");
        return intValue;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if(isDecimal)
            throw new IllegalArgumentException("You cant use int and decimal types at the same time");
        return intValue;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        if(!isDecimal)
            throw new IllegalArgumentException("You cant use int and decimal types at the same time");
        return decimalValue;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x, BigDecimal y, BigDecimal z) {
        if(!isDecimal)
            throw new IllegalArgumentException("You cant use int and decimal types at the same time");
        return decimalValue;
    }

    @Override
    public boolean equals(Object e) {
        if(e instanceof Const c){
            if(getIsDecimal() != c.getIsDecimal())
                return false;
            if(isDecimal)
                return getDecimalValue().equals(c.getDecimalValue());
            return getIntValue() == c.getIntValue();
        }
        return false;
    }

    @Override
    public int getPriority(){
        return 4;
    }

    @Override
    public int hashCode(){
        return (int) 999999937L * intValue % 998244353;
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
