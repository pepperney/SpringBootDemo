package cn.pepper.mapper;

import cn.pepper.model.Wechat;

public interface WechatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wechat record);

    int insertSelective(Wechat record);

    Wechat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wechat record);

    int updateByPrimaryKey(Wechat record);
}