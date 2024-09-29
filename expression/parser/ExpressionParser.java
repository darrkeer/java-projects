package expression.parser;

import expression.*;

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
                "&", 0,
                "^", -1,
                "|", -2
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
                        case "+" -> new Add(left, parseBrackets(priority.get(op), null, false, endc));
                        case "-" -> new Subtract(left, parseBrackets(priority.get(op), null, false, endc));
                        case "*" -> new Multiply(left, parseBrackets(priority.get(op), null, false, endc));
                        case "/" -> new Divide(left, parseBrackets(priority.get(op), null, false, endc));
                        case "&" -> new BitwiseAND(left, parseBrackets(priority.get(op), null, false, endc));
                        case "^" -> new BitwiseXOR(left, parseBrackets(priority.get(op), null, false, endc));
                        case "|" -> new BitwiseOR(left, parseBrackets(priority.get(op), null, false, endc));
                        default -> null;
                    };
                } else if (priority.get(op) <= prio) {
                    reverse(op.length());
                    return left;
                }
            }
        }

        private String parseOperation(){
            //multiple char op case
            return String.valueOf(take());
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
                    return new Negate(parseExpression());
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
            StringBuilder var = new StringBuilder();
            char ch = peek();
            while(Character.isAlphabetic(ch)){
                var.append(ch);
                take();
                ch = peek();
            }
            return new Variable(var.toString());
        }

        private ModifiedExpression parseInt(String start) {
            StringBuilder sb = new StringBuilder(start);
            while (Character.isDigit(peek()))
                sb.append(take());
            Const res = new Const(Integer.parseInt(sb.toString()));
            return res;
        }
    }
}
