package com.fvillegasos.coffeeshop.menu_service.repository;

import com.fvillegasos.coffeeshop.menu_service.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuItemRepository extends MongoRepository<MenuItem, String>, MenuItemCustomRepository {

}
