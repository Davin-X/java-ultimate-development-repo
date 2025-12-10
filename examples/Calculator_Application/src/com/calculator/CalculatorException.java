// Custom exception hierarchy for calculator errors
public class CalculatorException extends RuntimeException {

    public CalculatorException(String message) {
        super(message);
    }

    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    // Specific calculator exceptions for better error handling
    public static class InvalidExpressionException extends CalculatorException {
        public InvalidExpressionException(String message) {
            super(message);
        }
    }

    public static class DivisionByZeroException extends CalculatorException {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }

    public static class InvalidOperationException extends CalculatorException {
        public InvalidOperationException(String message) {
            super(message);
        }
    }

    public static class MemoryOperationException extends CalculatorException {
        public MemoryOperationException(String message) {
            super(message);
        }
    }
}
