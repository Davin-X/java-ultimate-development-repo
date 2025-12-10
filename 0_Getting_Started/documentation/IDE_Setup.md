# IDE Setup Guide

This guide will help you install and configure popular Java IDEs for development. Choose one that best fits your needs and preferences.

## 📋 Table of Contents

- [Recommended IDEs](#recommended-ides)
- [IntelliJ IDEA Setup (Most Popular)](#intellij-idea-setup-most-popular)
- [Visual Studio Code Setup](#visual-studio-code-setup)
- [Eclipse Setup](#eclipse-setup)
- [NetBeans Setup](#netbeans-setup)
- [IDE Features Comparison](#ide-features-comparison)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Recommended IDEs

| IDE | Best For | Learning Curve | JDK Required | Size | Free |
|-----|----------|----------------|--------------|------|------|
| **IntelliJ IDEA Community** | **Professional Development** | Medium | Built-in | ~1GB | ✅ Yes |
| **VS Code + Extensions** | **Lightweight & Flexible** | Easy | Manual | ~300MB | ✅ Yes |
| **Eclipse** | **Open Source Tradition** | Steep | Manual | ~500MB | ✅ Yes |
| **NetBeans** | **Comprehensive Bundle** | Medium | Built-in | ~600MB | ✅ Yes |

---

## 🚀 IntelliJ IDEA Setup (Most Popular)

### Installation

#### Option 1: Official Installer (Recommended)
1. **Download from JetBrains:**
   - Visit: [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/)
   - Download for your OS (Windows/macOS/Linux)
   - Choose Community Edition (free)

2. **Run Installer:**
   - Windows: Double-click the `.exe` file
   - macOS: Double-click the `.dmg` file
   - Linux: Extract and run the script
   - Follow installation wizard

3. **First Launch:**
   - Accept terms, choose UI theme
   - Configure memory settings (optional)
   - Install any suggested plugins

#### Option 2: Toolbox App
```bash
# Download and install IntelliJ Toolbox
# Visit: https://www.jetbrains.com/toolbox-app/

# Use Toolbox to install IDEA Community
# Provides easy updates and version management
```

### JDK Configuration

IntelliJ usually detects JDK automatically, but you can configure it manually:

1. **Open IntelliJ IDEA**
2. **Navigate:** File → Project Structure (Ctrl+Alt+Shift+S)
3. **Go to:** Project SDK section
4. **Click Add JDK:**
   - Choose JDK
   - Navigate to your JDK installation directory
   - Usually: `/Library/Java/JavaVirtualMachines/` (macOS)
   - Usually: `C:\Program Files\Java\` (Windows)
   - Usually: `/usr/lib/jvm/` (Linux)

5. **Apply and OK**

### Creating Your First Project

1. **Launch IntelliJ IDEA**
2. **Click:** "New Project"
3. **Choose:** Java
4. **Name your project:** `HelloWorld`
5. **Choose JDK:** Select the JDK you configured
6. **Project Location:** Choose a folder
7. **Finish**

### Adding a Java Class

1. **Right-click on `src` folder**
2. **New → Java Class**
3. **Name:** `HelloWorld`
4. **Paste this code:**

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java! 🚀");
        System.out.println("IntelliJ IDEA is awesome!");
    }
}
```

5. **Right-click on file → Run `HelloWorld.main()`**

### Essential Shortcuts

#### Windows/Linux:
- `Ctrl + N`: New file
- `Ctrl + Space`: Auto-complete
- `Alt + Enter`: Quick fix
- `Ctrl + Shift + F10`: Run current file
- `Ctrl + F9`: Build project
- `Shift + F6`: Rename
- `Ctrl + Y`: Delete line

#### macOS:
- `Cmd + N`: New file
- `Ctrl + Space`: Auto-complete
- `Opt + Enter`: Quick fix
- `Ctrl + R`: Run current file
- `Cmd + F9`: Build project
- `Shift + F6`: Rename
- `Cmd + Y`: Delete line

---

## 🎨 Visual Studio Code Setup

### Installation

1. **Download VS Code:**
   - Visit: [Visual Studio Code](https://code.visualstudio.com/)
   - Download for your OS

2. **Install Extensions:**
   - Launch VS Code
   - Open Extensions sidebar (Ctrl/Cmd + Shift + X)
   - Search and install:
     - **Java Extension Pack** (Microsoft)
     - **Java Test Runner** (Microsoft)
     - **Maven for Java** (Microsoft)
     - **Gradle for Java** (Microsoft)

### JDK Configuration

1. **Install JDK** (if not already installed)
2. **Configure in VS Code:**
   - File → Preferences → Settings
   - Search: `java.home`
   - Set: `java.home: "path/to/your/jdk"`

### Creating First Project

1. **Open VS Code**
2. **File → Open Folder** (choose your project directory)
3. **Create Java file:** `HelloWorld.java`

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello from VS Code! 💡");
    }
}
```

4. **Run:** Right-click in file → "Run Java"

### Essential Extensions

- **Java Extension Pack**: Core Java support
- **Debugger for Java**: Integrated debugging
- **Java Test Runner**: JUnit integration
- **Lombok Annotations**: Code generation
- **GitLens**: Git integration

---

## 🔧 Eclipse Setup

### Installation

1. **Download Eclipse IDE for Java Developers:**
   - Visit: [Eclipse Downloads](https://www.eclipse.org/downloads/packages/)
   - Download "Eclipse IDE for Java Developers"

2. **Extract and Run:**
   - Extract the downloaded archive
   - Run the `eclipse` executable

### Workspace Setup

1. **Choose Workspace:** Select directory for projects
2. **Welcome Screen:** Close any welcome dialogs

### Create Project

1. **File → New → Java Project**
2. **Name:** `HelloWorld`
3. **Execution Environment:** Choose your JDK version
4. **Create**

### Add Java Class

1. **File → New → Class**
2. **Source folder:** `HelloWorld/src`
3. **Package:** Leave empty (default package)
4. **Name:** `HelloWorld`
5. **Check:** `public static void main(String[] args)`

### Run Application

1. **Right-click on class file**
2. **Run As → Java Application**

---

## 🏗️ NetBeans Setup

### Installation

1. **Download NetBeans:**
   - Visit: [NetBeans Downloads](https://netbeans.apache.org/download/)
   - Download installer for Java SE (smaller)

2. **Run Installer:** Follow setup wizard

### Creating Project

1. **File → New Project**
2. **Categories:** Java with Maven
3. **Projects:** Java Application
4. **Name:** MyJavaApp
5. **Finish**

### Add Code

1. **Expand project in Projects window**
2. **Right-click on source package**
3. **New → Java Class**
4. **Class Name:** `Main`

5. **Add main method:**

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from NetBeans! 🎉");
    }
}
```

6. **Run:** Right-click → Run File

---

## 📊 IDE Features Comparison

| Feature | IntelliJ IDEA | VS Code | Eclipse | NetBeans |
|---------|---------------|---------|---------|----------|
| **Java Completion** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Refactoring** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Debugging** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Git Integration** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Maven/Gradle** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Memory Usage** | ⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ |
| **Learning Curve** | ⭐⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Plugins/Ecosystem** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |

**Verdict:** 🏆 **IntelliJ IDEA Community** for most users (professional features, excellent Java support)

---

## 🔧 Troubleshooting

### Common Issues

#### 1. "Cannot find main class"
**Symptoms:** Class runs fine, but sometimes doesn't run
**Solutions:**
- **IntelliJ:** Check Run Configuration (Run → Edit Configurations)
- **VS Code:** Make sure Java is properly configured
- **Eclipse:** Right-click on containing folder → Run As

#### 2. Build Errors
**Symptoms:** Cannot compile Java files
**Solutions:**
- Check JDK is properly configured in IDE
- Clean and rebuild project
- Check Java version compatibility

#### 3. Memory Issues
**Solutions:**
- **IntelliJ:** Help → Change Memory Settings
- **VS Code:** Check Java configuration in settings
- **Run with custom memory:** `java -Xmx2g YourClass`

#### 4. Extension/Plugin Issues
**VS Code Solutions:**
- Reload window (Ctrl/Cmd + Shift + P → "Developer: Reload Window")
- Reinstall extensions
- Update VS Code to latest version

#### 5. Path Issues (Windows)
**Symptoms:** "java is not recognized"
**Solutions:**
- Reopen IDE as Administrator
- Add to PATH: `%JAVA_HOME%\bin`
- Use full paths in configurations

### Configuration Files

#### IntelliJ `.idea` folder
Contains project configuration - can be committed to Git (recommended)

#### VS Code `settings.json`
```json
{
    "java.home": "path/to/jdk",
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-17",
            "path": "path/to/jdk-17",
            "default": true
        }
    ]
}
```

#### IDE Virtual Machines
- **IntelliJ:** File → Project Structure → Project SDK → Edit...
- **Eclipse:** Window → Preferences → Java → Installed JREs
- **VS Code:** Settings → search "java.configuration.runtimes"

### Performance Tips

- **Disable unnecessary plugins/extensions**
- **Allocate sufficient memory** (especially for IntelliJ)
- **Use SSD** for better performance
- **Enable compiler daemon** where possible
- **Close unused projects**

---

## 🎯 Next Steps

**After IDE setup:**

1. **Create your first project** with the IDE
2. **Write and run simple programs** ("Hello World")
3. **Explore IDE features** (debugging, refactoring)
4. **Set up version control** (Git integration)
5. **Install additional plugins** as needed

---

**🚀 Ready for development? Choose your IDE and create your first Java program!**

> **Pro Tip:** Start with IntelliJ IDEA for the best Java development experience
