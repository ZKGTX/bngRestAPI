package com.trch.utility;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonConverter<E> {
	
	private final Gson gson;

    public JsonConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertToJson(List<E> elements, String objectNames) {

        JsonArray array = gson.toJsonTree(elements).getAsJsonArray();
        
        JsonObject jsonObject = new JsonObject();
        
        jsonObject.add(objectNames, array);

        return jsonObject.toString();
    }
    
    public String convertToJson (E elements, String objectNames) {
    	
    	JsonObject obj = gson.toJsonTree(elements).getAsJsonObject();
        
        JsonObject jsonObject = new JsonObject();
        
        jsonObject.add(objectNames, obj);

        return jsonObject.toString();
    }
    
    
    
    

}
