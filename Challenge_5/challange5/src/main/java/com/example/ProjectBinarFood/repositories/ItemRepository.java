package com.example.ProjectBinarFood.repositories;


import com.example.ProjectBinarFood.models.Item;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ListCrudRepository<Item, Long> {

}