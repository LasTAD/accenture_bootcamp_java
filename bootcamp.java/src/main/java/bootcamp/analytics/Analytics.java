package bootcamp.analytics;

import bootcamp.analytics.models.Client;
import bootcamp.analytics.models.Product;

import java.util.*;

public class Analytics {
    public static Map<String, Integer> countSoldProducts(List<Product> products){
        System.out.println("How much products sold in each category?");
        Map<String, Integer> resultSet = new HashMap<String, Integer>();
        for (Product product : products) {
            String cat = product.prodCategory;
            if (resultSet.get(cat) == null)
                resultSet.put(cat, product.prodCount);
            else {
                Integer num = resultSet.get(cat);
                num += product.prodCount;
                resultSet.replace(cat, num);
            }
        }
        return resultSet;
    }

    public static Map<String, Double> mostExpSold(List<Product> products, List<Client> clients){
        System.out.println("\nMost expensive purchase of each client:");
        Map<String, Double> resultSet = new HashMap<String, Double>();
        for (int i = 0; i <clients.size(); i++) {
            String clientFullName = clients.get(i).clientFirstName + ' ' + clients.get(i).clientLastName;
            for (Product product : products) {
                if (product.idClient == i) {
                    Double cost = product.prodCount * product.prodPrice;
                    if (resultSet.get(clientFullName) == null)
                        resultSet.put(clientFullName, cost);
                    else if (resultSet.get(clientFullName) != null && resultSet.get(clientFullName) < cost)
                        resultSet.replace(clientFullName, cost);
                }
            }
        }
        return resultSet;
    }

    public static Map<String, Double> secondMaxSpent(List<Product> products, List<Client> clients){
        System.out.println("\nSecond most expensive purchase of each client:");
        Map<String, Double> resultSet = new HashMap<String, Double>();
        for (int i = 0; i <clients.size(); i++) {
            String clientFullName = clients.get(i).clientFirstName + ' ' + clients.get(i).clientLastName;
            List <Double> spending = new ArrayList<Double>();
            for (Product product : products) {
                if (product.idClient == i) {
                    Double cost = product.prodCount * product.prodPrice;
                    spending.add(cost);
                }
            }
            Collections.sort(spending);
            resultSet.put(clientFullName, spending.get((spending.size() > 1) ? 1 : 0));
        }
        return resultSet;
    }
}
