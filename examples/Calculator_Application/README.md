# Interactive Calculator Application

> A complete, production-ready Java application demonstrating OOP, exception handling, and interactive user interfaces.

## 🚀 Features

- **Interactive CLI Interface**: User-friendly command-line calculator
- **Mathematical Operations**: Addition, subtraction, multiplication, division, exponentiation
- **Advanced Functions**: Square root, trigonometric functions, factorial, percentage
- **Memory Functions**: Store and retrieve calculation results (M+, MR, MC)
- **History Tracking**: View calculation history
- **Robust Error Handling**: Comprehensive input validation and error recovery
- **Mathematical Expression Evaluation**: Parentheses and operator precedence
- **Multiple Input Methods**: Direct input, history recall, memory operations

## 🏗️ Architecture

```
Calculator_Application/
├── src/com/calculator/
│   ├── Calculator.java          # Main calculator engine
│   ├── CalculatorUI.java        # User interface management
│   ├── MathFunctions.java       # Advanced mathematical operations
│   ├── HistoryManager.java      # Calculation history tracking
│   ├── MemoryManager.java       # Memory storage operations
│   ├── InputValidator.java      # Input validation and sanitization
│   └── CalculatorException.java # Custom exceptions
├── build.gradle                 # Gradle build configuration
├── calculator.iml               # IntelliJ project file
└── README.md                    # This file
```

## 🖥️ Quick Start

### Prerequisites
- Java 17+
- Gradle (optional - wrapper included)

### Run the Application

**Option 1: Using Gradle Wrapper (Recommended)**
```bash
cd examples/Calculator_Application
./gradlew run  # On Unix/Linux/macOS
# or
gradlew.bat run  # On Windows
```

**Option 2: Manual Compilation**
```bash
# Compile all Java files
javac -d build src/com/calculator/*.java

# Run the application
java -cp build com.calculator.CalculatorUI
```

### Sample Interaction
```
=== Interactive Calculator ===
Enter calculation (or 'help' for commands): 2 + 3 * 4
Result: 14.0

Enter calculation (or 'help' for commands): sqrt(16)
Result: 4.0

Enter calculation (or 'help' for commands): sin(30)
Result: 0.5

Enter calculation (or 'help' for commands): history
=== Calculation History ===
1. 2 + 3 * 4 = 14.0
2. sqrt(16) = 4.0
3. sin(30) = 0.5

Enter calculation (or 'help' for commands): quit
Thank you for using Interactive Calculator!
```

## 🎯 Learning Objectives

### Java Concepts Demonstrated

#### Core OOP Principles
- **Encapsulation**: Data hiding with private fields and public methods
- **Abstraction**: Clean interfaces that hide implementation details
- **Modularity**: Separated concerns across multiple classes
- **Inheritance**: Exception hierarchy and interface implementation

#### Advanced Java Features
- **Exception Handling**: Try-catch-finally, custom exceptions, error recovery
- **Collections Framework**: ArrayList for history tracking, HashMap for operations
- **Generic Types**: Type-safe collections and method parameters
- **String Manipulation**: Parsing mathematical expressions
- **Regular Expressions**: Input validation patterns
- **Scanner API**: User input processing
- **switch Expressions**: Modern switch with pattern matching

#### Design Patterns
- **Command Pattern**: Mathematical operations as commands
- **Factory Pattern**: Operation creation from user input
- **Observer Pattern**: History tracking notifications
- **Singleton Pattern**: Shared calculator state management

## 📋 Available Commands

### Mathematical Operations
```
Basic: +, -, *, /, ^ (power), % (modulo)
Functions: sqrt(x), sin(x), cos(x), tan(x), log(x), ln(x), exp(x), abs(x), fact(n), pi, e
Parentheses: Use ( and ) for expression grouping
Example: sin(30) + sqrt(16) * (2 + 3)
```

### Special Commands
```
help          - Show all available commands
history       - Display calculation history
clear         - Clear history and memory
m+ or M+      - Add current result to memory
mr or MR      - Recall value from memory
mc or MC      - Clear memory
quit          - Exit the calculator
```

### Examples
```
2 + 3 * 4          # Basic arithmetic (returns 14.0)
sqrt(25) + sin(90) # Functions (returns 5.0 + 1.0 = 6.0)
(10 - 3) * 2       # Parentheses (returns 14.0)
5!                 # Factorial (returns 120.0)
100 % 7            # Modulo (returns 2.0)
pi * 2^2           # Constants and exponents (returns ~6.28)
```

## 🔧 Error Handling

The calculator includes comprehensive error handling for:
- **Invalid input**: Non-numeric characters in expressions
- **Mathematical errors**: Division by zero, square root of negative numbers
- **Syntax errors**: Malformed expressions, missing parentheses
- **Range errors**: Numbers too large/small, factorial of non-integers

```java
try {
    double result = calculator.evaluate(expression);
    System.out.println("Result: " + result);
} catch (CalculatorException e) {
    System.out.println("Error: " + e.getMessage());
    // Offer suggestions for correction
} catch (Exception e) {
    System.out.println("Unexpected error occurred");
}
```

## 🎓 Educational Benefits

### Beginner Friendly
- **Gradual complexity**: Start with basic addition, progress to advanced functions
- **Interactive feedback**: Immediate results and error messages help learning
- **Command help**: Built-in documentation reduces frustration

### Intermediate Developers
- **Architecture study**: Learn about multi-class application design
- **Error handling patterns**: Professional-grade exception management
- **Testing patterns**: Unit tested with comprehensive test coverage

### Advanced Concepts
- **Expression parsing**: Real-world parser implementation
- **State management**: Memory and history tracking
- **Design patterns**: Practical examples of common patterns
- **Performance considerations**: Efficient data structures and algorithms

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test  # Run all tests
./gradlew test --tests CalculatorTest  # Run specific test class
```

### Code Quality
```bash
./gradlew checkstyleMain  # Run code style checks
./gradlew jacocoTestReport  # Generate coverage report
```

## 📚 How to Extend

### Adding New Functions
```java
// In MathFunctions.java
public static double cubeRoot(double x) {
    return Math.cbrt(x);
}

// In Calculator.java
private static final Map<String, MathFunction> FUNCTIONS = Map.of(
    // ... existing functions,
    "cbrt", MathFunctions::cubeRoot
);
```

### Adding New Commands
```java
// In CalculatorUI.java
private void processCommand(String command, Scanner scanner) {
    switch (command.toLowerCase()) {
        // ... existing commands
        case "stats" -> showStatistics();
        case "export" -> exportHistory(scanner);
        // ... more commands
    }
}
```

## 🎯 Real-World Applications

This calculator demonstrates patterns used in:
- **Financial Applications**: Interest calculation, loan payments
- **Scientific Computing**: Mathematical modeling, data analysis
- **Game Development**: Physics calculations, scoring systems
- **Business Logic**: Pricing algorithms, discount calculations
- **Data Processing**: Formula evaluation, statistical analysis

## 🎉 Build Your Own Features

Try extending the calculator with:
- **Unit conversions**: Temperature, currency, length
- **Statistical functions**: Mean, median, standard deviation
- **Graphing capabilities**: Plot mathematical functions
- **Programming mode**: Store and recall multi-step calculations
- **Collaborative features**: Share calculations across devices

---

**Ready to build something amazing? Let's calculate! 🔢📊✨**
