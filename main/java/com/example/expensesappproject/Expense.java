// package specific to this expenses app project
package com.example.expensesappproject;

// Requires: category must be a string, amount must be a positive number, and date must be a string
// Modifies: stores the category, amount, and date in an expense instance
// Effects: creates an Expense object representing a transaction

public class Expense {
    private String category;
    private double amount;
    private String date;

    public Expense(String category, double amount, String date) {
        this.category = category; // assigns the category
        this.amount = amount;     // assigns an amount
        this.date = date;         // assigns a date
    }


    public String getCategory() { // returns the category of the expense
        return category;
    } // gets the category


    public double getAmount() { // returns the amount of the expense
        return amount;
    } // gets the amount


    public String getDate() { // returns the date of the expense
        return date;
    } // gets the date


    @Override
    public String toString() { // converts the expense object into a string format
        return category + "," + amount + "," + date;
    } // returns all the information


    public static Expense fromString(String line) { // creates an expense object from a formatted string input
        String[] parts = line.trim().split(",");

        if (parts.length != 3) {
            throw new IllegalArgumentException("The input is invalid. The expected input: category,amount,date");
        }

        try {
            String category = parts[0].trim();
            double amount = Double.parseDouble(parts[1].trim());
            String date = parts[2].trim();

            return new Expense(category, amount, date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The amount format in entering the expense is invalid: " + line);
        }
    }
}