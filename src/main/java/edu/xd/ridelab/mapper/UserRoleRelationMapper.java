package edu.xd.ridelab.mapper;

/**
 * @Author ChenXiang
 * @Date 2018/08/14,19:19
 */
public interface UserRoleRelationMapper {
    Long selectRoleIdByUserId(Long userId);
}
