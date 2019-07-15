package cn.smileyan.demo.springbootjpa.service.impl;

import cn.smileyan.demo.springbootjpa.dao.TestDao;
import cn.smileyan.demo.springbootjpa.entity.Test;
import cn.smileyan.demo.springbootjpa.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public long save(Test test) {
       testDao.save(test);
        return 0;
    }
}
