package com.ema.ema.dao;

import com.ema.ema.model.Project;
import com.ema.ema.services.DBService;
import com.ema.ema.utilities.serdeser.ObjectIDGsonDeserializer;
import com.ema.ema.utilities.serdeser.ObjectIDGsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.bson.types.ObjectId;


public class ProjectDAO {

    public ProjectDAO(){

    }

    public void addProject(Project project){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ObjectId.class, new ObjectIDGsonDeserializer())
                .registerTypeAdapter(ObjectId.class, new ObjectIDGsonSerializer())
                .setPrettyPrinting().create();
        String json = gson.toJson(project);
        Document projectDoc = Document.parse(json);
        new DBService().getCollection("project").insertOne(projectDoc);
    }

}
