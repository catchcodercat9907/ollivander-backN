package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, CrudRepository<Product, Integer> {
    @Query("select p from Product p order by p.id")
    List<Product> getAllProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.id IN (:productIds)")
    List<Product> getProductByRequest(@Param("productIds") Collection<Integer> productIds);

    @Query("SELECT p FROM Product p WHERE p.slug like ?1")
    Product getDetailBySlug(String slug);
}
