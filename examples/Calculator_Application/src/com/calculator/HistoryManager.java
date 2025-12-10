import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Manages calculation history with timestamps and operations tracking.
 * Demonstrates collections usage, immutability, and data persistence concepts.
 */
public class HistoryManager {

    /**
     * Record to store calculation entries (Java 14+ feature, can be replaced with
     * class)
     */
    public static record CalculationEntry(String expression, double result, LocalDateTime timestamp) {

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return String.format("%s → %.4f (%s)", expression, result, timestamp.format(formatter));
        }

        /**
         * Create a new entry with current timestamp
         */
        public static CalculationEntry create(String expression, double result) {
            return new CalculationEntry(expression, result, LocalDateTime.now());
        }
    }

    private final List<CalculationEntry> history;
    private final int maxHistorySize;

    /**
     * Create history manager with default size limit
     */
    public HistoryManager() {
        this(100); // Default capacity
    }

    /**
     * Create history manager with custom size limit
     */
    public HistoryManager(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max history size must be positive");
        }
        this.maxHistorySize = maxSize;
        this.history = new ArrayList<>();
    }

    /**
     * Add a calculation to history
     */
    public void addEntry(String expression, double result) {
        CalculationEntry entry = CalculationEntry.create(expression, result);
        history.add(0, entry); // Add to beginning for most recent first

        // Trim history if it exceeds size limit
        if (history.size() > maxHistorySize) {
            // Remove oldest entries (from the end)
            List<CalculationEntry> toRemove = new ArrayList<>(
                    history.subList(maxHistorySize, history.size()));
            history.removeAll(toRemove);
        }
    }

    /**
     * Get all history entries (immutable view)
     */
    public List<CalculationEntry> getAllEntries() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }

    /**
     * Get recent entries (most recent first)
     */
    public List<CalculationEntry> getRecentEntries(int count) {
        if (count <= 0)
            return new ArrayList<>();

        int size = Math.min(count, history.size());
        return history.subList(0, size);
    }

    /**
     * Get history size
     */
    public int getSize() {
        return history.size();
    }

    /**
     * Check if history is empty
     */
    public boolean isEmpty() {
        return history.isEmpty();
    }

    /**
     * Clear all history
     */
    public void clear() {
        history.clear();
    }

    /**
     * Find entries by result value
     */
    public List<CalculationEntry> findByResult(double result) {
        return history.stream()
                .filter(entry -> Math.abs(entry.result() - result) < 0.0001) // Floating point comparison
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Find entries containing specific text
     */
    public List<CalculationEntry> findByExpression(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            return new ArrayList<>();
        }

        final String search = searchText.toLowerCase().trim();
        return history.stream()
                .filter(entry -> entry.expression().toLowerCase().contains(search))
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get summary statistics
     */
    public String getSummary() {
        if (history.isEmpty()) {
            return "No calculations in history";
        }

        int totalCount = history.size();
        double minResult = history.stream().mapToDouble(CalculationEntry::result).min().orElse(0);
        double maxResult = history.stream().mapToDouble(CalculationEntry::result).max().orElse(0);
        double avgResult = history.stream().mapToDouble(CalculationEntry::result).average().orElse(0);

        CalculationEntry oldest = history.get(history.size() - 1);
        CalculationEntry newest = history.get(0);

        return String.format(
                "History Summary:\\n" +
                        "Total calculations: %d\\n" +
                        "Result range: %.4f to %.4f\\n" +
                        "Average result: %.4f\\n" +
                        "Oldest calculation: %s\\n" +
                        "Newest calculation: %s",
                totalCount, minResult, maxResult, avgResult,
                oldest.timestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                newest.timestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * Export history as formatted text
     */
    public String exportAsText() {
        if (history.isEmpty()) {
            return "No calculation history to export";
        }

        StringBuilder export = new StringBuilder();
        export.append("CALCULATION HISTORY EXPORT\\n");
        export.append("========================\\n\\n");

        for (int i = 0; i < history.size(); i++) {
            CalculationEntry entry = history.get(i);
            export.append(String.format("%d. %s\\n", i + 1, entry.toString()));
        }

        export.append("\\n").append(getSummary());
        return export.toString();
    }

    /**
     * Get formatted history for display
     */
    public String getFormattedHistory(int maxEntries) {
        if (history.isEmpty()) {
            return "No calculations in history yet";
        }

        List<CalculationEntry> entries = getRecentEntries(maxEntries);
        StringBuilder formatted = new StringBuilder();

        formatted.append("=== Calculation History ===\\n");
        for (int i = 0; i < entries.size(); i++) {
            formatted.append(String.format("%d. %s\\n", i + 1, entries.get(i).toString()));
        }

        if (history.size() > maxEntries) {
            formatted.append(String.format("\\n... and %d more entries\\n", history.size() - maxEntries));
        }

        return formatted.toString();
    }

    /**
     * Get expression for reuse (for UI to process)
     */
    public String getExpressionForReuse(int index) {
        if (index < 1 || index > history.size()) {
            return null; // Invalid index
        }

        // Indices are 1-based in display, convert to 0-based for list access
        CalculationEntry entry = history.get(index - 1);
        return entry.expression();
    }
}
