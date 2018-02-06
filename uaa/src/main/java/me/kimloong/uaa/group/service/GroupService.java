package me.kimloong.uaa.group.service;

import me.kimloong.uaa.group.Groups;
import me.kimloong.uaa.group.entity.ChildOf;
import me.kimloong.uaa.group.entity.Group;
import me.kimloong.uaa.group.entity.GroupType;
import me.kimloong.uaa.group.model.GroupMigration;
import me.kimloong.uaa.group.repository.ChildOfRepository;
import me.kimloong.uaa.group.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 群组 Service
 *
 * @author KimLoong
 */
@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private ChildOfRepository childOfRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        Group commonGroup = findCommon();
        if (null != commonGroup) {
            return;
        }
        commonGroup = new Group();
        commonGroup.setName(Groups.COMMON);
        commonGroup.setDisplayName("公共");
        commonGroup.setType(GroupType.VIRTUAL);
        add(commonGroup);
    }

    public Optional<Group> findOne(String id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    public Group findStrictOne(String id) {
        return findOne(id)
                .orElseThrow(() -> new RuntimeException());
    }

    public Group findCommon() {
        return repository.findByName(Groups.COMMON);
    }

    public Group add(Group group) {
        group.setId(null);
        return repository.save(group);
    }

    public Group addChild(String parentId, Group childGroup) {
        Group parentGroup = findStrictOne(parentId);
        add(childGroup);
        associate(parentGroup, childGroup);
        return childGroup;
    }

    public void associate(String parentId, String childId) {
        Group parentGroup = findStrictOne(parentId);
        Group childGroup = findStrictOne(childId);
        associate(parentGroup, childGroup);
    }

    public void disassociate(String parentId, String childId) {
        childOfRepository.deleteByParentIdAndChildId(parentId, childId);
    }

    public void migrate(GroupMigration migration) {
        associate(migration.getToParentId(), migration.getChildId());
        disassociate(migration.getFromParentId(), migration.getChildId());
    }

    private void associate(Group parentGroup, Group childGroup) {
        ChildOf childOf = new ChildOf();
        childOf.setParent(parentGroup);
        childOf.setChild(childGroup);
        childOfRepository.save(childOf);
    }
}
