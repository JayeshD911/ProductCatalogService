package io.github.jayeshd911.productcatalogservice.repositories;

import io.github.jayeshd911.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    List<Product> findProductByPriceBetween(Double low, Double high);

    List<Product> findAllOrderByPrice(Double price);

    @Query("SELECT p.description FROM Product p WHERE p.id = :id")
    String getDescrioptionWhereId(@Param("id") Long id);

}