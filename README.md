# Java Learning Repository

A comprehensive Java learning resource with structured progression from beginner to advanced topics.

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://java.oracle.com/)
[![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)](https://spring.io/projects/spring-boot)

## Learning Path

| Level | Focus Area | Content |
|-------|------------|---------|
| **0 Getting Started** | Foundation & Setup | JDK setup, IDE configuration, build tools, first programs |
| **1 Beginner** | Core Java | OOP, control structures, collections, basic I/O |
| **2 Intermediate** | Professional Skills | Inheritance, generics, exceptions, advanced collections, I/O |
| **3 Advanced** | Senior Developer | Design patterns, multithreading, JVM internals |
| **4 Algorithms & Interview Prep** | Technical Excellence | Arrays/strings algorithms, system design |

## Repository Structure

```
java-learning-repo/
├── 0_Getting_Started/           # Foundation: JDK setup, IDE, build tools, first programs
│   ├── documentation/           # Setup guides (JDK, IDE, build tools)
│   └── notebooks/               # First Java program tutorial
├── 1_Beginner/                  # Core Java: OOP, control structures, collections
├── 2_Intermediate/              # Advanced: Inheritance, generics, exceptions, I/O
├── 3_Advanced/                  # Senior: Multithreading, design patterns, JVM
├── 4_Algorithms_Interview_Prep/ # Algorithms: Arrays/strings algorithms, competitive coding
├── examples/                    # Practical runnable applications
│   └── Calculator_Application/  # Interactive OOP calculator example
├── .github/workflows/           # CI/CD automation
└── README.md                    # This document
```

## Getting Started

### Prerequisites
- **Java 17+** (LTS version recommended)
- **Git** for version control
- **Gradle** or **Maven** for build management
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions

### Quick Setup
1. **Install Java 17+:**
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or [OpenJDK](https://openjdk.java.net/)
   - Set `JAVA_HOME` environment variable
   - Verify: `java -version` and `javac -version`

2. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/java-learning-repo.git
   cd java-learning-repo
   ```

3. **Choose your learning path:**
   - **Beginners**: Start with `0_Getting_Started/`
   - **Intermediates**: Jump to `1_Beginner/` for foundation
   - **Advanced**: Explore `3_Advanced/` for senior topics

### Recommended IDE Setup
- **VS Code**: Install "Extension Pack for Java" and "Gradle for Java"
- **IntelliJ IDEA**: Community Edition is free and recommended
- **Eclipse**: Install Eclipse IDE for Java Developers

## Content Types

### 📓 Notebooks (`.ipynb`)
- Interactive coding examples
- Output demonstrations
- Practice exercises with solutions
- Visual explanations with diagrams

### 📄 Documentation (`.md`)
- Detailed explanations
- Best practices and guidelines
- API references and architecture notes

### 💻 Code Examples
- Complete, runnable programs
- Test files with JUnit
- Configuration examples
- Design pattern implementations

### 🛠️ Projects
- Full-stack applications
- Console applications
- Web services
- Distributed systems examples

## Learning Outcomes

By completing this repository, you will be able to:

### Beginner Level
- ✅ Write, compile, and run Java programs
- ✅ Use primitive data types and operators
- ✅ Implement control structures (if-else, loops, switch)
- ✅ Create classes and objects with proper encapsulation
- ✅ Work with arrays and basic collections

### Intermediate Level
- ✅ Implement inheritance and polymorphism
- ✅ Handle exceptions properly
- ✅ Use generics and advanced collections
- ✅ Read/write files and handle I/O operations
- ✅ Write unit tests with JUnit

### Advanced Level
- ✅ Implement multithreading and concurrency
- ✅ Apply design patterns in real applications
- ✅ Understand JVM internals and performance tuning
- ✅ Build complete applications with proper architecture
- ✅ Debug and optimize Java applications

## Contributing

Contributions are welcome! If you find errors, have improvements, or want to add new content:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m 'Add amazing feature'`
4. Push to the branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Ready to start your Java journey? Let's begin with [Getting Started](0_Getting_Started/)**
