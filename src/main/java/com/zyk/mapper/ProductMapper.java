package com.zyk.mapper;

import com.zyk.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select count(pid) from product")
    int getProductNum();

    @Select("select * from product")
    List<Product> getAllProduct();

    @Select("select * from product where is_hot=1")
    List<Product> getHotProduct();

    @Select("select * from product ")
    List<Product> getNewProduct();

    @Select("select * from product where pid=#{pid}")
    Product getProductByPid(@Param("pid") int pid);

    @Select("select * from product where csid=#{csid}")
    List<Product> getProductByCsid(@Param("csid") int csid);

    @Select({
            "<script>",
            "select",
            "*",
            "from product",
            "where pid in",
            "<foreach collection='pids' item='pid' open='(' separator=',' close=')'>",
            "#{pid}",
            "</foreach>",
            "</script>"
    })
    List<Product> getProductByPids(@Param("pids") int pids[]);

    @Delete({
            "<script>",
            "delete",
            "from product",
            "where pid in",
            "<foreach collection='pids' item='pid' open='(' separator=',' close=')'>",
            "#{pid}",
            "</foreach>",
            "</script>"
    })
    void deleteProductByPids(@Param("pids") int pids[]);

    @Delete("delete from product where pid=#{pid}")
    void deleteProductByPid(@Param("pid") int pid);

    @Insert("insert into product (pid,pname,market_price,shop_price,image,pdesc,is_hot,csid) value(" +
            "#{pid},#{pname}" +
            ",#{market_price},#{shop_price},#{image},#{pdesc},#{is_hot},#{csid})")
    void addOneProduct(@Param("pid") int pid,@Param("pname") String pname, @Param("market_price") double market_price,
                       @Param("shop_price") double shop_price, @Param("image") String image,
                       @Param("pdesc") String pdesc, @Param("is_hot") int is_hot
            , @Param("csid") int csid);

    @Update("update  product set pname=#{pname},market_price=#{market_price},shop_price=#{shop_price}," +
            "image=#{image},pdesc=#{pdesc},is_hot=#{is_hot},csid= #{csid} where pid=#{pid}")
    void updateOneProduct(@Param("pname") String pname, @Param("market_price") double market_price,
                          @Param("shop_price") double shop_price, @Param("image") String image,
                          @Param("pdesc") String pdesc, @Param("is_hot") int is_hot
            , @Param("csid") int csid, @Param("pid") int pid);

    @Select("select max(pid) from product ")
    int getMaxPid();
}
