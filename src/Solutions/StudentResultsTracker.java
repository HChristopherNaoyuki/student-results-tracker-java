package Solutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class StudentResultsTracker extends JFrame
{
    // GUI Components
    private JComboBox<String> studentIDComboBox;
    private JTextField txtAverage, txtHighest, txtLowest;
    private JButton btnSearch;
    private HashMap<String, int[]> studentResults;

    // Constructor to initialize the GUI and load student data
    public StudentResultsTracker()
    {
        super("Student Results Tracker");
        setLayout(new GridLayout(5, 2, 10, 10));

        // Initialize GUI components
        studentIDComboBox = new JComboBox<>();
        txtAverage = new JTextField();
        txtHighest = new JTextField();
        txtLowest = new JTextField();
        btnSearch = new JButton("Search");

        // Set JTextFields to be non-editable
        txtAverage.setEditable(false);
        txtHighest.setEditable(false);
        txtLowest.setEditable(false);

        // Add components to the frame
        add(new JLabel("Select Student ID:"));
        add(studentIDComboBox);
        add(new JLabel("Average:"));
        add(txtAverage);
        add(new JLabel("Highest:"));
        add(txtHighest);
        add(new JLabel("Lowest:"));
        add(txtLowest);
        add(btnSearch);

        // Load student data from file
        loadStudentData();

        // Add action listener to the Search button
        btnSearch.addActionListener(e -> searchStudentResults());

        // Frame settings
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    // Method to load student data from file
    private void loadStudentData()
    {
        studentResults = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("student.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(",");
                String studentID = data[0];
                int[] results = 
                {
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    Integer.parseInt(data[3])
                };
                studentResults.put(studentID, results);
                studentIDComboBox.addItem(studentID);
            }
        }
        catch (FileNotFoundException e)
        {
            showError("File not found: " + e.getMessage());
        }
        catch (IOException e)
        {
            showError("Error reading file: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            showError("Invalid number format in file: " + e.getMessage());
        }
        catch (Exception e)
        {
            showError("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Method to search for and display the selected student's results
    private void searchStudentResults()
    {
        String studentID = (String) studentIDComboBox.getSelectedItem();
        int[] results = studentResults.get(studentID);

        if (results != null)
        {
            try
            {
                int total = 0, highest = results[0], lowest = results[0];

                for (int result : results)
                {
                    total += result;
                    if (result > highest)
                    {
                        highest = result;
                    }
                    if (result < lowest)
                    {
                        lowest = result;
                    }
                }

                double average = (double) total / results.length;

                txtAverage.setText(String.format("%.2f", average));
                txtHighest.setText(String.valueOf(highest));
                txtLowest.setText(String.valueOf(lowest));
            }
            catch (ArithmeticException e)
            {
                showError("Error calculating results: " + e.getMessage());
            }
            catch (Exception e)
            {
                showError("An unexpected error occurred: " + e.getMessage());
            }
        }
        else
        {
            showError("Student ID not found.");
        }
    }

    // Method to display error messages
    private void showError(String message)
    {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Main method to launch the application
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new StudentResultsTracker());
    }
}
