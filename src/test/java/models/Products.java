package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Products {

      List<String> productList;

    protected void setUserNameList(String... products) {
        if (products.length > 0) {
            for (String name : products)
                productList.add(name);
        }
    }
}
