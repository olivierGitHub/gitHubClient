package com.prismic2.util.mapper;


import com.prismic2.dto.RepositoryDto;
import org.json.JSONObject;

public class RepositoriesMapper {


    static public RepositoryDto convert (JSONObject repositoryJson){
        RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setId((Integer) repositoryJson.get("id"));
        repositoryDto.setName((String) repositoryJson.get("name"));
        repositoryDto.setCommitsUrl(getCommitsUrl(repositoryJson));
        repositoryDto.setOwner(getOwner(repositoryJson));

        return repositoryDto;
    }

    static private String getOwner(JSONObject repositoryJson){
        return (String) new JSONObject(repositoryJson.get("owner").toString()).get("login");
    }

    static private String getCommitsUrl(JSONObject repositoryJson){
        String str = (String) repositoryJson.get("commits_url");
        return str.substring(0, str.length() -6);
    }
}
