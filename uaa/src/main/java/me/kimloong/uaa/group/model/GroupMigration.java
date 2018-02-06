package me.kimloong.uaa.group.model;

/**
 * 群组移动
 *
 * @author KimLoong
 */
public class GroupMigration {

    /**
     * 子群组id
     */
    private String childId;

    /**
     * 来源父群组id
     */
    private String fromParentId;

    /**
     * 目标父组id
     */
    private String toParentId;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getFromParentId() {
        return fromParentId;
    }

    public void setFromParentId(String fromParentId) {
        this.fromParentId = fromParentId;
    }

    public String getToParentId() {
        return toParentId;
    }

    public void setToParentId(String toParentId) {
        this.toParentId = toParentId;
    }
}
