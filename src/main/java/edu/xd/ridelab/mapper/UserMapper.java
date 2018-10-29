package edu.xd.ridelab.mapper;

import edu.xd.ridelab.model.UserModel;
import edu.xd.ridelab.vo.UserVO;

/**
 * @Author ChenXiang
 * @Date 2018/08/13,11:11
 */
public interface UserMapper {
    UserVO selectIdByName(String userId);

    UserModel selectByUserName(String userName);
}
