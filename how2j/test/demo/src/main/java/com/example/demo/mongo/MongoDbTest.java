package com.example.demo.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author chenxin
 * @date 2019/8/22 8:53
 */
public class MongoDbTest {
   static MongoDatabase mongoDatabase ;
    @BeforeClass
    public  static void createClent(){
        try {
            MongoClient mongoClient = new MongoClient("192.168.254.130",27017);
            mongoDatabase = mongoClient.getDatabase("warehouse");
            System.out.println(mongoDatabase);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void collectionTest(){
         mongoDatabase.createCollection("mycol1");
         MongoCollection<Document> mycol = mongoDatabase.getCollection("mycol1");
        Document doc = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("url", "//www.w3cschool.cn/mongodb/").
                append("by", "w3cschool.cn");
        mycol.insertOne(doc);

        FindIterable<Document> documents = mycol.find();
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    @Test
    public void dropCol(){
        MongoCollection<Document> mycol = mongoDatabase.getCollection("mycol");
        MongoCollection<Document> mycol1 = mongoDatabase.getCollection("mycol1");
        mycol.drop();
        mycol1.drop();
    }


















































































}
