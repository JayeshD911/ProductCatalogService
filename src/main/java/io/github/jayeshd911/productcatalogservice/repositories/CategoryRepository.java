package io.github.jayeshd911.productcatalogservice.repositories;

import io.github.jayeshd911.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
