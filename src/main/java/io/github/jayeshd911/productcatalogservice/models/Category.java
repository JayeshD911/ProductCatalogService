package io.github.jayeshd911.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;

//    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY) // This indicates that the "category" field in the Product entity is the owner of the relationship
//    @Fetch(FetchMode.SUBSELECT) // Use Lazy loading with subselect to avoid N+1 problem
//    private List<Product>  products;

    //OR use @BatchSize to optimize the fetching of collections with select as fetch mode
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5) // Adjust the batch size as needed
    private List<Product>  products;

}

/*
Product Category
  1 1
  M  1
  M : 1


  100K categories
approx 1 category has 100 products
  100K categories + 10^7  products = ~1M records
  1 record = 100 bytes
    100M bytes = 100 MB


Whenever you fetch collections, eager fetch is a very bad choice
because a lot of data at once will be brought to your working memory/RAM
which may potentially crash your application.

Never use eager in collections



  1. LAZY + SUB_SELECT = 2 queries
a. bringing all categories
b. bringing all products for each category (Can lead to memory explosion)
  2. use batch size (recommended)
a. bring all categories in first query
b. you'll run N/Batch size queries to bring products
i.e. 100K/1500 = ~67 queries



when you fetch an object from db,
all list types are fetched lazily by default,
so they are not fetched until you call the getter method of that list.
 */