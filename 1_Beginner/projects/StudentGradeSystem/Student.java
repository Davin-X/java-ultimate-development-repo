// Level 1 Beginner Project - Complete Application
// Student Grade Management System
// Demonstrates: Classes & Objects, Variables & Data Types, Control Structures

public class Student {
    // Instance variables (fields) using different data types
    private String firstName; // String for text data
    private String lastName; // String
    private int studentId; // int for whole numbers
    private double exam1Score; // double for decimal grades
    private double exam2Score; // double
    private double finalExamScore; // double
    private boolean hasExtraCredit; // boolean for true/false

    // Constructor to create Student objects
    public Student(String firstName, String lastName, int studentId,
            double exam1Score, double exam2Score, double finalExamScore) {
        this.firstName = firstName; // Initialize fields
        this.lastName = lastName;
        this.studentId = studentId;
        this.exam1Score = exam1Score;
        this.exam2Score = exam2Score;
        this.finalExamScore = finalExamScore;
        this.hasExtraCredit = false; // Default to no extra credit
    }

    // Default constructor
    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.studentId = 0;
        this.exam1Score = 0.0;
        this.exam2Score = 0.0;
        this.finalExamScore = 0.0;
        this.hasExtraCredit = false;
    }

    // Getters - Methods to access private fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getExam1Score() {
        return exam1Score;
    }

    public double getExam2Score() {
        return exam2Score;
    }

    public double getFinalExamScore() {
        return finalExamScore;
    }

    public boolean getHasExtraCredit() {
        return hasExtraCredit;
    }

    // Setters - Methods to modify private fields safely
    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.trim().isEmpty()) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()) {
            this.lastName = lastName;
        }
    }

    public void setStudentId(int studentId) {
        if (studentId > 0) { // Validation
            this.studentId = studentId;
        }
    }

    public void setExam1Score(double score) {
        if (score >= 0 && score <= 100) { // Grade validation
            this.exam1Score = score;
        }
    }

    public void setExam2Score(double score) {
        if (score >= 0 && score <= 100) {
            this.exam2Score = score;
        }
    }

    public void setFinalExamScore(double score) {
        if (score >= 0 && score <= 100) {
            this.finalExamScore = score;
        }
    }

    public void setHasExtraCredit(boolean extraCredit) {
        this.hasExtraCredit = extraCredit;
    }

    // Business logic methods

    // Calculate weighted average using control structures
    public double calculateWeightedAverage() {
        // Weights: Exam1=25%, Exam2=25%, Final=50%
        double weightedAverage = (exam1Score * 0.25) + (exam2Score * 0.25) + (finalExamScore * 0.5);

        // Apply extra credit if available
        if (hasExtraCredit && weightedAverage < 90.0) {
            weightedAverage += 5.0; // 5 point bonus

            // Cap at 100%
            if (weightedAverage > 100.0) {
                weightedAverage = 100.0;
            }
        }

        return weightedAverage;
    }

    // Determine letter grade using control structures (if-else)
    public char getLetterGrade() {
        double average = calculateWeightedAverage();

        // Nested if-else for letter grade determination
        char letterGrade;
        if (average >= 90.0) {
            if (average >= 95.0) {
                letterGrade = 'A';
            } else {
                letterGrade = 'A';
            }
        } else if (average >= 80.0) {
            if (average >= 87.0) {
                letterGrade = 'B';
            } else if (average >= 83.0) {
                letterGrade = 'B';
            } else {
                letterGrade = 'B';
            }
        } else if (average >= 70.0) {
            letterGrade = 'C';
        } else if (average >= 60.0) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }

        return letterGrade;
    }

    // Get letter grade description
    public String getGradeDescription() {
        char grade = getLetterGrade();
        switch (grade) {
            case 'A':
                return "Excellent";
            case 'B':
                return "Good";
            case 'C':
                return "Satisfactory";
            case 'D':
                return "Passing";
            case 'F':
                return "Failing";
            default:
                return "Unknown";
        }
    }

    // Passing status using boolean logic
    public boolean isPassing() {
        return getLetterGrade() != 'F';
    }

    // Display student information
    @Override
    public String toString() {
        return String.format("Student: %s %s (ID: %d)%n" +
                "Exam Scores: %.1f, %.1f, %.1f%n" +
                "Weighted Average: %.1f%%%n" +
                "Letter Grade: %s (%s)%n" +
                "Status: %s%n" +
                "Extra Credit: %s%n",
                firstName, lastName, studentId,
                exam1Score, exam2Score, finalExamScore,
                calculateWeightedAverage(),
                getLetterGrade(), getGradeDescription(),
                isPassing() ? "PASSING" : "FAILING",
                hasExtraCredit ? "Yes" : "No");
    }

    // Get full name (method combining fields)
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
