# 0. Getting Started with Java

Set up your Java development environment and write your first programs.

## 🚀 Quick Setup (30 minutes)

### 1. Install Java 17+
```bash
# Download from: https://adoptium.net/temurin/releases/
# Or use package manager:
# macOS: brew install openjdk@17
# Ubuntu: sudo apt install openjdk-17-jdk
# Windows: Download installer from adoptium.net
```

### 2. Verify Installation
```bash
java -version    # Should show Java 17+
javac -version   # Compiler version
```

### 3. Choose Your IDE
- **IntelliJ IDEA Community** (Recommended for beginners)
- **VS Code** with Java extensions
- **Eclipse IDE**

## 📚 Learning Path

| Step | Topic | Time | Resource |
|------|--------|------|----------|
| 1 | Java Installation | 30 min | [Java_Installation.md](documentation/Java_Installation.md) |
| 2 | IDE Setup | 30 min | [IDE_Setup.md](documentation/IDE_Setup.md) |
| 3 | First Program | 15 min | [01_First_Java_Program.ipynb](notebooks/01_First_Java_Program.ipynb) |
| 4 | Java Basics | 1 hour | [Java_Basics.md](documentation/Java_Basics.md) |
| 5 | Build Tools | 45 min | [Build_Tools.md](documentation/Build_Tools.md) |

## 📓 Learning Content

### [📔 Java Installation Guide](documentation/Java_Installation.md)
- Step-by-step installation for Windows, macOS, and Linux
- Environment variable configuration
- Troubleshooting common issues
- Multiple JDK version management

### [📔 IDE Setup Guide](documentation/IDE_Setup.md)
- IntelliJ IDEA installation and configuration
- VS Code Java extensions setup
- Eclipse workspace setup
- Project structure best practices

### [📓 First Java Program](notebooks/01_First_Java_Program.ipynb)
- Hello World application
- Understanding main method
- Compilation and execution process
- Common compilation errors

### [📔 Java Development Basics](documentation/Java_Basics.md)
- JVM, JRE, and JDK concepts
- Java syntax basics
- Package structure
- Classpath understanding

### [📔 Build Tools Introduction](documentation/Build_Tools.md)
- Introduction to Maven and Gradle
- Project structure standards
- Dependency management
- Build lifecycle basics

## 🛠️ Practical Exercises

### Exercise 1: Hello World Variations
Create different versions of Hello World:
- Command-line arguments
- Reading user input
- Multiple classes
- Package structure

### Exercise 2: Basic Calculator
Build a simple calculator application:
- Basic arithmetic operations
- Input validation
- Error handling
- Modular design

## 🎯 By the End of This Section

You should be able to:
- ✅ Install and configure Java JDK on your system
- ✅ Set up a development IDE
- ✅ Write, compile, and run basic Java programs
- ✅ Understand Java project structure
- ✅ Use build tools for project management

## 🔍 Common Issues & Solutions

### Issue: "java is not recognized as an internal or external command"
**Solution:** Add Java bin directory to your system PATH
- Windows: Add `%JAVA_HOME%\bin` to PATH environment variable
- macOS/Linux: Add `$JAVA_HOME/bin` to PATH in your shell profile

### Issue: "Could not find or load main class"
**Solution:** Ensure correct class name and package structure
- Check file name matches class name
- Ensure package declaration is correct
- Verify classpath when running

### Issue: IDE not recognizing Java installation
**Solution:** Configure JDK in IDE settings
- IntelliJ: File → Project Structure → Project SDK
- VS Code: Settings → Java: Home
- Eclipse: Window → Preferences → Java → Installed JREs

## 📚 Additional Resources

### Official Documentation
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [OpenJDK Documentation](https://openjdk.java.net/)
- [Java SE Documentation](https://docs.oracle.com/en/java/javase/index.html)

### Community Resources
- [Stack Overflow Java](https://stackoverflow.com/questions/tagged/java)
- [Java subreddit](https://www.reddit.com/r/java/)
- [Baeldung Java Tutorials](https://www.baeldung.com/)

### Course Recommendations
- [Codecademy Java](https://www.codecademy.com/learn/learn-java)
- [freeCodeCamp Java](https://www.freecodecamp.org/learn/java/)
- [Udemy Java for Beginners](https://www.udemy.com/topic/java/)

## 🧪 Testing Your Setup

Create a test file `TestSetup.java`:

```java
public class TestSetup {
    public static void main(String[] args) {
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
        System.out.println("Java Home: " + System.getProperty("java.home"));
        System.out.println("OS Name: " + System.getProperty("os.name"));
        System.out.println("Setup complete! Ready for Java development.");
    }
}
```

Compile and run:
```bash
javac TestSetup.java
java TestSetup
```

## 🎉 Next Steps

Once you've completed this section:
1. **Navigate to Beginner Level**: `../1_Beginner/`
2. **Start learning OOP concepts**: Classes, objects, inheritance
3. **Practice daily**: Write code every day to build muscle memory
4. **Join communities**: Engage with other Java learners

---

**🚀 Ready to write your first Java program? Start with the [Java Installation Guide](documentation/Java_Installation.md)**
