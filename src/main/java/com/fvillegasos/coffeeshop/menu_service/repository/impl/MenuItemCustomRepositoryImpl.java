package com.fvillegasos.coffeeshop.menu_service.repository.impl;

import com.fvillegasos.coffeeshop.menu_service.model.MenuItem;
import com.fvillegasos.coffeeshop.menu_service.repository.MenuItemCustomRepository;
import com.fvillegasos.coffeeshop.menuservice.model.ProductTypeEnum;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class MenuItemCustomRepositoryImpl implements MenuItemCustomRepository {

    private final MongoTemplate mongoTemplate;

    public MenuItemCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MenuItem> findAllByEnabled(String productType, Boolean isEnabled) {
        var query = new Query();
        if (!ProductTypeEnum.ALL.getValue().equals(productType)) {
            query.addCriteria(Criteria.where("type").is(productType));
        }
        if (isEnabled != null) {
            query.addCriteria(Criteria.where("isEnabled").is(isEnabled));
        }

        return mongoTemplate.find(query, MenuItem.class);
    }
}
