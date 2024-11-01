package com.fvillegasos.coffeeshop.menu_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MenuItem {

    @Id
    private String id;

    private String name;

    private String price;

    private String type;

    private Boolean isEnabled;

    public MenuItem() {
    }

    public MenuItem(String id, String name, String price, String type, Boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.isEnabled = isEnabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
