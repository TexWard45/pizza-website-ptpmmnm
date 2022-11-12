package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.GroupEntity;
import com.openedsource.pizzastore.database.repository.GroupRepository;
import com.openedsource.pizzastore.dto.GroupDto;
import com.openedsource.pizzastore.service.GroupService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/group")
@CrossOrigin(origins = "*")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Object> addGroup(@RequestBody GroupDto groupDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(groupDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            GroupEntity groupEntity = new GroupEntity();
            BeanUtils.copyProperties(groupDto, groupEntity);

            try {
                groupService.insertGroup(groupEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Object> updateGroup(@RequestBody(required = false) GroupDto groupDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(groupDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            GroupEntity groupEntity = new GroupEntity();
            BeanUtils.copyProperties(groupDto, groupEntity);
            if (groupRepository.findById(groupEntity.getId()).isPresent()) {
                groupService.updateGroup(groupEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                groupService.updateGroup(groupEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (groupRepository.findById(id).isPresent()) {
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");

            groupRepository.deleteById(id);
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "Group was not found");
        }
        return response;
    }

    private String inputcheck(GroupDto groupDto) {

        if (ValidateUtils.isNullOrEmpty(groupDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[GroupEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(groupDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(groupDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
