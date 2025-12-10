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

## Exercise 3: Switch Statements

**Objective:** Learn switch statements for multiple choice scenarios.

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

## Exercise 4: For Loops

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

## Exercise 5: While & Do-While Loops

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

## Exercise 6: Complete Control Flow Mastery

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
