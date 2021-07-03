package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;
import java.util.HashMap;
import java.util.Map;

public class OrderStep extends BaseStep {
    private Map<String, String> productMap = new HashMap<>();

    public OrderStep(WebDriver driver) {
        super(driver);
    }

    public void orderOneProduct(String... productNames) throws InterruptedException {
        ProductsPage productsPage = new ProductsPage(driver, true);

        for (String name : productNames) {

            productsPage.addToCart(name);
            productMap.put(name, productsPage.getInventoryPrice(name));

        }
    }
    public Map<String, String> getAddedProduct(){
        return productMap;
    }


}
