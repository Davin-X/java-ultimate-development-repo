# Effective Java Principles for Intermediate Developers

"Effective Java" by Joshua Bloch is the definitive guide to Java best practices. This guide summarizes key principles that every intermediate Java developer should master.

## 🎯 Core Principles Overview

### 1. **Creating and Destroying Objects**
### 2. **Methods Common to All Objects**
### 3. **Classes and Interfaces**
### 4. **Generics**
### 5. **Enums and Annotations**
### 6. **Lambdas and Streams**
### 7. **Methods and General Programming**
### 8. **Exceptions**
### 9. **Concurrency**
### 10. **Serialization**

---

## 🏗️ Chapter 1-2: Creating and Destroying Objects

### Item 1: Consider static factory methods instead of constructors

```java
// ❌ Constructor only
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// ✅ Static factory method + private constructor
public class Person {
    private final String name;
    private final int age;

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Static factory method
    public static Person of(String name, int age) {
        return new Person(name, age);
    }

    // Named factory methods for clarity
    public static Person adult(String name) {
        return new Person(name, 18);
    }

    public static Person child(String name, int age) {
        if (age > 18) throw new IllegalArgumentException();
        return new Person(name, age);
    }
}

// Usage
Person adult = Person.adult("John");
Person teen = Person.child("Alice", 16);
```

**Benefits:**
- **Meaningful names** (not just "Person")
- **Caching** (object reuse possible)
- **Subclassing** control
- **No public constructor** requirement

### Item 2: Consider a builder when faced with many constructor parameters

```java
// ❌ Telescoping constructor anti-pattern
public class NutritionFacts {
    private final int servingSize;      // required
    private final int servings;         // required
    private final int calories;         // optional
    private final int fat;              // optional
    private final int sodium;           // optional
    private final int carbohydrate;     // optional

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    // More constructors... repetitive and error-prone
}

// ✅ Builder pattern
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) { calories = val; return this; }
        public Builder fat(int val) { fat = val; return this; }
        public Builder sodium(int val) { sodium = val; return this; }
        public Builder carbohydrate(int val) { carbohydrate = val; return this; }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
}

// Usage - readable and flexible
NutritionFacts facts = new NutritionFacts.Builder(240, 8)
    .calories(100)
    .sodium(35)
    .carbohydrate(27)
    .build();
```

### Item 5: Prefer dependency injection to hardwiring resources

```java
// ❌ Hardwired dependency (tight coupling)
public class SpellChecker {
    private static final Lexicon dictionary = new EnglishLexicon();

    private SpellChecker() {} // Noninstantiable

    public static boolean isValid(String word) {
        return dictionary.isValid(word);
    }
}

// ✅ Dependency injection (loose coupling)
public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
        return dictionary.isValid(word);
    }
}

// Usage
SpellChecker checker = new SpellChecker(new EnglishLexicon());
```

---

## 📋 Chapter 3: Methods Common to All Objects

### Item 10: Obey the general contract when overriding equals

**The equals contract (must obey):**
- **Reflexive**: `x.equals(x)` returns true
- **Symmetric**: `x.equals(y) == y.equals(x)`
- **Transitive**: if `x.equals(y)` and `y.equals(z)`, then `x.equals(z)`
- **Consistent**: multiple calls return same result (if objects unchanged)
- **Non-nullity**: `x.equals(null)` returns false

```java
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;           // Performance optimization
        if (!(obj instanceof Point)) return false; // Type check
        Point point = (Point) obj;              // Cast is safe
        return point.x == this.x && point.y == this.y; // Field comparison
    }

    // Must override hashCode when overriding equals
    @Override
    public int hashCode() {
        return Objects.hash(x, y); // Java 7+ utility method
    }
}
```

### Item 11: Always override hashCode when you override equals

```java
// ❌ Wrong: equals overridden, hashCode not
public class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.areaCode == areaCode &&
               pn.prefix == prefix &&
               pn.lineNum == lineNum;
    }
    // hashCode not overridden - violates hash table contract!
}

// ✅ Correct: both overridden
public class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhoneNumber)) return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.areaCode == areaCode &&
               pn.prefix == prefix &&
               pn.lineNum == lineNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, prefix, lineNum);
    }
}
```

### Item 12: Always override toString

```java
// ❌ Default toString (uninformative)
public class Person {
    private String name;
    private int age;
    // toString() returns something like "Person@163b91"
}

// ✅ Informative toString
public class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
```

---

## 🏢 Chapter 4: Classes and Interfaces

### Item 15: Minimize the accessibility of classes and members

**Access Level Hierarchy (most to least restrictive):**
1. **private** - class only
2. **package-private** (default) - package only
3. **protected** - package + subclasses
4. **public** - everywhere

```java
// ✅ Encapsulated class
public class BankAccount {
    private BigDecimal balance;  // private - no external access

    // Public operations with validation
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance = balance.add(amount);
    }

    // Defensive copy to prevent external modification
    public BigDecimal getBalance() {
        return new BigDecimal(balance.toString()); // New instance
    }
}
```

### Item 16: Favor composition over inheritance

```java
// ❌ Inheritance for code reuse (fragile)
public class InstrumentedSet<E> extends HashSet<E> {
    private int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

// ✅ Composition (forwarding - more robust)
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
```

### Item 22: Use interfaces only to define types

```java
// ✅ Interface defines contract
public interface Singer {
    void sing(String song);
    default void rest() {
        System.out.println("Taking a break...");
    }
}

// ❌ Constants interface anti-pattern
public interface Constants {
    static final double PI = 3.14159;    // DON'T DO THIS!
    static final int MAX_SIZE = 100;
}

// ✅ Constants in utility class
public class MathConstants {
    private MathConstants() {} // Prevent instantiation

    public static final double PI = 3.14159;
    public static final int MAX_SIZE = 100;
}
```

---

## 🎯 Chapter 5: Generics

### Item 26: Don't use raw types

```java
// ❌ Raw type usage (dangerous)
public static void main(String[] args) {
    List names = new ArrayList();  // Raw type
    names.add("John");
    names.add(42);  // No compile-time checking!

    String name = (String) names.get(1); // Runtime ClassCastException!
}

// ✅ Parameterized type (type-safe)
public static void main(String[] args) {
    List<String> names = new ArrayList<>();
    names.add("John");
    // names.add(42);  // Compile-time error!
    String name = names.get(0); // Safe cast
}
```

### Item 27: Eliminate unchecked warnings

```java
// ❌ Unchecked generic array creation
public static <T> T[] toArray(Collection<T> c) {
    T[] array = (T[]) new Object[c.size()]; // Unchecked cast warning
    return c.toArray(array);
}

// ✅ Suppress warning with justification
public static <T> T[] toArray(Collection<T> c) {
    @SuppressWarnings("unchecked")
    T[] array = (T[]) new Object[c.size()]; // Warning suppressed
    return c.toArray(array);
}

// Best solution: Use collection or create array externally
public static <T> List<T> toList(Collection<T> c) {
    return new ArrayList<>(c); // No warnings, type-safe
}
```

### Item 28: Prefer lists to arrays

```java
// ❌ Arrays have runtime type issues
public class ArrayChooser {
    public static <T> T[] choose(T a, T b) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[2]; // Unchecked cast
        array[0] = a;
        array[1] = b;
        return array;
    }
}

// ✅ Lists are type-safe and flexible
public class ListChooser {
    public static <T> List<T> choose(T a, T b) {
        List<T> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list; // No warnings, fully type-safe
    }
}
```

### Item 29: Favor generic types

```java
// ❌ Concrete type (less reusable)
public class ObjectStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public ObjectStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) throw new EmptyStackException();
        @SuppressWarnings("unchecked")
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    // ... isEmpty, ensureCapacity methods
}

// ✅ Generic type (type-safe and reusable)
public class Stack<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) throw new EmptyStackException();
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    // ... isEmpty, ensureCapacity methods
}
```

---

## 🚨 Chapter 8-9: Exceptions

### Item 61: Throw exceptions appropriate to the abstraction

```java
// ❌ Low-level exception leaking through abstraction
public class DatabaseManager {
    public User getUser(String id) throws SQLException { // Leaks DB details
        Connection conn = getConnection();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("name"));
            }
            throw new SQLException("User not found"); // Database-specific
        }
    }
}

// ✅ Higher-level exception appropriate to abstraction
public class DatabaseManager {
    public User getUser(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("name"));
            }
            throw new UserNotFoundException("User not found: " + id);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve user: " + id, e);
        } finally {
            closeSilently(stmt);
            closeSilently(conn);
        }
    }
}

// Custom exceptions
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### Item 62: Document all exceptions thrown by each method

```java
// ✅ Properly documented exceptions
/**
 * Returns the element at the specified position in this list.
 *
 * @param index index of the element to return
 * @return the element at the specified position in this list
 * @throws IndexOutOfBoundsException if the index is out of range
 *         ({@code index < 0 || index >= size()})
 * @throws ClassCastException if the element cannot be cast to the specified type
 */
public <T> T get(int index) {
    // Implementation
}
```

### Item 65: Don't ignore exceptions

```java
// ❌ Silent exception swallowing (dangerous)
public static int getIntValue(String s) {
    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        return 0; // Silent failure - caller doesn't know parsing failed
    }
}

// ✅ Report exception or fail fast
public static int getIntValue(String s) {
    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid number format: " + s, e);
    }
}

// Alternative: Use Optional for recoverable failures
public static Optional<Integer> tryParseInt(String s) {
    try {
        return Optional.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
        return Optional.empty();
    }
}
```

---

## ⚡ Chapter 10-11: Concurrency and Serialization

### Item 66: Synchronize access to shared mutable data

```java
// ❌ Non-thread-safe (race condition prone)
public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true; // Visibility problem - thread may not see this!
    }
}

// ✅ Thread-safe with volatile
public class StopThread {
    private static volatile boolean stopRequested; // Volatile ensures visibility

    // Alternative: Use AtomicBoolean
    private static final AtomicBoolean stopRequested = new AtomicBoolean(false);

    public static void stop() {
        stopRequested.set(true);
    }

    public static boolean isStopped() {
        return stopRequested.get();
    }
}
```

### Item 67: Avoid excessive synchronization

```java
// ❌ Excessive synchronization (performance penalty)
public class SynchronizedStringList {
    private final List<String> list = new ArrayList<>();

    public synchronized boolean putIfAbsent(String x) {
        synchronized (this) { // Nested locks - redundant!
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}

// ✅ Better synchronization
public class BetterStringList {
    private final List<String> list = new ArrayList<>();

    public synchronized boolean putIfAbsent(String x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}
```

### Item 74: Implement Serializable judiciously

```java
// ⚠️ Careful with Serializable
public class Person implements Serializable {
    private static final long serialVersionUID = 1L; // Explicit serialVersionUID

    private String name;
    private int age;
    private transient String password; // Don't serialize sensitive data

    // Custom serialization for validation/compression
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        // Additional validation or encryption can be added here
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        // Additional validation or decryption can be added here
    }
}
```

---

## 🧪 Testing Principles

### Item 69: Use exceptions only for exceptional conditions

```java
// ❌ Using exceptions for control flow (slow and confusing)
public boolean isInteger(String s) {
    try {
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

// ✅ Use result object or state testing
public boolean isInteger(String s) {
    try {
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

// Better: Separate parsing from validation
public static Optional<Integer> tryParseInt(String s) {
    try {
        return Optional.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
        return Optional.empty();
    }
}
```

---

## 📚 Summary of Key Themes

### **API Design Principles:**
- Make APIs hard to misuse, easy to use correctly
- Fail fast on parameter validation
- Use consistent naming and style
- Document carefully, especially exceptions

### **Class Design Principles:**
- Encapsulate internal representation
- Favor composition over inheritance
- Design for extension or prohibit it
- Prefer interfaces over abstract classes

### **Immutable Objects:**
- Classes should be immutable unless there's a good reason to do otherwise
- Ensure exclusive access to mutable components
- Make defensive copies when needed

### **Performance Considerations:**
- Avoid creating unnecessary objects
- Prefer primitive types to boxed primitives
- Eliminate obsolete object references
- Use lazy initialization judiciously

### **Generics and Collections:**
- Use generics everywhere possible
- Prefer collections to arrays
- Use EnumMap instead of ordinal indexing
- Use ConcurrentHashMap in concurrent contexts

### **Exception Handling:**
- Use checked exceptions for recoverable conditions only
- Use runtime exceptions for programming errors
- Never ignore exceptions inappropriately
- Document all exceptions thrown

### **Concurrency:**
- Synchronize access to shared mutable data
- Prefer executors and tasks to threads
- Use ConcurrentHashMap for concurrent access
- Document thread safety guarantees

These principles form the foundation of writing robust, maintainable, and efficient Java code. Study them carefully and apply them consistently in your development work!

---

**🎯 "Effective Java" is not just a book—it's a philosophy for writing better Java code that will serve you throughout your career.**
