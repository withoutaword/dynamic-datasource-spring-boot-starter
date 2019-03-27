package com.zhongxiaohua.samples.dao;

import com.zhongxiaohua.samples.domain.po.Demo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能：demo表对应数据库操作类
 *
 * @Author zhongshenghua
 * @Date 2019/3/26 16:57
 */
@Mapper
public interface DemoDao {

    /**
     * 插入一条记录
     * @param entity 实体类
     * @return 影响行数
     */
    int insert(Demo entity);

    /**
     * 查询全部记录
     * @return 记录列表
     */
    List<Demo> listByAll();
}
