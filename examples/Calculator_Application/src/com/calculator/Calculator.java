import java.util.*;
import java.util.function.DoubleBinaryOperator;

/**
 * Core calculator engine that evaluates mathematical expressions.
 * Demonstrates parsing, stack-based evaluation, and expression handling.
 */
public class Calculator {

    // Operator precedence (higher numbers = higher precedence)
    private static final Map<String, Integer> OPERATOR_PRECEDENCE = Map.of(
            "+", 1, "-", 1, "*", 2, "/", 2, "%", 2,
            "^", 3, "**", 3);

    // Mathematical operators
    private static final Map<String, DoubleBinaryOperator> OPERATORS = Map.of(
            "+", (a, b) -> a + b,
            "-", (a, b) -> a - b,
            "*", (a, b) -> a * b,
            "/", (a, b) -> {
                if (b == 0)
                    throw new CalculatorException.DivisionByZeroException("Division by zero");
                return a / b;
            },
            "%", (a, b) -> {
                if (b == 0)
                    throw new CalculatorException.DivisionByZeroException("Modulo by zero");
                return a % b;
            },
            "^", (a, b) -> MathFunctions.power(a, b),
            "**", (a, b) -> MathFunctions.power(a, b));

    private final HistoryManager historyManager;
    private final MemoryManager memoryManager;

    public Calculator() {
        this.historyManager = new HistoryManager();
        this.memoryManager = new MemoryManager();
    }

    public Calculator(HistoryManager historyManager, MemoryManager memoryManager) {
        this.historyManager = historyManager;
        this.memoryManager = memoryManager;
    }

    /**
     * Main evaluation method for mathematical expressions
     */
    public double evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new CalculatorException.InvalidExpressionException("Expression cannot be null or empty");
        }

        try {
            String sanitized = InputValidator.sanitizeInput(expression);
            List<String> tokens = tokenize(sanitized);
            List<String> rpn = convertToRPN(tokens);
            double result = evaluateRPN(rpn);

            // Store in history
            historyManager.addEntry(expression, result);

            return result;
        } catch (Exception e) {
            if (e instanceof CalculatorException) {
                throw e;
            }
            throw new CalculatorException.InvalidExpressionException(
                    "Failed to evaluate expression: " + e.getMessage());
        }
    }

    /**
     * Tokenize expression into numbers, operators, and functions
     */
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isWhitespace(c)) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                continue;
            }

            if (isOperator(c)) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else if (c == '(' || c == ')') {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            tokens.add(current.toString());
        }

        return tokens;
    }

    /**
     * Convert tokens to Reverse Polish Notation (RPN) using Shunting Yard algorithm
     */
    private List<String> convertToRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isConstant(token)) {
                output.add(resolveConstant(token));
            } else if (MathFunctions.isFunction(token)) {
                stack.push(token);
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                        hasHigherOrEqualPrecedence(stack.peek(), token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (stack.isEmpty()) {
                    throw new CalculatorException.InvalidExpressionException("Mismatched parentheses");
                }
                stack.pop(); // Remove the '('

                // If there's a function on top of stack, add it to output
                if (!stack.isEmpty() && MathFunctions.isFunction(stack.peek())) {
                    output.add(stack.pop());
                }
            } else {
                throw new CalculatorException.InvalidExpressionException("Unknown token: " + token);
            }
        }

        // Pop remaining operators from stack
        while (!stack.isEmpty()) {
            String op = stack.pop();
            if (op.equals("(")) {
                throw new CalculatorException.InvalidExpressionException("Mismatched parentheses");
            }
            output.add(op);
        }

        return output;
    }

    /**
     * Evaluate RPN expression
     */
    private double evaluateRPN(List<String> rpn) {
        Deque<Double> stack = new ArrayDeque<>();

        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new CalculatorException.InvalidExpressionException(
                            "Insufficient operands for operator: " + token);
                }
                double b = stack.pop();
                double a = stack.pop();
                DoubleBinaryOperator op = OPERATORS.get(token);
                if (op == null) {
                    throw new CalculatorException.InvalidOperationException("Unknown operator: " + token);
                }
                stack.push(op.applyAsDouble(a, b));
            } else if (MathFunctions.isFunction(token)) {
                if (stack.isEmpty()) {
                    throw new CalculatorException.InvalidExpressionException(
                            "Missing argument for function: " + token);
                }
                double arg = stack.pop();
                stack.push(executeFunction(token, arg));
            } else {
                throw new CalculatorException.InvalidExpressionException("Invalid token: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new CalculatorException.InvalidExpressionException("Invalid expression");
        }

        return stack.pop();
    }

    /**
     * Execute mathematical function
     */
    private double executeFunction(String functionName, double argument) {
        return MathFunctions.getFunction(functionName)
                .orElseThrow(() -> new CalculatorException.InvalidOperationException(
                        "Unknown function: " + functionName))
                .applyAsDouble(argument);
    }

    /**
     * Check if character is an operator
     */
    private boolean isOperator(char c) {
        return "+-*/%^".indexOf(c) != -1;
    }

    /**
     * Check if string is an operator
     */
    private boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    /**
     * Check if string is a number
     */
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if string is a mathematical constant
     */
    private boolean isConstant(String token) {
        return token.equalsIgnoreCase("pi") || token.equalsIgnoreCase("e");
    }

    /**
     * Resolve mathematical constants to their values
     */
    private String resolveConstant(String constant) {
        switch (constant.toLowerCase()) {
            case "pi":
                return String.valueOf(MathFunctions.PI);
            case "e":
                return String.valueOf(MathFunctions.E);
            default:
                throw new CalculatorException.InvalidExpressionException("Unknown constant: " + constant);
        }
    }

    /**
     * Check if operator1 has higher or equal precedence to operator2
     */
    private boolean hasHigherOrEqualPrecedence(String op1, String op2) {
        Integer prec1 = OPERATOR_PRECEDENCE.get(op1);
        Integer prec2 = OPERATOR_PRECEDENCE.get(op2);

        if (prec1 == null || prec2 == null) {
            throw new CalculatorException.InvalidExpressionException("Unknown operator precedence");
        }

        return prec1 >= prec2;
    }

    // Memory operations
    public void storeInMemory(double value) {
        memoryManager.store(value);
    }

    public double recallFromMemory() {
        return memoryManager.recall();
    }

    public void addToMemory(double value) {
        memoryManager.addToMemory(value);
    }

    public void clearMemory() {
        memoryManager.clear();
    }

    public String getMemorySummary() {
        return memoryManager.getMemorySummary();
    }

    public boolean memoryHasValue() {
        return memoryManager.hasValue();
    }

    // History operations
    public String getHistorySummary() {
        return historyManager.getSummary();
    }

    public String getFormattedHistory(int maxEntries) {
        return historyManager.getFormattedHistory(maxEntries);
    }

    public void clearHistory() {
        historyManager.clear();
    }

    public int getHistorySize() {
        return historyManager.getSize();
    }

    // Get managers for external access if needed
    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }

    /**
     * Parse and evaluate a complex mathematical expression
     * This method handles expressions like:
     * - "2 + 3 * 4"
     * - "sqrt(16) + sin(30)"
     * - "(5 + 3) * 2"
     * - "pi * 2^2"
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Test basic operations
        System.out.println("Testing Calculator Engine:");
        System.out.println("=========================");

        String[] testExpressions = {
                "2 + 3 * 4",
                "sqrt(16) + sin(30)",
                "(5 + 3) * 2",
                "pi * 2^2",
                "fact(5)",
                "log(100)",
                "10 / 0" // This should throw an exception
        };

        for (String expr : testExpressions) {
            try {
                double result = calculator.evaluate(expr);
                System.out.println(expr + " = " + result);
            } catch (Exception e) {
                System.out.println(expr + " = ERROR: " + e.getMessage());
            }
        }

        // Test memory operations
        System.out.println("\nTesting Memory Operations:");
        System.out.println("==========================");
        calculator.storeInMemory(42.5);
        System.out.println("Stored 42.5 in memory");
        System.out.println("Recalled: " + calculator.recallFromMemory());
        calculator.addToMemory(7.5);
        System.out.println("Added 7.5, new value: " + calculator.recallFromMemory());

        // Test history
        System.out.println("\nTesting History:");
        System.out.println("================");
        System.out.println(calculator.getHistorySummary());
    }
}
