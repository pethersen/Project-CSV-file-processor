import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * CSV file processor for payment terminals.
 * This class reads a CSV file containing transaction data and computes
 * the total number of transactions, total net amount, total gross amount, and total fee amount.
 */
public class CSVfileProcessor {

    public static void main(String[] args) {
        // Define the path to the CSV file
        String filePath = "elektronicky-vypis.csv";

        // Load CSV file to process
        File file = new File(filePath);
        if (file.exists()) {
            // Read CSV file
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                int transactionCount = 0;
                double totalNettoAmount = 0;
                double totalBruttoAmount = 0;
                double totalFeeAmount = 0;

                // Skip the header line
                br.readLine();

                String row;
                // Read each non-empty row
                while ((row = br.readLine()) != null && !row.trim().isEmpty()) {
                    // Split the row by commas
                    String[] parts = row.split(",");

                    // Parse the required columns and update the totals
                    transactionCount++;
                    totalBruttoAmount += Double.parseDouble(parts[11]);
                    totalFeeAmount += Double.parseDouble(parts[12]);
                    totalNettoAmount += Double.parseDouble(parts[13]);
                }

                // Output the results
                System.out.println("Transaction count: " + transactionCount);
                System.out.println("Total brutto amount: " + totalBruttoAmount + " CZK");
                System.out.println("Total fee amount: " + totalFeeAmount + " CZK");
                System.out.println("Total netto amount: " + totalNettoAmount + " CZK");
            } catch (IOException e) {
                // Print the stack trace if an IO error occurs
                e.printStackTrace();
            } catch (NumberFormatException e) {
                // Print the stack trace if a parsing error occurs
                System.err.println("Error parsing number from CSV file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("File not found: " + filePath);
        }
    }
}
