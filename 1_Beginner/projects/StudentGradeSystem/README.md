# 🎓 Student Grade Management System

**Level 1 Beginner Project - Complete Application**

This project demonstrates practical application of all Java Level 1 concepts in a complete, working grade management system.

## 🏗️ Project Overview

The Student Grade Management System allows you to:
- Add students with their exam scores
- Display all student information
- Search for students by ID
- Generate comprehensive class reports
- Calculate weighted averages and letter grades

## 💡 Java Concepts Demonstrated

### **Variables & Data Types**
- Primitive types: `int`, `double`, `boolean`
- Reference types: `String`, custom objects
- Type casting and conversion

### **Classes & Objects**
- Student class with encapsulation
- Getters and setters for data safety
- Constructors for object initialization

### **Control Structures**
- `if-else` for conditional logic
- `switch` statements for menu choices
- `for`/`while` loops for iteration

### **Arrays & Collections**
- `ArrayList<Student>` for storing objects
- `int[]` arrays for grade distribution
- Enhanced for-each loops

### **Exception Handling**
- `try-catch` blocks for input validation
- `InputMismatchException` handling
- User-friendly error messages

### **I/O Operations**
- `Scanner` for user input
- `System.out.println()` for output
- Resource management with `.close()`

### **Methods & Functions**
- Parameterized methods
- Return value handling
- Method decomposition and organization

---

## 🚀 How to Run

1. **Compile the Java files:**
   ```bash
   cd 1_Beginner/projects/StudentGradeSystem
   javac *.java
   ```

2. **Run the application:**
   ```bash
   java StudentGradeManager
   ```

3. **Use the menu system:**
   - Choose option 1 to add students
   - Choose option 2 to view all students
   - Choose option 3 to search by ID
   - Choose option 4 for class reports
   - Choose option 5 to exit

---

## 🎮 Sample Usage

```
🎓 STUDENT GRADE MANAGEMENT SYSTEM
===================================

=== MAIN MENU ===
1. Add New Student
2. Display All Students
3. Search Student by ID
4. Generate Class Report
5. Exit
Enter your choice (1-5): 1

--- ADD NEW STUDENT ----
Enter first name: John
Enter last name: Doe
Enter student ID (positive number): 1001
Enter Exam 1 score (0-100): 85.5
Enter Exam 2 score (0-100): 92.0
Enter Final Exam score (0-100): 87.5
Does student have extra credit? (y/n): y

✅ Student added successfully!
Student details:
Student: John Doe (ID: 1001)
Exam Scores: 85.5, 92.0, 87.5
Weighted Average: 91.2%
Letter Grade: A (Excellent)
Status: PASSING
Extra Credit: Yes
```

---

## 📊 Grading System

- **Weights:** Exam1 (25%), Exam2 (25%), Final (50%)
- **Letter Grade Scale:**
  - A: 90-100%
  - B: 80-89%
  - C: 70-79%
  - D: 60-69%
  - F: <60%
- **Extra Credit:** +5% bonus for students who qualify

---

## 🎯 Learning Objectives

After completing this project, you'll understand:
- How to structure a complete Java application
- Object-oriented design principles
- Input validation and error handling
- Collections for data storage
- User interface design (text-based menus)

---

## 🔧 Project Structure

```
StudentGradeSystem/
├── Student.java                 # Student class (data model)
├── StudentGradeManager.java     # Main application (business logic)
└── README.md                    # This file
```

- **Student.java:** Defines student data and behavior
- **StudentGradeManager.java:** Handles user interface and operations
- **README.md:** Project documentation

---

## 💡 Advanced Features

- **Data Validation:** Comprehensive input checking
- **Duplicate Prevention:** No duplicate student IDs
- **Statistics Generation:** Class performance reports
- **Error Recovery:** Graceful handling of invalid input
- **Resource Management:** Proper closing of I/O streams

---

## 🎓 Next Steps

After mastering this project, you can extend it with:
- File I/O to save/load student data
- More advanced sorting and searching
- GUI interface using JavaFX
- Database integration with JDBC
- Web interface with Spring Boot

---

## 🏆 Level 1 Beginner Capstone

This project represents the culmination of all Level 1 concepts. Successfully implementing and understanding this complete application means you have mastered:

✅ **Variables & Data Types** - Using all primitive and reference types
✅ **Control Structures** - Building logic with conditionals and loops
✅ **Classes & Objects** - Creating and using custom objects
✅ **Arrays & Collections** - Storing and manipulating data
✅ **Exception Handling** - Managing errors gracefully
✅ **I/O Operations** - Reading input and writing output
✅ **Methods & Functions** - Organizing code into reusable units

**You are now ready to advance to Level 2 Intermediate! 🚀**
