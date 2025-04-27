Expense Tracker Project


Expense Tracker is a JavaFX-based application that allows users to log, save, and load expense entries. It helps users manage their expenses by categorizing them, tracking what has been spent, and storing data.

What it can do
- Adding expenses by entering a category, amount, and date.
- Saving expenses to a csv file.
- Loading expenses from a file to retrieve previous entries.
- Viewing the total amount spent.
- Each expense category is in its own sheet in the csv file.

Installation Instructions:
1. Ensure JDK 8 and JavaFX are installed.
2. Import apache.poi (3.10.1 or the latest version) and Junit 5 or 4.13.
2. Open the project in your IntelliJ.
3. Run the application using JavaFX.

How to Use It
1. Start the application.
2. Enter the details of the expense.
3. Click 'Add Expense' to store the entry.
4. Click 'Save to File' to save the expenses.
5. Click 'Load from File' to retrieve any saved expenses.
6. The total amount spent can be seen at the bottom of the screen.
7. Expenses are stored in an `expenses.txt` file.

JUnit Testing
JUnit tests are included to ensure the application functions correctly.

Steps to Run the Tests
1. Open `ExpenseManagerTest.java` in intelliJ.
2. Run the test class using JUnit 4.13 or 5.

Tests
- testAddExpense checks if expenses are correctly added.
- testSaveAndLoadExpenses ensures expenses are saved and loaded correctly.
- testGetTotalExpenses verifies the correct total calculation.




