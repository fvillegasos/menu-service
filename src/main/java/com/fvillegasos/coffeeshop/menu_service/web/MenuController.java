package com.fvillegasos.coffeeshop.menu_service.web;

import com.fvillegasos.coffeeshop.menu_service.service.MenuService;
import com.fvillegasos.coffeeshop.menuservice.api.ProductsApi;
import com.fvillegasos.coffeeshop.menuservice.model.ProductInfo;
import com.fvillegasos.coffeeshop.menuservice.model.ProductItem;
import com.fvillegasos.coffeeshop.menuservice.model.ProductTypeEnum;
import com.fvillegasos.coffeeshop.menuservice.model.SavedProductItem;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController implements ProductsApi {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public ResponseEntity<List<ProductItem>> getProducts(ProductTypeEnum type, Boolean isEnabled) {
        return ResponseEntity.ok(menuService.getMenuItems(type, isEnabled));
    }

    @Override
    public ResponseEntity<SavedProductItem> disableProducts(String productId) {
        var result = menuService.disableMenuItem(productId);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<SavedProductItem> postProducts(ProductItem productItem) {
        var result = menuService.saveMenuItem(productItem);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(HttpStatus.SC_CREATED));
    }

    @Override
    public ResponseEntity<SavedProductItem> putProducts(String productId, ProductInfo productInfo) {
        return ResponseEntity.ok(menuService.updateMenuItem(productId, productInfo));
    }
}
