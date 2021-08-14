package com.star.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.star.sys.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.sys.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Njq
 * @since 2021-08-09
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 分页查询用户列表
     * @param page      分页信息
     * @param userVo    查询条件对象
     * @return
     * @throws Exception
     */
    IPage<User> findUserListByPage(@Param("page") IPage<User> page, @Param("user") UserVo userVo) throws Exception;




}
