package com.boot.multi.datasource;

import com.boot.multi.datasource.mybatis.dao.primary.UserDao;
import com.boot.multi.datasource.mybatis.dao.server.RoleDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author Xingyu Sun
 */
@SpringBootApplication
public class DatasourceApplication implements CommandLineRunner {

    @Resource
    private UserDao dao;

    @Resource
    @Qualifier(ApplicationProperties.SECOND)
    private RoleDao roleDao;

    public static void main(String[] args) {
        SpringApplication.run(DatasourceApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        dao.findAll().forEach(System.out::println);
        roleDao.findAll().forEach(System.out::println);
    }
}
