package com.ema.ema.services;

import org.bson.Document;

import java.util.List;


public interface DBServiceInterface {

    public Document fetchDocument(List<Document> queryList, String collectionName);
    public Document insertDocument(String collectionName, Document document);
    public void updateDocument(String collectionName, Document queryDocument, Document updatedDocument);
    public void deleteDocument(String id, String collectionName);

}
