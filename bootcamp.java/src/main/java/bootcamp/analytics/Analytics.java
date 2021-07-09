package bootcamp.analytics;

import bootcamp.analytics.models.Client;
import bootcamp.analytics.models.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analytics {
    public static Map<String, Integer> countSoldProducts(List<Product> products){
        Map<String, Integer> resultSet = new HashMap<String, Integer>();
        System.out.println("How much products sold in each category?");
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
}
