package com.xucheng.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Nick zhang
 * @Date: 2019/7/18 17:24
 */
public class MongoDBDemo {
   static MongoDatabase database = MongoDBUtil.getConnect();

    public static void main(String[] args) {
        //insertOneTest();
//        insertManyTest();
//        deleteOneTest();
//        updateOneTest();
//        updateManyTest();
//        findTest();
//        filterFindTest();
        firstFindTest();
    }

    public static void firstFindTest(){
        MongoCollection<Document> collection = database.getCollection("userList");
        FindIterable<Document> documents = collection.find();
        Document first = documents.first();
        System.out.println(first);
    }

    public static void filterFindTest(){
        MongoCollection<Document> collection = database.getCollection("userList");

        Bson filter = Filters.eq("sex","女");
        FindIterable<Document> documents = collection.find(filter);

        MongoCursor<Document> iterator = documents.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void findTest() {
        MongoCollection<Document> collection = database.getCollection("userList");

        FindIterable<Document> documents = collection.find();

        MongoCursor<Document> iterator = documents.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void updateManyTest() {
        MongoCollection<Document> collection = database.getCollection("userList");

        Bson filter = Filters.eq("sex","男");

        Document document = new Document("$set",new Document("age",99));

        collection.updateMany(filter,document);
    }

    public static void updateOneTest(){
        MongoCollection<Document> collection = database.getCollection("userList");

        //修改过滤器
        Bson filter = Filters.eq("name","nihao");

        //指定修改的更新文档
        Document document = new Document("$set",new Document("sex","女"));

        collection.updateOne(filter,document);

    }

    public static void deleteOneTest() {
        MongoCollection<Document> collection = database.getCollection("userList");

        //申明删除条件
        Bson filter = Filters.eq("anme","zhangsan");

        collection.deleteOne(filter);
    }

    public static void insertManyTest () {
        MongoCollection<Document> collection = database.getCollection("userList");

        List<Document> list = new ArrayList<>();
        for (int i=0;i<3;i++) {
            Document document = new Document("name","lisi"+i).append("age",88+i).append("sex","男");

            list.add(document);
        }

        collection.insertMany(list);
    }

    public static void insertOneTest () {
        MongoCollection<Document> collection = database.getCollection("userList");

        Document document = new Document("name","lisi").append("age",88).append("sex","男");

        collection.insertOne(document);
    }



}
