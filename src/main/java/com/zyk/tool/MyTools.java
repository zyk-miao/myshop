package com.zyk.tool;

import com.zyk.entity.CProduct;
import com.zyk.entity.Product;
import org.hamcrest.core.Is;

public class MyTools {
    public  CProduct productToCproduct(Product product) {
        CProduct c = new CProduct(product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(), product.getImage(),
                product.getPdesc(), product.getPdate());
        if (product.getIs_hot() == 1)
            c.setIs_hot("是");
        else c.setIs_hot("否");
        switch (product.getCsid()) {
            case 1:
                c.setCsid("女装男装");
                break;
            case 2:
                c.setCsid("鞋靴箱包");
                break;
            case 3:
                c.setCsid("运动户外");
                break;
            case 4:
                c.setCsid("珠宝配饰");
                break;
            case 5:
                c.setCsid("手机数码");
                break;
            case 6:
                c.setCsid("家电办公");
                break;
            case 7:
                c.setCsid("护肤彩妆");
                break;

        }
        return c;
    }
    public Product cProductToProduct(CProduct c){
         Product product=new Product(c.getPname(),c.getMarket_price(),c.getShop_price(),c.getPdesc());
        if(c.getIs_hot().equals("is_hot")) product.setIs_hot(1);
        else product.setIs_hot(0);
        switch (c.getCsid()){
            case "lei_1":
                product.setCsid(1);
                break;
            case "lei_2":
                product.setCsid(2);
                break;
            case "lei_3":
                product.setCsid(3);
                break;
            case "lei_4":
                product.setCsid(4);
                break;
            case "lei_5":
                product.setCsid(5);
                break;
            case "lei_6":
                product.setCsid(6);
                break;
            case "lei_7":
                product.setCsid(7);
                break;

        }
        return product;
    }
}
