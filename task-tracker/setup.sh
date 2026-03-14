#!/bin/bash

# Setup script for Task Tracker Java CLI Project
# Creates directory structure, installs dependencies, and initializes Maven project

set -e  # Exit on error

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_NAME="task-tracker"
PACKAGE_NAME="com.tasktracker"
PACKAGE_PATH="src/main/java/com/tasktracker"

echo "=========================================="
echo "Setting up $PROJECT_NAME Java CLI Project"
echo "=========================================="

# 1. Check and install Java 17
echo ""
echo "Step 1: Checking Java installation..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1)
    echo "✓ Java found: $JAVA_VERSION"
else
    echo "✗ Java not found. Installing Java 17..."
    if command -v apt-get &> /dev/null; then
        sudo apt-get update
        sudo apt-get install -y openjdk-17-jdk
    elif command -v brew &> /dev/null; then
        brew install openjdk@17
    else
        echo "Please install Java 17 manually and try again."
        exit 1
    fi
fi

# 2. Check and install Maven
echo ""
echo "Step 2: Checking Maven installation..."
if command -v mvn &> /dev/null; then
    MVN_VERSION=$(mvn -v 2>&1 | head -n 1)
    echo "✓ Maven found: $MVN_VERSION"
else
    echo "✗ Maven not found. Installing Maven..."
    if command -v apt-get &> /dev/null; then
        sudo apt-get update
        sudo apt-get install -y maven
    elif command -v brew &> /dev/null; then
        brew install maven
    else
        echo "Please install Maven manually and try again."
        exit 1
    fi
fi

# 3. Create directory structure
echo ""
echo "Step 3: Creating directory structure..."
mkdir -p "$PROJECT_ROOT/$PACKAGE_PATH"
echo "✓ Created $PACKAGE_PATH"

# 4. Create pom.xml
echo ""
echo "Step 4: Creating pom.xml..."
cat > "$PROJECT_ROOT/pom.xml" << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.tasktracker</groupId>
    <artifactId>task-cli</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>Task Tracker CLI</name>
    <description>A simple CLI tool to track and manage tasks</description>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.10.1</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.tasktracker.TaskCli</mainClass>
                        </manifest>
                    </archive>
                    <finalName>task-cli</finalName>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF
echo "✓ Created pom.xml"

# 5. Create task-cli shell script
echo ""
echo "Step 5: Creating task-cli shell script wrapper..."
cat > "$PROJECT_ROOT/task-cli" << 'EOF'
#!/bin/bash

# Task Tracker CLI Wrapper Script
# Compiles and runs the Java application

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$SCRIPT_DIR"
BUILD_DIR="$PROJECT_DIR/target/classes"
MAIN_CLASS="com.tasktracker.TaskCli"

# Check if Maven is available
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed. Please install Maven and try again."
    exit 1
fi

# Compile the project
echo "Compiling Task Tracker..." >&2
if ! mvn -q clean compile -f "$PROJECT_DIR/pom.xml" 2>/dev/null; then
    echo "Error: Compilation failed" >&2
    exit 1
fi

# Run the application
java -cp "$BUILD_DIR" "$MAIN_CLASS" "$@"
EOF
chmod +x "$PROJECT_ROOT/task-cli"
echo "✓ Created task-cli script (executable)"

# 6. Summary
echo ""
echo "=========================================="
echo "Setup Complete!"
echo "=========================================="
echo ""
echo "Project structure created at: $PROJECT_ROOT"
echo ""
echo "Next steps:"
echo "1. Implement Java classes in: $PACKAGE_PATH"
echo "   - Task.java (model)"
echo "   - TaskStorage.java (JSON persistence)"
echo "   - TaskManager.java (business logic)"
echo "   - TaskCli.java (entry point)"
echo ""
echo "2. Test the setup:"
echo "   cd $PROJECT_ROOT"
echo "   ./task-cli --help"
echo ""
echo "3. Build and run:"
echo "   mvn clean package"
echo "   ./task-cli add \"Buy groceries\""
echo ""
