package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.database.entity.Category;
import com.openedsource.pizzastore.database.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/category/add")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        Category newCategory =  categoryRepository.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/category/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category updateCategory = categoryRepository.save(category);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }
    @PutMapping("/category/update/{id}")
    public ResponseEntity<Category> updateBase(@RequestBody Category category,@PathVariable("id")Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        category.setId(id);
        Category updateCategory = categoryRepository.save(category);
        return new ResponseEntity<>(updateCategory,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id")Integer id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

