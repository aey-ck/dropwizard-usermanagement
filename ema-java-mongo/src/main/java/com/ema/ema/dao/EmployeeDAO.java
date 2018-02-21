package com.ema.ema.dao;

import com.ema.ema.model.Employee;
import com.ema.ema.services.DBService;
import com.ema.ema.utilities.serdeser.ObjectIDGsonDeserializer;
import com.ema.ema.utilities.serdeser.ObjectIDGsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    DBService mongoService = new DBService();
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(ObjectId.class, new ObjectIDGsonDeserializer())
            .registerTypeAdapter(ObjectId.class, new ObjectIDGsonSerializer())
            .setPrettyPrinting().create();
    private String employeeCollectionString = "employee";

    public EmployeeDAO() {

    }

    public ObjectId addEmployee(Employee employee) {
        String json = gson.toJson(employee);
        Document doc = mongoService.insertDocument(employeeCollectionString,Document.parse(json));
        ObjectId id = (ObjectId) doc.get("_id");
        return id;
    }

    public void updateEmployee(Employee employee) {
        String json = gson.toJson(employee);
        Document queryObj = new Document();
        queryObj.append("_id", new ObjectId(employee.getId()));
        mongoService.updateDocument(employeeCollectionString,queryObj,Document.parse(json));
    }

    public void removeEmployee(String employeeId) {
        mongoService.deleteDocument(employeeId,employeeCollectionString);
    }

    public Employee viewEmployee(String employeeId) {
        Document matchObj = new Document("$match",new Document("_id",new ObjectId(employeeId)));
        Document lookupObj = new Document("$lookup", new Document("from", "project").append("localField", "_id").append("foreignField", "employeeId").append("as", "lookup"));
        List<Document> queryList = new ArrayList<Document>();
        queryList.add(matchObj);
        queryList.add(lookupObj);
        Employee employee = gson.fromJson(mongoService.fetchDocument(queryList,employeeCollectionString).toJson(), Employee.class);
        return employee;
    }

}
