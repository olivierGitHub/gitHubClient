package com.githubapi.util.mapper;


import com.githubapi.dto.CommitDto;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommitsMapper {


    public CommitDto convert(JSONObject commitJson){
        CommitDto commitDto = new CommitDto();
        commitDto.setSha((String) commitJson.get("sha"));
        commitDto.setCommitterName(findCommitterName(commitJson));
        commitDto.setDate(findCommitterDate(commitJson));

        return commitDto;
    }


    private String findCommitterName(JSONObject commitJson){
        return (String) findSpecificValue(commitJson, "commit", "committer", "name");
    }

    private String findCommitterDate(JSONObject commitJson){
        String date = (String) findSpecificValue(commitJson, "commit", "committer", "date");
        return date.substring(0, 10);
    }

    private Object findSpecificValue(JSONObject repositoryJson, String parent, String child, String child2){
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
