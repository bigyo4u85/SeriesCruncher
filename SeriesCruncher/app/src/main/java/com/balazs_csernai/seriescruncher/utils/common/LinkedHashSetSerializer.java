package com.balazs_csernai.seriescruncher.utils.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Erik_Markus_Kramli on 2016-02-04.
 */
public class LinkedHashSetSerializer implements JsonSerializer<Set<String>>, JsonDeserializer<Set<String>> {

    @Override
    public JsonElement serialize(Set<String> src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return null;

        } else {
            JsonArray jsonArray = new JsonArray();
            for (String item : src) {
                jsonArray.add(context.serialize(item));
            }
            return jsonArray;
        }
    }

    @Override
    public Set<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Set<String> set = new LinkedHashSet<>();
        JsonArray jsonArray = json.getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            String item = context.deserialize(jsonElement, String.class);
            set.add(item);
        }
        return set;
    }
}
