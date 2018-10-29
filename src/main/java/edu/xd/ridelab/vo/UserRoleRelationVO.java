package edu.xd.ridelab.vo;

/**
 * @Author ChenXiang
 * @Date 2018/08/14,19:17
 */
public class UserRoleRelationVO {
    private Long relationId;
    private Long fkUserId;
    private Long fkRoleId;

    public UserRoleRelationVO(){

    }

    public UserRoleRelationVO(Long relationId, Long fkUserId, Long fkRoleId) {
        this.relationId = relationId;
        this.fkUserId = fkUserId;
        this.fkRoleId = fkRoleId;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Long getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
    }
}
