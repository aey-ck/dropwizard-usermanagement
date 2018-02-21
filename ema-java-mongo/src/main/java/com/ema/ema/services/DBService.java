package com.ema.ema.services;

import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.List;
import org.bson.Document;

public class DBService implements DBServiceInterface {

    private static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));;
    private static MongoDatabase database;
    private String dbString = "ema";

    public MongoCollection getCollection(String collectionName) {
        database = mongoClient.getDatabase(dbString);
        return  database.getCollection(collectionName);
    }

    public void deleteDocument(String id, String collectionName) {
        BasicDBObject queryObj = new BasicDBObject("_id", new ObjectId(id));
        this.getCollection(collectionName).deleteOne(queryObj);
    }

    public Document fetchDocument(List<Document> queryList, String collectionName) {
        AggregateIterable<org.bson.Document> aggregateIterable = this.getCollection(collectionName).aggregate(queryList);
        return Document.parse(aggregateIterable.first().toJson());
    }

    public Document insertDocument(String collectionName, Document document ) {
        this.getCollection(collectionName).insertOne(document);
        return document;
    }

    public void updateDocument(String collectionName, Document queryDocument, Document updatedDocument) {
        this.getCollection(collectionName).replaceOne(queryDocument, updatedDocument);
    }

}
