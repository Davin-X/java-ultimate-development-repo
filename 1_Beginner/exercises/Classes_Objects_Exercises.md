# Classes & Objects - Practice Exercises

## Level 1 Beginner - Classes & Objects Exercises

**Master object-oriented programming fundamentals with these comprehensive exercises**

---

## Exercise 1: Class Design Fundamentals

**Objective:** Design and implement basic classes with proper encapsulation

```java
// EXERCISE 1.1: Basic Class Design
class Book {
    // TODO: Declare instance variables:
    // - title (String)
    // - author (String)
    // - pages (int)
    // - isAvailable (boolean)

    // TODO: Create constructor that takes all parameters

    // TODO: Create getter methods for all fields

    // TODO: Create setter methods (with validation where appropriate)

    // TODO: Add a displayInfo() method that prints book details
}

// Test the Book class
public class BookStoreDemo {
    public static void main(String[] args) {
        // Create a Book object
        // Set its properties
        // Display its information
    }
}
```

---

## Exercise 2: Constructors and Object Initialization

**Objective:** Practice different constructor patterns and object creation

```java
class Student {
    private String name;
    private int age;
    private String studentId;
    private double gpa;

    // Default constructor
    public Student() {
        this.name = "Unknown";
        this.age = 18;
        this.studentId = "PENDING";
        this.gpa = 0.0;
    }

    // Constructor with name and age
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.studentId = "PENDING";
        this.gpa = 0.0;
    }

    // EXERCISE 2.1: Constructor with all parameters
    // TODO: Implement a constructor that takes name, age, studentId, gpa
    //       Include validation for age (>=16, <=100) and GPA (>=0.0, <=4.0)

    // EXERCISE 2.2: toString() method
    // TODO: Override toString() to return a formatted student summary

    // Getters and setters for all fields
    public String getName() { return name; }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age >= 16 && age <= 100) {
            this.age = age;
        }
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) {
        if (studentId != null && studentId.length() >= 6) {
            this.studentId = studentId;
        }
    }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }
}

// EXERCISE 2.3: Constructor Testing
public class StudentManagement {
    public static void main(String[] args) {
        // Test different constructors
        Student student1 = new Student();
        Student student2 = new Student("Alice Johnson", 20);
        Student student3 = new Student("Bob Smith", 19, "ABC123", 3.8);

        // Display student information
        Student[] students = {student1, student2, student3};
        for (int i = 0; i < students.length; i++) {
            System.out.println("Student " + (i+1) + ": " + students[i]);
            // TODO: Test setter methods with valid and invalid data
        }

        // TODO: Demonstrate object references by creating two references to same object
    }
}
```

---

## Exercise 3: Method Overloading

**Objective:** Practice creating multiple methods with the same name but different parameters

```java
class Calculator {
    // EXERCISE 3.1: Add method overloads for different data types

    // TODO: Add two integers

    // TODO: Add two doubles

    // TODO: Add three integers

    // TODO: Add an array of integers (return the sum)

    // EXERCISE 3.2: Multiply method overloads

    // TODO: Multiply two integers

    // TODO: Multiply two doubles

    // TODO: Multiply three doubles

    // EXERCISE 3.3: Utility methods with overloading
    // TODO: Create displayResult methods for different result types
}

public class CalculatorDemo {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Test integer operations
        System.out.println("Addition (int): " + calc.add(5, 3));
        System.out.println("Addition (3 int): " + calc.add(2, 4, 6));
        System.out.println("Multiplication (int): " + calc.multiply(5, 3));

        // Test double operations
        System.out.println("Addition (double): " + calc.add(5.5, 3.7));
        System.out.println("Multiplication (double): " + calc.multiply(5.0, 2.5));

        // Test array operation
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Array sum: " + calc.add(numbers));

        // Test display methods
        calc.displayResult(calc.add(10, 20));
        calc.displayResult(calc.add(10.5, 20.7));
    }
}
```

---

## Exercise 4: Object Interactions

**Objective:** Practice having objects work together and maintain relationships

```java
class Engine {
    private String type;
    private int horsepower;
    private boolean isRunning;

    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Engine " + type + " started");
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Engine " + type + " stopped");
        }
    }

    public boolean isRunning() { return isRunning; }
    public String getType() { return type; }
    public int getHorsepower() { return horsepower; }
}

class Car {
    private String make;
    private String model;
    private int year;
    private Engine engine;  // Car has an Engine (composition)

    // EXERCISE 4.1: Constructor with Engine parameter
    // TODO: Implement constructor that takes make, model, year, and Engine

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        // TODO: Create a default Engine (e.g., "Gasoline", 150 HP)
    }

    // EXERCISE 4.2: Method delegation
    // TODO: Implement start(), stop(), and isEngineRunning() methods
    //       These should delegate to the Engine methods

    // EXERCISE 4.3: Object state methods
    // TODO: Implement getCarInfo() that returns formatted car information
    //       with engine details

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public Engine getEngine() { return engine; }
}

// EXERCISE 4.4: Object Interaction Demo
public class CarDemo {
    public static void main(String[] args) {
        // Create engines
        Engine gasEngine = new Engine("Gasoline", 180);
        Engine electricEngine = new Engine("Electric", 250);

        // Create cars
        Car sedan = new Car("Toyota", "Camry", 2022);
        Car electric = new Car("Tesla", "Model 3", 2023);

        // TODO: Install the appropriate engines in each car

        // Test car operations
        Car[] cars = {sedan, electric};
        for (Car car : cars) {
            System.out.println(car.getCarInfo());
            System.out.println("Engine running? " + car.isEngineRunning());

            car.start();
            System.out.println("After start - Engine running? " + car.isEngineRunning());

            car.stop();
            System.out.println("After stop - Engine running? " + car.isEngineRunning());
            System.out.println();
        }

        // TODO: Demonstrate what happens when you try to modify engine after installing it
    }
}
```

---

## Exercise 5: Static Members

**Objective:** Practice static fields and methods for class-level functionality

```java
class BankAccount {
    // Static fields (shared by all accounts)
    private static int totalAccounts = 0;
    private static double totalBalance = 0.0;
    private static final double INTEREST_RATE = 0.025; // 2.5%

    // Instance fields
    private String accountNumber;
    private String ownerName;
    private double balance;

    // EXERCISE 5.1: Constructors and static counters
    // TODO: Implement constructor that takes accountNumber and ownerName
    //       Initialize balance to 0 and increment totalAccounts

    // EXERCISE 5.2: Static methods for bank operations
    // TODO: Implement deposit() and withdraw() methods
    //       Update totalBalance when individual balances change

    // TODO: Implement getAccountCount() static method to return totalAccounts

    // TODO: Implement getTotalBankBalance() static method to return totalBalance

    // EXERCISE 5.3: Static utility methods
    // TODO: Implement static method to apply interest to an account

    // TODO: Implement static method to transfer money between accounts

    // Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    public static double getInterestRate() { return INTEREST_RATE; }

    // Private balance setters (hidden from public interface)
    private void setBalance(double balance) {
        totalBalance -= this.balance; // Remove old balance from total
        this.balance = balance;
        totalBalance += this.balance; // Add new balance to total
    }
}

// EXERCISE 5.4: Banking System Demo
public class BankDemo {
    public static void main(String[] args) {
        System.out.println("=== BANKING SYSTEM DEMO ===");

        // Create accounts
        BankAccount account1 = new BankAccount("12345", "Alice Smith");
        BankAccount account2 = new BankAccount("67890", "Bob Johnson");

        // Display initial bank statistics
        System.out.println("Initial bank status:");
        // TODO: Print total accounts and total balance

        // Perform transactions
        account1.deposit(1000);
        account2.deposit(500);

        // TODO: Apply interest to accounts

        // TODO: Transfer money between accounts

        System.out.println("\\nFinal account details:");
        System.out.println("Account 1: $" + account1.getBalance());
        System.out.println("Account 2: $" + account2.getBalance());

        System.out.println("\\nFinal bank status:");
        // TODO: Print final bank statistics

        // Demonstrate static rate access
        System.out.println("\\nInterest rate: " + BankAccount.getInterestRate() + "%");
    }
}
```

---

## Exercise 6: Complete OOP Mastery

**Objective:** Combine all OOP concepts in a student management system

```java
// EXERCISE 6.1: Course class
class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private static int totalCourses = 0;

    // TODO: Implement constructor, getters, setters
    // TODO: Static methods for course counting
    // TODO: Override toString()
}

// EXERCISE 6.2: StudentRegistration class
class StudentRegistration {
    private Student student;
    private ArrayList<Course> enrolledCourses;

    // TODO: Implement full enrollment system
    // TODO: Add/drop courses functionality
    // TODO: Calculate total credits
    // TODO: Print enrollment summary

    // TODO: Demonstrate method overloading for registration
    // TODO: Use static methods for system-wide statistics
}

// EXERCISE 6.3: Complete University System
public class UniversityDemo {
    public static void main(String[] args) {
        System.out.println("=== UNIVERSITY MANAGEMENT SYSTEM ===");

        // Create students
        // Create courses
        // Register students for courses
        // Display reports and statistics

        // Demonstrate all OOP concepts in action
        // TODO: Show encapsulation, inheritance readiness, polymorphism expectations
        // TODO: Prove static members for system-wide data
        // TODO: Demonstrate proper object interactions
        // TODO: Show constructor overloading and method overloading
    }
}
```

---

## Solutions & Learning Guide

### Key OOP Concepts Reinforced:

1. **Classes**: Blueprints for creating objects
2. **Objects**: Instances of classes with their own data
3. **Encapsulation**: Private fields with public accessors
4. **Constructors**: Methods to initialize objects
5. **Getter/Setter Methods**: Controlled access to private fields
6. **Method Overloading**: Multiple methods with same name
7. **Static Members**: Class-level variables and methods

### Object-Oriented Principles Applied:

- **Abstraction**: Hiding implementation details (private fields)
- **Information Hiding**: Protecting internal state
- **Modularity**: Separating concerns across classes
- **Code Reusability**: Objects that can be instantiated multiple times
- **Maintainability**: Organized code that is easy to modify

### Common Mistakes to Avoid:

1. **Direct Field Access**: Always use getters/setters instead of public fields
2. **Missing Validation**: Validate all input in setters
3. **Incorrect Static Usage**: Use static only for shared data/methods
4. **Poor Encapsulation**: Don't expose internal implementation
5. **Method Overloading Confusion**: Parameter lists must differ clearly
6. **Object References**: Understand reference vs value assignment

### Best Practices:

- **Naming Convention**: Class names start with uppercase letters
- **Method Names**: Start with lowercase, use camelCase
- **Field Names**: Start with lowercase, use camelCase
- **Constant Names**: Use UPPER_CASE for final static fields
- **Comment Your Code**: Explain complex logic and business rules
- **Test Thoroughly**: Try edge cases like null values, invalid data

### Interview Preparation:

- **"Explain Encapsulation"**: Use door lock analogy
- **"When to use Static?"**: Shared behavior across all instances
- **"Constructor vs Method?"**: Initialization is constructor's job
- **"Why Getters/Setters?"**: Controlled access to private fields

**Master these patterns and you'll have a solid grasp of object-oriented programming fundamentals!**
