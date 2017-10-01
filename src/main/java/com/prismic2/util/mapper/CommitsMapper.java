package com.prismic2.util.mapper;


import com.prismic2.dto.CommitDto;
import org.json.JSONObject;

public class CommitsMapper {


    static public CommitDto convert(JSONObject commitJson){
        CommitDto commitDto = new CommitDto();
        commitDto.setSha((String) commitJson.get("sha"));
        commitDto.setCommitterName(findCommitterName(commitJson));
        commitDto.setCommitterId(findCommitterId(commitJson));
        commitDto.setMessage(findMessage(commitJson));

        return commitDto;
    }


    static private String findCommitterName(JSONObject commitJson){
        return (String) findSpecificValue(commitJson, "committer", "login");
    }

    static private Integer findCommitterId(JSONObject commitJson){
        return (Integer) findSpecificValue(commitJson, "committer", "id");
    }

    static private String findMessage(JSONObject commitJson){
        return (String) findSpecificValue(commitJson, "commit", "message");
    }


    static private Object findSpecificValue(JSONObject repositoryJson, String parent, String child){
        return new JSONObject(repositoryJson.get(parent).toString()).get(child);
    }

}
