import java.util.*;

/**
 * Interactive command-line user interface for the calculator.
 * Demonstrates I/O handling, menu systems, and user experience design.
 */
public class CalculatorUI {

    private final Calculator calculator;
    private final Scanner scanner;
    private boolean running;

    public CalculatorUI() {
        this.calculator = new Calculator();
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public CalculatorUI(Calculator calculator) {
        this.calculator = calculator;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    /**
     * Main application loop
     */
    public void run() {
        displayWelcome();

        while (running) {
            try {
                displayPrompt();
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }

                processInput(input);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Try again or type 'help' for assistance.\n");
            }
        }

        displayGoodbye();
        scanner.close();
    }

    /**
     * Display welcome message
     */
    private void displayWelcome() {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║          INTERACTIVE CALCULATOR       ║");
        System.out.println("║      Advanced Mathematical Engine     ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println();
        System.out.println("Type 'help' for available commands and functions.");
        System.out.println("Enter 'quit' to exit the calculator.");
        System.out.println();
    }

    /**
     * Display the main prompt
     */
    private void displayPrompt() {
        if (calculator.memoryHasValue()) {
            System.out.print("Calculator [M]: ");
        } else {
            System.out.print("Calculator: ");
        }
    }

    /**
     * Process user input
     */
    private void processInput(String input) {
        // Validate input first
        var validation = InputValidator.validateExpression(input);

        if (!validation.isValid()) {
            System.out.println("❌ Invalid input: " + validation.getMessage());
            return;
        }

        // Handle commands
        if (validation.getType() == InputValidator.ValidationType.COMMAND) {
            processCommand(input.toLowerCase());
            return;
        }

        // Handle mathematical expressions
        if (validation.getType() == InputValidator.ValidationType.EXPRESSION) {
            evaluateExpression(input);
        }
    }

    /**
     * Process mathematical expression
     */
    private void evaluateExpression(String expression) {
        try {
            double result = calculator.evaluate(expression);
            System.out.println("✓ Result: " + formatResult(result));

            // Show memory hint if applicable
            if (calculator.memoryHasValue()) {
                System.out.println("💾 Memory: " + formatResult(calculator.recallFromMemory()));
            }

        } catch (CalculatorException e) {
            System.out.println("❌ Calculation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Process user commands
     */
    private void processCommand(String command) {
        switch (command) {
            case "help":
                showHelp();
                break;
            case "history":
                showHistory();
                break;
            case "clear":
                clearAll();
                break;
            case "m+":
                handleMemoryStore();
                break;
            case "mr":
                handleMemoryRecall();
                break;
            case "mc":
                handleMemoryClear();
                break;
            case "memory":
                showMemory();
                break;
            case "stats":
                showStatistics();
                break;
            case "export":
                exportHistory();
                break;
            case "quit":
            case "exit":
                running = false;
                break;
            default:
                System.out.println("❌ Unknown command. Type 'help' for available commands.");
                break;
        }
    }

    /**
     * Display help information
     */
    private void showHelp() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                     CALCULATOR HELP                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("📊 MATHEMATICAL OPERATIONS:");
        System.out.println("─────────────────────────");
        System.out.println("Basic:     +, -, *, /, % (modulo), ^ (power)");
        System.out.println("Examples:  2 + 3 * 4, (5 + 3) * 2, 10 % 3");
        System.out.println();

        System.out.println("🔢 MATHEMATICAL FUNCTIONS:");
        System.out.println("────────────────────────");
        System.out.println("Trigonometric:  sin(x), cos(x), tan(x), asin(x), acos(x), atan(x)");
        System.out.println("Exponential:    sqrt(x), cbrt(x), exp(x), ln(x), log(x)");
        System.out.println("Rounding:       abs(x), ceil(x), floor(x), round(x)");
        System.out.println("Other:         fact(n) [factorial]");
        System.out.println("Constants:     pi, e");
        System.out.println("Examples:      sin(30), sqrt(16), fact(5), pi * 2");
        System.out.println();

        System.out.println("💾 MEMORY COMMANDS:");
        System.out.println("──────────────────");
        System.out.println("m+  - Store current result in memory");
        System.out.println("mr  - Recall value from memory");
        System.out.println("mc  - Clear memory");
        System.out.println("memory - Show memory contents");
        System.out.println();

        System.out.println("📚 INFORMATION COMMANDS:");
        System.out.println("────────────────────────");
        System.out.println("history  - Show calculation history");
        System.out.println("stats    - Show calculator statistics");
        System.out.println("export   - Export history to file");
        System.out.println("clear    - Clear history and memory");
        System.out.println();

        System.out.println("🔧 UTILITY COMMANDS:");
        System.out.println("───────────────────");
        System.out.println("help     - Show this help");
        System.out.println("quit     - Exit calculator");
        System.out.println();

        System.out.println("💡 TIPS:");
        System.out.println("────────");
        System.out.println("• Use parentheses for complex expressions");
        System.out.println("• Memory is persistent across calculations");
        System.out.println("• History shows your recent calculations");
        System.out.println("• Invalid expressions show helpful error messages");
        System.out.println();
    }

    /**
     * Display calculation history
     */
    private void showHistory() {
        if (calculator.getHistorySize() == 0) {
            System.out.println("No calculations in history.");
            return;
        }

        System.out.println(calculator.getFormattedHistory(10));
    }

    /**
     * Handle memory store command
     */
    private void handleMemoryStore() {
        try {
            calculator.storeInMemory(calculator.getHistoryManager()
                    .getRecentEntries(1).get(0).result());
            System.out.println("✓ Last result stored in memory");
        } catch (Exception e) {
            System.out.println("❌ No result to store. Make a calculation first.");
        }
    }

    /**
     * Handle memory recall command
     */
    private void handleMemoryRecall() {
        try {
            double value = calculator.recallFromMemory();
            System.out.println("💾 Memory value: " + formatResult(value));
        } catch (Exception e) {
            System.out.println("❌ Memory is empty");
        }
    }

    /**
     * Handle memory clear command
     */
    private void handleMemoryClear() {
        calculator.clearMemory();
        System.out.println("✓ Memory cleared");
    }

    /**
     * Show memory contents
     */
    private void showMemory() {
        System.out.println(calculator.getMemorySummary());
    }

    /**
     * Show calculator statistics
     */
    private void showStatistics() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   CALCULATOR STATISTICS                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();

        // History stats
        System.out.println("📚 HISTORY STATISTICS:");
        System.out.println("─────────────────────");
        System.out.println(calculator.getHistorySummary());
        System.out.println();

        // Memory stats
        System.out.println("💾 MEMORY STATISTICS:");
        System.out.println("─────────────────────");
        System.out.println(calculator.getMemoryManager().getStatistics());
        System.out.println();

        // Calculator engine stats
        System.out.println("⚙️  ENGINE INFORMATION:");
        System.out.println("────────────────────────");
        System.out.println("Mathematical functions available: " + MathFunctions.getFunctionNames().size());
        System.out.println("Supported operators: +, -, *, /, %, ^");
        System.out.println("Parser type: Shunting Yard Algorithm (RPN)");
        System.out.println("Expression evaluation: Stack-based");
        System.out.println();
    }

    /**
     * Export history to file
     */
    private void exportHistory() {
        if (calculator.getHistorySize() == 0) {
            System.out.println("No history to export.");
            return;
        }

        String filename = "calculator_history_" + System.currentTimeMillis() + ".txt";
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter(filename);
            writer.println(calculator.getHistoryManager().exportAsText());
            writer.close();
            System.out.println("✓ History exported to: " + filename);
        } catch (Exception e) {
            System.out.println("❌ Failed to export history: " + e.getMessage());
        }
    }

    /**
     * Clear all data
     */
    private void clearAll() {
        calculator.clearHistory();
        calculator.clearMemory();
        System.out.println("✓ All history and memory cleared");
    }

    /**
     * Display goodbye message
     */
    private void displayGoodbye() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║         THANK YOU FOR USING           ║");
        System.out.println("║       INTERACTIVE CALCULATOR!         ║");
        System.out.println("║                                       ║");
        System.out.println("║    Happy calculating! 🚀✨             ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    /**
     * Format result for display
     */
    private String formatResult(double value) {
        if (Double.isNaN(value)) {
            return "NaN";
        } else if (Double.isInfinite(value)) {
            return "Infinity";
        } else {
            // Format with appropriate precision
            if (Math.abs(value) < 1e-6 || Math.abs(value) > 1e6) {
                return String.format("%.6e", value);
            } else if (value == (long) value) {
                return String.valueOf((long) value);
            } else {
                return String.format("%.6f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
            }
        }
    }

    /**
     * Test the calculator UI
     */
    public static void main(String[] args) {
        CalculatorUI ui = new CalculatorUI();

        // Demo mode for testing
        System.out.println("Running Calculator UI in demo mode...");
        System.out.println("Enter mathematical expressions or commands like 'help', 'history', etc.");
        System.out.println("Type 'quit' to exit.\n");

        ui.run();
    }
}
