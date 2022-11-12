package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.CategoryEntity;
import com.openedsource.pizzastore.database.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getCategoryList(){
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        return ResponseEntity.ok().body(categoryList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable(name = "id") Integer id){
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        return ResponseEntity.ok().body(category.get());
    }
}
