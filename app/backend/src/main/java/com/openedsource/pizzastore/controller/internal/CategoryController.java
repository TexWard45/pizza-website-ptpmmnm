package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.CategoryEntity;
import com.openedsource.pizzastore.database.repository.CategoryRepository;
import com.openedsource.pizzastore.dto.CategoryDto;
import com.openedsource.pizzastore.dto.UserDto;
import com.openedsource.pizzastore.service.CategoryService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("internal")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/add")
    public ResponseEntity<Object> addCategory(@RequestBody(required = false) CategoryDto categoryDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(categoryDto);

        if (ValidateUtils.isNullOrEmpty(message)) {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(categoryDto, categoryEntity);

            try {
                categoryService.insertCategory(categoryEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/category/update")
    public ResponseEntity<Object> updateCategory(@RequestBody(required = false) CategoryDto categoryDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(categoryDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            CategoryEntity categoryEntity = new CategoryEntity();
            BeanUtils.copyProperties(categoryDto, categoryDto);

            categoryService.updateCategory(categoryEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(CategoryDto categoryDto) {

        if (ValidateUtils.isNullOrEmpty(categoryDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[UserEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(categoryDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(categoryDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}

