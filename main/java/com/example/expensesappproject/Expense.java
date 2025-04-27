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
        return category + " ," + amount + " ," + date;
    } // returns all the information


    public static Expense fromString(String line) { // creates an expense object from a formatted string input
        String[] parts = line.split(",");


        if (parts.length != 3) { // ensures that the input structure is valid before parsing
            throw new IllegalArgumentException("Invalid input format. Expected: category,amount,date"); // if it is invalid, this message comes up
        }

        return new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]);// returns the parsed values
    }
}
