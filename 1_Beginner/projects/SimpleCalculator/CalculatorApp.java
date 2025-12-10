// Level 1 Beginner Project - Complete Main Application
// Demonstrates: I/O Operations, Control Structures, Exception Handling, Method Organization

import java.util.Scanner; // For user input (I/O)
import java.util.InputMismatchException; // For exception handling

public class CalculatorApp {

    private Calculator calculator;
    private Scanner scanner;

    // Constructor - initialize I/O and calculator
    public CalculatorApp() {
        this.calculator = new Calculator();
        this.scanner = new Scanner(System.in);
    }

    // Main application loop - control structure cornerstone
    public void run() {
        System.out.println("🧮 SIMPLE CALCULATOR - Level 1 Java Project");
        System.out.println("===========================================");
        System.out.println("Type 'help' for available operations");

        boolean running = true; // Control variable for while loop

        // Main application loop - runs until user chooses to quit
        while (running) {
            // Display prompt and get user command
            System.out.print("\\nCalculator > ");
            String command = scanner.nextLine().toLowerCase().trim();

            // Process command using switch statement (control structure)
            switch (command) {
                case "quit":
                case "exit":
                case "q":
                    running = false; // Exit the loop
                    break;

                case "help":
                case "h":
                    calculator.displayHelp();
                    break;

                case "stats":
                case "statistics":
                    calculator.displayStatistics();
                    break;

                case "reset":
                case "clear":
                    calculator.reset();
                    break;

                case "calc":
                case "calculate":
                case "compute":
                    performCalculation();
                    break;

                case "sqrt":
                    performSquareRoot();
                    break;

                case "add":
                case "sum":
                    performArrayAddition();
                    break;

                default:
                    // Try to parse as a calculation (e.g., "5 + 3")
                    if (tryParseCalculation(command)) {
                        break;
                    }

                    // If not a calculation, show error (with control structure)
                    System.out.println("Unknown command: " + command);
                    System.out.println("Type 'help' for available commands or operations.");
                    break;
            }
        }

        // Cleanup resources (I/O best practices)
        scanner.close();
        System.out.println("\\nThank you for using Simple Calculator!");
        System.out.println("Final statistics:");
        calculator.displayStatistics();
    }

    // Method to perform two-number calculations
    private void performCalculation() {
        try {
            // Get first number with exception handling
            double num1 = getValidNumber("Enter first number: ");

            // Get operation
            System.out.print("Enter operation (+, -, *, /, %, ^): ");
            String operation = scanner.nextLine().trim();

            // Check if it's a two-number operation
            String[] twoNumberOps = { "+", "-", "*", "/", "%", "^", "add", "subtract",
                    "multiply", "divide", "modulus", "power" };

            boolean isTwoNumberOp = false;
            for (String op : twoNumberOps) {
                if (operation.equalsIgnoreCase(op)) {
                    isTwoNumberOp = true;
                    break;
                }
            }

            if (isTwoNumberOp) {
                // Get second number
                double num2 = getValidNumber("Enter second number: ");

                // Perform calculation
                double result = calculator.calculate(num1, num2, operation);
                displayResult(num1, operation, num2, result);
            } else {
                System.out.println("Unknown operation: " + operation);
            }

        } catch (CalculatorException e) {
            // Custom exception handling - display user-friendly error
            System.out.println(e.getUserFriendlyMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Method to perform square root (single number operation)
    private void performSquareRoot() {
        try {
            double number = getValidNumber("Enter number for square root: ");

            double result = calculator.squareRoot(number);
            displaySqrtResult(number, result);

        } catch (CalculatorException e) {
            System.out.println(e.getUserFriendlyMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to add array of numbers
    private void performArrayAddition() {
        try {
            System.out.println("How many numbers would you like to add?");
            int count = getValidInt();

            if (count <= 0 || count > 20) {
                System.out.println("Please enter a number between 1 and 20.");
                return;
            }

            double[] numbers = new double[count];

            // Get each number with validation
            for (int i = 0; i < count; i++) {
                numbers[i] = getValidNumber("Enter number " + (i + 1) + ": ");
            }

            // Calculate sum using overloaded method
            double result = calculator.add(numbers);

            // Display result
            System.out.print("Sum of ");
            for (int i = 0; i < numbers.length; i++) {
                System.out.print(numbers[i]);
                if (i < numbers.length - 1) {
                    System.out.print(" + ");
                }
            }
            System.out.println(" = " + result);

        } catch (CalculatorException e) {
            System.out.println(e.getUserFriendlyMessage());
        } catch (Exception e) {
            System.out.println("Error during array addition: " + e.getMessage());
        }
    }

    // Method to try parsing direct calculations like "5 + 3"
    private boolean tryParseCalculation(String input) {
        // Look for operators in the input
        String[] operators = { "+", "-", "*", "/", "%", "^" };

        for (String op : operators) {
            if (input.contains(op)) {
                try {
                    // Split input around operator
                    String[] parts = input.split("\\" + op); // Escape special regex chars

                    if (parts.length == 2) {
                        double num1 = Double.parseDouble(parts[0].trim());
                        double num2 = Double.parseDouble(parts[1].trim());

                        double result = calculator.calculate(num1, num2, op);
                        displayResult(num1, op, num2, result);
                        return true;
                    }
                } catch (NumberFormatException | CalculatorException e) {
                    // Not a valid direct calculation, continue
                }
            }
        }
        return false; // Not a direct calculation
    }

    // Helper method to get valid number with exception handling
    private double getValidNumber(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double number = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                return number;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Helper method to get valid integer
    private int getValidInt() {
        while (true) {
            try {
                System.out.print("Enter count: ");
                int number = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return number;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a whole number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Result display methods (method organization)
    private void displayResult(double a, String operation, double b, double result) {
        char op = operation.charAt(0); // Get first character for display
        System.out.printf("%.2f %c %.2f = %.6f\\n", a, op, b, result);
        System.out.println("Calculations performed: " + calculator.getCalculationsPerformed());
    }

    private void displaySqrtResult(double a, double result) {
        System.out.printf("√%.2f = %.6f\\n", a, result);
        System.out.println("Calculations performed: " + calculator.getCalculationsPerformed());
    }

    // Main method to start the application
    public static void main(String[] args) {
        try {
            CalculatorApp app = new CalculatorApp();
            app.run();
        } catch (Exception e) {
            System.out.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
