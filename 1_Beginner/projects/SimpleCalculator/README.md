# Simple Calculator - Level 1 Beginner Project

**Interactive command-line calculator demonstrating all Level 1 Java concepts**

## 🏗️ Project Overview

A fully functional calculator application with multiple interface options:
- Interactive menu-driven interface
- Direct command parsing (e.g., "5 + 3")
- Multiple arithmetic operations
- Exception handling and input validation
- Calculation history and statistics

## 💡 Java Concepts Demonstrated

### **Variables & Data Types**
- `int`, `double`, `boolean` primitive types
- Reference types (`String[]`, `Scanner`)
- Casting and type conversion

### **Control Structures**
- `while` loop for main application loop
- `switch` statement for command handling
- `if-else` for input validation
- `for-each` loops for array processing
- `break` and `continue` in loops
- Method dispatch using conditional logic

### **Classes & Objects**
- `Calculator` class with encapsulation
- `CalculatorApp` main application class
- `CalculatorException` custom exception class
- Instance variables and methods
- Constructor initialization

### **Exception Handling**
- `try-catch` blocks for input validation
- Custom exception class extending `Exception`
- Multiple catch blocks for different exception types
- User-friendly error messages
- Exception chaining and recovery

### **I/O Operations**
- `Scanner` for user input reading
- Console output with `System.out.println()`
- Input validation with loops
- Resource management (`scanner.close()`)

### **Methods & Functions**
- Method overloading (same name, different parameters)
- Parameterized methods and return values
- Static vs instance methods
- Helper methods for code organization
- Method decomposition for readability

---

## 🚀 How to Run

1. **Compile the Java files:**
   ```bash
   cd 1_Beginner/projects/SimpleCalculator
   javac *.java
   ```

2. **Run the calculator:**
   ```bash
   java CalculatorApp
   ```

3. **Use the calculator:**
   ```
   🧮 SIMPLE CALCULATOR - Level 1 Java Project
   ===========================================
   Type 'help' for available operations

   Calculator > calc
   Enter first number: 15
   Enter operation (+, -, *, /, %, ^): *
   Enter second number: 4
   15.0 * 4.0 = 60.000000
   Calculations performed: 1

   Calculator > sqrt
   Enter number for square root: 16
   √16.00 = 4.000000
   ```

---

## 🎮 Usage Examples

### **Basic Arithmetic:**
```
Calculator > calc
Enter first number: 23.5
Enter operation (+, -, *, /, %, ^): +
Enter second number: 17.8
23.50 + 17.80 = 41.300000
```

### **Direct Commands:**
```
Calculator > 12 * 8
12.0 * 8.0 = 96.000000

Calculator > 100 / 0    (error handling)
Error: Division by zero is not allowed. Please use a non-zero divisor.
```

### **Special Operations:**
```
Calculator > sqrt
Enter number for square root: 144
√144.00 = 12.000000

Calculator > add
How many numbers would you like to add? 3
Enter number 1: 10
Enter number 2: 20
Enter number 3: 30
Sum of 10.0 + 20.0 + 30.0 = 60.0
```

---

## 📊 Features & Capabilities

### **Supported Operations:**
- **Arithmetic:** `+`, `-`, `*`, `/`, `%`, `^` (power)
- **Advanced:** Square root, array summation
- **Aliases:** Support multiple operation names

### **Interface Options:**
- **Interactive:** Step-by-step guided input
- **Direct:** Type equations like "5 + 3"
- **Commands:** Type commands like "help", "stats", "reset"

### **Error Handling:**
- Invalid number input recovery
- Division by zero prevention
- Square root of negative numbers
- Unknown operation detection
- Empty array handling

### **Statistics & History:**
- Track total calculations performed
- Display last result
- Reset functionality
- Usage statistics

---

## 🔧 Project Architecture

```
SimpleCalculator/
├── Calculator.java          # Core calculator logic
├── CalculatorApp.java       # Main application interface
├── CalculatorException.java # Custom exception handling
└── README.md                # This documentation
```

**Calculator.java:** Contains calculation methods, history tracking, and display functions
**CalculatorApp.java:** Handles user interface, input processing, and command dispatch
**CalculatorException.java:** Custom exception with user-friendly error messages

---

## 🎯 Learning Objectives

After studying this project, you will understand:

### **Technical Skills:**
- How to structure a complete Java application
- Proper exception handling throughout an application
- User input validation and error recovery
- Method overloading and organization
- Control flow patterns for interactive programs

### **Programming Mindset:**
- Breaking complex applications into manageable classes
- Applying all Level 1 concepts in a cohesive project
- Error handling as a fundamental programming skill
- User experience considerations in program design
- Testing and debugging interactive applications

### **Career Readiness:**
- Professional code structure and organization
- Comprehensive input validation practices
- Error handling for robust applications
- User interface design principles
- Portfolio-quality application development

---

## 💡 Extension Ideas

After mastering this project, you can add:

- **Scientific Calculator:** Trigonometric functions, logarithms
- **Unit Conversion:** Length, weight, temperature conversions
- **Memory Functions:** Store and recall values (M+, MR, MC)
- **History Feature:** View previous calculations
- **File Operations:** Save/load calculation history
- **GUI Interface:** Swing or JavaFX graphical interface

---

## 🏆 Level 1 Beginner Capstone Achievement

**This Simple Calculator demonstrates Level 1 mastery because it combines:**

✅ **All Primitive & Reference Types** - ints, doubles, Strings, arrays, objects
✅ **Complex Control Flow** - nested loops, switch statements, complex conditionals
✅ **Object-Oriented Design** - Multiple classes with proper encapsulation
✅ **Robust Error Handling** - Try-catch throughout, custom exceptions
✅ **Professional Input/Output** - Scanner usage, validation loops, resource management
✅ **Advanced Method Techniques** - Overloading, decomposition, organization

**Completing and understanding this calculator proves you have mastered all Level 1 Beginner Java concepts and are ready for intermediate OOP topics! 🚀**

---

## 📈 Project Impact

Students who complete this project demonstrate:
- **Application Development:** Ability to create complete, working programs
- **Problem Decomposition:** Breaking complex requirements into implementable parts
- **Code Quality:** Professional structure, error handling, and organization
- **Learning Integration:** Applying theoretical knowledge practically
- **Career Preparation:** Portfolio project suitable for job applications

**This calculator serves as tangible proof of Level 1 Java proficiency! ⭐**
