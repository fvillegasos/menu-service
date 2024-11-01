package com.fvillegasos.coffeeshop.menu_service.service.impl;

import com.fvillegasos.coffeeshop.menu_service.exception.CustomErrorTypeEnum;
import com.fvillegasos.coffeeshop.menu_service.exception.CustomHttpException;
import com.fvillegasos.coffeeshop.menu_service.mapper.MenuItemMapper;
import com.fvillegasos.coffeeshop.menu_service.model.MenuItem;
import com.fvillegasos.coffeeshop.menu_service.repository.MenuItemRepository;
import com.fvillegasos.coffeeshop.menu_service.service.MenuService;
import com.fvillegasos.coffeeshop.menuservice.model.ProductInfo;
import com.fvillegasos.coffeeshop.menuservice.model.ProductItem;
import com.fvillegasos.coffeeshop.menuservice.model.ProductTypeEnum;
import com.fvillegasos.coffeeshop.menuservice.model.SavedProductItem;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuServiceImpl(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    public List<ProductItem> getMenuItems(ProductTypeEnum type, Boolean isEnabled) {
        var menuItems = menuItemRepository.findAllByEnabled(type.getValue(), isEnabled);

        if (CollectionUtils.isEmpty(menuItems)) {
            throw CustomHttpException.of(CustomErrorTypeEnum.NOT_FOUND_ANY_PRODUCTS);
        }

        return menuItemMapper.fromMenuItemListToProductItemList(menuItems);
    }

    @Override
    public SavedProductItem disableMenuItem(String productId) {
        var menuItem = this.findMenuItem(productId);

        if (Boolean.FALSE.equals(menuItem.getIsEnabled())) {
            return null; //null for handling 204 response
        }

        menuItem.setIsEnabled(false);
        menuItemRepository.save(menuItem);

        return menuItemMapper.generateSavedProductItemInfo(productId);
    }

    @Override
    public SavedProductItem saveMenuItem(ProductItem productItem) {
        var menuItem = menuItemMapper.fromProductItemToMenuItem(productItem);

        var savedMenuItem = menuItemRepository.save(menuItem);

        return menuItemMapper.generateSavedProductItemInfo(savedMenuItem.getId());
    }

    @Override
    public SavedProductItem updateMenuItem(String productId, ProductInfo productInfo) {
        var menuItem = this.findMenuItem(productId);

        menuItemMapper.updateMenuItemFromApiDomain(menuItem, productInfo);

        menuItemRepository.save(menuItem);

        return menuItemMapper.generateSavedProductItemInfo(productId);
    }

    @Override
    public MenuItem findMenuItem(String id) {
        var menuItemOpt = menuItemRepository.findById(id);
        return menuItemOpt.orElseThrow(() ->
                CustomHttpException.of(CustomErrorTypeEnum.NOT_FOUND_ID_PRODUCT));
    }
}
