package bootcamp;

import bootcamp.analytics.Analytics;
import bootcamp.analytics.models.Client;
import bootcamp.analytics.models.Product;
import bootcamp.csv.CSVGen;

import java.util.List;
import java.util.Map;

import static bootcamp.analytics.Analytics.*;

public class Main {
    public static void main(String[] args) {
        Boolean reload = Boolean.FALSE;
        System.out.println("Start executing...\n");
        CSVGen.genCsvFileProduct("./products.csv", reload);
        List<Product> products = CSVGen.ParseProductFromCsv("./products.csv");

        CSVGen.genCsvFileClient("./clients.csv", reload);
        List<Client> clients = CSVGen.ParseClientFromCsv("./clients.csv");

        Map<String, Integer> task11 = countSoldProducts(products);
        task11.forEach((key, value) -> System.out.println(key + " " + value));

        Map<String, Double> task12 = mostExpSold(products, clients);
        task12.forEach((key, value) -> System.out.println(key + " " + value));

        Map<String, Double> task13 = secondMaxSpent(products, clients);
        task13.forEach((key, value) -> System.out.println(key + " " + value));

        String catName = "Mars";
        Map<String, Double> task21 = countSpentMoneyByCat(products, clients, catName);
        task21.forEach((key, value) -> System.out.println(key + " " + value));

        System.out.println("End executing...");
    }
}
