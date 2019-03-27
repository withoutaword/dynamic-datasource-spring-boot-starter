package com.zhongxiaohua.samples.service;

import com.zhongxiaohua.samples.dao.DemoDao;
import com.zhongxiaohua.samples.domain.po.Demo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能：demo表对应业务类
 *
 * @Author zhongshenghua
 * @Date 2019/3/26 17:03
 */
@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    /**
     * 插入一条记录
     * @param entity 实体类
     * @return 影响行数
     */
    public int insert(Demo entity){
        return demoDao.insert(entity);
    }

    /**
     * 查询全部记录
     * @return 记录列表
     */
    public List<Demo> listByAll(){
        return demoDao.listByAll();
    }

    /**
     * 插入数据后马上查询记录
     * @param entity
     */
    @Transactional
    public List<Demo> insertAndSelect(Demo entity){
        this.insert(entity);
        return this.listByAll();
    }

}
