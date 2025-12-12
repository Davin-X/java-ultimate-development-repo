# Effective Java Principles - Key Takeaways

## Core Principles Every Java Developer Should Know

### 1. Creating and Destroying Objects
- **Static factory methods** over constructors for better naming and flexibility
- **Builder pattern** for classes with many optional parameters  
- **Dependency injection** over hardcoded resources for testability
- **Private constructors** to prevent instantiation of utility classes

### 2. Methods Common to All Objects
- **Override equals properly** following the contract (reflexive, symmetric, transitive)
- **Always override hashCode** when overriding equals
- **Override toString** for informative debugging output
- **Override clone judiciously** (prefer copy constructors or factories)

### 3. Classes and Interfaces
- **Minimize accessibility** of classes and members
- **Favor composition over inheritance** for flexibility
- **Design and document for inheritance** or prohibit it
- **Use interfaces only to define types**, not for constants

### 4. Generics
- **Don't use raw types** - prefer parameterized types
- **Eliminate unchecked warnings** with proper suppression
- **Prefer lists to arrays** for type safety
- **Favor generic types** and methods

### 5. Enums and Annotations
- **Use enums instead of int constants** for type safety
- **Use EnumMap** for enum-keyed collections
- **Use interfaces for extensible enums**
- **Document thread safety** for annotations

### 6. Lambdas and Streams
- **Prefer lambdas to anonymous classes**
- **Use method references** where appropriate
- **Favor streams over loops** for bulk operations
- **Use collectors** judiciously

### 7. Methods and General Programming
- **Check parameters** for validity
- **Make defensive copies** when needed
- **Design method signatures carefully**
- **Overload carefully** to avoid confusion

### 8. Exceptions
- **Use exceptions only for exceptional conditions**
- **Don't ignore exceptions inappropriately**
- **Document all exceptions thrown**
- **Throw exceptions appropriate to the abstraction**

### 9. Concurrency
- **Synchronize access** to shared mutable data
- **Avoid excessive synchronization** for performance
- **Prefer executors and tasks** to threads
- **Document thread safety guarantees**

### 10. Serialization
- **Implement Serializable judiciously**
- **Consider using custom serialized forms**
- **Defend against serialization attacks**
- **Provide readResolve/writeReplace** when needed

## Key Design Patterns from Effective Java

### Builder Pattern
```java
public class NutritionFacts {
    private final int calories;
    
    private NutritionFacts(Builder builder) {
        calories = builder.calories;
    }
    
    public static class Builder {
        private int calories = 0;
        
        public Builder calories(int val) { 
            calories = val; 
            return this; 
        }
        
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
}
```

### Singleton with Enum
```java
public enum Singleton {
    INSTANCE;
    
    public void doSomething() {
        // implementation
    }
}
```

### Static Factory Methods
```java
public class Objects {
    private Objects() {} // Non-instantiable
    
    public static <T> T requireNonNull(T obj) {
        if (obj == null) throw new NullPointerException();
        return obj;
    }
}
```

## Performance Best Practices

- **Avoid creating unnecessary objects**
- **Prefer primitives to boxed primitives**
- **Eliminate obsolete object references**
- **Use lazy initialization carefully**

## API Design Guidelines

- **Make APIs hard to misuse, easy to use correctly**
- **Fail fast on invalid parameters**
- **Use consistent naming conventions**
- **Document thoroughly, especially exceptions**

## Recommended Reading Order

1. **Items 1-4**: Object creation and methods
2. **Items 15-18**: Class and interface design  
3. **Items 25-31**: Generics and collections
4. **Items 69-74**: Exceptions and serialization
5. **Items 78-82**: Concurrency

## Implementation Checklist

- [ ] Override equals/hashCode together
- [ ] Use generics everywhere possible
- [ ] Minimize class/member accessibility
- [ ] Document thread safety guarantees
- [ ] Fail fast on invalid parameters
- [ ] Use composition over inheritance
- [ ] Implement Serializable carefully

---

**These principles will guide you toward writing more robust, maintainable, and efficient Java code.**
