package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.GroupEntity;
import com.openedsource.pizzastore.database.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/group")
@CrossOrigin(origins = "*")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public ResponseEntity<List<GroupEntity>> getGroupList(){
        List<GroupEntity> groupList = groupRepository.findAll();
        return ResponseEntity.ok().body(groupList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getGroup(@PathVariable(name = "id") Integer id){
        Optional<GroupEntity> group = groupRepository.findById(id);
        return ResponseEntity.ok().body(group.get());
    }
}
