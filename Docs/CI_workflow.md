# CI Workflow

This Markdown file describes the structure and functionality of the Continuous Integration (CI) workflow configured in the provided GitHub Actions YAML file.

## Overview

The CI workflow is triggered on a `push` event to the repository. It is configured to execute a series of jobs that manage code checkout, building, testing, and running static analysis on a Java-based project.

### Environment Variables

- **`SERVER_PATH`**: The path to the server code (`PopcornPals-Server`).
- **`JAVA_VERSION`**: The Java version to be used (`21`).
- **`JAVA_DISTRIBUTION`**: The distribution of Java to be used (`temurin`).
- **`OS_VERSION`**: The operating system version, which is set to `ubuntu-latest`.

## Jobs

### 1. Clone-repo

This job checks out the repository, including submodules, and uploads the server code as an artifact.

- **Runs-on**: `ubuntu-latest`
- **Steps**:
  - **Checkout repository**: Uses `actions/checkout@v4` to clone the repository.
  - **Upload server code**: Uses `actions/upload-artifact@v4` to upload the server code as an artifact.

### 2. Build

This job builds the project using Maven, caches dependencies, and uploads the build artifacts.

- **Runs-on**: `ubuntu-latest`
- **Needs**: `Clone-repo`
- **Steps**:
  - **Download server code**: Retrieves the uploaded server code artifact.
  - **Set up JDK**: Configures the Java Development Kit (JDK) using the specified version and distribution.
  - **Cache Maven packages**: Caches Maven dependencies to speed up future builds.
  - **Build with Maven**: Runs the Maven `clean install` command to compile and package the project.
  - **Upload build artifacts**: Uploads the generated JAR files as build artifacts.

### 3. Dependency-Check

This job runs an OWASP Dependency-Check to identify vulnerabilities in the project's dependencies.

- **Runs-on**: `ubuntu-latest`
- **Needs**: `Clone-repo`
- **Steps**:
  - **Download server code**: Retrieves the uploaded server code artifact.
  - **Set up Java**: Configures the JDK using the specified version and distribution.
  - **Download OWASP Dependency-Check**: Downloads and extracts the OWASP Dependency-Check tool.
  - **Set executable permissions**: Grants execution permissions to the Dependency-Check script.
  - **Run OWASP Dependency-Check on Server**: Executes the Dependency-Check against the server code.
  - **Upload Dependency-Check Report**: Uploads the generated report as an artifact.

### 4. Unit-tests

This job runs unit tests on the server code using Maven to ensure the code's functionality.

- **Runs-on**: `ubuntu-latest`
- **Needs**: `Build`
- **Steps**:
  - **Set up JDK**: Configures the JDK using the specified version and distribution.
  - **Download server code**: Retrieves the uploaded server code artifact.
  - **Run unit tests**: Executes the Maven `test` command to run the project's unit tests.

### 5. SAST-check

This job performs Static Application Security Testing (SAST) using SpotBugs to detect potential security vulnerabilities in the code.

- **Runs-on**: `ubuntu-latest`
- **Needs**: `Build`
- **Steps**:
  - **Set up JDK**: Configures the JDK using the specified version and distribution.
  - **Download server code**: Retrieves the uploaded server code artifact.
  - **Run SpotBugs**: Executes SpotBugs to analyze the code for security issues.

### 6. Static-Analysis

This job runs a static analysis using Checkstyle to enforce coding standards and identify potential issues in the code.

- **Runs-on**: `ubuntu-latest`
- **Needs**: `Build`
- **Steps**:
  - **Download build artifacts**: Retrieves the uploaded build artifacts.
  - **Set up JDK**: Configures the JDK using the specified version and distribution.
  - **Install Checkstyle**: Downloads and prepares Checkstyle for execution.
  - **Run Checkstyle**: Executes Checkstyle with the provided configuration file to analyze the code.
