// package specific to this expenses app project
package com.example.expensesappproject;

// imports all the necessary libraries for the code to function

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Requires: a valid file name when saving and loading expenses, and valid expense objects when adding them
// Modifies: the expenses list by adding, clearing, or updating entries
// Effects: manages expense records by adding, saving, and loading them from a file

public class ExpenseManager {
    private ArrayList<Expense> expenses = new ArrayList<>(); // creates a list to store expense objects


    public void addExpense(Expense expense) { // adds an expense to the list
        expenses.add(expense);
    }


    public void saveExpenses(String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // creates a new excel workbook
        Map<String, Sheet> categorySheets = new HashMap<>(); // stores the sheets by category

        for (Expense expense : expenses) {
            String category = expense.getCategory(); // gets the expense category
            Sheet sheet = categorySheets.computeIfAbsent(category, k -> workbook.createSheet(k)); // each category (i.e., utilities, transport) gets its own sheet

            int rowNum = sheet.getPhysicalNumberOfRows(); // finds the next available row
            Row row = sheet.createRow(rowNum); // creates a new row within the sheet
            row.createCell(0).setCellValue(expense.getCategory()); // the first column is the category
            row.createCell(1).setCellValue(expense.getAmount()); // the second column is the amount
            row.createCell(2).setCellValue(expense.getDate()); // the third column is the date
        }

        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut); // saves everything in one csv file with each category having its own sheet
        }

    }



    public void loadExpenses(String fileName) throws IOException { // loads expenses from a file
        expenses.clear(); // clears the current list before loading the  new data
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expenses.add(Expense.fromString(line)); // parses each expense from the file
            }
        }
    }

    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum(); // gets the total of the expenses
    }


    public ArrayList<Expense> getExpenses() {
        return expenses;
    } // gets all the expenses
}

