import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    // Get your API key from https://www.exchangerate-api.com/
    private static final String API_KEY = "a6c2dae643f3c8895d6e1fb4";

    // API URL for getting latest exchange rates
    private static final String
            API_URL = "https://v6.exchangerate-api.com/v6/a6c2dae643f3c8895d6e1fb4/latest/USD";

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter!");
        System.out.println("Enter the source currency code (e.g., USD):");
        String sourceCurrency = userInput.next().toUpperCase();

        System.out.println("Enter the target currency code (e.g., EUR):");
        String targetCurrency = userInput.next().toUpperCase();

        System.out.println("Enter the amount to convert:");
        double amount = userInput.nextDouble();

        // Get the exchange rate for the given currencies and convert the amount
        double exchangeRate = getExchangeRate(sourceCurrency, targetCurrency);

        // Display the converted amount if the exchange rate is valid
        if (exchangeRate != -1) {

            // Round the converted amount to 2 decimal places
            double convertedAmount = amount * exchangeRate;
            System.out.printf("%.2f %s is equal to %.2f %s\n",
                    amount, sourceCurrency, convertedAmount, targetCurrency);
        } else {
            System.out.println("Unable to fetch exchange rates. Please try again later.");
        }

        userInput.close(); // Close the scanner
    }

    private static double getExchangeRate(String sourceCurrency, String targetCurrency) {
        try {
            String jsonResponse = getString();
            double sourceRate = getRateFromJson(jsonResponse, sourceCurrency);
            double targetRate = getRateFromJson(jsonResponse, targetCurrency);

            if (sourceRate != -1 && targetRate != -1) {
                return targetRate / sourceRate;
            } else {
                System.out.println("Invalid currency codes.");
            }

        } catch (IOException e) {
            System.out.println("Error fetching exchange rates. Please check your internet connection.");
            e.printStackTrace();
        }

        return -1;
    }

    private static String getString() throws IOException {
        URL url = new URL(API_URL + "?apikey=" + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        // Parse JSON response to get exchange rates
        return response.toString();
    }

    private static double getRateFromJson(String jsonResponse, String currencyCode) {
        String searchString = "\"" + currencyCode + "\":";
        int index = jsonResponse.indexOf(searchString);

        if (index != -1) {
            int startIndex = index + searchString.length();
            int endIndex = jsonResponse.indexOf(",", startIndex);
            String rateString = jsonResponse.substring(startIndex, endIndex).trim();
            return Double.parseDouble(rateString);
        }

        return -1;
    }
}
