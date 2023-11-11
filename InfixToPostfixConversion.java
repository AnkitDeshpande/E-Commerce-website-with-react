import java.util.Stack;

public class InfixToPostfixConversion {
    public static String infixToPostfix(String expression) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        String operators = "+-*/^";
        String openingBrackets = "([{";
        String closingBrackets = ")]}";

        for (char character : expression.toCharArray()) {
            if (Character.isLetterOrDigit(character)) {
                postfix.append(character);
            } else if (openingBrackets.contains(String.valueOf(character))) {
                stack.push(character);
            } else if (closingBrackets.contains(String.valueOf(character))) {
                char correspondingOpeningBracket = getCorrespondingOpeningBracket(character);
                while (!stack.isEmpty() && stack.peek() != correspondingOpeningBracket) {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == correspondingOpeningBracket) {
                    stack.pop(); // Pop the corresponding opening bracket
                } else {
                    return "Unbalanced brackets in infix expression";
                }
            } else if (operators.contains(String.valueOf(character))) {
                while (!stack.isEmpty() && stack.peek() != '(' && hasHigherPrecedence(stack.peek(), character)) {
                    postfix.append(stack.pop());
                }
                stack.push(character);
            }
        }

        while (!stack.isEmpty()) {
            if (openingBrackets.contains(String.valueOf(stack.peek())) || closingBrackets.contains(String.valueOf(stack.peek()))) {
                return "Unbalanced brackets in infix expression";
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);
        return precedence1 >= precedence2;
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    private static char getCorrespondingOpeningBracket(char closingBracket) {
        switch (closingBracket) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return ' ';
        }
    }

   public static void main(String[] args) {
        testInfixToPostfix("a+b-c+d*[e-f]/g+(h*(i/j))", "ab+c-def-*g/+hij/*+");
        testInfixToPostfix("a+b-c+d*[e-f]/g+(h*(i/j}", "Unbalanced brackets in infix expression");
        testInfixToPostfix("a+b-c+d*[e-f]/g+(h*(i/j))}", "Unbalanced brackets in infix expression");
        testInfixToPostfix("((a+b)*c-d)/e", "ab+c*d-e/");
        testInfixToPostfix("((a+b)*c-d)/e)", "Unbalanced brackets in infix expression");
    }

    private static void testInfixToPostfix(String infixExpression, String expectedPostfix) {
        String postfixExpression = InfixToPostfixConversion.infixToPostfix(infixExpression);
        if (postfixExpression.equals(expectedPostfix)) {
            System.out.println("Pass - Infix: " + infixExpression + ", Postfix: " + postfixExpression);
        } else {
            System.out.println("Fail - Infix: " + infixExpression + ", Expected: " + expectedPostfix + ", Actual: " + postfixExpression);
        }
    }
}
