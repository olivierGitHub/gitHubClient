package com.prismic2.util.helper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonHelper {


    public List<JSONObject> toJsonObjectList(String response) {
        JSONArray jsonArray = new JSONArray(response);
        List<JSONObject> repositoriesList = new ArrayList<>();
        Iterator<Object> iterator = jsonArray.iterator();

        while (iterator.hasNext())
            repositoriesList.add((JSONObject)iterator.next());

        return repositoriesList;
    }

}
