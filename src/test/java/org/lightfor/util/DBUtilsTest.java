package org.lightfor.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * db utils test
 * Created by Light on 2017/8/21.
 */
public class DBUtilsTest {

    @Test
    //700
    public void test(){
        DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "dev", "dev");
        DBUtils.setAutoCommit(false);
        long startTime = 0;
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            if(i % 10000 == 0){
                startTime = System.currentTimeMillis();
            }
            DBUtils.insert("insert into forum (name, parent_id) values ('11',10)");
            //break;
            if(i % 10000 == 9999){
                DBUtils.commit();
                System.out.println(System.currentTimeMillis() - startTime);
            }
        }
    }


    @Test
    //700
    public void test1(){
        DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "dev", "dev");
        DBUtils.setAutoCommit(false);
        long startTime = 0;
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            if(i % 10000 == 0){
                startTime = System.currentTimeMillis();
            }
            DBUtils.addBatch("insert into forum (name, parent_id) values ('11',10)");
            //break;
            if(i % 10000 == 9999){
                DBUtils.executeBatch();
                DBUtils.clearBatch();
                DBUtils.commit();
                System.out.println(System.currentTimeMillis() - startTime);
            }
        }
    }

    @Test
    //21000
    public void test3(){
        DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "dev", "dev");
        long startTime = 0;
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            if(i % 10000 == 0){
                startTime = System.currentTimeMillis();
            }
            DBUtils.insert("insert into forum (name, parent_id) values ('11',10)");
            if(i % 10000 == 9999){
                System.out.println(System.currentTimeMillis() - startTime);
            }
        }
    }

    @Test
    public void test4(){
        //DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "dev", "dev");
        DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:32783/dev", "dev", "dev");
        long startTime = 0;
        DBUtils.setAutoCommit(false);
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            if(i % 250 == 0){
                startTime = System.currentTimeMillis();
            }
            //3200 autoCommit
            //700 ??????commit
            //150 ????????????????????????
            //70 ????????????
            DBUtils.insert("insert into forum (name, parent_id) values ('11',10)");
            DBUtils.insert("insert into forum (name, parent_id) values ('11',10)");
            DBUtils.insert("insert into forum (name, parent_id) values ('11',10)");
            DBUtils.insert("insert into forum (name, parent_id) values ('11',10)");
            //DBUtils.update("update forum set name='root1' where id=2");
            //DBUtils.update("update forum set name='root2' where id=2");

            if(i % 250 == 249){
                DBUtils.commit();
                System.out.println(System.currentTimeMillis() - startTime);
            }
        }
    }

    @Test
    public void test5(){
        DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "dev", "dev");
        long startTime = 0;
        DBUtils.setAutoCommit(false);
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            if(i % 250 == 0){
                startTime = System.currentTimeMillis();
            }
            DBUtils.selectOne("select * from forum where id = 2");
            if(i % 250 == 249){
                System.out.println(System.currentTimeMillis() - startTime);
            }
        }
    }


    @Test
    public void test6(){
        DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:32779/dev", "dev", "dev");
        long startTime = 0;
        DBUtils.setAutoCommit(false);
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            if(i % 250 == 0){
                startTime = System.currentTimeMillis();
            }
            DBUtils.insert("insert into forum (name, parent_id) values ('"+i+"', "+i+")" );
            if(i % 250 == 249){
                System.out.println(System.currentTimeMillis() - startTime);
            }
        }
    }

    @Test
    // 2002 no index
    // 150 index
    public void test7(){
        DBUtils.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:32779/dev", "dev", "dev");
        long startTime = 0;
        for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
            if(i % 250 == 0){
                startTime = System.currentTimeMillis();
            }
            DBUtils.selectOne("select * from forum where name='"+i+"'" );
            if(i % 250 == 249){
                System.out.println(System.currentTimeMillis() - startTime);
            }
        }
    }
}
