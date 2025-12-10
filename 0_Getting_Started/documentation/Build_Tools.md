# Build Tools Introduction

This guide introduces essential build tools for Java development: Maven and Gradle. These tools automate project building, dependency management, and project lifecycle.

## 📋 Table of Contents

- [Why Build Tools Matter](#why-build-tools-matter)
- [Maven vs Gradle Comparison](#maven-vs-gradle-comparison)
- [Getting Started with Maven](#getting-started-with-maven)
- [Getting Started with Gradle](#getting-started-with-gradle)
- [Project Structure](#project-structure)
- [Dependency Management](#dependency-management)
- [Build Lifecycle](#build-lifecycle)
- [IDE Integration](#ide-integration)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Why Build Tools Matter

### 🛠️ Problems Without Build Tools

```bash
# Manual compilation (prone to errors)
javac -cp lib/junit.jar:lib/mockito.jar src/main/java/com/example/**.java -d target/classes
java -cp lib/junit.jar:lib/mockito.jar:target/classes com.example.MyApp

# Manual JAR creation (inconsistent)
jar cvf MyApp.jar -C target/classes .

# Manual testing (hacky)
java -cp lib/junit.jar:lib/junit-console-standalone.jar:target/classes org.junit.platform.console.ConsoleLauncher --scan-class-path=target/classes
```

### ✅ Solutions with Build Tools

- **Automated compilation**: Single command builds entire project
- **Dependency management**: Automatic JAR downloads and classpath setup
- **Standard structure**: Consistent project organization
- **Testing framework**: Integrated unit testing execution
- **Packaging**: JAR/WAR generation with single commands
- **Multi-platform**: Works consistently across operating systems

---

## ⚖️ Maven vs Gradle Comparison

| Aspect | Maven | Gradle |
|--------|-------|--------|
| **Configuration** | XML (`pom.xml`) | Groovy/Kotlin DSL (`build.gradle`) |
| **Build Speed** | Good | Excellent (incremental, parallel) |
| **Learning Curve** | Gentle | Moderate |
| **IDE Support** | Excellent | Excellent |
| **Customization** | XML plugins | Direct code |
| **Community** | Mature, stable | Modern, growing |
| **Convention** | Strong conventions | Flexible conventions |
| **Performance** | Good for small projects | Superior for large projects |

**Recommendation:**
- **Maven**: Traditional projects, standard Java applications, libraries
- **Gradle**: Android apps, complex multi-language projects, large enterprise

---

## 🎯 Getting Started with Maven

### Installation

#### Option 1: Package Manager (Recommended)

```bash
# macOS with Homebrew
brew install maven

# Ubuntu/Debian
sudo apt update
sudo apt install maven

# CentOS/RHEL
sudo yum install maven

# Windows with Chocolatey
choco install maven
```

#### Option 2: Manual Download

1. **Download:**
   - Visit: [Maven Downloads](https://maven.apache.org/download.cgi)
   - Download `apache-maven-x.x.x-bin.zip`

2. **Extract:**
   - Extract to local directory (e.g., `C:\Programs\maven` or `/opt/maven`)

3. **Add to PATH:**
   - Windows: Add `%MAVEN_HOME%\bin` to PATH
   - Unix: Add `$MAVEN_HOME/bin` to PATH in shell profile

### Verify Installation

```bash
mvn --version

# Expected output:
# Apache Maven 3.9.4
# Maven home: /opt/homebrew/Cellar/maven/3.9.4/libexec
# Java version: 17.0.2, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/17.0.2/libexec
```

### Create First Maven Project

```bash
# Create basic Java project
mvn archetype:generate \
    -DgroupId=com.example \
    -DartifactId=my-first-app \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false
```

### Essential Maven Commands

```bash
cd my-first-app

# Compile source code
mvn compile

# Run tests
mvn test

# Package as JAR
mvn package

# Install to local repository
mvn install

# Clean build artifacts
mvn clean

# Generate project documentation
mvn site

# Show dependency tree
mvn dependency:tree

# Update dependencies
mvn dependency:go-offline
```

### Basic pom.xml Structure

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Project coordinates -->
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <!-- Project metadata -->
    <name>my-app</name>
    <description>My first Maven project</description>

    <!-- Java version configuration -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <!-- Dependencies -->
    <dependencies>
        <!-- JUnit for testing -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.9.2</version>
            <scope>test</scope>
        </dependency>

        <!-- JSON processing -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.2</version>
        </dependency>
    </dependencies>

    <!-- Build configuration -->
    <build>
        <plugins>
            <!-- Maven compiler plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>

            <!-- Maven Surefire plugin for tests -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## ⚡ Getting Started with Gradle

### Installation

#### Option 1: SDKMAN (macOS/Linux - Recommended)

```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Gradle
sdk install gradle

# Verify
gradle --version
```

#### Option 2: Manual Download

1. **Download:**
   - Visit: [Gradle Downloads](https://gradle.org/releases/)
   - Download `gradle-x.x.x-all.zip`

2. **Extract and configure:**
   - Extract to local directory
   - Add `gradle-x.x.x/bin` to PATH

#### Option 3: Package Managers

```bash
# macOS
brew install gradle

# Ubuntu
sudo apt install gradle

# Windows
choco install gradle
```

### Verify Installation

```bash
gradle --version

# Expected output:
# Gradle 8.3
# Build time: 2023-08-17 07:09:12 UTC
# Revision: 8f89700179
# Kotlin: 1.9.0
# Groovy: 3.0.17
# Ant: Apache Ant(TM) version 1.10.13
# JVM: 17.0.2 (Eclipse Adoptium 17.0.2+8)
```

### Create First Gradle Project

```bash
# Create basic Java project
mkdir my-gradle-app
cd my-gradle-app

# Initialize with Java application template
gradle init --type java-application --dsl groovy

# Or create build.gradle manually
```

### Essential Gradle Commands

```bash
# View available tasks
gradle tasks

# Build the project
gradle build

# Run the application
gradle run

# Run tests
gradle test

# Clean build artifacts
gradle clean

# Generate wrapper (recommended)
gradle wrapper

# Use wrapper (cross-platform)
./gradlew build  # Unix
gradlew.bat build # Windows
```

### Basic build.gradle (Groovy DSL)

```groovy
plugins {
    id 'java'
    id 'application'
    id 'jacoco'  // Code coverage
}

// Project metadata
group = 'com.example'
version = '1.0-SNAPSHOT'

// Java version configuration
java {
    sourceCompatibility = '17'
    targetCompatibility = '17'
}

// Application main class
application {
    mainClass = 'com.example.App'
}

// Repositories for dependencies
repositories {
    mavenCentral()
    google()  // For Android projects
    maven { url 'https://jitpack.io' } // GitHub dependencies
}

// Dependencies
dependencies {
    // Implementation dependencies (compile + runtime)
    implementation 'org.slf4j:slf4j-api:2.0.7'
    implementation 'ch.qos.logback:logback-classic:1.4.11'

    // Test dependencies (compile + runtime for tests only)
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.2'

    // Annotation processors (compile time only)
    annotationProcessor 'org.projectlombok:lombok:1.18.28'
    compileOnly 'org.projectlombok:lombok:1.18.28'

    // Runtime only dependencies
    runtimeOnly 'mysql:mysql-connector-java:8.0.33'
}

// Test configuration
test {
    useJUnitPlatform()
    finalizedBy jacocoTestReport  // Generate coverage after tests
}

// JaCoCo code coverage
jacocoTestReport {
    dependsOn test
    reports {
        xml.enabled true
        html.enabled true
    }
}

// Custom tasks
task fatJar(type: Jar) {
    manifest {
        attributes 'Main-Class': 'com.example.App'
    }
    from { configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) } }
    with jar
}

task printVersions {
    doLast {
        println \"Java version: ${System.getProperty('java.version')}\"
        println \"Gradle version: ${gradle.gradleVersion}\"
    }
}
```

---

## 🏗️ Project Structure

### Maven Standard Directory Layout

```
my-project/
├── pom.xml                    # Project configuration
├── src/
│   ├── main/                  # Application source
│   │   ├── java/              # Java source files
│   │   │   └── com/example/
│   │   │       └── App.java
│   │   ├── resources/         # Resources (config files, etc.)
│   │   └── webapp/            # Web application files
│   └── test/                  # Test source
│       ├── java/              # Test classes
│       │   └── com/example/
│       │       └── AppTest.java
│       └── resources/         # Test resources
├── target/                    # Build output (auto-generated)
│   ├── classes/               # Compiled classes
│   ├── test-classes/          # Compiled test classes
│   └── my-project-1.0.jar     # JAR file
└── README.md                  # Documentation
```

### Gradle Standard Directory Layout

```
my-project/
├── build.gradle              # Project configuration
├── settings.gradle           # Multi-module settings
├── gradlew & gradlew.bat     # Wrapper scripts
├── src/
│   ├── main/                 # Application source
│   │   ├── java/             # Java source files
│   │   │   └── com/example/
│   │   │       └── App.java
│   │   ├── resources/        # Resources
│   │   └── groovy/           # Groovy sources (if any)
│   └── test/                 # Test source
│       ├── java/             # Test classes
│       │   └── com/example/
│       │       └── AppTest.java
│       └── resources/        # Test resources
├── build/                    # Build output (auto-generated)
│   ├── classes/              # Compiled classes
│   ├── libs/                 # JAR files
│   ├── reports/              # Test and coverage reports
│   └── tmp/                  # Temporary files
└── .gradle/                  # Gradle cache
```

---

## 📦 Dependency Management

### Maven Dependencies

Common Maven coordinates format:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.1.2</version>
</dependency>
```

### Gradle Dependencies

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web:3.1.2'
}
```

### Popular Java Libraries

```xml
<!-- Spring Boot (Maven) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.1.2</version>
</dependency>
```

```groovy
// Spring Boot (Gradle)
implementation 'org.springframework.boot:spring-boot-starter-web:3.1.2'
```

```xml
<!-- Jackson JSON (Maven) -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>
```

```groovy
// Jackson JSON (Gradle)
implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
```

```xml
<!-- JUnit 5 (Maven) -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>
```

```groovy
// JUnit 5 (Gradle)
testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
```

---

## 🔄 Build Lifecycle

### Maven Lifecycle Phases

```
validate → compile → test → package → verify → install → deploy
```

Key phases:
- **validate**: Validate project structure
- **compile**: Compile source code
- **test**: Run unit tests
- **package**: Create JAR/WAR
- **install**: Install to local repo
- **deploy**: Deploy to remote repo

### Gradle Lifecycle Tasks

```
compileJava → processResources → classes → jar → assemble → build
```

Key tasks:
- **build**: Full project build
- **test**: Run tests
- **jar**: Create JAR
- **publish**: Publish artifacts

---

## 🖥️ IDE Integration

### IntelliJ IDEA

- **Maven**: File → New → Project from Existing Sources → Import Maven project
- **Gradle**: File → Open → build.gradle, or New → Project from Existing Sources

### Visual Studio Code

- **Maven**: Install "Maven for Java" extension
- **Gradle**: Install "Gradle for Java" extension
- Use Command Palette: "Java: Create Java Project"

### Eclipse

- **Maven**: File → Import → Maven → Existing Maven Projects
- **Gradle**: File → Import → Gradle → Existing Gradle Project

---

## 🔧 Troubleshooting

### Maven Issues

#### 1. "Could not transfer artifact"
```
[ERROR] Failed to execute goal on project: Could not resolve dependencies
```

**Solutions:**
```bash
# Clear local repository cache
mvn dependency:purge-local-repository

# Force update dependencies
mvn clean compile -U

# Check proxy settings in ~/.m2/settings.xml
```

#### 2. "Plugin execution not covered by lifecycle configuration"
**Solution:** Add plugin to build section in pom.xml

#### 3. "Failed to execute goal ... testCompile"
**Solution:** Check Java version compatibility, or skip tests temporarily:
```bash
mvn clean package -DskipTests
```

### Gradle Issues

#### 1. "Could not resolve ..."
```
Could not resolve all files for configuration ':compileClasspath'
```

**Solutions:**
```bash
# Clear Gradle cache
gradle clean
rm -rf ~/.gradle/caches/

# Refresh dependencies
gradle build --refresh-dependencies

# Use offline mode if needed (after initial download)
gradle build --offline
```

#### 2. "Compilation failed"
**Solution:** Check Java version, clean build:
```bash
gradle clean build
```

#### 3. Wrapper Issues
```bash
# Regenerate wrapper
gradle wrapper --gradle-version 8.3

# Make wrapper executable (Unix)
chmod +x gradlew
```

### Common Build Tool Issues

#### 1. Java Version Mismatch
```xml
<!-- Maven pom.xml -->
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

```groovy
// Gradle build.gradle
java {
    sourceCompatibility = '17'
    targetCompatibility = '17'
}
```

#### 2. Memory Issues
```bash
# Maven
mvn clean compile -Xmx1024m

# Gradle
gradle build -Xmx1024m
```

#### 3. Proxy Configuration

**Maven settings.xml (~/.m2/settings.xml):**
```xml
<proxies>
  <proxy>
    <id>my-proxy</id>
    <active>true</active>
    <protocol>http</protocol>
    <host>proxy.company.com</host>
    <port>8080</port>
  </proxy>
</proxies>
```

**Gradle build.gradle:**
```groovy
systemProp.http.proxyHost=proxy.company.com
systemProp.http.proxyPort=8080
```

---

## 🎯 Best Practices

### General Guidelines

1. **Use build tool wrapper**: `mvnw`/`mvnw.cmd` or `gradlew`
2. **Follow standard directory layout**: Don't reinvent structure
3. **Use semantic versioning**: Proper version management
4. **Keep dependencies updated**: Security and bug fixes
5. **Use repositories**: Leverage Maven Central/Gradle Plugin Portal

### Maven Best Practices

- **Use pom.xml inheritance**: Parent-child project relationships
- **Leverage plugin management**: Centralize plugin versions
- **Use profiles**: Environment-specific configurations
- **Minimize dependency scope**: Don't overuse provided/compile scope

### Gradle Best Practices

- **Use declarative syntax**: Prefer `dependencies` block over imperative
- **Leverage task configuration avoidance**: Use `TaskProvider` for performance
- **Use buildSrc or build scripts**: Avoid inline script logic in build.gradle
- **Enable build caching**: `org.gradle.caching=true`

---

## 📚 Next Steps

After mastering build tools:

1. **Choose your build tool**: Maven for traditional, Gradle for modern/flexible
2. **Set up your IDE integration**: Enable build tool support
3. **Create your first project**: Use archetype/init for quick start
4. **Add dependencies**: Start with JUnit for testing
5. **Practice the lifecycle**: Understand compile → test → package
6. **Explore advanced features**: Profiles (Maven), custom tasks (Gradle)

---

**🚀 Build tools transform Java development from manual processes to automated, reliable software engineering!**

> **Recommendation:** Start with Maven for its gentle learning curve, then explore Gradle's power and flexibility
