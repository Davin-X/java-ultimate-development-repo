# 0. Getting Started with Java Development

Welcome to your Java development journey! This section will help you set up your development environment and write your first Java programs.

## 🎯 Section Overview

| Topic | Duration | Priority |
|-------|----------|----------|
| [Java Installation](documentation/Java_Installation.md) | 1-2 hours | High |
| [IDE Setup](documentation/IDE_Setup.md) | 1-2 hours | High |
| [First Java Program](notebooks/01_First_Java_Program.ipynb) | 30-45 mins | High |
| [Java Development Basics](documentation/Java_Basics.md) | 2-3 hours | High |
| [Build Tools Introduction](documentation/Build_Tools.md) | 1-2 hours | Medium |

## 🚀 Quick Start Checklist

### System Requirements
- **Operating System**: Windows 10+, macOS 10.14+, Linux (Ubuntu 18.04+)
- **RAM**: Minimum 4GB, Recommended 8GB+
- **Disk Space**: 2GB free space
- **Internet**: Required for downloads

### 1. Install Java JDK
```bash
# Check if Java is already installed
java -version

# If not installed, download from:
# Windows: https://adoptium.net/temurin/releases/
# macOS: brew install openjdk@17
# Linux: sudo apt update && sudo apt install openjdk-17-jdk
```

### 2. Choose and Setup IDE
**Recommended IDEs:**
- **IntelliJ IDEA Community Edition** (Most popular for Java)
- **Visual Studio Code** with Java extensions
- **Eclipse IDE**
- **NetBeans**

### 3. Verify Installation
```bash
# Check Java version
java -version
javac -version

# Check JAVA_HOME environment variable
echo $JAVA_HOME

# Test compilation
javac HelloWorld.java
java HelloWorld
```

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
