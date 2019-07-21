package com.xucheng.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Nick zhang
 * @Date: 2019/7/19 9:19
 */
public class MongoDBUtil {

    //获取不需要验证的数据库连接对象
    public static MongoDatabase getConnect(){
        //连接到mongodb服务
        MongoClient mongoClient = new MongoClient("localhost",27017);
        //连接到数据库
        MongoDatabase database = mongoClient.getDatabase("test");
        //返回连接数据库对象
        return database;
    }

    //获取需要密码验证的数据库连接对象
    public static MongoDatabase getConnect2(){
        List<ServerAddress> adds = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        adds.add(serverAddress);


        List<MongoCredential> credentials = new ArrayList<>();
        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
        credentials.add(mongoCredential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(adds, credentials);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

        //返回连接数据库对象
        return mongoDatabase;

    }

}
