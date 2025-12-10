// Level 1 Beginner Project - Exception Handling Demonstration
// Custom exception class for the Calculator

public class CalculatorException extends Exception {

    // Default constructor
    public CalculatorException() {
        super("Calculator calculation error occurred");
    }

    // Constructor with message
    public CalculatorException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    // Method to get user-friendly error description
    public String getUserFriendlyMessage() {
        String originalMessage = getMessage();

        if (originalMessage.contains("divide by zero")) {
            return "Error: Division by zero is not allowed. Please use a non-zero divisor.";
        } else if (originalMessage.contains("negative number")) {
            return "Error: Cannot perform operation on negative numbers.";
        } else if (originalMessage.contains("empty array")) {
            return "Error: Cannot perform operation on empty or null data.";
        } else if (originalMessage.contains("Unsupported operation")) {
            return "Error: Unknown operation selected. Type 'help' for available operations.";
        } else {
            return "Calculator Error: " + originalMessage;
        }
    }
}
