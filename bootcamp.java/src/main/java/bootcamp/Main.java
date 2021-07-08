package bootcamp;

import bootcamp.analytics.models.Client;
import bootcamp.analytics.models.Product;
import bootcamp.csv.CSVGen;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Boolean reload = Boolean.FALSE;
        CSVGen.genCsvFileProduct("./products.csv", reload);
        List<Product> products = CSVGen.ParseProductFromCsv("./products.csv");

        CSVGen.genCsvFileClient("./clients.csv", reload);
        List<Client> clients = CSVGen.ParseClientFromCsv("./clients.csv");

    }
}
