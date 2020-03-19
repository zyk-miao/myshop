package com.zyk.service;

import com.github.pagehelper.PageHelper;
import com.zyk.mapper.ProductMapper;
import com.zyk.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    public int getProductNum() {
        return productMapper.getProductNum();
    }

    public List<Product> getHotProduct() {
        return productMapper.getHotProduct();
    }

    public List<Product> getProductByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Product> list = productMapper.getAllProduct();
        return list;
    }

    public List<Product> getAllProduct() {
        return productMapper.getAllProduct();
    }

    public List<Product> getNewProduct() {
        return productMapper.getNewProduct();
    }

    public Product getProductByPid(int pid) {
        return productMapper.getProductByPid(pid);
    }

    public List<Product> getProductByCsid(int csid) {
        return productMapper.getProductByCsid(csid);
    }

    public List<Product> getProductByPids(int pids[]) {
        return productMapper.getProductByPids(pids);
    }

    public String delProductByPids(int pids[]) {
        try {
            productMapper.deleteProductByPids(pids);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String delProductByPid(int pid) {
        try {
            productMapper.deleteProductByPid(pid);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    public String addOneProduct(int pid,String pname, double market_price,
                                double shop_price, String image,
                                String pdesc, int is_hot, int csid){
        try {
            productMapper.addOneProduct(pid,pname,market_price,shop_price,image,pdesc,is_hot,csid);
            return "success";
        }
        catch (Exception e){
            return "fail";
        }
    }
    public String updateOneProduct(String pname, double market_price,
                                   double shop_price, String image,
                                   String pdesc, int is_hot, int csid,int pid){
        try {
            productMapper.updateOneProduct(pname,market_price,shop_price,image,pdesc,is_hot,csid,pid);
            return "success";
        }
        catch (Exception e){
            return "fail";
        }

    }
    public int getNewPid(){
        return productMapper.getMaxPid()+1;
    }
}
