# AccessAVLApp: Campus Access Management with AVL Tree

## Overview

AccessAVLApp is a Java application designed to manage campus access during lockdown by storing and retrieving student data using an AVL Tree data structure. The AVL Tree ensures efficient searching and retrieval of student information.

## Features

- **Print Student Information:** Given a student ID, the application prints the corresponding student name or displays "Access denied!" if no match is found.
  
- **Print All Students:** Prints all student numbers and names stored in the AVL Tree in any order.

## Usage

### Prerequisites

- Java installed on your machine.

### Commands

1. To print the name for a specific student:

   ```bash
   java AccessAVLApp "MNGREA015"
   ```

   Output: Confirming access for the specified student.

2. To print all student details:

   ```bash
   java AccessAVLApp
   ```

   Output: Displaying all student numbers and names.

## Experiment

Conducted performance testing using known and invalid parameters:

- Tested with 3 valid student IDs.
- Tested with 3 invalid student IDs.
- Executed without any parameters.

Used Unix output redirection to save results to different files for analysis.

## How to Contribute

Feel free to contribute to the project by opening issues or submitting pull requests. Your insights and suggestions are highly valued.

## Acknowledgments
- Dataset: oklist.txt
