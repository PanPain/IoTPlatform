package edu.xd.ridelab.mapper;

import edu.xd.ridelab.vo.RoleVO;

import java.util.List;

/**
 * @Author ChenXiang
 * @Date 2018/08/14,17:28
 */
public interface RoleMapper {
    List<RoleVO> selectById(Long roleId);
}
