package com.coding.sales.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;

public class ProductBuilder {
    public static ArrayList<Product> getProduct(OrderCommand command) {
        ArrayList<Product> list = new ArrayList<Product>();
        for (OrderItemCommand item : command.getItems()) {
            list.add(buildProduct(item));
        }
        list.sort(new Comparator<Product>(){

            @Override
            public int compare(Product o1, Product o2) {
                return 0;
            }
            
        });
        return list;
    }

    private static Product buildProduct(OrderItemCommand item) {
        Product product = new Product();
        product.mCount = item.getAmount().intValue();
        product.productNo = item.getProduct();
        if ("001001".equals(product.productNo)) {
            product.productName = "世园会五十国钱币册";
            product.mPrice = new BigDecimal("998.00");
        } else if ("001002".equals(product.productNo)) {
            product.productName = "2019北京世园会纪念银章大全40g";
            product.mPrice = new BigDecimal("1380.00");
            product.mDiscountCard.add("9折券");
        } else if ("003001".equals(product.productNo)) {
            product.productName = "招财进宝";
            product.mPrice = new BigDecimal("1580.00");
            product.mDiscountCard.add("95折券");
        } else if ("003002".equals(product.productNo)) {
            product.productName = "水晶之恋";
            product.mPrice = new BigDecimal("980.00");
            product.mActivity.add("第3件半价");
            product.mActivity.add("满3送1");
        } else if ("002002".equals(product.productNo)) {
            product.productName = "中国经典钱币套装";
            product.mPrice = new BigDecimal("998.00");
            product.mActivity.add("每满2000减30");
            product.mActivity.add("每满1000减10");
        } else if ("002001".equals(product.productNo)) {
            product.productName = "守扩之羽比翼双飞4.8g";
            product.mPrice = new BigDecimal("1080.00");
            product.mActivity.add("第3件半价");
            product.mActivity.add("满3送1");
            product.mDiscountCard.add("95折券");
        } else if ("002003".equals(product.productNo)) {
            product.productName = "中国银象棋12g";
            product.mPrice = new BigDecimal("698.00");
            product.mActivity.add("每满3000元减350");
            product.mActivity.add("每满2000减30");
            product.mActivity.add("每满1000减10");
            product.mDiscountCard.add("9折券");
        }
        return product;
    }
}