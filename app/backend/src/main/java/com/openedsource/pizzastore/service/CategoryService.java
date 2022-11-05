package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.CategoryEntity;
import com.openedsource.pizzastore.database.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void insertCategory(CategoryEntity categoryEntity) {

        Optional<CategoryEntity> category = categoryRepository.findById(categoryEntity.getId());
        if (category.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        categoryRepository.save(categoryEntity);
    }

    public void updateCategory(CategoryEntity categoryEntity) {
        categoryRepository.save(categoryEntity);
    }
}
