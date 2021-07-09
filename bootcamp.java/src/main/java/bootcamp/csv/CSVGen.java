package bootcamp.csv;
import bootcamp.analytics.models.Client;
import bootcamp.analytics.models.Product;
import net.andreinc.mockneat.MockNeat;
import java.io.IOException;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class CSVGen {

    public static int clientNum = 100;

    public static void genCsvFileProduct(String filename, Boolean reload){
        MockNeat m = MockNeat.threadLocal();
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        final Path path = Paths.get(filename);
        if(reload){
            try {
                Files.deleteIfExists(path);
                String header = "idSales,prodCategory,prodName,prodCount,prodPrice,idClient\n";
                m.fmt(Arrays.stream(header.trim().split(",")).map(s -> "#{" + s + "}").collect(Collectors.joining(",")))
                        .param("idSales",  m.intSeq())
                        .param("prodCategory", m.from(new String[]{"Coca-Cola", "Danone", "Unilever", "Kellogg", "Mars", "Mondelez", "Nestle", "PepsiCo"}))
                        .param("prodName", m.from(new String[]{"Soda", "Juice", "Chocolate bar", "Crisps", "Tea", "Coffee", "Milk", "Ice-cream", "Cookies", "Candies", "Bread"}))
                        .param("prodCount", m.ints().range(1, 100))
                        .param("prodPrice", m.doubles().bound(1500.00).map((p) -> decimalFormat.format(p).replace(",", ".")))
                        .param("idClient",  m.ints().rangeClosed(0, clientNum))
                        .list(clientNum * 5)
                        .consume(list -> {
                            try {
                                Files.write(path, header.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                                Files.write(path, list,              StandardOpenOption.APPEND, StandardOpenOption.WRITE); }
                            catch (IOException e) { e.printStackTrace(); }
                        });
                System.out.println("Product file generated!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void genCsvFileClient(String filename, Boolean reload){
        MockNeat m = MockNeat.threadLocal();
        final Path path = Paths.get(filename);
        if(reload){
            try {
                Files.deleteIfExists(path);
                String header = "idClient,clientFirstName,clientLastName,countryCode\n";
                m.fmt(Arrays.stream(header.trim().split(",")).map(s -> "#{" + s + "}").collect(Collectors.joining(",")))
                        .param("idClient",  m.intSeq())
                        .param("clientFirstName", m.names().first())
                        .param("clientLastName", m.names().last())
                        .param("countryCode", m.from(new String[]{"RUS", "BEL", "KAZ"}))
                        .list(clientNum)
                        .consume(list -> {
                            try {
                                Files.write(path, header.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                                Files.write(path, list,              StandardOpenOption.APPEND, StandardOpenOption.WRITE); }
                            catch (IOException e) { e.printStackTrace(); }
                        });
                System.out.println("Client file generated!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Product> ParseProductFromCsv(String filename) {
        //Загружаем строки из файла
        List<Product> products = new ArrayList<Product>();
        List<String> fileLines = null;
        try {
            fileLines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileLines.remove(0);
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            Collections.addAll(columnList, splitedText);
            Product product = new Product();
            product.idSales = Integer.valueOf(columnList.get(0));
            product.prodCategory = columnList.get(1);
            product.prodName = columnList.get(2);
            product.prodCount = Integer.valueOf(columnList.get(3));
            product.prodPrice = Double.valueOf(columnList.get(4));
            product.idClient = Integer.valueOf(columnList.get(5));
            products.add(product);
        }
        System.out.println("Product file readed!");
        return products;
    }

    public static List<Client> ParseClientFromCsv(String filename) {
        List<Client> clients = new ArrayList<Client>();
        List<String> fileLines = null;
        try {
            fileLines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileLines.remove(0);
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            Collections.addAll(columnList, splitedText);
            Client client = new Client();
            client.idClient = Integer.valueOf(columnList.get(0));
            client.clientFirstName = columnList.get(1);
            client.clientLastName = columnList.get(2);
            client.countryCode = columnList.get(3);
            clients.add(client);
        }
        System.out.println("Client file readed!");
        return clients;
    }
}
