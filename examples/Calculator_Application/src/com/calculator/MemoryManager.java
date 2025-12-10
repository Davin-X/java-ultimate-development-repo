import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Manages calculator memory operations with persistence and validation.
 * Demonstrates Maps usage, encapsulation, and thread-safety considerations.
 */
public class MemoryManager {

    private final Map<String, Double> memory;
    private final int maxMemorySlots;

    /**
     * Create memory manager with default capacity
     */
    public MemoryManager() {
        this(26); // Default 26 slots (A-Z) like traditional calculators
    }

    /**
     * Create memory manager with custom capacity
     */
    public MemoryManager(int capacity) {
        if (capacity <= 0) {
            throw new CalculatorException.MemoryOperationException(
                    "Memory capacity must be positive");
        }
        this.maxMemorySlots = capacity;
        this.memory = new HashMap<>(capacity);
    }

    /**
     * Store value in named memory slot
     * Traditional calculator uses single slot, modern uses named slots
     */
    public void store(double value) {
        store("M", value); // Default memory slot
    }

    /**
     * Store value in specific memory slot
     */
    public void store(String slot, double value) {
        if (slot == null || slot.trim().isEmpty()) {
            throw new CalculatorException.MemoryOperationException(
                    "Memory slot name cannot be null or empty");
        }

        // Validate slot name (allow letters, numbers, underscore)
        if (!slot.matches("[A-Za-z][A-Za-z0-9_]*")) {
            throw new CalculatorException.MemoryOperationException(
                    "Invalid memory slot name. Use letters, numbers, and underscores only");
        }

        if (memory.size() >= maxMemorySlots && !memory.containsKey(slot)) {
            throw new CalculatorException.MemoryOperationException(
                    String.format("Memory capacity exceeded. Maximum %d slots allowed", maxMemorySlots));
        }

        memory.put(slot, value);
    }

    /**
     * Recall value from default memory slot
     */
    public double recall() {
        return recall("M");
    }

    /**
     * Recall value from specific memory slot
     */
    public double recall(String slot) {
        if (slot == null || slot.trim().isEmpty()) {
            throw new CalculatorException.MemoryOperationException(
                    "Memory slot name cannot be null or empty");
        }

        Double value = memory.get(slot);
        if (value == null) {
            throw new CalculatorException.MemoryOperationException(
                    String.format("No value stored in memory slot '%s'", slot));
        }

        return value;
    }

    /**
     * Add current result to memory (M+ operation)
     */
    public void addToMemory(double value) {
        addToMemory("M", value);
    }

    /**
     * Add value to specific memory slot
     */
    public void addToMemory(String slot, double value) {
        try {
            double currentValue = recall(slot);
            store(slot, currentValue + value);
        } catch (CalculatorException.MemoryOperationException e) {
            // If slot doesn't exist, store the value directly
            store(slot, value);
        }
    }

    /**
     * Clear default memory slot
     */
    public void clear() {
        clear("M");
    }

    /**
     * Clear specific memory slot
     */
    public void clear(String slot) {
        if (slot == null) {
            throw new CalculatorException.MemoryOperationException(
                    "Memory slot name cannot be null");
        }

        if (!memory.containsKey(slot)) {
            throw new CalculatorException.MemoryOperationException(
                    String.format("Memory slot '%s' does not exist", slot));
        }

        memory.remove(slot);
    }

    /**
     * Clear all memory slots
     */
    public void clearAll() {
        memory.clear();
    }

    /**
     * Check if default memory slot has value
     */
    public boolean hasValue() {
        return hasValue("M");
    }

    /**
     * Check if specific memory slot has value
     */
    public boolean hasValue(String slot) {
        return slot != null && memory.containsKey(slot);
    }

    /**
     * Get all memory slot names
     */
    public Set<String> getSlotNames() {
        return Set.copyOf(memory.keySet()); // Immutable copy
    }

    /**
     * Get memory usage summary
     */
    public String getMemorySummary() {
        if (memory.isEmpty()) {
            return "Memory is empty";
        }

        StringBuilder summary = new StringBuilder();
        summary.append("Memory Contents:\\n");
        summary.append("================\\n");

        memory.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> summary.append(String.format("%s: %.4f\\n",
                        entry.getKey(), entry.getValue())));

        summary.append("\\nTotal slots used: ").append(memory.size());
        summary.append(String.format("\\nCapacity: %d slots\\n", maxMemorySlots));

        return summary.toString();
    }

    /**
     * Get formatted memory display for UI
     */
    public String getFormattedMemory() {
        if (memory.isEmpty()) {
            return "No memory values stored";
        }

        StringBuilder display = new StringBuilder();
        display.append("MEMORY SLOTS\\n");
        display.append("============\\n");

        memory.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> display.append(String.format("%s = %.6f\\n",
                        entry.getKey(), entry.getValue())));

        return display.toString();
    }

    /**
     * Get number of occupied memory slots
     */
    public int getOccupiedSlots() {
        return memory.size();
    }

    /**
     * Get memory capacity
     */
    public int getCapacity() {
        return maxMemorySlots;
    }

    /**
     * Check if memory is full
     */
    public boolean isFull() {
        return memory.size() >= maxMemorySlots;
    }

    /**
     * Get memory statistics
     */
    public String getStatistics() {
        int occupied = getOccupiedSlots();
        int available = maxMemorySlots - occupied;

        double minValue = memory.values().stream().mapToDouble(Double::doubleValue).min().orElse(0);
        double maxValue = memory.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);
        double avgValue = memory.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);

        return String.format(
                "Memory Statistics:\\n" +
                        "Occupied slots: %d/%d\\n" +
                        "Available slots: %d\\n" +
                        "Value range: %.4f to %.4f\\n" +
                        "Average value: %.4f\\n" +
                        "Memory utilization: %.1f%%",
                occupied, maxMemorySlots, available, minValue, maxValue, avgValue,
                (occupied * 100.0) / maxMemorySlots);
    }
}
