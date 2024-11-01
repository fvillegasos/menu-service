package com.fvillegasos.coffeeshop.menu_service.mapper;

import com.fvillegasos.coffeeshop.menu_service.model.MenuItem;
import com.fvillegasos.coffeeshop.menuservice.model.ProductInfo;
import com.fvillegasos.coffeeshop.menuservice.model.ProductItem;
import com.fvillegasos.coffeeshop.menuservice.model.SavedProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItem fromProductItemToMenuItem(ProductItem source);

    ProductItem fromMenuItemToProductItem(MenuItem source);

    List<ProductItem> fromMenuItemListToProductItemList(List<MenuItem> source);

    void updateMenuItemFromApiDomain(@MappingTarget MenuItem menuItem, ProductInfo apiDomain);

    @Mapping(target = "id", source = "productId")
    @Mapping(target = "savedAt", expression = "java(getNowDate())")
    SavedProductItem generateSavedProductItemInfo(String productId);

    default OffsetDateTime getNowDate() {
        return OffsetDateTime.now();
    }

}
