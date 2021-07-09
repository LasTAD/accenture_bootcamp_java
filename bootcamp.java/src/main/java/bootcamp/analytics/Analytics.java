package bootcamp.analytics;

import bootcamp.analytics.models.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analytics {
    public static Map<String, Integer> countSoldProducts(List<Product> products){
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (Product product : products) {
            String cat = product.prodCategory;
            if (res.get(cat) == null)
                res.put(cat, product.prodCount);
            else {
                Integer num = res.get(cat);
                num += product.prodCount;
                res.replace(cat, num);
            }
        }
        return res;
    }
}
