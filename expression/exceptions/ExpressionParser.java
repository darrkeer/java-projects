package expression.exceptions;

import expression.*;
import expression.parser.TripleParser;

import java.util.Map;

public class ExpressionParser implements TripleParser {

    @Override
    public ModifiedExpression parse(String expression) {
        return new Parser(new StringSource(expression)).parse();
    }

    private static class Parser extends BaseParser {
        private static final Map<String, Integer> priority = Map.of(
                "+", 1,
                "-", 1,
                "*", 2,
                "/", 2,
                ">>", -10,
                "<<", -10,
                ">>>", -10
        );
        private static final int MIN_PRIO = -1000;

        public Parser(CharSource source) {
            super(source);
        }

        public void skipWhitespace() {
            while(Character.isWhitespace(peek())) {
                take();
            }
        }

        public ModifiedExpression parse() {
            ModifiedExpression res = parseBrackets(MIN_PRIO, null, true, END);
            if(peek() != END)
                throw error();
            return res;
        }

        private ModifiedExpression parseBrackets(int prio, ModifiedExpression left, boolean first, char endc) {
            skipWhitespace();
            if (left == null)
                left = parseExpression();

            while (true) {
                skipWhitespace();
                if (peek() == endc) {
                    if (first)
                        take();
                    return left;
                }

                String op = parseOperation();

                if (priority.get(op) > prio) {
                    left = switch (op) {
                        case "+" -> new CheckedAdd(left, parseBrackets(priority.get(op), null, false, endc));
                        case "-" -> new CheckedSubtract(left, parseBrackets(priority.get(op), null, false, endc));
                        case "*" -> new CheckedMultiply(left, parseBrackets(priority.get(op), null, false, endc));
                        case "/" -> new CheckedDivide(left, parseBrackets(priority.get(op), null, false, endc));
                        case ">>" -> new RightShift(left, parseBrackets(priority.get(op), null, false, endc));
                        case "<<" -> new LeftShift(left, parseBrackets(priority.get(op), null, false, endc));
                        case ">>>" -> new ArithmeticalRightShift(left, parseBrackets(priority.get(op), null, false, endc));
                        default -> null;
                    };
                } else if (priority.get(op) <= prio) {
                    reverse(op.length());
                    return left;
                }
            }
        }

        private String parseOperation(){
//            return String.valueOf(peek());
            if (take('*'))
                return "*";
            if (take('+'))
                return "+";
            if (take('-'))
                return "-";
            if (take('/'))
                return "/";
            if(take('>')){
                except('>');
                if(take('>'))
                    return ">>>";
                return ">>";
            }
            if(take('<')){
                except('<');
                return "<<";
            }
            throw error();
        }

        private ModifiedExpression parseExpression() {
            skipWhitespace();
            if (take('(')) {
                return parseBrackets(MIN_PRIO, null, true, ')');
            }
            if (take('-')) {
                if (Character.isDigit(peek())) {
                    return parseInt("-");
                } else {
                    return new CheckedNegate(parseExpression());
                }
            }
            if (take('l')) {
                if (take('1')) {
                    return new L1(parseExpression());
                }
                if (take('o')) {
                    except('w');
                    return new Low(parseExpression());
                }
            }
            if (take('t')) {
                except('1');
                return new T1(parseExpression());
            }
            if (take('h')) {
                except("igh");
                return new High(parseExpression());
            }
            if (Character.isDigit(peek())) {
                return parseInt("");
            }

            //variables case left 'x' 'y' 'z'
//            StringBuilder var = new StringBuilder();
//            char ch = peek();
//            if(!Character.isAlphabetic(ch))
//                throw error();
//            while(Character.isAlphabetic(ch)){
//                var.append(ch);
//                take();
//                ch = peek();
//            }
//            return new Variable(var.toString());
            if(take('x'))
                return new Variable("x");
            if(take('y'))
                return new Variable("y");
            if(take('z'))
                return new Variable("z");
            throw error();
        }

        private ModifiedExpression parseInt(String start) {
            StringBuilder sb = new StringBuilder(start);
            while (Character.isDigit(peek()))
                sb.append(take());
            Const res;
            try {
                res = new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException exception){
                throw new IllegalArgumentException("Parse integer overflow on " + (getAmount() + 1) + "th symbol");
            }
            return res;
        }

        private IllegalArgumentException error(){
            return new IllegalArgumentException(
                    "Invalid source text on " + (getAmount() + 1)
                            + "th symbol: '" + (peek() == END ? "EOF" : peek()) + "'"
            );
        }
    }
}
