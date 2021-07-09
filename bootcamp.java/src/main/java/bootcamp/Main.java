package bootcamp;

import bootcamp.analytics.Analytics;
import bootcamp.analytics.models.Client;
import bootcamp.analytics.models.Product;
import bootcamp.csv.CSVGen;

import java.util.List;
import java.util.Map;

import static bootcamp.analytics.Analytics.countSoldProducts;

public class Main {
    public static void main(String[] args) {
        Boolean reload = Boolean.FALSE;
        System.out.println("Start executing...\n");
        CSVGen.genCsvFileProduct("./products.csv", reload);
        List<Product> products = CSVGen.ParseProductFromCsv("./products.csv");

        CSVGen.genCsvFileClient("./clients.csv", reload);
        List<Client> clients = CSVGen.ParseClientFromCsv("./clients.csv");

        Map<String, Integer> task11 = countSoldProducts(products);
        System.out.println("\nHow much products sold in each category?");
        task11.forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("\nEnd executing...");
    }
}
