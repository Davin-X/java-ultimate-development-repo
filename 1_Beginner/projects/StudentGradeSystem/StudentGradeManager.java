// Level 1 Beginner Project - Complete Application
// Student Grade Management System - Main Application
// Demonstrates: Arrays & Collections, I/O, Exception Handling, Control Structures

import java.util.ArrayList; // For ArrayList collection
import java.util.List; // For List interface
import java.util.Scanner; // For user input (I/O)
import java.util.InputMismatchException; // For exception handling

public class StudentGradeManager {
    // Use ArrayList to store Student objects (Collections)
    private List<Student> students;
    private Scanner scanner;

    // Constructor initializes our collection and I/O
    public StudentGradeManager() {
        students = new ArrayList<>(); // ArrayList implementation of List interface
        scanner = new Scanner(System.in); // Scanner for reading user input
    }

    // Main application loop using control structures
    public void runApplication() {
        System.out.println("🎓 STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("===================================");

        boolean running = true; // Control variable for while loop

        // Main application loop - runs until user chooses to exit
        while (running) {
            displayMainMenu(); // Display menu options

            int choice = getUserChoice(); // Get menu choice from user

            // Handle menu selection using switch statement
            switch (choice) {
                case 1: // Add new student
                    addNewStudent();
                    break;

                case 2: // Display all students
                    displayAllStudents();
                    break;

                case 3: // Search for student by ID
                    searchStudentById();
                    break;

                case 4: // Generate class report
                    generateClassReport();
                    break;

                case 5: // Exit application
                    running = false; // Set control variable to exit loop
                    break;

                default: // Invalid choice
                    System.out.println("❌ Invalid choice. Please select 1-5.");
            }
        }

        System.out.println("\\nThank you for using Student Grade Management System!");
        scanner.close(); // Close I/O resource
    }

    // Display main menu using simple output
    private void displayMainMenu() {
        System.out.println("\\n=== MAIN MENU ===");
        System.out.println("1. Add New Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Generate Class Report");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    // Get user choice with exception handling
    private int getUserChoice() {
        try {
            return scanner.nextInt(); // Try to read integer
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            return -1; // Return invalid choice to trigger default case
        } finally {
            scanner.nextLine(); // Always consume newline character
        }
    }

    // Add new student with complete data validation
    private void addNewStudent() {
        System.out.println("\\n--- ADD NEW STUDENT ---");

        try {
            // Get student information with validation

            // Name input with empty string check
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("❌ First name cannot be empty.");
                return;
            }

            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("❌ Last name cannot be empty.");
                return;
            }

            // Student ID input with exception handling
            int studentId = -1;
            do {
                try {
                    System.out.print("Enter student ID (positive number): ");
                    studentId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (studentId <= 0) {
                        System.out.println("❌ Student ID must be positive.");
                        continue;
                    }

                    // Check for duplicate ID
                    if (findStudentById(studentId) != null) {
                        System.out.println("❌ Student ID already exists.");
                        studentId = -1; // Reset to retry
                        continue;
                    }

                    break; // Valid ID entered and not duplicate

                } catch (InputMismatchException e) {
                    System.out.println("❌ Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear invalid input
                }
            } while (studentId <= 0);

            // Exam score inputs with range validation
            double exam1Score = getValidScore("Enter Exam 1 score (0-100): ");
            double exam2Score = getValidScore("Enter Exam 2 score (0-100): ");
            double finalExamScore = getValidScore("Enter Final Exam score (0-100): ");

            // Extra credit choice
            System.out.print("Does student have extra credit? (y/n): ");
            String extraCreditInput = scanner.nextLine().toLowerCase();
            boolean hasExtraCredit = extraCreditInput.startsWith("y");

            // Create and add student
            Student newStudent = new Student(firstName, lastName, studentId,
                    exam1Score, exam2Score, finalExamScore);
            newStudent.setHasExtraCredit(hasExtraCredit);

            // Add to collection using ArrayList methods
            students.add(newStudent);

            System.out.println("✅ Student added successfully!");
            System.out.println("Student details:");
            System.out.println(newStudent);

        } catch (Exception e) {
            System.out.println("❌ An error occurred while adding student: " + e.getMessage());
        }
    }

    // Helper method for score validation
    private double getValidScore(String prompt) {
        double score = -1.0;
        do {
            try {
                System.out.print(prompt);
                score = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                if (score < 0 || score > 100) {
                    System.out.println("❌ Score must be between 0 and 100.");
                    score = -1.0; // Reset for retry
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                score = -1.0; // Reset for retry
            }
        } while (score < 0);
        return score;
    }

    // Display all students using enhanced for loop
    private void displayAllStudents() {
        System.out.println("\\n--- ALL STUDENTS ---");

        if (students.isEmpty()) { // Check collection size
            System.out.println("📝 No students registered yet.");
            System.out.println("Start by adding a student from the main menu.");
        } else {
            System.out.println("📊 Registered Students: " + students.size());

            // Enhanced for loop to iterate through collection
            for (Student student : students) {
                System.out.println("\\n" + "=".repeat(50)); // Repeat string method
                System.out.println(student);
            }

            System.out.println("\\n" + "=".repeat(50));
        }
    }

    // Search student by ID using linear search
    private void searchStudentById() {
        System.out.println("\\n--- SEARCH STUDENT ---");

        if (students.isEmpty()) {
            System.out.println("📝 No students to search.");
            return;
        }

        try {
            System.out.print("Enter student ID to search: ");
            int searchId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Student foundStudent = findStudentById(searchId);

            if (foundStudent != null) {
                System.out.println("✅ Student found:");
                System.out.println(foundStudent);
            } else {
                System.out.println("❌ No student found with ID: " + searchId);
            }

        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    // Helper method to find student by ID
    private Student findStudentById(int studentId) {
        // Linear search through collection
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null; // Not found
    }

    // Generate comprehensive class report
    private void generateClassReport() {
        System.out.println("\\n=== CLASS GRADE REPORT ===");

        if (students.isEmpty()) {
            System.out.println("📝 No students to report on.");
            return;
        }

        // Report statistics variables
        int totalStudents = students.size();
        int passingStudents = 0;
        int studentsWithExtraCredit = 0;
        double highestScore = Double.MIN_VALUE;
        double lowestScore = Double.MAX_VALUE;
        double sumOfAverages = 0.0;

        // Count grade distribution using arrays
        int[] gradeCounts = new int[5]; // A, B, C, D, F

        // Process each student using enhanced for loop
        for (Student student : students) {
            double average = student.calculateWeightedAverage();
            char letterGrade = student.getLetterGrade();

            // Track statistics
            if (student.isPassing()) {
                passingStudents++;
            }

            if (student.getHasExtraCredit()) {
                studentsWithExtraCredit++;
            }

            if (average > highestScore)
                highestScore = average;
            if (average < lowestScore)
                lowestScore = average;

            sumOfAverages += average;

            // Count grade distribution
            switch (letterGrade) {
                case 'A':
                    gradeCounts[0]++;
                    break;
                case 'B':
                    gradeCounts[1]++;
                    break;
                case 'C':
                    gradeCounts[2]++;
                    break;
                case 'D':
                    gradeCounts[3]++;
                    break;
                case 'F':
                    gradeCounts[4]++;
                    break;
            }
        }

        // Calculate final statistics
        double classAverage = sumOfAverages / totalStudents;
        double passRate = (double) passingStudents / totalStudents * 100;

        // Display report using formatted output
        System.out.println("📊 CLASS STATISTICS:");
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Passing: " + passingStudents + " (" + String.format("%.1f", passRate) + "%)");
        System.out.println("With Extra Credit: " + studentsWithExtraCredit);
        System.out.println("Highest Score: " + String.format("%.1f%%", highestScore));
        System.out.println("Lowest Score: " + String.format("%.1f%%", lowestScore));
        System.out.println("Class Average: " + String.format("%.1f%%", classAverage));

        System.out.println("\\n📈 GRADE DISTRIBUTION:");
        System.out.println("A grades: " + gradeCounts[0]);
        System.out.println("B grades: " + gradeCounts[1]);
        System.out.println("C grades: " + gradeCounts[2]);
        System.out.println("D grades: " + gradeCounts[3]);
        System.out.println("F grades: " + gradeCounts[4]);

        // Performance assessment
        System.out.println("\\n🎯 CLASS PERFORMANCE:");
        if (passRate >= 90) {
            System.out.println("🏆 Excellent - Outstanding performance！");
        } else if (passRate >= 80) {
            System.out.println("👍 Good - Strong performance");
        } else if (passRate >= 70) {
            System.out.println("⚖️ Satisfactory - Meets expectations");
        } else {
            System.out.println("⚠️ Concerning - May need additional support");
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Create and run the application
        StudentGradeManager manager = new StudentGradeManager();
        manager.runApplication();
    }
}
