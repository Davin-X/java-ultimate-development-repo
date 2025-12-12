# Build Tools Introduction

Learn the basics of Maven and Gradle for Java project management.

## Why Build Tools?

Build tools automate:
- **Compilation** of Java source code
- **Dependency management** (downloading JAR files)
- **Testing** execution
- **Packaging** into JAR/WAR files

Without build tools, you'd manually manage hundreds of commands and dependencies.

## Maven vs Gradle

| Feature | Maven | Gradle |
|---------|-------|--------|
| **Config** | XML files | Groovy/Kotlin scripts |
| **Speed** | Good | Faster, parallel builds |
| **Learning** | Easier for beginners | More flexible |

**Start with Maven** - it's simpler and widely used.

## Maven Quick Start

### Install Maven
```bash
# macOS
brew install maven

# Ubuntu
sudo apt install maven

# Windows: Download from maven.apache.org
```

### Verify Installation
```bash
mvn --version
```

### Create Your First Project
```bash
mvn archetype:generate \
  -DgroupId=com.example \
  -DartifactId=hello-world \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false

cd hello-world
```

### Essential Commands
```bash
mvn compile      # Compile source code
mvn test         # Run unit tests
mvn package      # Create JAR file
mvn clean        # Remove build files
```

### Basic pom.xml
```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>hello-world</artifactId>
  <version>1.0</version>
  
  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
  
  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.9.2</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

## Gradle Quick Start

### Install Gradle
```bash
# macOS
brew install gradle

# Ubuntu  
sudo apt install gradle

# Windows: Download from gradle.org
```

### Verify Installation
```bash
gradle --version
```

### Create Your First Project
```bash
mkdir hello-gradle
cd hello-gradle
gradle init --type java-application --dsl groovy
```

### Essential Commands
```bash
gradle build     # Full build
gradle run       # Run application
gradle test      # Run tests
gradle clean     # Clean build files
```

### Basic build.gradle
```groovy
plugins {
    id 'java'
    id 'application'
}

group = 'com.example'
version = '1.0'

java {
    sourceCompatibility = '17'
    targetCompatibility = '17'
}

application {
    mainClass = 'com.example.App'
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
}
```

## Project Structure

Both tools use similar directory layouts:

```
project/
├── src/
│   ├── main/java/          # Source code
│   └── test/java/          # Test code
├── pom.xml                 # Maven config
├── build.gradle            # Gradle config
└── build/ or target/       # Generated files
```

## Adding Dependencies

### Maven (pom.xml)
```xml
<dependencies>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
  </dependency>
</dependencies>
```

### Gradle (build.gradle)
```groovy
dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
}
```

## Next Steps

1. **Choose Maven or Gradle** - Maven is easier to start with
2. **Create a project** using the commands above
3. **Add a dependency** and see how it works
4. **Run tests** and package your application

## Troubleshooting

### Common Maven Issues
```bash
mvn dependency:purge-local-repository  # Clear cache
mvn clean compile -U                   # Force update
```

### Common Gradle Issues
```bash
gradle clean build --refresh-dependencies
```

---

**Build tools save hours of manual work and prevent errors!**
