package com.fvillegasos.coffeeshop.menu_service.service;

import com.fvillegasos.coffeeshop.menu_service.model.MenuItem;
import com.fvillegasos.coffeeshop.menuservice.model.ProductInfo;
import com.fvillegasos.coffeeshop.menuservice.model.ProductItem;
import com.fvillegasos.coffeeshop.menuservice.model.ProductTypeEnum;
import com.fvillegasos.coffeeshop.menuservice.model.SavedProductItem;

import java.util.List;

public interface MenuService {

    List<ProductItem> getMenuItems(ProductTypeEnum type, Boolean isEnabled);

    SavedProductItem disableMenuItem(String productId);

    SavedProductItem saveMenuItem(ProductItem productItem);

    SavedProductItem updateMenuItem(String productId, ProductInfo productInfo);

    MenuItem findMenuItem(String id);

}
