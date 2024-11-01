package com.fvillegasos.coffeeshop.menu_service.repository;

import com.fvillegasos.coffeeshop.menu_service.model.MenuItem;

import java.util.List;

public interface MenuItemCustomRepository {

    List<MenuItem> findAllByEnabled(String productType, Boolean isEnabled);

}
