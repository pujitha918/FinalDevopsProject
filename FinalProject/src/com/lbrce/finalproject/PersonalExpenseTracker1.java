package com.lbrce.finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PersonalExpenseTracker1 {

    // List to hold expenses
    private static ArrayList<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Personal Expense Tracker");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel to hold input fields and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));  // 4 rows, 2 columns with gaps

        // Label for expense amount
        JLabel amountLabel = new JLabel("Amount:");
        inputPanel.add(amountLabel);

        // Text field for amount
        JTextField amountTextField = new JTextField(20);
        inputPanel.add(amountTextField);

        // Label for category
        JLabel categoryLabel = new JLabel("Category:");
        inputPanel.add(categoryLabel);

        // Text field for category
        JTextField categoryTextField = new JTextField(20);
        inputPanel.add(categoryTextField);

        // Label for description
        JLabel descriptionLabel = new JLabel("Description:");
        inputPanel.add(descriptionLabel);

        // Text field for description
        JTextField descriptionTextField = new JTextField(20);
        inputPanel.add(descriptionTextField);

        // Button to add expense
        JButton addButton = new JButton("Add Expense");
        inputPanel.add(addButton);

        // Button to delete the last expense
        JButton deleteButton = new JButton("Delete Last Expense");
        inputPanel.add(deleteButton);

        // Text area to display expenses
        JTextArea expenseArea = new JTextArea();
        expenseArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(expenseArea);  // To add scroll bar
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel for total and expense display area
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Label to display total expenses
        JLabel totalLabel = new JLabel("Total: $0.00");
        bottomPanel.add(totalLabel, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // ActionListener for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountTextField.getText());
                    String category = categoryTextField.getText();
                    String description = descriptionTextField.getText();

                    // Add expense to the list
                    expenses.add(new Expense(amount, category, description));
                    amountTextField.setText("");  // Clear input fields
                    categoryTextField.setText("");
                    descriptionTextField.setText("");
                    updateExpenseArea(expenseArea);
                    updateTotalLabel(totalLabel);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount entered.");
                }
            }
        });

        // ActionListener for the Delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!expenses.isEmpty()) {
                    expenses.remove(expenses.size() - 1);  // Remove the last expense
                    updateExpenseArea(expenseArea);
                    updateTotalLabel(totalLabel);
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }

    // Method to update the expenses displayed in the text area
    private static void updateExpenseArea(JTextArea expenseArea) {
        StringBuilder expenseText = new StringBuilder();
        for (Expense expense : expenses) {
            expenseText.append(expense).append("\n");
        }
        expenseArea.setText(expenseText.toString());
    }

    // Method to update the total expenses displayed
    private static void updateTotalLabel(JLabel totalLabel) {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        totalLabel.setText(String.format("Total: $%.2f", total));
    }
}

// Expense class to hold individual expense details
class Expense {
    private double amount;
    private String category;
    private String description;

    public Expense(double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("$%.2f - %s: %s", amount, category, description);
    }
}
