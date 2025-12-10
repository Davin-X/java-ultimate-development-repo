import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;

/**
 * Validates and sanitizes calculator input expressions.
 * Demonstrates regular expressions, input validation, and parsing concepts.
 */
public class InputValidator {

    // Patterns for different input types
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^-?\\d*\\.?\\d+$");
    private static final Pattern FUNCTION_PATTERN = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*\\s*\\(");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("^[+\\-*/%^]$");
    private static final Pattern CONSTANT_PATTERN = Pattern.compile("^(pi|e)$", Pattern.CASE_INSENSITIVE);

    // Valid characters in expressions
    private static final Set<Character> VALID_CHARS = Set.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '+', '-', '*', '/', '%', '^', '(', ')', '.', ' ',
            'p', 'i', 'e', 's', 'n', 'c', 't', 'q', 'r', 'l', 'x', 'o', 'a', 'g', 'h' // Function names
    );

    // Mathematical functions (for validation)
    private static final Set<String> MATHEMATICAL_FUNCTIONS = Set.of(
            "sin", "cos", "tan", "asin", "acos", "atan", "sinh", "cosh", "tanh",
            "log", "ln", "sqrt", "exp", "abs", "ceil", "floor", "round",
            "fact", "cbrt", "deg", "rad");

    // Command keywords
    private static final Set<String> COMMAND_KEYWORDS = Set.of(
            "help", "history", "clear", "m+", "mr", "mc", "quit",
            "memory", "stats", "export");

    /**
     * Validate complete expression
     */
    public static ValidationResult validateExpression(String input) {
        if (input == null) {
            return ValidationResult.invalid("Input cannot be null");
        }

        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            return ValidationResult.invalid("Input cannot be empty");
        }

        // Handle command inputs
        if (isCommand(trimmed)) {
            return ValidationResult.valid(trimmed, ValidationType.COMMAND);
        }

        // Basic character validation
        ValidationResult charValidation = validateCharacters(trimmed);
        if (!charValidation.isValid()) {
            return charValidation;
        }

        // Check for balanced parentheses
        ValidationResult parenValidation = validateParentheses(trimmed);
        if (!parenValidation.isValid()) {
            return parenValidation;
        }

        // Validate function calls
        ValidationResult functionValidation = validateFunctions(trimmed);
        if (!functionValidation.isValid()) {
            return functionValidation;
        }

        // Check for common syntax errors
        ValidationResult syntaxValidation = validateSyntax(trimmed);
        if (!syntaxValidation.isValid()) {
            return syntaxValidation;
        }

        return ValidationResult.valid(trimmed, ValidationType.EXPRESSION);
    }

    /**
     * Validate individual characters
     */
    private static ValidationResult validateCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!VALID_CHARS.contains(c) && !Character.isLetter(c)) {
                return ValidationResult.invalid(
                        String.format("Invalid character '%c' at position %d", c, i + 1));
            }
        }
        return ValidationResult.valid(input, ValidationType.EXPRESSION);
    }

    /**
     * Validate balanced parentheses
     */
    private static ValidationResult validateParentheses(String input) {
        int balance = 0;
        int maxNesting = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                balance++;
                maxNesting = Math.max(maxNesting, balance);
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    return ValidationResult.invalid(
                            "Unmatched closing parenthesis at position " + (i + 1));
                }
            }
        }

        if (balance > 0) {
            return ValidationResult.invalid("Unmatched opening parenthesis");
        }

        if (maxNesting > 10) {
            return ValidationResult.invalid("Too many nested parentheses (max 10 levels)");
        }

        return ValidationResult.valid(input, ValidationType.EXPRESSION);
    }

    /**
     * Validate function calls
     */
    private static ValidationResult validateFunctions(String input) {
        // Find all function patterns
        java.util.regex.Matcher matcher = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(")
                .matcher(input);

        while (matcher.find()) {
            String functionName = matcher.group(1).toLowerCase();

            if (!MATHEMATICAL_FUNCTIONS.contains(functionName)) {
                return ValidationResult.invalid(
                        String.format("Unknown function '%s'", functionName));
            }

            // Check for empty parentheses or missing closing paren
            int openParenIndex = matcher.end() - 1; // Position of '('
            if (openParenIndex + 1 >= input.length()) {
                return ValidationResult.invalid("Incomplete function call: missing closing parenthesis");
            }

            // Look for closing parenthesis
            int closeParenIndex = findMatchingParen(input, openParenIndex);
            if (closeParenIndex == -1) {
                return ValidationResult.invalid("Unmatched parenthesis in function call");
            }
        }

        return ValidationResult.valid(input, ValidationType.EXPRESSION);
    }

    /**
     * Find matching closing parenthesis
     */
    private static int findMatchingParen(String input, int openParenIndex) {
        int balance = 1;
        for (int i = openParenIndex + 1; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                balance++;
            } else if (input.charAt(i) == ')') {
                balance--;
                if (balance == 0) {
                    return i;
                }
            }
        }
        return -1; // Not found
    }

    /**
     * Check for common syntax errors
     */
    private static ValidationResult validateSyntax(String input) {
        // Check for consecutive operators (except after parentheses)
        for (int i = 0; i < input.length() - 1; i++) {
            char current = input.charAt(i);
            char next = input.charAt(i + 1);

            if (isOperator(current) && isOperator(next)) {
                // Allow '**' for power, but not other consecutive operators
                if (!(current == '*' && next == '*')) {
                    return ValidationResult.invalid(
                            String.format("Consecutive operators '%c%c' at position %d", current, next, i + 1));
                }
            }
        }

        // Check for empty parentheses
        if (input.contains("()")) {
            return ValidationResult.invalid("Empty parentheses '()' are not allowed");
        }

        // Check for division by zero patterns
        if (input.matches(".*\\([^)]*\\s*[/\\\\]\\s*0\\s*\\).*")) {
            return ValidationResult.invalid("Division by zero in expression");
        }

        // Check for incomplete expressions at end
        char lastChar = input.charAt(input.length() - 1);
        if (isOperator(lastChar) && lastChar != ')') {
            return ValidationResult.invalid("Expression ends with operator");
        }

        return ValidationResult.valid(input, ValidationType.EXPRESSION);
    }

    /**
     * Sanitize input by removing unnecessary spaces and normalizing
     */
    public static String sanitizeInput(String input) {
        if (input == null)
            return "";

        // Remove extra whitespace
        String result = input.replaceAll("\\s+", "");

        // Handle power operator normalization
        result = result.replace("**", "^");

        // Ensure proper spacing around some operators (might be needed for parsing)
        // But for calculator expressions, we'll keep it tight

        return result;
    }

    /**
     * Check if input is a command
     */
    private static boolean isCommand(String input) {
        return COMMAND_KEYWORDS.contains(input.toLowerCase());
    }

    /**
     * Check if character is an operator
     */
    private static boolean isOperator(char c) {
        return "+-*/%^".indexOf(c) != -1;
    }

    /**
     * Check if string represents a number
     */
    public static boolean isNumber(String str) {
        return str != null && NUMBER_PATTERN.matcher(str.trim()).matches();
    }

    /**
     * Check if string is a mathematical constant
     */
    public static boolean isConstant(String str) {
        return str != null && CONSTANT_PATTERN.matcher(str.trim()).matches();
    }

    /**
     * Tokenize expression into basic components (for debugging)
     */
    public static java.util.List<String> tokenizeExpression(String expression) {
        java.util.List<String> tokens = new java.util.ArrayList<>();

        StringBuilder currentToken = new StringBuilder();
        boolean inFunction = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isWhitespace(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                continue;
            }

            if (c == '(') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add("(");
                inFunction = true;
            } else if (c == ')') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(")");
                inFunction = false;
            } else if (isOperator(c)) {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    // Validation result class
    public static class ValidationResult {
        private final boolean valid;
        private final String message;
        private final String cleanedInput;
        private final ValidationType type;

        private ValidationResult(boolean valid, String message, String cleanedInput, ValidationType type) {
            this.valid = valid;
            this.message = message;
            this.cleanedInput = cleanedInput;
            this.type = type;
        }

        public static ValidationResult valid(String input, ValidationType type) {
            return new ValidationResult(true, null, input, type);
        }

        public static ValidationResult invalid(String message) {
            return new ValidationResult(false, message, null, ValidationType.INVALID);
        }

        public boolean isValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }

        public String getCleanedInput() {
            return cleanedInput;
        }

        public ValidationType getType() {
            return type;
        }
    }

    public enum ValidationType {
        EXPRESSION, COMMAND, INVALID
    }
}
