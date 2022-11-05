package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Group;
import com.openedsource.pizzastore.database.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/group")
    public ResponseEntity<List<Group>> getGroupList(){
        List<Group> groupList = groupRepository.findAll();
        return ResponseEntity.ok().body(groupList);
    }
    @GetMapping("/group/{id}")
    public ResponseEntity<Object> getGroup(@PathVariable(name = "id") Integer id){
        Optional<Group> group = groupRepository.findById(id);
        return ResponseEntity.ok().body(group.get());
    }
}
