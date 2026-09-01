# Final Project Report — Student Management System

## Project Overview
The Student Management System is a console-based Java application that allows
users to Add, Update, Delete, and View student records. It was built as the
Week 4 capstone project, combining everything learned across the internship:
OOP principles, Collections, and File Handling.

## Architecture
The project loosely follows an MVC-style separation of concerns:

- **Model** — `Student` class: holds student data (id, name, course, marks)
  and knows how to convert itself to/from a file-storable string format.
- **Data Access (File Handling)** — `StudentFileHandler` class: responsible
  purely for reading from and writing to `students.txt`, so persistence logic
  is isolated from business logic.
- **Controller/Service** — `StudentManager` class: holds the in-memory
  `ArrayList<Student>` and exposes add/update/delete/view operations. Every
  change immediately triggers a save to file, so data is never lost between
  runs.
- **View (Menu)** — `StudentManagementSystem` (main class): a loop-driven
  console menu that takes user input, validates it, and calls into
  `StudentManager`.

## Key Concepts Applied
- **OOP**: Encapsulation (private-style data handling within `Student`),
  separation of responsibilities across classes.
- **Collections**: `ArrayList<Student>` stores all records in memory for fast
  add/update/delete/search operations.
- **File Handling**: `BufferedReader`/`FileWriter` persist data between
  program runs, so records survive a restart.
- **Exception Handling**: `NumberFormatException` is caught wherever numeric
  input (ID, Marks) is parsed, so the program doesn't crash on bad input.

## Why File Storage Instead of a Database
The task description offered JDBC + MySQL as one backend option. This
implementation uses file-based storage instead, since it needs no external
database server or driver setup and runs immediately in any Java
environment. The same `StudentFileHandler` class could be swapped for a
`StudentDAO` class using JDBC without changing any other part of the
program — this is exactly why the storage logic was kept isolated from the
`StudentManager` business logic in the first place.

## Possible Future Improvements
- Replace file storage with a real JDBC + MySQL backend.
- Add search/filter by name or course.
- Add input validation for duplicate student IDs.
- Build a simple GUI (JavaFX/Swing) instead of a console menu.
