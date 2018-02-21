package com.ema.ema.utilities.serdeser;

import com.google.gson.*;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;

public  class ObjectIDGsonDeserializer implements JsonDeserializer<ObjectId> {
    @Override
    public ObjectId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.has("$oid")) {
            JsonElement elem = jsonObject.get("$oid");
            if (elem != null && !elem.isJsonNull()) {
                return new ObjectId(elem.getAsString());
            }
        }
        return null;
    }
}