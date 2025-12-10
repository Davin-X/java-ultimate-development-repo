import java.util.function.DoubleUnaryOperator;
import java.util.Optional;

/**
 * Advanced mathematical functions for the calculator.
 * Demonstrates functional programming concepts and mathematical accuracy.
 */
public class MathFunctions {

    // Mathematical constants with high precision
    public static final double PI = Math.PI;
    public static final double E = Math.E;

    /**
     * Function registry for string-to-function mapping
     */
    private static final java.util.Map<String, DoubleUnaryOperator> FUNCTIONS = java.util.Map.ofEntries(
            java.util.Map.entry("sin", x -> Math.sin(x)),
            java.util.Map.entry("cos", x -> Math.cos(x)),
            java.util.Map.entry("tan", x -> Math.tan(x)),
            java.util.Map.entry("asin", x -> Math.asin(x)),
            java.util.Map.entry("acos", x -> Math.acos(x)),
            java.util.Map.entry("atan", x -> Math.atan(x)),
            java.util.Map.entry("sinh", x -> Math.sinh(x)),
            java.util.Map.entry("cosh", x -> Math.cosh(x)),
            java.util.Map.entry("tanh", x -> Math.tanh(x)),
            java.util.Map.entry("log", MathFunctions::log),
            java.util.Map.entry("ln", x -> Math.log(x)),
            java.util.Map.entry("sqrt", x -> Math.sqrt(x)),
            java.util.Map.entry("exp", x -> Math.exp(x)),
            java.util.Map.entry("abs", x -> Math.abs(x)),
            java.util.Map.entry("ceil", x -> Math.ceil(x)),
            java.util.Map.entry("floor", x -> Math.floor(x)),
            java.util.Map.entry("round", MathFunctions::round),
            java.util.Map.entry("fact", MathFunctions::factorial),
            java.util.Map.entry("cbrt", x -> Math.cbrt(x)),
            java.util.Map.entry("deg", x -> Math.toDegrees(x)),
            java.util.Map.entry("rad", x -> Math.toRadians(x)));

    /**
     * Get a mathematical function by name
     */
    public static Optional<DoubleUnaryOperator> getFunction(String name) {
        return Optional.ofNullable(FUNCTIONS.get(name.toLowerCase()));
    }

    /**
     * Check if a name represents a mathematical function
     */
    public static boolean isFunction(String name) {
        return FUNCTIONS.containsKey(name.toLowerCase());
    }

    /**
     * Get all available function names
     */
    public static java.util.Set<String> getFunctionNames() {
        return FUNCTIONS.keySet();
    }

    // Advanced mathematical functions

    /**
     * Natural logarithm (base e) - handles edge cases
     */
    public static double log(double x) {
        if (x <= 0) {
            throw new CalculatorException.InvalidExpressionException(
                    "Logarithm is undefined for non-positive numbers");
        }
        return Math.log(x);
    }

    /**
     * Rounding function (different from Math.round which returns long)
     */
    public static double round(double x) {
        return Math.round(x * 100.0) / 100.0; // Round to 2 decimal places
    }

    /**
     * Factorial function with overflow protection
     */
    public static double factorial(double n) {
        if (n < 0) {
            throw new CalculatorException.InvalidExpressionException(
                    "Factorial is undefined for negative numbers");
        }

        if (n != (int) n) {
            throw new CalculatorException.InvalidExpressionException(
                    "Factorial requires integer input");
        }

        if (n > 20) {
            throw new CalculatorException.InvalidExpressionException(
                    "Factorial for n > 20 is too large to represent");
        }

        int intN = (int) n;
        long result = 1;
        for (int i = 2; i <= intN; i++) {
            if (result > Long.MAX_VALUE / i) {
                throw new CalculatorException.InvalidExpressionException(
                        "Factorial result exceeds maximum representable value");
            }
            result *= i;
        }

        return result;
    }

    /**
     * Safe power function with better error handling
     */
    public static double power(double base, double exponent) {
        // Handle special cases
        if (base == 0 && exponent < 0) {
            throw new CalculatorException.InvalidExpressionException(
                    "Division by zero (0^x where x < 0)");
        }

        if (base < 0 && exponent != (int) exponent) {
            throw new CalculatorException.InvalidExpressionException(
                    "Complex numbers not supported (negative base with fractional exponent)");
        }

        try {
            return Math.pow(base, exponent);
        } catch (Exception e) {
            throw new CalculatorException.InvalidExpressionException(
                    "Power operation failed: " + e.getMessage());
        }
    }

    /**
     * Safe square root with error checking
     */
    public static double safeSqrt(double x) {
        if (x < 0) {
            throw new CalculatorException.InvalidExpressionException(
                    "Square root of negative numbers is not supported");
        }
        return Math.sqrt(x);
    }

    /**
     * Trigonometric functions with degree/radian handling
     */
    public static double sin(double angle, boolean isDegrees) {
        return Math.sin(isDegrees ? Math.toRadians(angle) : angle);
    }

    public static double cos(double angle, boolean isDegrees) {
        return Math.cos(isDegrees ? Math.toRadians(angle) : angle);
    }

    public static double tan(double angle, boolean isDegrees) {
        return Math.tan(isDegrees ? Math.toRadians(angle) : angle);
    }

    /**
     * Statistical functions (extension possibilities)
     */
    public static double average(double... numbers) {
        if (numbers.length == 0)
            return 0;
        return java.util.Arrays.stream(numbers).average().orElse(0);
    }

    /**
     * Get information about available functions
     */
    public static String getFunctionsHelp() {
        StringBuilder help = new StringBuilder();
        help.append("Available Mathematical Functions:\\n");
        help.append("=================================\\n");
        help.append("Trigonometric: sin(x), cos(x), tan(x), asin(x), acos(x), atan(x)\\n");
        help.append("Hyperbolic: sinh(x), cosh(x), tanh(x)\\n");
        help.append("Logarithmic: log(x), ln(x) [natural log]\\n");
        help.append("Exponential: exp(x), sqrt(x), cbrt(x) [cube root]\\n");
        help.append("Other: abs(x), ceil(x), floor(x), round(x), fact(n) [factorial]\\n");
        help.append("Conversion: deg(x), rad(x) [degrees/radians]\\n");
        help.append("\\nConstants: pi = ").append(PI).append(", e = ").append(E);
        help.append("\\n\\nExample: sin(pi/2) = ").append(Math.sin(Math.PI / 2));
        help.append("\\nExample: fact(5) = ").append(factorial(5));

        return help.toString();
    }
}
