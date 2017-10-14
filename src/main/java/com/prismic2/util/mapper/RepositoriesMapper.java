package com.prismic2.util.mapper;


import com.prismic2.dto.RepositoryDto;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RepositoriesMapper {


    private static String baseUrl = "http://localhost:3000/commits/";


    public RepositoryDto convert (JSONObject repositoryJson){
        RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setId((Integer) repositoryJson.get("id"));
        repositoryDto.setName((String) repositoryJson.get("name"));
        repositoryDto.setOwner(getOwner(repositoryJson));
        repositoryDto.setCommitsUrl(baseUrl + repositoryDto.getOwner() + "/" +repositoryDto.getName());

        return repositoryDto;
    }

    private String getOwner(JSONObject repositoryJson){
        return (String) new JSONObject(repositoryJson.get("owner").toString()).get("login");
    }
}
