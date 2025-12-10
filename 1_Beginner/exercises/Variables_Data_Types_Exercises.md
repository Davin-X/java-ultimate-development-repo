# Variables & Data Types - Practice Exercises

## Level 1 Beginner - Variables & Data Types Exercises

**Complete these exercises to master Java variables, data types, and basic operations**

---

## Exercise 1: Variable Declaration & Initialization

**Objective:** Practice declaring variables of different types and understanding initialization rules.

```java
// EXERCISE: Declare and initialize variables for each type below
// Hint: Use the primitive data types we learned: int, double, char, boolean

// 1.1 Create a variable to store your age (choose appropriate type)

// 1.2 Create a variable to store your grade point average (choose appropriate type)

// 1.3 Create a variable to store your first initial (choose appropriate type)

// 1.4 Create a variable to store whether you're enrolled in school (choose appropriate type)

// 1.5 Create a variable without initialization, then initialize it later

// 1.6 Print all your variables using System.out.println()

```

---

## Exercise 2: Data Type Conversion

**Objective:** Practice explicit and implicit type casting between different numeric types.

```java
public class TypeConversionExercises {

    public static void main(String[] args) {

        // EXERCISE: Perform these type conversions

        // 2.1 Cast int to double (implicit - widening)
        int wholeNumber = 42;
        // Create double variable from wholeNumber

        // 2.2 Cast double to int (explicit - narrowing)
        double decimalNumber = 3.99;
        // Create int variable from decimalNumber (will lose decimal part)

        // 2.3 Cast from int to char (ASCII table conversion)
        int asciiValue = 65;
        // Create char variable from asciiValue

        // 2.4 Cast from char to int (get ASCII value)
        char letter = 'A';
        // Create int variable to store ASCII value of letter

        // 2.5 Arithmetic operation with mixed types
        int x = 10;
        double y = 3.5;
        // What type will result be when you add x + y?

        // 2.6 Division with integers vs doubles
        int a = 7, b = 2;
        double result1 = a / b;  // What value do you get?
        double result2 = (double) a / b;  // What value now?

        System.out.println("Arithmetic results:");
        System.out.println("Integer division: " + result1);
        System.out.println("Cast division: " + result2);

    }
}
```

---

## Exercise 3: Primitive Data Types Range & Limits

**Objective:** Understand the range limitations of different data types and when overflow occurs.

```java
public class DataTypeLimits {

    public static void main(String[] args) {

        System.out.println("DATA TYPE LIMITS DEMONSTRATION");

        // EXERCISE: Explore the limits of various primitive types

        // 3.1 Integer.MIN_VALUE and Integer.MAX_VALUE
        System.out.println("Integer range: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;

        // What happens when you add 1 to MAX_VALUE?
        System.out.println("MAX_VALUE + 1 = " + (maxInt + 1));
        // What happens when you subtract 1 from MIN_VALUE?
        System.out.println("MIN_VALUE - 1 = " + (minInt - 1));

        // 3.2 Long range for very large numbers
        System.out.println("\\nLong range: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);

        // 3.3 Double precision and special values
        double infinity = Double.POSITIVE_INFINITY;
        double negInfinity = Double.NEGATIVE_INFINITY;
        double nan = Double.NaN;

        System.out.println("\\nDouble special values:");
        System.out.println("Positive Infinity: " + infinity);
        System.out.println("Negative Infinity: " + negInfinity);
        System.out.println("Not a Number: " + nan);

        // Test what happens with 1.0 / 0.0, -1.0 / 0.0, 0.0 / 0.0

        // 3.4 Byte overflow (8-bit range)
        byte byteVal = 127; // Maximum byte value
        System.out.println("\\nOriginal byte: " + byteVal);
        byteVal++; // Overflow to -128
        System.out.println("After increment: " + byteVal);

        // 3.5 Character ranges (0 to 65535)
        System.out.println("\\nCharacter range: 0 to " + (int) Character.MAX_VALUE);
        char firstChar = 0;
        char lastChar = 65535; // Or use Character.MAX_VALUE
        // Note: Printing characters beyond 127 may not display properly

    }
}
```

---

## Exercise 4: String Basics & Concatenation

**Objective:** Practice string creation, concatenation, and basic string operations.

```java
public class StringExamples {

    public static void main(String[] args) {

        // EXERCISE: String Operations Practice

        // 4.1 Create strings using different methods
        String literal = "Hello";           // String literal
        String object = new String("World"); // Using constructor
        String empty = "";                   // Empty string
        String whitespace = "   ";          // Whitespace string

        // 4.2 String concatenation
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + lastName; // Simple concatenation

        // 4.3 Improved concatenation with space
        String fullNameWithSpace = firstName + " " + lastName;

        // 4.4 Concatenation with numbers
        int age = 25;
        String greeting = "Hello, " + firstName + "! You are " + age + " years old.";

        // 4.5 String + numeric operations
        String numConcat = "Number: " + 42;        // String + int
        String doubleConcat = "Value: " + 3.14;    // String + double

        // Print all results
        System.out.println("=== STRING CONCATENATION RESULTS ===");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Full Name (no space): " + fullName);
        System.out.println("Full Name (with space): " + fullNameWithSpace);
        System.out.println("Greeting: " + greeting);
        System.out.println("Number concat: " + numConcat);
        System.out.println("Double concat: " + doubleConcat);

        // 4.6 String properties
        System.out.println("\\n=== STRING PROPERTIES ===");
        System.out.println("empty.length(): " + empty.length());
        System.out.println("whitespace.isEmpty(): " + whitespace.isEmpty());
        System.out.println("whitespace.trim().isEmpty(): " + whitespace.trim().isEmpty());

    }
}
```

---

## Exercise 5: Variable Scope & Lifetime

**Objective:** Understand where variables can be accessed and their lifetime rules.

```java
public class VariableScopeExercise {

    // Class-level (instance) variables - accessible anywhere in this class
    static int classVariable = 100;

    public static void main(String[] args) {
        // System.out.println(methodVariable); // ERROR - not accessible here

        System.out.println("Class variable in main: " + classVariable);

        // Call method to demonstrate scope
        demonstrateScope();

        System.out.println("Class variable after method call: " + classVariable);
    }

    public static void demonstrateScope() {
        // Method-level variables have method lifetime
        int methodVariable = 50;
        System.out.println("\\n+++ Inside demonstrateScope method +++");

        // Block scope with if statement
        if (true) {
            int blockVariable = 25;
            System.out.println("Block variable: " + blockVariable);
            System.out.println("Method variable: " + methodVariable);
            System.out.println("Class variable: " + classVariable);

            // Nested block
            {
                int nestedBlockVar = 10;
                System.out.println("Nested block variable: " + nestedBlockVar);
                // All outer variables are accessible here
            }

            // nestedBlockVar not accessible here
            // System.out.println(nestedBlockVar); // COMPILATION ERROR!
        }

        // blockVariable not accessible here (out of scope)
        // System.out.println(blockVariable); // COMPILATION ERROR!

        System.out.println("Method variable after block: " + methodVariable);
        System.out.println("Class variable after block: " + classVariable);
        System.out.println("+++ Leaving demonstrateScope method +++\\n");
    }

    // EXERCISE: Create methods that demonstrate different scoping scenarios
    // Method 1: Variables with same name in different scopes
    // Method 2: Local variables shadowing class variables
    // Method 3: Variables in for/while loops

    public static void scopingChallenge() {
        // TODO: Implement methods that challenge your understanding of scope
        // Hint: Try creating variables with same names in different scopes
        // Hint: Try accessing variables from nested methods/blocks
    }
}
```

---

## Exercise 6: Complete Data Types Summary

**Objective:** Combine all concepts learned into a comprehensive demonstration.

```java
// FINAL EXERCISE: Complete Data Types Mastery
public class DataTypesMastery {

    public static void main(String[] args) {
        System.out.println("=== DATA TYPES MASTERY EXHIBITION ===\\n");

        // PART 1: PRIMITIVE TYPES DEMONSTRATION
        demonstratePrimitives();

        // PART 2: STRING OPERATIONS DEMONSTRATION
        demonstrateStrings();

        // PART 3: TYPE CONVERSION CHALLENGES
        demonstrateConversions();

        // PART 4: VARIABLE SCOPE QUIZ
        demonstrateScopeQuiz();

        System.out.println("\\n🎉 IF YOU CAN EXPLAIN ALL OUTPUT, YOU'VE MASTERED VARIABLES & DATA TYPES!");
    }

    // TODO: Implement each demonstration method
    public static void demonstratePrimitives() {
        System.out.println("1. PRIMITIVES: Runtime values and limits");
        // Show min/max values, arithmetic operations, overflow behavior
    }

    public static void demonstrateStrings() {
        System.out.println("\\n2. STRINGS: Construction and manipulation");
        // Show different creation methods, concatenation performance
    }

    public static void demonstrateConversions() {
        System.out.println("\\n3. CONVERSIONS: Lossy vs lossless casting");
        // Show explicit/implicit casting, truncation effects
    }

    public static void demonstrateScopeQuiz() {
        System.out.println("\\n4. SCOPE QUIZ: Variable accessibility rules");
        // Create nested blocks, methods, and test variable lifetimes
    }
}
```

---

## Solutions & Additional Practice

### Key Learning Takeaways:

1. **Data Types:** Choose the right type for your data needs
2. **Casting:** Widening (automatic) vs narrowing (explicit) conversions
3. **Overflow:** Understand limits and when they occur
4. **Scope:** Variables only accessible in their declared blocks
5. **Lifetime:** Variables exist from declaration until block/method ends

### Additional Exercises:

- Try to minimize variable declarations (good programming practice)
- Practice type inference with `var` keyword (Java 10+)
- Experiment with different number systems (hex, binary, octal)
- Create an ASCII art table using char data type

### Debugging Tips:

- Use descriptive variable names
- Print variable values frequently when debugging
- Understand compilation vs runtime errors
- Learn to read stack traces for debugging help

**Remember:** Code runs top-to-bottom, left-to-right. Variables must be declared before use and are only accessible in their scopes!</result>
</write_to_file>
