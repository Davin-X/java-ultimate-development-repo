# Performance Considerations in Java

Performance optimization is critical for writing scalable Java applications. This guide covers key performance considerations that intermediate developers should understand and apply.

## 🎯 Performance Fundamentals

### Big O Notation Quick Reference
```
| Complexity | Name        | Example Operations          |
|------------|-------------|----------------------------|
| O(1)       | Constant    | Array access, HashMap get  |
| O(log n)   | Logarithmic | Binary search, Tree lookup |
| O(n)       | Linear      | List traversal, Stream count |
| O(n log n) | Log-linear  | Sorting algorithms         |
| O(n²)      | Quadratic   | Nested loops, Bubble sort  |
| O(2^n)     | Exponential | Subset generation, DP      |
```

### JVM Performance Model
- **Startup Cost**: Class loading, JIT compilation
- **Runtime**: Garbage collection, optimization
- **Memory**: Heap usage, object allocation patterns
- **CPU**: Algorithm efficiency, contention

---

## 🔍 String Performance Considerations

### String Concatenation Performance

```java
// ❌ Poor performance with many concatenations (O(n²))
public String concatenatePoorly(List<String> words) {
    String result = "";
    for (String word : words) {
        result += word; // Creates new String object each time!
    }
    return result;
}

// ✅ Good: StringBuilder (O(n))
public String concatenateWell(List<String> words) {
    StringBuilder sb = new StringBuilder();
    for (String word : words) {
        sb.append(word);
    }
    return sb.toString();
}

// ✅ Best: Java 8+ Stream (parallelizable)
public String concatenateBest(List<String> words) {
    return words.stream()
                .collect(Collectors.joining());
}

// ✅ Alternative: Pre-size StringBuilder
public String concatenateOptimal(List<String> words) {
    int totalLength = words.stream()
                          .mapToInt(String::length)
                          .sum();
    StringBuilder sb = new StringBuilder(totalLength);
    for (String word : words) {
        sb.append(word);
    }
    return sb.toString();
}
```

### String Interning
```java
// Automatic interning for string literals
String s1 = "hello";        // Points to intern pool
String s2 = "hello";        // Same object as s1
System.out.println(s1 == s2); // true

// Manual interning for runtime strings
String s3 = new String("world");      // New object
String s4 = s3.intern();             // From intern pool
String s5 = "world";                 // From intern pool
System.out.println(s4 == s5);         // true (same reference)
```

---

## 📊 Collections Performance

### Choosing the Right Collection

| Collection Type | Add | Remove | Lookup | Memory | Thread-Safe |
|-----------------|-----|--------|--------|--------|-------------|
| **ArrayList** | O(1) | O(n) | O(1) | Low | ❌ |
| **LinkedList** | O(1) | O(1)* | O(n) | High | ❌ |
| **HashSet** | O(1) | O(1) | O(1) | Medium | ❌ |
| **TreeSet** | O(log n) | O(log n) | O(log n) | Medium | ❌ |
| **HashMap** | O(1) | O(1) | O(1) | Medium | ❌ |
| **ConcurrentHashMap** | O(1) | O(1) | O(1) | Medium | ✅ (partial) |

[*] LinkedList remove is O(1) only with iterator

### Collection Sizing Best Practices

```java
// ❌ Default sizing - may require resizing
List<String> list = new ArrayList<>();  // Default: 10 elements

// ✅ Pre-allocate if size known
List<String> list = new ArrayList<>(expectedSize);

// ✅ Capacity planning for HashMap
Map<String, Object> map = new HashMap<>(initialCapacity, loadFactor);
// loadFactor=0.75 means resize at 75% capacity
```

### Iterator vs Enhanced For-Loop

```java
List<String> list = new ArrayList<>();

// Enhanced for-loop (preferred for simple iteration)
for (String item : list) {
    System.out.println(item);
}

// Iterator when modification is needed
for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
    String item = it.next();
    if (item.equals("remove")) {
        it.remove(); // Safe removal during iteration
    }
}

// Stream API for functional operations
list.stream()
    .filter(item -> !item.isEmpty())
    .forEach(System.out::println);
```

---

## 🔄 Method Call Performance

### Inlining Considerations

```java
// JVM may inline small methods
public int getSize() {
    return size; // Likely inlined by JIT
}

// Complex methods less likely to be inlined
public int complexCalculation() {
    int result = 0;
    // Many operations...
    return result; // Less likely inlined
}
```

### HotSpot Method Call Optimization
- **Monomorphic**: Single target - fast (direct call)
- **Bimorphic**: Two targets - bytecode transformation
- **Polymorphic**: Multiple targets - vtable/virtual call

```java
interface Calculator {
    int compute(int a, int b);
}

class Adder implements Calculator {
    public int compute(int a, int b) { return a + b; }
}

class Multiplier implements Calculator {
    public int compute(int a, int b) { return a * b; }
}

// Monomorphic (Adder only) - fast
Calculator calc1 = new Adder();
calc1.compute(1, 2);

// Polymorphic (runtime determination) - slower
Calculator calc2 = getCalculator(); // Could be Adder or Multiplier
calc2.compute(1, 2);
```

---

## 🚮 Memory Management Performance

### Object Allocation Patterns

```java
// ✅ Reuse objects when possible
public class DateFormatter {
    private static final SimpleDateFormat formatter =
        new SimpleDateFormat("yyyy-MM-dd"); // Thread-safe instance

    public String format(Date date) {
        return formatter.format(date);
    }
}

// ✅ Object pooling for expensive objects
public class ConnectionPool {
    private final Queue<Connection> pool = new LinkedList<>();

    public Connection getConnection() {
        Connection conn = pool.poll();
        return conn != null ? conn : createNewConnection();
    }

    public void returnConnection(Connection conn) {
        if (pool.size() < MAX_SIZE) {
            pool.offer(conn);
        } else {
            conn.close();
        }
    }
}
```

### Avoiding Memory Leaks

```java
// ❌ Memory leak with static collection
public class StaticCache {
    private static final Map<String, Object> cache = new HashMap<>();

    public static void put(String key, Object value) {
        cache.put(key, value); // Objects never removed!
    }
}

// ✅ Bounded cache with cleanup
public class BoundedCache {
    private final Map<String, Object> cache = new LinkedHashMap<String, Object>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_SIZE; // Automatic cleanup
        }
    };

    public void put(String key, Object value) {
        cache.put(key, value);
    }
}

// ✅ Using Weak/Soft references for caching
public class WeakValueCache<K, V> {
    private final Map<K, WeakReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new WeakReference<>(value));
    }

    public V get(K key) {
        WeakReference<V> ref = cache.get(key);
        return ref != null ? ref.get() : null; // May be null if GC'd
    }
}
```

---

## ⚡ Exception Performance

### Exception Creation Cost

```java
// ✅ Use exceptions only for exceptional cases
public Optional<User> findUser(String id) {
    for (User user : users) {
        if (user.getId().equals(id)) {
            return Optional.of(user);
        }
    }
    return Optional.empty(); // No exception thrown
}

// ❌ Don't use exceptions for control flow
public boolean isValidUser(String id) {
    try {
        findUserById(id);
        return true;
    } catch (UserNotFoundException e) {
        return false; // Exception used for control flow - SLOW!
    }
}
```

### Stack Trace Cost
```java
// ✅ Exceptions with meaningful messages only when needed
public void validateInput(String input) throws ValidationException {
    if (input == null) {
        throw new ValidationException("Input cannot be null");
    }
    if (input.trim().isEmpty()) {
        throw new ValidationException("Input cannot be empty");
    }
}

// ✅ Use supplier to avoid unnecessary message creation
public void checkPermission() throws SecurityException {
    if (!hasPermission()) {
        throw new SecurityException("Access denied"); // Only created when needed
    }
}
```

---

## 🔄 I/O Performance Optimization

### Buffered I/O

```java
// ❌ Unbuffered I/O (slow)
try (FileReader reader = new FileReader("large.txt")) {
    int c;
    while ((c = reader.read()) != -1) {
        processChar((char) c); // Slow: one character at a time
    }
}

// ✅ Buffered I/O (fast)
try (BufferedReader reader = new BufferedReader(new FileReader("large.txt"))) {
    String line;
    while ((line = reader.readLine()) != -1) {
        processLine(line); // Fast: buffered reading
    }
}

// ✅ Custom buffer size for large files
try (BufferedReader reader = new BufferedReader(
         new FileReader("large.txt"), 8192 * 4)) { // 32KB buffer
    // Buffer size tuned for specific use case
    String line;
    while ((line = reader.readLine()) != -1) {
        processLine(line);
    }
}
```

### NIO.2 Performance

```java
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

// ✅ Asynchronous file operations
Path source = Paths.get("source.txt");
Path target = Paths.get("target.txt");

// Async copy (non-blocking)
CompletableFuture<Path> futureCopy = CompletableFuture.supplyAsync(() -> {
    try {
        return Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
});

// Do other work while copy happens...

// Get result when needed
Path result = futureCopy.get(10, TimeUnit.SECONDS);
```

---

## 🔧 JVM Tuning Fundamentals

### HotSpot GC Algorithms

| GC Algorithm | Use Case | Latency | Throughput |
|--------------|----------|---------|------------|
| **Serial GC** | Small apps | High | Low |
| **Parallel GC** | Batch processing | Medium | High |
| **CMS GC** | Response-time sensitive | Low-medium | Medium |
| **G1 GC** | General purpose (default) | Low | High |
| **ZGC/Shenandoah** | Low latency | Very low | High |

### Basic JVM Tuning Flags

```bash
# Heap sizing
java -Xmx4g -Xms4g MyApp        # Fixed 4GB heap
java -Xmx8g -Xms2g MyApp        # Min 2GB, max 8GB heap

# GC selection
java -XX:+UseG1GC MyApp         # Use G1 Garbage Collector
java -XX:+UseSerialGC MyApp     # Use Serial GC for small apps

# GC tuning
java -XX:MaxGCPauseMillis=200   # Target pause time
java -XX:G1HeapRegionSize=16m   # Region size for G1

# JIT optimization
java -server MyApp              # Server VM for better optimization
java -client MyApp              # Client VM for faster startup
```

### Memory Pool Monitoring

```java
// Runtime memory information
Runtime runtime = Runtime.getRuntime();

// Current memory usage
long totalMemory = runtime.totalMemory();     // Allocated heap
long freeMemory = runtime.freeMemory();       // Free in allocated heap
long usedMemory = totalMemory - freeMemory;   // Actually used

long maxMemory = runtime.maxMemory();         // Maximum heap size

System.out.printf("Used: %dMB, Free: %dMB, Total: %dMB, Max: %dMB%n",
    usedMemory / 1024 / 1024,
    freeMemory / 1024 / 1024,
    totalMemory / 1024 / 1024,
    maxMemory / 1024 / 1024);
```

---

## 📈 Profiling and Measurement

### Basic Performance Testing

```java
// ✅ Simple performance measurement
public class PerformanceTest {
    public static long measureTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        // Warm up JVM
        for (int i = 0; i < 1000; i++) {
            measureTime(() -> {
                // Warm-up operation
            });
        }

        // Actual measurement
        Runnable task = () -> {
            // Code to measure
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                list.add(String.valueOf(i));
            }
        };

        long averageTime = 0;
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            averageTime += measureTime(task);
        }
        averageTime /= iterations;

        System.out.printf("Average execution time: %.2f ms%n",
            averageTime / 1_000_000.0);
    }
}
```

### JMH Benchmarking Example

```java
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class CollectionBenchmark {

    private List<String> arrayList;
    private List<String> linkedList;

    @Setup
    public void setup() {
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
        // Populate lists...
    }

    @Benchmark
    public void arrayListAdd() {
        arrayList.add("element");
    }

    @Benchmark
    public void linkedListAdd() {
        linkedList.add("element");
    }
}
```

---

## 🔍 Anti-Patterns to Avoid

### 1. Premature Optimization
```java
// ❌ Optimize before measuring
public int prematureOptimization(List<Integer> numbers) {
    // Complex optimization for unverified bottleneck
    return numbers.stream()
                  .parallel()
                  .mapToInt(i -> i * i)
                  .sum();
}

// ✅ Measure first, then optimize
public int measureThenOptimize(List<Integer> numbers) {
    // Simple implementation first
    int sum = 0;
    for (int num : numbers) {
        sum += num * num;
    }
    return sum;
}
```

### 2. Micro-optimization Obsession
```java
// ❌ Micro-optimization without proof
public int microOptimized(String s) {
    // Replace all characters manually "for performance"
    char[] chars = new char[s.length()];
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        chars[i] = Character.toUpperCase(c);
    }
    return new String(chars);
}

// ✅ Let JVM optimize - focus on algorithm choice
public String goodEnough(String s) {
    return s.toUpperCase(); // JVM will optimize this
}
```

### 3. Ignoring Reality
```java
// ❌ Theory without practice
Map<String, Object> cache = new HashMap<>();
// Assumes O(1) lookup is always fast

// ✅ Understand real performance characteristics
Map<String, Object> cache = new ConcurrentHashMap<>();
cache.put("key", computeExpensiveValue());
// Measure cache hit/miss ratios in real usage
```

---

## 📊 Performance Checklist

### Code Review Questions
- [ ] Does the algorithm have optimal Big O complexity?
- [ ] Are collections sized appropriately for expected load?
- [ ] Is string concatenation optimized (StringBuilder)?
- [ ] Are objects reused instead of recreated?
- [ ] Are exceptions used only for exceptional conditions?
- [ ] Is I/O properly buffered?
- [ ] Are data structures chosen for intended usage patterns?

### JVM Monitoring Checklist
- [ ] GC logs analyzed for memory pressure?
- [ ] Memory leaks investigated (heap dumps)?
- [ ] CPU profiling done for hotspots?
- [ ] Thread contention analyzed (thread dumps)?

### Production Readiness
- [ ] Application profiled under real load?
- [ ] Memory usage monitored during extended runs?
- [ ] Error conditions tested for performance?
- [ ] Resource leaks detected and fixed?

### Continuous Monitoring
- [ ] Performance metrics collected in production?
- [ ] Alert thresholds set for performance degradation?
- [ ] Automated tests prevent performance regression?
- [ ] User-facing metrics monitored?

---

## 🛠️ Advanced Performance Tools

### JVM Tools
```bash
# JVM process information
jps                                    # List Java processes
jstat -gc <pid>                       # GC statistics
jstat -class <pid>                    # Class loading statistics
jmap -heap <pid>                      # Heap usage
jstack <pid>                          # Thread dump

# Profilers
java -XX:+PrintGCDetails MyApp        # Detailed GC logging
java -XX:+HeapDumpOnOutOfMemoryError  # Automatic heap dumps
```

### External Tools
- **VisualVM**: Free GUI profiler from JDK
- **YourKit**: Commercial profiler with advanced features
- **JProfiler**: Enterprise Java profiler
- **APM Tools**: New Relic, Application Insights, DataDog

### Build-time Tools
- **SpotBugs**: Static analysis for performance issues
- **PMD/CPD**: Code duplication and complexity analysis
- **JCStress**: Concurrency testing framework

---

## 📈 Conclusion

### Performance Optimization Hierarchy
1. **Choose the Right Algorithm/Design** (80% impact)
2. **Use Appropriate Data Structures** (15% impact)
3. **Micro-optimizations** (5% impact - last resort)

### Key Takeaways
- **Measure Before Optimizing**: Don't guess, profile and measure
- **Understand JVM Behavior**: Learn how JVM optimizes your code
- **Focus on Hotspots**: Optimize the 10% of code that matters
- **Consider Trade-offs**: Performance often involves memory/time trade-offs
- **Monitor in Production**: Real usage patterns may surprise you

Performance optimization is an iterative process that requires understanding your application, measuring real behavior, and applying targeted optimizations. Start with algorithmic choices, then data structures, and only then worry about micro-optimizations.

**🎯 Remember: Programs must be correct first, then fast. But premature optimization kills productivity!**
