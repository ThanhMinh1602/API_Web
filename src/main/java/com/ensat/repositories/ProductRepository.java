package com.ensat.repositories;

import com.ensat.entities.Category;
import com.ensat.entities.Product;
import org.springframework.data.jpa.repository.*;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);

    @Query(value = "select * from product where cID = :cID", nativeQuery = true)
    List<Product> findProductBycCategoryId(Integer cID);

}
