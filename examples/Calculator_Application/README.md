# Interactive Calculator Application

A complete Java calculator demonstrating OOP, exception handling, and CLI interfaces.

## 🚀 Features

- **CLI Interface**: Command-line calculator with interactive input
- **Math Operations**: Basic arithmetic (+, -, *, /, ^, %) and advanced functions
- **Memory Functions**: Store/recall values (M+, MR, MC)
- **History Tracking**: View calculation history
- **Error Handling**: Comprehensive input validation and exception management
- **Expression Parsing**: Supports parentheses and operator precedence

## 🏗️ Architecture

```
Calculator_Application/
├── src/com/calculator/
│   ├── Calculator.java          # Main calculator engine
│   ├── CalculatorUI.java        # Command-line interface
│   ├── MathFunctions.java       # Advanced math operations
│   ├── HistoryManager.java      # Calculation history
│   ├── MemoryManager.java       # Memory storage
│   ├── InputValidator.java      # Input validation
│   └── CalculatorException.java # Custom exceptions
├── build.gradle                 # Gradle build config
└── README.md                    # This file
```

## 🖥️ Quick Start

### Prerequisites
- Java 17+
- Gradle (wrapper included)

### Run the Application
```bash
cd examples/Calculator_Application
./gradlew run    # Unix/Linux/macOS
gradlew.bat run  # Windows
```

### Sample Usage
```
=== Interactive Calculator ===
Enter calculation (or 'help' for commands): 2 + 3 * 4
Result: 14.0

Enter calculation (or 'help' for commands): sqrt(16)
Result: 4.0

Enter calculation (or 'help' for commands): history
=== Calculation History ===
1. 2 + 3 * 4 = 14.0
2. sqrt(16) = 4.0
```

## 📋 Commands

### Math Operations
```
Basic: +, -, *, /, ^ (power), % (modulo)
Functions: sqrt(x), sin(x), cos(x), tan(x), log(x), exp(x), abs(x), fact(n)
Constants: pi, e
Parentheses: ( ) for grouping
```

### Special Commands
```
help     - Show commands
history  - View calculation history
clear    - Clear history and memory
m+ / M+  - Add to memory
mr / MR  - Recall from memory
mc / MC  - Clear memory
quit     - Exit calculator
```

## 🎯 Learning Objectives

This project demonstrates:

### OOP Concepts
- **Encapsulation**: Private fields, public methods
- **Abstraction**: Clean interfaces hiding complexity
- **Modularity**: Separated concerns across classes

### Java Features
- **Exception Handling**: Custom exceptions, try-catch blocks
- **Collections**: ArrayList for history, HashMap for operations
- **String Processing**: Expression parsing and validation
- **File I/O**: History persistence (optional)

### Design Patterns
- **Command Pattern**: Mathematical operations
- **Factory Pattern**: Operation creation
- **Singleton Pattern**: Shared state management

## 🧪 Testing

```bash
./gradlew test                    # Run all tests
./gradlew jacocoTestReport        # Generate coverage report
```

## 📚 How to Study

1. **Run the Application**: Get familiar with the interface
2. **Examine the Code**: Start with Calculator.java and CalculatorUI.java
3. **Understand Architecture**: See how classes interact
4. **Modify Features**: Add new mathematical functions
5. **Add Tests**: Write unit tests for new features

## 🎉 Extensions

Try adding:
- **Unit Conversions**: Temperature, currency, length
- **Statistical Functions**: Mean, median, standard deviation
- **Graphing**: Plot mathematical functions
- **Programming Mode**: Store multi-step calculations
- **GUI Version**: Swing-based interface

---

**Perfect for learning Java through a substantial project! 🧮**
