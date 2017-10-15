package com.githubapi.util.helper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class JsonHelper {


    public List<JSONObject> toJsonObjectList(String response) {
        JSONArray jsonArray = new JSONArray(response);

        return IntStream.range(0, jsonArray.length())
                .mapToObj(i -> jsonArray.getJSONObject(i))
                .collect(Collectors.toList());
    }

}
