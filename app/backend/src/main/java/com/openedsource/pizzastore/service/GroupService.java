package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.GroupEntity;
import com.openedsource.pizzastore.database.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public void insertGroup(GroupEntity groupEntity) {

        Optional<GroupEntity> group = groupRepository.findById(groupEntity.getId());
        if (group.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        groupRepository.save(groupEntity);
    }

    public void updateGroup(GroupEntity groupEntity) {
        groupRepository.save(groupEntity);
    }
}
