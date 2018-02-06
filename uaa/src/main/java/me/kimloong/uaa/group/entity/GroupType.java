package me.kimloong.uaa.group.entity;

/**
 * @author KimLoong
 */
public enum GroupType {

    /**
     * 节点
     */
    NODE(false),

    /**
     * 实体组织
     */
    REAL(true),

    /**
     * 虚拟
     */
    VIRTUAL(true);

    /**
     * 是否是一个端点，端点才允许使用此进行登录
     */
    private boolean endpoint;

    GroupType(boolean endpoint) {
        this.endpoint = endpoint;
    }
}
