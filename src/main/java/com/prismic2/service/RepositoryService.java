package com.prismic2.service;

import com.prismic2.dto.RepositoryDto;
import com.prismic2.util.helper.JsonHelper;
import com.prismic2.util.helper.UrlHelper;
import com.prismic2.util.mapper.RepositoriesMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    @Autowired
    private JsonHelper jsonHelper;

    @Autowired
    private UrlHelper urlHelper;

    @Autowired
    private RepositoriesMapper repositoriesMapper;

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    public List<RepositoryDto> getAllRepositories(String user) throws Exception {
        if(checkString(user)){
            return Collections.emptyList();
        }
        String url = "https://api.github.com/users/" + user +"/repos";
        String response = requestGitHubApi(url);
        List<JSONObject> responseList = jsonHelper.toJsonObjectList(response);

        return responseList.stream()
                .map(repositoriesMapper::convert)
                .collect(Collectors.toList());
    }

    public RepositoryDto getRepository(String owner, String repo) throws Exception{
        if(checkString(owner) || checkString(repo)){
            return null;
        }
        String url = "https://api.github.com/repos/" +owner+ "/" + repo;
        String response = requestGitHubApi(url);

        return repositoriesMapper.convert(new JSONObject(response));
    }


    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    private String requestGitHubApi(String url) throws Exception {
        HttpURLConnection con = urlHelper.getConnection(url);

        return urlHelper.getResponse(con);
    }

    private Boolean checkString(String str){
        return str == null || "".equals(str.trim());
    }

}
