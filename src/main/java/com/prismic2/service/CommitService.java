package com.prismic2.service;


import com.prismic2.dto.CommitDto;
import com.prismic2.util.helper.JsonHelper;
import com.prismic2.util.helper.UrlHelper;
import com.prismic2.util.mapper.CommitsMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommitService {

    private static final int MAX = 100;

    @Autowired
    private JsonHelper jsonHelper;

    @Autowired
    private UrlHelper urlHelper;

    @Autowired
    private CommitsMapper commitsMapper;

    /**
     *
     * @param user
     * @param repository
     * @return
     * @throws Exception
     */
    public List<CommitDto> getAllCommits(String user, String repository) throws Exception {
        if(checkString(user) || checkString(repository)){
            return Collections.emptyList();
        }
        String url = "https://api.github.com/repos/"+ user +"/"+ repository+ "/commits";
        String response = requestGitHubApi(url);
        List<JSONObject> responseList = jsonHelper.toJsonObjectList(response);
        if(responseList.size() > MAX){
            responseList = responseList.subList(0, MAX);
        }

        return responseList.stream()
                .map(commitsMapper::convert)
                .collect(Collectors.toList());
    }

    public CommitDto getCommit(String owner, String repo, String sha) throws Exception{
        if(checkString(owner) || checkString(repo) || checkString(sha)){
            return null;
        }
        String url = "https://api.github.com/repos/" +owner+"/"+repo+"/commits/"+ sha;
        String response = requestGitHubApi(url);

        return commitsMapper.convert(new JSONObject(response));
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
