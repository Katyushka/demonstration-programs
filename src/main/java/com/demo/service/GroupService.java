package com.demo.service;

import com.demo.domain.Group;
import com.demo.domain.GroupCreateForm;
import com.demo.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ekaterina Pyataeva on 30.04.2017.
 */


@Service
@Transactional
public class GroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private GroupRepository groupRepository;


    public List<Group> getAllGroups(){
        List<Group> groups = new ArrayList<>();
        groups.addAll(groupRepository.findAll());
        return groups;
    }


    public Group getGroupById(Long id){
        return groupRepository.findOne(id);
    }

    public Group create(GroupCreateForm form){
        Group group = new Group();
        group.setName(form.getName());
        return groupRepository.save(group);
    }

    public Group save(Group group){
        return groupRepository.save(group);
    }

    public void delete(Group group){
        groupRepository.delete(group);
    }


}