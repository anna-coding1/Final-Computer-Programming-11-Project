// package specific to this expenses app project
package com.example.expensesappproject;

// imports all the necessary libraries for the code to function
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;


// Requires: valid user input for category, amount, and date fields and an expenses.csv file for saving and loading
// Modifies: the ExpenseManager instance by adding, saving, and loading expenses
// Effects: manages expense entries by adding them, saving them to a file, and loading them for display

public class ExpenseController {
    @FXML
    private TextField categoryField; // field for the expense category
    @FXML
    private TextField amountField;   // field for the expense amount
    @FXML
    private TextField dateField;     // field for the expense date
    @FXML
    private ListView<String> expenseList; // list view for displaying expenses
    @FXML
    private Label statusLabel; // label to show the status messages
    @FXML
    private Label totalLabel;  // label to display the total expenses

    private ExpenseManager manager = new ExpenseManager(); // manages the expense entries
    private ObservableList<String> expenseDisplayList = FXCollections.observableArrayList(); // holds the expense display data


    @FXML
    protected void addExpense() { // trims and parses the text
        try {
            String category = categoryField.getText().trim();
            double amount = Double.parseDouble(amountField.getText().trim());
            String date = dateField.getText().trim();


            if (category.isEmpty() || date.isEmpty()) { // validates inputs
                statusLabel.setText(" The category and date fields cannot be empty."); // if the input is invalid this message fomes up
                return;
            }

            Expense expense = new Expense(category, amount, date);
            manager.addExpense(expense); // adds the expense to manager
            expenseDisplayList.add(expense.toString()); // updates list view
            expenseList.setItems(expenseDisplayList);
            statusLabel.setText("The expense has been added successfully!");

            updateTotal(); // updates the total value

            // clears input fields
            categoryField.clear();
            amountField.clear();
            dateField.clear();
        } catch (NumberFormatException e) {
            statusLabel.setText("The entered amount must be a valid number.");
        }
    }


    @FXML
    protected void saveToFile() { // saves the expense data to a file
        try {
            manager.saveExpenses("expenses.csv"); // saves the expenses
            statusLabel.setText("Your expenses have been saved successfully!");
        } catch (IOException e) {
            statusLabel.setText("There has been an error in saving your expenses: " + e.getMessage());
        }
    }
    @FXML
    protected void deleteExpense() {
        // allows the user to delete an expense from the list
        int selectedIndex = expenseList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            manager.getExpenses().remove(selectedIndex); // removes the selected expense from the list
            expenseDisplayList.remove(selectedIndex);    // updates the list
            expenseList.setItems(expenseDisplayList);
            statusLabel.setText("The selected expense has been deleted successfully!"); // this message is to confirm that the expense has been deleted
            updateTotal(); // updates the display of the total expenses
        } else {
            statusLabel.setText("Please select an expense you want to delete."); // this message comes up if no selection was made
        }
    }

    @FXML
    protected void saveAndReloadExpenses() {
        // saves all the expenses to file and then loads them
        try {
            manager.saveExpenses("expenses.csv"); // saves the current expenses
            manager.loadExpenses("expenses.csv"); // loads the saved expenses

            expenseDisplayList.clear(); // clears the previous list before reloading
            for (Expense expense : manager.getExpenses()) {
                expenseDisplayList.add(expense.toString()); // adds each loaded expense back to the list
            }

            expenseList.setItems(expenseDisplayList);
            statusLabel.setText("Your expenses have been saved and reloaded successfully!"); // confirmation message
            updateTotal(); // updates the total expenses display
        } catch (IOException e) {
            statusLabel.setText("There has been an error in saving and reloading your expenses: " + e.getMessage()); // this handles any errors
        }
    }
    @FXML
    protected void openExpense() {
        // allows the user to see the details of an expense
        int selectedIndex = expenseList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Expense selectedExpense = manager.getExpenses().get(selectedIndex);
            categoryField.setText(selectedExpense.getCategory()); // fills the category field with the selected expense
            amountField.setText(String.valueOf(selectedExpense.getAmount())); // fills the amount field with the selected expense
            dateField.setText(selectedExpense.getDate()); // fills the date field with selected expense
            statusLabel.setText("Your expense details have been loaded successfully!"); // this is the confirmation message
        } else {
            statusLabel.setText("Please select an expense you want to open."); // this is the message that comes up if no selection was made
        }
    }


    @FXML
    protected void loadFromFile() { // loads the expense data from a file
        try {
            manager.loadExpenses("expenses.csv"); // loads the expenses
            expenseDisplayList.clear(); // clears the list before loading new data
            for (Expense expense : manager.getExpenses()) {
                expenseDisplayList.add(expense.toString());
            }
            expenseList.setItems(expenseDisplayList);
            statusLabel.setText("Your expenses have been loaded successfully!");
            updateTotal(); // updates the total display
        } catch (IOException e) {
            statusLabel.setText("There has been an error in loading your expenses: " + e.getMessage());
        }
    }


    private void updateTotal() {
        totalLabel.setText("Your total expenses are $" + manager.getTotalExpenses());
    } // updates the total expense amount displayed
}
