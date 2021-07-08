package bootcamp;

import bootcamp.analytics.models.Client;
import bootcamp.analytics.models.Product;
import bootcamp.csv.CSVGen;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CSVGen.genCsvFileProduct("./products.csv", Boolean.TRUE);
        List<Product> products = CSVGen.ParseProductFromCsv("./products.csv");
        CSVGen.genCsvFileClient("./clients.csv", Boolean.TRUE);
        List<Client> clients = CSVGen.ParseClientFromCsv("./clients.csv");
    }
}
