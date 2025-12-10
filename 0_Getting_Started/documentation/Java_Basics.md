# Java Development Basics

Understanding the fundamental concepts of Java development is crucial before writing your first programs. This guide covers the core concepts that form the foundation of Java programming.

## 🎯 Core Concepts Overview

| Concept | Description | Importance |
|---------|-------------|------------|
| **JVM, JRE, JDK** | Java Virtual Machine ecosystem | Critical - execution environment |
| **Compilation Process** | Java source to bytecode | Critical - understanding workflow |
| **Main Method** | Program entry point | Critical - every Java program |
| **Packages** | Code organization | High - package management |
| **Classpath** | Runtime class loading | High - dependency management |

---

## ☕ JVM, JRE, and JDK

### Java Virtual Machine (JVM)
The JVM is the runtime environment that executes Java bytecode. It provides:
- **Platform Independence**: "Write once, run anywhere"
- **Memory Management**: Automatic garbage collection
- **Security**: Sandboxed execution environment
- **Performance**: Just-In-Time (JIT) compilation

**Key Point**: Java source code (.java) is compiled to bytecode (.class), which runs on the JVM.

### Java Runtime Environment (JRE)
JRE = JVM + Core Libraries
- **Contains JVM** for executing Java programs
- **Core Java libraries** (java.lang, java.util, etc.)
- **No development tools** (can't compile code)
- **For end-users** running Java applications

### Java Development Kit (JDK)
JDK = JRE + Development Tools
- **Contains JRE** for running Java programs
- **Compilers and tools**: javac, jar, javadoc, etc.
- **Source code** for core Java libraries
- **Documentation** and examples
- **For developers** creating Java applications

**Visual Flow**:
```
Source Code (.java) → JDK Compiler (javac) → Bytecode (.class) → JRE (JVM) → Execution
```

### Version Comparison
```bash
$ java -version      # Check JVM version in JRE
$ javac -version     # Check compiler version in JDK
$ jshell --version   # New interactive shell (Java 9+)
```

---

## 🔧 Compilation and Execution Process

### Step-by-Step Process

#### 1. Writing Source Code
```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

#### 2. Compilation (javac)
```bash
$ javac HelloWorld.java
# Creates HelloWorld.class (bytecode)
```

**What happens during compilation:**
- **Syntax checking** for Java language rules
- **Type checking** for variable and method compatibility
- **Code optimization** by compiler
- **Bytecode generation** in .class files

#### 3. Execution (java)
```bash
$ java HelloWorld
# Output: Hello, Java!
```

**JVM execution process:**
1. **Class Loading**: Loading .class files into memory
2. **Verification**: Ensuring bytecode safety
3. **Preparation**: Allocating memory for static fields
4. **Resolution**: Linking symbolic references
5. **Initialization**: Executing static initializers
6. **Execution**: Running main method

### Common Compilation Errors

#### Syntax Errors
```java
public class SyntaxError {
    public static void main(String[] args) {
        System.out.println("Missing semicolon")  // ERROR: missing ;
    }
}
```

**Fix**: Add missing semicolon: `System.out.println("Missing semicolon");`

#### Case Sensitivity
```java
public class CaseSensitive {
    public static void Main(String[] args) {  // ERROR: Main != main
        System.out.println("Hello!");
    }
}
```

**Fix**: Change `Main` to `main` (lowercase).

#### File Naming
- Class name: `HelloWorld`
- File name: `HelloWorld.java` (must match exactly!)

---

## 🚪 The Main Method

### Method Signature
```java
public static void main(String[] args) {
    // Program execution starts here
}
```

#### Breaking Down Each Part
- **`public`**: Accessible from anywhere
- **`static`**: Can be called without creating an object
- **`void`**: Returns no value
- **`main`**: Exact method name (case-sensitive!)
- **`String[] args`**: Command-line arguments array

### Alternative Main Signatures (Java 9+)
```java
// Alternative 1: Varargs
public static void main(String... args) {
    System.out.println("Varargs syntax");
}

// Alternative 2: Final parameter
public static void main(final String[] args) {
    System.out.println("Final parameter");
}
```

### Command-Line Arguments
```java
public class CommandLineArgs {
    public static void main(String[] args) {
        System.out.println("Number of arguments: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + i + ": " + args[i]);
        }
    }
}
```

**Running with arguments**:
```bash
$ java CommandLineArgs Hello World Java
# Output:
# Number of arguments: 3
# Argument 0: Hello
# Argument 1: World
# Argument 2: Java
```

---

## 📦 Package Management

### What are Packages?
Packages organize Java classes into namespaces, preventing naming conflicts and providing access control.

### Package Declaration
```java
// File: com/company/project/Main.java
package com.company.project;  // Must be first non-comment line

public class Main {
    // Class implementation
}
```

### Package Naming Conventions
- **Reverse domain name**: `com.google`, `org.apache`
- **All lowercase**: No uppercase letters
- **Dot-separated**: Hierarchical structure
- **Unique prefix**: Use organization's domain

### Directory Structure
```
project/
├── src/
│   └── com/
│       └── company/
│           └── project/
│               ├── Main.java
│               ├── utils/
│               │   └── StringHelper.java
│               └── model/
│                   └── User.java
```

### Package Access Levels
```java
package com.company.project;

public class AccessLevels {
    public String publicField;      // Accessible everywhere
    protected String protectedField; // Accessible in same package + subclasses
    String packageField;            // Accessible in same package only
    private String privateField;    // Accessible only in this class
}
```

### Import Statement
```java
import java.util.Scanner;              // Single class import
import java.util.*;                    // Wildcard import
import static java.lang.Math.PI;       // Static import

public class ImportExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Uses import
        double piValue = PI;                        // Uses static import
    }
}
```

---

## 🛣️ Classpath and Java Libraries

### Understanding Classpath

The classpath tells Java where to find compiled .class files and JAR files.

#### Setting Classpath
```bash
# Windows
java -cp ".;lib/*" MyClass

# Linux/macOS
java -cp ".:lib/*" MyClass

# Set environment variable
export CLASSPATH=".:lib/*"
```

#### Common Classpath Issues
```bash
# ERROR: Could not find or load main class
java MyClass  # No classpath set!

# CORRECT: Specify classpath
java -cp . MyClass
```

### Java Standard Library

#### Core Packages
- **`java.lang`**: Auto-imported (String, Object, System, etc.)
- **`java.util`**: Collections (List, Map, Set), utilities
- **`java.io`**: Input/Output operations
- **`java.nio`**: New I/O with better performance
- **`java.net`**: Networking (URL, Socket)

#### Example Usage
```java
// java.lang (auto-imported)
String text = "Hello";
System.out.println(text);

// java.util (explicit import needed)
import java.util.ArrayList;
import java.util.List;

List<String> list = new ArrayList<>();

// java.io (explicit import needed)
import java.io.File;

File file = new File("data.txt");
```

### External Libraries (JAR Files)

#### Adding JAR to Classpath
```bash
# Compile with external JAR
javac -cp ".:json-lib.jar" MyClass.java

# Run with external JAR
java -cp ".:json-lib.jar" MyClass
```

#### Creating JAR Files
```bash
# Create JAR from compiled classes
jar cvf MyLibrary.jar *.class

# Run with your JAR
java -cp ".:MyLibrary.jar" MyApp
```

---

## 🧪 Testing Your Understanding

### Exercise 1: Basic Program Structure
Create a program that demonstrates proper package structure and main method:

```java
package com.javastudent.basics;

public class ProgramStructure {
    public static void main(String[] args) {
        System.out.println("JVM Version: " + System.getProperty("java.version"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
        System.out.println("Operating System: " + System.getProperty("os.name"));
    }
}
```

**Compile and run**:
```bash
$ mkdir -p com/javastudent/basics
$ mv ProgramStructure.java com/javastudent/basics/
$ javac com/javastudent/basics/ProgramStructure.java
$ java -cp . com.javastudent.basics.ProgramStructure
```

### Exercise 2: Command-Line Arguments
Create a calculator that accepts operation from command line:

```java
public class ArgsCalculator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java ArgsCalculator <num1> <operation> <num2>");
            return;
        }

        double num1 = Double.parseDouble(args[0]);
        double num2 = Double.parseDouble(args[2]);
        String operation = args[1];

        double result = switch (operation) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Unknown operation: " + operation);
        };

        System.out.printf("%.2f %s %.2f = %.2f%n", num1, operation, num2, result);
    }
}
```

**Test it**:
```bash
$ java ArgsCalculator 15 + 7    # 15 + 7 = 22
$ java ArgsCalculator 10 * 3    # 10 * 3 = 30
```

---

## 🔍 Common Beginner Mistakes

### Mistake 1: Wrong Main Method
**Wrong**:
```java
public class WrongMain {
    public static void main() {  // Missing String[] args
        System.out.println("Hello!");
    }
}
```

**Correct**:
```java
public class WrongMain {
    public static void main(String[] args) {  // Parameters required
        System.out.println("Hello!");
    }
}
```

### Mistake 2: Wrong Class/File Names
**Wrong**: Class `Hello` in file `helloworld.java` (case mismatch!)
**Correct**: Class `Hello` in file `Hello.java`

### Mistake 3: Missing Package Declaration
**Wrong**:
```java
// File in com/company/utils/StringHelper.java
public class StringHelper {  // ERROR: No package declaration
}
```

**Correct**:
```java
package com.company.utils;  // Required at top

public class StringHelper {
}
```

---

## 📚 Terminology Summary

### Key Terms
- **Bytecode**: Platform-independent compiled Java code (.class files)
- **JIT Compiler**: Just-In-Time compiler that improves runtime performance
- **Garbage Collector**: Automatic memory management system
- **Classpath**: Path where JVM looks for class files and JARs
- **JAR**: Java Archive file format for packaging libraries

### Important Concepts
- **Object-Oriented**: Java is purely object-oriented (except primitives)
- **Strongly Typed**: Variables must have declared types
- **Platform Independent**: "Write once, run anywhere" philosophy
- **Automatic Memory Management**: No manual memory allocation/deallocation

---

## 🚀 Next Steps

### Level 0 Completion Checklist
- [ ] Understand JVM, JRE, and JDK differences
- [ ] Successfully compile and run Java programs
- [ ] Use correct main method signature
- [ ] Organize code in proper packages
- [ ] Set classpath for dependencies

### Moving to Level 1
You're now ready to learn about:
- **Variables and data types**
- **Classes and objects**
- **Methods and constructors**
- **Basic control structures**

**Continue to: [Build Tools Introduction](../documentation/Build_Tools.md)**

---

**🎯 Solid understanding of Java basics = strong foundation for advanced topics!**
