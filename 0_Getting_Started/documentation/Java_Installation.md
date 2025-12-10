# Java JDK Installation Guide

This comprehensive guide will help you install Java Development Kit (JDK) on your system and set up your development environment.

## 📋 Table of Contents

- [System Requirements](#system-requirements)
- [Installation Options](#installation-options)
- [Windows Installation](#windows-installation)
- [macOS Installation](#macos-installation)
- [Linux Installation](#linux-installation)
- [Environment Variables](#environment-variables)
- [Verification](#verification)
- [Troubleshooting](#troubleshooting)

## 🖥️ System Requirements

| Component | Minimum | Recommended |
|-----------|---------|-------------|
| Operating System | Windows 7, macOS 10.12, Ubuntu 16.04 | Windows 10/11, macOS 12+, Ubuntu 18.04+ |
| RAM | 2GB | 4GB+ |
| Disk Space | 1GB | 2GB+ |
| Architecture | x64 | x64 |

## 🎯 Installation Options

### Option 1: Oracle JDK (Commercial)
- **Pros**: Official Oracle distribution, latest features
- **Cons**: Requires license for commercial use, frequent releases
- **Link**: [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)

### Option 2: OpenJDK (Recommended)
- **Pros**: Open source, free for all uses, LTS versions stable
- **Cons**: Slightly different in minor aspects
- **Link**: [Adoptium (Eclipse Temurin)](https://adoptium.net/)

### Option 3: Package Managers
- **Pros**: Easy updates, automatic dependency management
- **Cons**: May not have latest versions
- **Options**: Homebrew (macOS/Linux), Chocolatey (Windows), apt/yum (Linux)

## 🪟 Windows Installation

### Method 1: Adoptium (Recommended)

1. **Download Java 17/21**:
   - Visit: [Adoptium Temurin Downloads](https://adoptium.net/temurin/releases/)
   - Select "Windows x64 MSI Installer"
   - Choose JDK (not JRE)

2. **Run Installer**:
   - Double-click the downloaded `.msi` file
   - Follow installation wizard
   - Accept default location (recommended)

3. **Set Environment Variables**:
   - Press `Win + R`, type `sysdm.cpl`, press Enter
   - Go to "Advanced" tab → "Environment Variables"
   - Under "System variables", click "New":
     - Variable name: `JAVA_HOME`
     - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x.x-hotspot\`
   - Find "Path" variable in System variables, click "Edit"
   - Add: `%JAVA_HOME%\bin`

### Method 2: Chocolatey (Automated)

```powershell
# Open PowerShell as Administrator
choco install openjdk17

# Refresh environment variables
refreshenv
```

## 🍎 macOS Installation

### Method 1: Homebrew (Recommended)

1. **Install Homebrew** (if not installed):
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```

2. **Install Java**:
   ```bash
   # Install OpenJDK 17 (LTS)
   brew install openjdk@17

   # Optional: Install latest JDK
   brew install openjdk
   ```

3. **Link Java**:
   ```bash
   # For OpenJDK 17
   sudo ln -sfn /usr/local/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk

   # For latest OpenJDK
   sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
   ```

4. **Verify installation**:
   ```bash
   java --version
   ```

### Method 2: Manual Download

1. **Download from Adoptium**:
   - Visit: [Adoptium Temurin Downloads](https://adoptium.net/temurin/releases/)
   - Select "macOS x64 PKG Installer"
   - Run the installer package

2. **Set Environment Variable** (Optional):
   ```bash
   # Add to ~/.zshrc or ~/.bash_profile
   export JAVA_HOME=$(/usr/libexec/java_home)
   ```

## 🐧 Linux Installation

### Ubuntu/Debian

```bash
# Update package list
sudo apt update

# Install OpenJDK 17 (LTS)
sudo apt install openjdk-17-jdk

# Optional: Install OpenJDK 21 (Latest LTS)
sudo apt install openjdk-21-jdk

# Verify installation
java --version
javac --version
```

### Fedora/CentOS/RHEL

```bash
# Fedora
sudo dnf install java-17-openjdk-devel

# CentOS/RHEL 8+
sudo dnf install java-17-openjdk-devel

# CentOS/RHEL 7
sudo yum install java-1.8.0-openjdk-devel
```

### Arch Linux

```bash
# Install from community repository
sudo pacman -S jdk17-openjdk

# Verify
java --version
```

## 🌍 Environment Variables

### Windows

1. **Open System Properties**: `Win + R` → `sysdm.cpl`
2. **Environment Variables**: Go to "Advanced" tab → "Environment Variables"
3. **JAVA_HOME**: Add new system variable `JAVA_HOME` with path to JDK
4. **PATH**: Add `%JAVA_HOME%\bin` to existing PATH variable

### macOS/Linux

Add to `~/.bashrc`, `~/.zshrc`, or `~/.profile`:

```bash
# Java 17
export JAVA_HOME="/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home"

# Or use java_home command (macOS)
export JAVA_HOME=$(/usr/libexec/java_home)

# Add to PATH
export PATH="$JAVA_HOME/bin:$PATH"
```

Reload configuration:
```bash
# bash
source ~/.bashrc

# zsh
source ~/.zshrc
```

## ✅ Verification

### Basic Verification

```bash
# Check Java version
java --version

# Check compiler version
javac --version

# Check JAVA_HOME
echo $JAVA_HOME

# Test compilation
echo "public class Test { public static void main(String[] args) { System.out.println(\"Hello, Java!\"); }}" > Test.java
javac Test.java
java Test

# Clean up
rm Test.java Test.class
```

### Comprehensive System Information

Create `SystemInfo.java`:

```java
public class SystemInfo {
    public static void main(String[] args) {
        System.out.println("=== Java System Information ===");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
        System.out.println("Java Home: " + System.getProperty("java.home"));
        System.out.println("Java Specification Version: " + System.getProperty("java.specification.version"));
        System.out.println("OS Name: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));
        System.out.println("User Home: " + System.getProperty("user.home"));
        System.out.println("Classpath: " + System.getProperty("java.class.path"));
    }
}
```

Compile and run:
```bash
javac SystemInfo.java
java SystemInfo
```

### Expected Output
```
=== Java System Information ===
Java Version: 17.0.2
Java Vendor: Eclipse Adoptium
Java Home: /Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
Java Specification Version: 17
OS Name: Mac OS X
OS Version: 12.6
OS Architecture: x86_64
...
```

## 🔧 Troubleshooting

### Common Issues

#### 1. "java command not found"
- **Windows**: Reopen command prompt or restart computer after PATH update
- **macOS/Linux**: Reload shell with `source ~/.bashrc` or restart terminal
- **Check**: Run `echo $PATH` to verify Java bin directory is included

#### 2. "JAVA_HOME is not defined correctly"
- **Verify path**: Ensure JAVA_HOME points to JDK installation directory
- **No trailing slash**: Remove trailing slash from JAVA_HOME path
- **Restart applications**: IDEs need restart after environment changes

#### 3. "UnsupportedClassVersionError"
- **Cause**: Running code compiled with newer Java version on older JRE
- **Solution**: Ensure both compilation and runtime use compatible Java versions
- **Check**: `javac -version` and `java -version` should match major versions

#### 4. Multiple Java Versions
- **Windows**: Use `where java` to see all installations
- **macOS/Linux**: Use `which java` and `/usr/libexec/java_home -V` (macOS)
- **Solution**: Set JAVA_HOME to desired version, or use version managers

#### 5. IDE Issues
- **IntelliJ IDEA**: File → Project Structure → Project SDK
- **VS Code**: Settings → search "Java: Home"
- **Eclipse**: Window → Preferences → Java → Installed JREs

### Version Management

#### sdkman (macOS/Linux)
```bash
# Install sdkman
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install different Java versions
sdk install java 17.0.2-tem
sdk install java 11.0.14-tem

# Switch versions
sdk use java 17.0.2-tem

# List installed versions
sdk list java
```

#### jenv (macOS/Linux - Similar to pyenv)
```bash
# Install jenv
brew install jenv

# Add Java versions
jenv add /Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home/
jenv add /Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home/

# Set global version
jenv global 17.0.2

# Set local version (per directory)
jenv local 11.0.14
```

### Advanced Configuration

#### Memory Settings
```bash
# Check current memory
java -XX:+PrintFlagsFinal -version | grep -i -E "(MaxHeapSize|MaxNewSize)"

# Run with custom memory
java -Xmx2g -Xms512m YourApplication

# Permanent generation (Java 7 and earlier)
java -XX:MaxPermSize=256m YourApplication
```

#### Debugging JVM
```bash
# Enable verbose garbage collection
java -verbose:gc YourApplication

# Print JVM flags
java -XX:+PrintFlagsFinal YourApplication | grep -i heap

# Enable hotspot diagnostic
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly YourApplication
```

## 📚 Additional Resources

- [Official Java Installation Guide](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html)
- [Adoptium Documentation](https://adoptium.net/docs/)
- [Java Tutorials - Getting Started](https://docs.oracle.com/javase/tutorial/getStarted/index.html)
- [Stack Overflow: Java Environment Setup](https://stackoverflow.com/questions/tagged/java)

## 🎯 Next Steps

After successful Java installation:

1. **Choose an IDE**: Set up your development environment
2. **Write your first program**: Follow the notebook in this section
3. **Learn basic concepts**: Start with variables and data types
4. **Join communities**: Connect with other Java developers

---

**🚀 Installation complete? Proceed to [IDE Setup](IDE_Setup.md)**
