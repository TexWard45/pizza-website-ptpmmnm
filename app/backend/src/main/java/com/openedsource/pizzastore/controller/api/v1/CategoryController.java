package com.openedsource.pizzastore.controller.api.v1;

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

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategoryList(){
        List<Category> categoryList = categoryRepository.findAll();
        return ResponseEntity.ok().body(categoryList);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable(name = "id") Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return ResponseEntity.ok().body(category.get());
    }
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
    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id")Integer id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
