package cn.smileyan.demo.springbootjpa.dao;

import cn.smileyan.demo.springbootjpa.entity.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestDao extends CrudRepository<Test,Long> {

}
