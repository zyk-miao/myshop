package com.zyk.controller;

import com.zyk.entity.CProduct;
import com.zyk.entity.Product;
import com.zyk.service.ProductService;
import com.zyk.tool.JsonResult;
import com.zyk.tool.MyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class ProductController {

    private String filePath = System.getProperty("user.dir")+"/src/main/resources/static/products";
    private static  final String imgaddress="http://103.40.20.150/products/";
    private static final String houhui=".png";
    @Autowired
    ProductService productService;

    @RequestMapping(value = "gethot", method = RequestMethod.POST)
    private @ResponseBody
    List getHotProduce() {
        return productService.getHotProduct();
    }

    @RequestMapping(value = "getnew", method = RequestMethod.POST)
    private @ResponseBody
    List getNewProduce() {
        int total=0,t;
        Random random=new Random();
        List<Product> list=productService.getNewProduct();
        List<Product> list1=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            if(total==10) break;
            if(random.nextInt(100)%5>2) {
                list1.add(list.get(i));
                total++;
            }
        }
        return list1;
    }

    @RequestMapping(value = "getproductbypid", method = RequestMethod.POST)
    private @ResponseBody
    Product getProduceByPid(HttpServletRequest request) {
        return productService.getProductByPid(Integer.parseInt(request.getParameter("pid")));
    }

    @RequestMapping(value = "getproductbycsid", method = RequestMethod.POST)
    private @ResponseBody
    List<Product> getProduceByCsid(HttpServletRequest request) {
        return productService.getProductByCsid(Integer.parseInt(request.getParameter("csid")));
    }

    @ResponseBody
    @RequestMapping(value = "getproductbypage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    private JsonResult
    getProductByPage(HttpServletRequest request) {
        List<Product> list = productService.getProductByPage(Integer.parseInt(request.getParameter("page")),
                Integer.parseInt(request.getParameter("limit")));
        List<CProduct> list1 = new ArrayList(list.size());
        MyTools m=new MyTools();
        for (int i = 0, l = list.size(); i < l; i++) {
            list1.add(m.productToCproduct(list.get(i)));
        }
        return new JsonResult(0, list1, "", productService.getProductNum());
    }

    @RequestMapping(value = "getproductbypids", method = RequestMethod.POST)
    @ResponseBody
    private List<Product> getProductByPids(HttpServletRequest request) {
        String[] s = request.getParameterValues("pids");
        if (s == null) return null;
        int t[] = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            t[i] = Integer.parseInt(s[i].replace("pid", ""));
        }
        return productService.getProductByPids(t);
    }

    @RequestMapping(value = "delproductbypids", method = RequestMethod.POST)
    @ResponseBody
    private String delProductByPids(HttpServletRequest request) {
        String[] s = request.getParameterValues("pids");
        if (s == null) return "fail";
        int t[] = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            t[i] = Integer.parseInt(s[i].replace("pid", ""));
        }
        return productService.delProductByPids(t);
    }

    @RequestMapping(value = "delproductbypid", method = RequestMethod.POST)
    private @ResponseBody
    String detProduceByPid(HttpServletRequest request) {
        return productService.delProductByPid(Integer.parseInt(request.getParameter("pid")));
    }
    @RequestMapping(value="addproduct",method = RequestMethod.POST)
    private @ResponseBody
    int addProduct(HttpServletRequest request) {
        int pid=productService.getNewPid();
        productService.addOneProduct(pid,request.getParameter("pname"),Double.parseDouble(request.getParameter("m_price")),
                Double.parseDouble(request.getParameter("s_price")),
                imgaddress+pid+houhui, request.getParameter("pdesc"),Integer.parseInt(request.getParameter("is_hot")),
                Integer.parseInt(request.getParameter("csid")));
        return pid;
    }

    private void uploadFile(byte[] file, String fileName) throws Exception {
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
            out.close();
    }
    @RequestMapping(value="updateproduct",method = RequestMethod.POST)
    private @ResponseBody
        String updateProduct(HttpServletRequest request) {
        productService.updateOneProduct(request.getParameter("pname"),Double.parseDouble(request.getParameter("m_price")),
                Double.parseDouble(request.getParameter("s_price")),
                imgaddress+request.getParameter("pid")+houhui, request.getParameter("pdesc"),Integer.parseInt(request.getParameter("is_hot")),
                Integer.parseInt(request.getParameter("csid")),Integer.parseInt(request.getParameter("pid")));
        return "success";
    }

}
