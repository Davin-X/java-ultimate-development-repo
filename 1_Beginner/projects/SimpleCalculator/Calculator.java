// Level 1 Beginner Project - Simple Calculator
// Demonstrates: Variables & Data Types, Control Structures, Methods & Functions, Exception Handling

public class Calculator {

    // Instance variables to store calculation history
    private int calculationsPerformed;
    private double lastResult;

    // Constructor to initialize calculator
    public Calculator() {
        this.calculationsPerformed = 0;
        this.lastResult = 0.0;
    }

    // Method to add two numbers (method overloading)
    public double add(double a, double b) {
        double result = a + b;
        updateHistory(result);
        return result;
    }

    // Method to add three numbers
    public double add(double a, double b, double c) {
        double result = a + b + c;
        updateHistory(result);
        return result;
    }

    // Method to add an array of numbers
    public double add(double[] numbers) throws CalculatorException {
        if (numbers == null || numbers.length == 0) {
            throw new CalculatorException("Cannot add empty array");
        }

        double result = 0.0;
        for (double num : numbers) {
            result += num;
        }
        updateHistory(result);
        return result;
    }

    // Method to subtract two numbers
    public double subtract(double a, double b) {
        double result = a - b;
        updateHistory(result);
        return result;
    }

    // Method to multiply two numbers
    public double multiply(double a, double b) {
        double result = a * b;
        updateHistory(result);
        return result;
    }

    // Method to divide two numbers (with exception handling)
    public double divide(double a, double b) throws CalculatorException {
        if (b == 0) {
            throw new CalculatorException("Cannot divide by zero");
        }
        double result = a / b;
        updateHistory(result);
        return result;
    }

    // Method to calculate modulus
    public double modulus(double a, double b) throws CalculatorException {
        if (b == 0) {
            throw new CalculatorException("Cannot calculate modulus with zero divisor");
        }
        double result = a % b;
        updateHistory(result);
        return result;
    }

    // Method to calculate power (a^b)
    public double power(double base, int exponent) {
        double result = Math.pow(base, exponent);
        updateHistory(result);
        return result;
    }

    // Method to calculate square root
    public double squareRoot(double a) throws CalculatorException {
        if (a < 0) {
            throw new CalculatorException("Cannot calculate square root of negative number");
        }
        double result = Math.sqrt(a);
        updateHistory(result);
        return result;
    }

    // Method to perform calculation based on operation string
    public double calculate(double a, double b, String operation) throws CalculatorException {
        operation = operation.toLowerCase().trim();

        switch (operation) {
            case "+":
            case "add":
            case "plus":
                return add(a, b);

            case "-":
            case "subtract":
            case "minus":
                return subtract(a, b);

            case "*":
            case "multiply":
            case "times":
            case "x":
                return multiply(a, b);

            case "/":
            case "divide":
            case "div":
                return divide(a, b);

            case "%":
            case "modulus":
            case "mod":
                return modulus(a, b);

            case "^":
            case "power":
            case "pow":
                return power(a, (int) b); // Convert b to int for exponent

            default:
                throw new CalculatorException("Unsupported operation: " + operation);
        }
    }

    // Helper method to update calculation history
    private void updateHistory(double result) {
        this.calculationsPerformed++;
        this.lastResult = result;
    }

    // Getter methods
    public int getCalculationsPerformed() {
        return calculationsPerformed;
    }

    public double getLastResult() {
        return lastResult;
    }

    // Method to display calculator statistics
    public void displayStatistics() {
        System.out.println("\\n=== CALCULATOR STATISTICS ===");
        System.out.println("Calculations performed: " + calculationsPerformed);
        System.out.println("Last result: " + lastResult);
    }

    // Method to reset calculator
    public void reset() {
        this.calculationsPerformed = 0;
        this.lastResult = 0.0;
        System.out.println("Calculator reset!");
    }

    // Method to display usage help
    public void displayHelp() {
        System.out.println("\\n=== CALCULATOR OPERATIONS ===");
        System.out.println("Supports the following operations:");
        System.out.println("• + or add: Addition");
        System.out.println("• - or subtract: Subtraction");
        System.out.println("• * or multiply: Multiplication");
        System.out.println("• / or divide: Division");
        System.out.println("• % or modulus: Remainder of division");
        System.out.println("• ^ or power: Exponentiation (a^b)");
        System.out.println("\\nAlso supports single-number operations:");
        System.out.println("• sqrt: Square root");
        System.out.println("\\nEnter 'help' for this menu");
        System.out.println("Enter 'stats' for usage statistics");
        System.out.println("Enter 'reset' to clear history");
        System.out.println("Enter 'quit' to exit");
    }
}
