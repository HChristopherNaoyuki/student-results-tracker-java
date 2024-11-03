# Student Results Tracker

## Overview
The Student Results Tracker is a Java Swing application that enables users to view and analyze student scores. Users can select a student ID from a dropdown menu to see their average, highest, and lowest scores, all sourced from a data file. The application includes error handling to ensure a smooth user experience.

## Features
- Load student data from a file.
- Select a student by ID from a dropdown menu.
- Display average, highest, and lowest scores.
- Error handling for file operations and data validation.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE (like IntelliJ IDEA, Eclipse, or NetBeans) for Java development
- Basic knowledge of Java and Swing

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/HChristopherNaoyuki/student-results-tracker-java.git
   ```
2. Navigate to the project directory:
   ```bash
   cd student-results-tracker
   ```
3. Ensure you have a `student.txt` file in the project root directory with the following format:
   ```
   studentID,score1,score2,score3
   ```
   Example:
   ```
   12345,85,90,78
   ```

## Usage
1. Open the project in your preferred IDE.
2. Run the `StudentResultsTracker` class.
3. Select a student ID from the dropdown to view their results.

## Error Handling
The application handles various errors, including:
- File not found.
- Invalid number format in the data file.
- Student ID not found in the records.

---
