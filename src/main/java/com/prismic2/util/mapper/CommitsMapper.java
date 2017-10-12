package com.prismic2.util.mapper;


import com.prismic2.dto.CommitDto;
import org.json.JSONObject;

public class CommitsMapper {


    static public CommitDto convert(JSONObject commitJson){
        CommitDto commitDto = new CommitDto();
        commitDto.setSha((String) commitJson.get("sha"));
        commitDto.setCommitterName(findCommitterName(commitJson));
        commitDto.setMessage(findMessage(commitJson));
        commitDto.setDate(findCommitterDate(commitJson));

        return commitDto;
    }


    static private String findCommitterName(JSONObject commitJson){
        return (String) findSpecificValue2(commitJson, "commit", "committer", "name");
    }

    static private String findCommitterDate(JSONObject commitJson){
        return (String) findSpecificValue2(commitJson, "commit", "committer", "date");
    }

    static private String findMessage(JSONObject commitJson){
        return (String) findSpecificValue(commitJson, "commit", "message");
    }


    static private Object findSpecificValue(JSONObject repositoryJson, String parent, String child){
        if(!"".equals(repositoryJson.get(parent).toString().trim()) && !"null".equals(repositoryJson.get(parent).toString().trim()) ){
            return new JSONObject(repositoryJson.get(parent).toString()).get(child);
        }
        return  null;
    }

    static private Object findSpecificValue2(JSONObject repositoryJson, String parent, String child, String child2){
        if(!"".equals(repositoryJson.get(parent).toString().trim()) && !"null".equals(repositoryJson.get(parent).toString().trim()) ){
            JSONObject parentObject = new JSONObject(repositoryJson.get(parent).toString());
            if (parentObject !=null && !"".equals(parentObject.get(child).toString().trim()) && !"null".equals(parentObject.get(child).toString().trim()) ){
                JSONObject childObject = new JSONObject(parentObject.get(child).toString());
                if(childObject != null){
                    return childObject.get(child2);
                }
            }
        }
        return  null;
    }

}
