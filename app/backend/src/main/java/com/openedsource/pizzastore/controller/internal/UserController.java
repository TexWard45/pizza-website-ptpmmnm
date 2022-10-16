package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.UserEntity;
import com.openedsource.pizzastore.dto.UserDto;
import com.openedsource.pizzastore.service.UserService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Object> postUser(@RequestBody(required = false) UserDto userDto) {

        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(userDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDto, userEntity);

            try {
                userService.insertUser(userEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    private String inputcheck(UserDto userDto) {

        if (ValidateUtils.isNullOrEmpty(userDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[UserEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(userDto.getUsername())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Username" : ",Username"));
        }
        if (ValidateUtils.isNullOrEmpty(userDto.getPassword())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Password" : ",Password"));
        }
        if (ValidateUtils.isNullOrEmpty(userDto.getFullname())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Fullname" : ",Fullname"));
        }
        if (ValidateUtils.isTel(userDto.getEmail())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Email" : ",Email"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
