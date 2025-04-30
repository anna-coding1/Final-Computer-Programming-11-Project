// imports all the necessary libraries for the code to function
import com.example.expensesappproject.Expense;
import com.example.expensesappproject.ExpenseManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

// test class
public class ExpenseManagerTest {

    @Test
    void testAddExpense() {
        ExpenseManager manager = new ExpenseManager();
        Expense expense = new Expense("Utilities", 300.00, "2025-04-26");

        manager.addExpense(expense);

        // these tests are to check if the expense was added correctly
        assertEquals(1, manager.getExpenses().size(), "Your expense should be added to the list");
        assertEquals("Utilities", manager.getExpenses().get(0).getCategory(), "The category should match the one being tested");
        assertEquals(300.00, manager.getExpenses().get(0).getAmount(), "The amount should match the one being tested");
        assertEquals("2025-04-26", manager.getExpenses().get(0).getDate(), "The date should match the one being tested");
    }

    @Test
    void testSaveAndLoadExpenses() throws IOException {
        ExpenseManager expensemanager = new ExpenseManager();
        String testFile = "expenses.csv";


        Expense expense = new Expense("Rent", 250.00, "2025-04-26"); // this is the test expense
        expensemanager.addExpense(expense);


        expensemanager.saveExpenses(testFile); // saves to the file inside the tests directory


        ExpenseManager loadedManager = new ExpenseManager(); // loads the expenses from the file inside the tests directory
        loadedManager.loadExpenses(testFile);

        List<Expense> expenses = loadedManager.getExpenses();

        // these tests are to check if the expense was loaded correctly
        assertEquals(1, expenses.size(), "Your expense should be loaded correctly");
        assertEquals("Rent", expenses.get(0).getCategory(), "The category should match the one being tested after loading");
        assertEquals(250.00, expenses.get(0).getAmount(), "The amount should match the one being tested after loading");
        assertEquals("2025-04-26", expenses.get(0).getDate(), "The date should match the one being tested after loading");
    }

    @Test
    void testGetTotalExpenses() {
        ExpenseManager expensemanager = new ExpenseManager();


        expensemanager.addExpense(new Expense("Utilities", 300.00, "2025-04-26")); // adding multiple expenses
        expensemanager.addExpense(new Expense("Rent", 250.00, "2025-04-26"));


        double total = expensemanager.getTotalExpenses(); // calculates the total


        assertEquals(550.00, total, "Total expenses should be correctly added and summed"); // checks if the total is calculated correctly
    }
}