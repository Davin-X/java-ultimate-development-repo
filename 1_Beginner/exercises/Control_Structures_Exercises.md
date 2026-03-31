# Control Structures - Practice Exercises

## Level 1 Beginner - Control Structures Exercises

**Master conditional statements and loops with these comprehensive exercises**

---

## Exercise 1: Basic Conditional Statements

**Objective:** Practice if-else statements for decision-making logic.

```java
// PART 1: Temperature Classification
public class TemperatureChecker {

    public static void main(String[] args) {

        // Test different temperatures
        int[] temperatures = {15, 25, 35, -5, 0, 30, 40};

        for (int temp : temperatures) {
            System.out.print("Temperature " + temp + "°C: ");

            // EXERCISE 1.1: Classify temperature using if-else
            if (temp < 0) {
                System.out.println("FREEZING!");
            } else if (temp >= 0 && temp < 20) {
                System.out.println("Cold");
            } else if (temp >= 20 && temp < 30) {
                System.out.println("Pleasant");
            } else if (temp >= 30 && temp < 40) {
                System.out.println("Hot");
            } else {
                System.out.println("EXTREME HEAT!");
            }
        }

        System.out.println("\\n--- PART 2: Boolean Logic ---");

        // EXERCISE 1.2: Complex conditions with boolean operators
        // Test different combinations
        boolean hasPermission = true;
        boolean isAdult = false;
        int accountBalance = 150;

        // Test scenario: Can user make purchase of $100?
        int purchaseAmount = 100;

        // Complete the condition
        if (hasPermission && isAdult && accountBalance >= purchaseAmount) {
            System.out.println("✅ Purchase approved!");
            accountBalance -= purchaseAmount;
        } else {
            System.out.println("❌ Purchase denied");
            if (!hasPermission) System.out.println("  Reason: No permission");
            if (!isAdult) System.out.println("  Reason: Must be adult");
            if (accountBalance < purchaseAmount) System.out.println("  Reason: Insufficient balance");
        }

        System.out.println("Remaining balance: $" + accountBalance);
    }
}
```

---

## Exercise 2: Nested If Statements

**Objective:** Practice nested conditional logic for complex decision trees.

```java
// PART 1: Academic Grade Calculator
public class GradeCalculator {

    public static void main(String[] args) {

        // Test different student scenarios
        Object[][] students = {
            {"Alice", 95, 88, 92, false},    // name, exam1, exam2, final, extraCredit
            {"Bob", 78, 82, 75, true},
            {"Charlie", 67, 73, 70, false},
            {"Diana", 89, 91, 85, true}
        };

        for (Object[] student : students) {
            String name = (String) student[0];
            int exam1 = (int) student[1];
            int exam2 = (int) student[2];
            int finalExam = (int) student[3];
            boolean hasExtraCredit = (boolean) student[4];

            // EXERCISE 2.1: Calculate final grade with weighted averages
            double weightedAverage = (exam1 * 0.25) + (exam2 * 0.25) + (finalExam * 0.5);

            // Add extra credit if applicable
            if (hasExtraCredit && weightedAverage < 90) {
                weightedAverage += 5;  // 5 point bonus
                if (weightedAverage > 100) {
                    weightedAverage = 100;  // Cap at 100%
                }
            }

            // Determine letter grade
            char letterGrade;

            // EXERCISE 2.2: Complete the nested if-else for letter grades
            if (weightedAverage >= 90) {
                if (weightedAverage >= 95) {
                    letterGrade = 'A';
                } else {
                    letterGrade = 'A-';
                }
            } else if (weightedAverage >= 80) {
                if (weightedAverage >= 87) {
                    letterGrade = 'B+';
                } else if (weightedAverage >= 83) {
                    letterGrade = 'B';
                } else {
                    letterGrade = 'B-';
                }
            } else if (weightedAverage >= 70) {
                letterGrade = 'C';
            } else if (weightedAverage >= 60) {
                letterGrade = 'D';
            } else {
                letterGrade = 'F';
            }

            System.out.printf("%s: %.1f%% → %s%n", name, weightedAverage, letterGrade);
        }
    }
}
```

---

## Exercise 3: Switch Statements (Traditional)

**Objective:** Learn traditional switch statements for multiple choice scenarios.

```java
// PART 1: Day of Week Calculator
public class DayOfWeekCalculator {

    public static void main(String[] args) {

        // Test different days (1 = Monday, 7 = Sunday)
        int[] testDays = {1, 3, 5, 7, 8, 0};

        for (int dayNumber : testDays) {
            System.out.print("Day " + dayNumber + ": ");

            // EXERCISE 3.1: Complete the switch statement for days of week
            switch (dayNumber) {
                case 1:
                    System.out.println("Monday - Start of work week");
                    break;
                case 2:
                    System.out.println("Tuesday - Meeting day");
                    break;
                // TODO: Add cases 3-7
                // Hint: Wednesday, Thursday, Friday, Saturday, Sunday
                default:
                    System.out.println("Invalid day number (use 1-7)");
                    break;
            }
        }

        System.out.println("\\n--- PART 2: Menu System ---");

        // EXERCISE 3.2: Create a simple menu system
        String[] menuChoices = {"coffee", "tea", "sandwich", "unknown"};

        for (String choice : menuChoices) {
            System.out.print("\\nOrder: " + choice + " → ");

            switch (choice.toLowerCase()) {
                case "coffee":
                    System.out.println("Hot coffee coming up! ($3.50)");
                    break;
                case "tea":
                    System.out.println("Selection: Green, Black, or Herbal? ($2.50)");
                    break;
                case "sandwich":
                    System.out.println("BLT or Club sandwich? ($8.75)");
                    break;
                default:
                    System.out.println("Sorry, item not available. Try coffee, tea, or sandwich.");
                    break;
            }
        }
    }
}
```

---

## Exercise 4: Modern Switch Case (Java 12+)

**Objective:** Master modern switch expressions with arrow syntax, switch expressions, and pattern matching.

### Part 1: Arrow Case Labels

```java
// EXERCISE 4.1: Convert traditional switch to modern arrow syntax
public class ModernDayCalculator {

    public static void main(String[] args) {
        int[] testDays = {1, 3, 5, 7, 8, 0};

        for (int dayNumber : testDays) {
            System.out.print("Day " + dayNumber + ": ");

            // TODO: Rewrite using modern arrow syntax (->)
            // Benefits: No break needed, no fall-through bugs
            String dayName = switch (dayNumber) {
                // case 1 -> "Monday - Start of work week";
                // Add remaining cases...
                // Hint: Use case 6, 7 -> "Weekend!" for multiple cases
                default -> "Invalid day number (use 1-7)";
            };
            System.out.println(dayName);
        }
    }
}
```

### Part 2: Switch Expressions with Enums

```java
// EXERCISE 4.2: Switch expressions with enums
enum Season {
    SPRING, SUMMER, FALL, WINTER
}

enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}

public class SeasonSwitchExercise {

    public static void main(String[] args) {
        
        // Part A: Get season description using switch expression
        Season currentSeason = Season.SUMMER;
        
        // TODO: Use switch expression to get description
        String description = switch (currentSeason) {
            // case SPRING -> "Flowers blooming, weather warming up";
            // Add remaining seasons...
            default -> "Unknown season";
        };
        System.out.println(currentSeason + ": " + description);
        
        // Part B: Get months for each season
        System.out.println("\\nMonths by Season:");
        for (Season season : Season.values()) {
            System.out.print(season + ": ");
            
            // TODO: Use switch expression with multiple case labels
            String months = switch (season) {
                // case SPRING -> "March, April, May";
                // Add remaining seasons...
                default -> "Unknown";
            };
            System.out.println(months);
        }
        
        // Part C: Get number of days in a month (simplified)
        Month testMonth = Month.FEBRUARY;
        
        // TODO: Use switch expression to get days
        int days = switch (testMonth) {
            // case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> 31;
            // case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
            // case FEBRUARY -> 28; // Simplified, ignore leap year
            default -> 0;
        };
        System.out.println("\\n" + testMonth + " has " + days + " days");
    }
}
```

### Part 3: Switch Expression with Blocks and yield

```java
// EXERCISE 4.3: Complex switch with blocks and yield
public class TrafficLightSimulator {

    enum LightColor {
        RED, YELLOW, GREEN
    }

    public static void main(String[] args) {
        
        LightColor[] testColors = {LightColor.RED, LightColor.YELLOW, LightColor.GREEN};
        
        for (LightColor color : testColors) {
            String action = switch (color) {
                case RED -> {
                    System.out.println("[" + color + "] Stopping all traffic...");
                    yield "STOP";
                }
                case YELLOW -> {
                    System.out.println("[" + color + "] Warning: light changing...");
                    yield "CAUTION";
                }
                case GREEN -> {
                    System.out.println("[" + color + "] Traffic flowing...");
                    yield "GO";
                }
            };
            System.out.println("Action: " + action + "\\n");
        }
    }
}
```

### Part 4: Pattern Matching for Switch (Java 17+)

```java
// EXERCISE 4.4: Pattern matching switch - type patterns
public class TypePatternMatchingExercise {

    public static void main(String[] args) {
        
        // Test with different object types
        Object[] testObjects = {"Hello World", 42, 3.14159, true, 100L, null};
        
        System.out.println("=== Type Pattern Matching ===");
        for (Object obj : testObjects) {
            String result = describeObject(obj);
            System.out.println(obj + " → " + result);
        }
    }
    
    // TODO: Implement pattern matching switch for type detection
    public static String describeObject(Object obj) {
        // Handle null case first
        if (obj == null) {
            return "null value";
        }
        
        // Use pattern matching switch (Java 17+)
        return switch (obj) {
            // case String s -> "String with length " + s.length();
            // case Integer i -> "Integer: " + i + (i > 0 ? " (positive)" : i < 0 ? " (negative)" : " (zero)");
            // case Double d -> "Double: " + d;
            // case Boolean b -> "Boolean: " + (b ? "true" : "false");
            // case Long l -> "Long: " + l;
            // default -> "Unknown type: " + obj.getClass().getSimpleName();
            case null -> "null";
            default -> "unknown";
        };
    }
}
```

### Part 5: Pattern Matching with Records (Java 16+)

```java
// EXERCISE 4.5: Records and sealed interfaces with pattern matching
// Define record types
record Employee(String name, int id, double salary) {}
record Manager(String name, int id, double salary, String department) extends Person {}
record Contractor(String name, int id, double hourlyRate, int hoursWorked) extends Person {}

// Sealed interface for exhaustive matching
sealed interface Person permits Employee, Manager, Contractor {}

public class PersonPayrollProcessor {

    public static void main(String[] args) {
        
        // Create different person types
        Person alice = new Employee("Alice", 1001, 75000.0);
        Person bob = new Manager("Bob", 1002, 95000.0, "Engineering");
        Person charlie = new Contractor("Charlie", 2001, 85.0, 160);
        
        Person[] people = {alice, bob, charlie};
        
        System.out.println("=== Payroll Processing ===");
        for (Person person : people) {
            double pay = calculatePay(person);
            System.out.println(getName(person) + ": $" + String.format("%.2f", pay));
        }
    }
    
    // TODO: Implement pattern matching to get name
    public static String getName(Person person) {
        return switch (person) {
            // Use pattern matching to extract name from each type
            // case Employee e -> e.name();
            // case Manager m -> m.name();
            // case Contractor c -> c.name();
            case null -> "Unknown";
            default -> "Unknown";
        };
    }
    
    // TODO: Implement pattern matching to calculate pay
    public static double calculatePay(Person person) {
        return switch (person) {
            // case Employee e -> e.salary();
            // case Manager m -> m.salary() * 1.1; // Managers get 10% bonus
            // case Contractor c -> c.hourlyRate() * c.hoursWorked();
            case null -> 0.0;
            default -> 0.0;
        };
    }
}
```

### Part 6: Traditional vs Modern Switch Comparison

```java
// EXERCISE 4.6: Refactor traditional switch to modern syntax
public class SwitchRefactoringExercise {

    public static void main(String[] args) {
        
        System.out.println("=== Grade Calculator Comparison ===");
        
        // Test scores
        int[] scores = {95, 82, 76, 65, 58};
        
        for (int score : scores) {
            System.out.println("Score: " + score);
            System.out.println("  Traditional: " + traditionalGrade(score));
            System.out.println("  Modern: " + modernGrade(score));
        }
    }
    
    // Traditional switch (pre-Java 12)
    public static String traditionalGrade(int score) {
        String grade;
        int gradeLevel = score / 10;
        
        switch (gradeLevel) {
            case 10:
            case 9:
                grade = "A";
                break;
            case 8:
                grade = "B";
                break;
            case 7:
                grade = "C";
                break;
            case 6:
                grade = "D";
                break;
            default:
                grade = "F";
                break;
        }
        return grade;
    }
    
    // TODO: Refactor to modern switch expression
    public static String modernGrade(int score) {
        int gradeLevel = score / 10;
        
        return switch (gradeLevel) {
            // Rewrite using modern arrow syntax
            // case 10, 9 -> "A";
            // Add remaining cases...
            default -> "F";
        };
    }
}
```

---

## Exercise 5: For Loops

**Objective:** Master for loops for counted iterations.

```java
// PART 1: Array Operations with Loops
public class ArrayForLoops {

    public static void main(String[] args) {

        // EXERCISE 4.1: Array Sum Calculator
        int[] numbers = {12, 45, 23, 67, 89, 34, 56, 78};
        int sum = 0;

        // Calculate sum using for loop
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }

        double average = (double) sum / numbers.length;
        System.out.println("Array Sum: " + sum);
        System.out.println("Array Average: " + String.format("%.2f", average));

        // EXERCISE 4.2: Find Maximum Value
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println("Maximum Value: " + max);

        // EXERCISE 4.3: Enhanced For Loop Practice
        System.out.print("\\nEven numbers in array: ");
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // EXERCISE 4.4: Nested Loops - Multiplication Table
        System.out.println("\\n--- MULTIPLICATION TABLE ---");

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.printf("%2d ", i * j);
            }
            System.out.println();
        }

        // EXERCISE 4.5: Loop with Break/Continue
        System.out.println("\\n--- BREAK/CONTINUE DEMO ---");

        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                System.out.println("Skipping number 3 (continue)");
                continue;
            }
            if (i == 7) {
                System.out.println("Breaking at number 7");
                break;
            }
            System.out.println("Processing number: " + i);
        }
    }
}
```

---

## Exercise 6: While & Do-While Loops

**Objective:** Practice loops that execute based on conditions rather than counts.

```java
// PART 1: User Input Simulation
public class WhileLoops {

    public static void main(String[] args) {

        // EXERCISE 5.1: Password Validation
        String correctPassword = "java123";
        int attempts = 0;
        int maxAttempts = 3;

        // Simulate password checking
        String[] attemptedPasswords = {"wrong1", "java123", "wrong2", "java123"};

        for (String attempt : attemptedPasswords) {
            attempts++;
            System.out.println("Attempt " + attempts + ": " + attempt);

            // Check password
            if (attempt.equals(correctPassword)) {
                System.out.println("✅ Access granted!");
                break;
            } else {
                System.out.println("❌ Incorrect password");

                if (attempts >= maxAttempts) {
                    System.out.println("⚠️  Too many attempts! Account locked.");
                    break;
                }
            }
        }

        System.out.println("\\n--- PART 2: Number Guessing Game ---");

        // EXERCISE 5.2: Guess the number game simulation
        int targetNumber = 42;
        int guess;
        int guessCount = 0;

        // Simulated guesses
        int[] guesses = {15, 30, 50, 42};

        // Use while loop for guessing
        int index = 0;
        while (index < guesses.length) {
            guess = guesses[index];
            guessCount++;

            System.out.print("Guess #" + guessCount + ": " + guess);

            if (guess < targetNumber) {
                System.out.println(" - Too low!");
            } else if (guess > targetNumber) {
                System.out.println(" - Too high!");
            } else {
                System.out.println(" - Correct! 🎉");
                break;
            }

            index++;
        }

        // EXERCISE 5.3: Do-While Loop Guarantee
        System.out.println("\\n--- DO-WHILE vs WHILE ---");

        int counter = 10;

        System.out.println("Do-While (executes at least once):");
        do {
            System.out.println("Counter: " + counter);
            counter--;
        } while (counter > 10);  // Condition is false initially

        System.out.println("\\nWhile (may never execute):");
        counter = 10;
        while (counter > 10) {  // Condition is false - loop never runs
            System.out.println("This will never print: " + counter);
            counter--;
        }

        System.out.println("While loop completed without execution (condition was false)");
    }
}
```

---

## Exercise 7: Complete Control Flow Mastery

**Objective:** Combine all control structures in complex algorithms.

```java
public class ControlFlowMastery {

    // EXERCISE 6.1: Simple Calculator
    public static void simpleCalculator() {
        System.out.println("=== SIMPLE CALCULATOR ===");

        double a = 15.5, b = 4.2;
        char operation = '+';  // Try: +, -, *, /

        // Use switch for operation selection
        switch (operation) {
            case '+':
                System.out.printf("%.1f + %.1f = %.1f\\n", a, b, a + b);
                break;
            case '-':
                System.out.printf("%.1f - %.1f = %.1f\\n", a, b, a - b);
                break;
            case '*':
                System.out.printf("%.1f * %.1f = %.1f\\n", a, b, a * b);
                break;
            case '/':
                if (b != 0) {
                    System.out.printf("%.1f / %.1f = %.1f\\n", a, b, a / b);
                } else {
                    System.out.println("Cannot divide by zero!");
                }
                break;
            default:
                System.out.println("Invalid operation");
        }
    }

    // EXERCISE 6.2: Number Classification
    public static void classifyNumbers() {
        System.out.println("\\n=== NUMBER CLASSIFICATION ===");

        int[] testNumbers = {7, 12, 15, 20, 0, -5, 23};

        for (int num : testNumbers) {
            System.out.print("Number: " + num + " - ");

            // Even/Odd check
            if (num % 2 == 0) {
                System.out.print("Even");
            } else {
                System.out.print("Odd");
            }

            // Positive/Negative/Zero
            if (num > 0) {
                System.out.print(", Positive");
            } else if (num < 0) {
                System.out.print(", Negative");
            } else {
                System.out.print(", Zero");
            }

            // Prime check (for positive numbers)
            if (num > 1) {
                boolean isPrime = true;
                for (int i = 2; i <= Math.sqrt(num); i++) {
                    if (num % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                System.out.print(", " + (isPrime ? "Prime" : "Not Prime"));
            } else if (num == 1) {
                System.out.print(", Not Prime");
            }

            System.out.println();
        }
    }

    // EXERCISE 6.3: Grade Analyzer
    public static void gradeAnalyzer() {
        System.out.println("\\n=== GRADE ANALYZER ===");

        // Sample class grades
        int[] grades = {95, 78, 82, 67, 91, 85, 73, 88, 90, 76};

        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeF = 0;
        int sum = 0, max = grades[0], min = grades[0];

        // Analyze grades with loop
        for (int grade : grades) {
            sum += grade;

            // Update max/min
            if (grade > max) max = grade;
            if (grade < min) min = grade;

            // Count by letter grade
            if (grade >= 90) gradeA++;
            else if (grade >= 80) gradeB++;
            else if (grade >= 70) gradeC++;
            else if (grade >= 60) gradeD++;
            else gradeF++;
        }

        double average = (double) sum / grades.length;

        System.out.println("Class Statistics:");
        System.out.println("Highest Grade: " + max);
        System.out.println("Lowest Grade: " + min);
        System.out.printf("Average Grade: %.1f\\n", average);
        System.out.println("Grade Distribution:");
        System.out.println("  A (90-100): " + gradeA + " students");
        System.out.println("  B (80-89):  " + gradeB + " students");
        System.out.println("  C (70-79):  " + gradeC + " students");
        System.out.println("  D (60-69):  " + gradeD + " students");
        System.out.println("  F (<60):    " + gradeF + " students");
    }

    public static void main(String[] args) {
        simpleCalculator();
        classifyNumbers();
        gradeAnalyzer();

        System.out.println("\\n🎉 CONTROL STRUCTURES MASTERY ACHIEVED!");
        System.out.println("You can now control program flow like a pro!");
    }
}
```

---

## Solutions Guide

### Exercise Solve Order:
1. **Start Simple**: Basic if-else with single conditions
2. **Add Complexity**: Nested if statements and boolean logic
3. **Practice Patterns**: Switch statements for menu systems
4. **Master Loops**: For-loops for counted iterations
5. **Condition-Based**: While loops for dynamic behavior
6. **Combine All**: Complex programs using all control structures

### Common Mistakes to Avoid:
1. **Missing braces** in if-else blocks
2. **Infinite loops** without proper exit conditions
3. **Off-by-one errors** in loop bounds
4. **Wrong comparison operators** (== vs =, > vs >=)
5. **Missing break statements** in switch cases
6. **Null pointer exceptions** with string comparisons

### Debugging Tips:
- Always test boundary conditions (0, negative, max values)
- Print intermediate values inside loops
- Use descriptive variable names
- Comment complex nested logic
- Test with small input sets first

### Key Takeaways:
- **Conditionals**: Choose appropriate if-else, switch based on complexity
- **Loops**: For loops for known iterations, while loops for conditions
- **Break/Continue**: Control loop flow when needed
- **Nesting**: Use proper indentation and comments for readability
- **Logic**: Use De Morgan's laws to simplify boolean expressions</result>
</write_to_file>
