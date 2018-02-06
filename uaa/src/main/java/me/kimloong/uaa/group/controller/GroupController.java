package me.kimloong.uaa.group.controller;

import me.kimloong.uaa.group.entity.Group;
import me.kimloong.uaa.group.model.GroupMigration;
import me.kimloong.uaa.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 组织/节点 Controller
 *
 * @author KimLoong
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService service;

    /**
     * 新增群组
     *
     * @param group 群组信息
     * @return 新增后的群组信息
     */
    @PostMapping
    public Group add(@RequestBody Group group) {
        return service.add(group);
    }

    /**
     * 在指定群组下创建子群组
     *
     * @param parentId   指定群组id
     * @param childGroup 子群组信息
     * @return 新增后的子群组信息
     */
    @PostMapping("{parentId}/children")
    public Group addChild(
            @PathVariable String parentId,
            @RequestBody Group childGroup) {
        return service.addChild(parentId, childGroup);
    }

    /**
     * 关联两个群组为父子群组
     *
     * @param parentId 父群组id
     * @param childId  子群组id
     */
    @PostMapping(value = "{parentId}/children/{childId}")
    public void associate(
            @PathVariable String parentId,
            @PathVariable String childId) {
        service.associate(parentId, childId);
    }

    /**
     * 取消关联两个群组为父子群组
     *
     * @param parentId 父群组id
     * @param childId  子群组id
     */
    @DeleteMapping(value = "{parentId}/children/{childId}")
    public void disassociate(
            @PathVariable String parentId,
            @PathVariable String childId) {
        service.disassociate(parentId, childId);
    }


    @PatchMapping(value = "/parent")
    public void move(@RequestBody GroupMigration migration) {
        service.migrate(migration);
    }
}
