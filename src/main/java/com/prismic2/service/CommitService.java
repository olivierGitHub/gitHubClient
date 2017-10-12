package com.prismic2.service;


import com.prismic2.dto.CommitDto;
import com.prismic2.util.helper.JsonHelper;
import com.prismic2.util.helper.UrlHelper;
import com.prismic2.util.mapper.CommitsMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommitService {

    @Autowired
    private JsonHelper jsonHelper;

    @Autowired
    private UrlHelper urlHelper;

    /**
     *
     * @param user
     * @param repository
     * @return
     * @throws Exception
     */
    public List<CommitDto> getAllCommits(String user, String repository) throws Exception {
        String url = "https://api.github.com/repos/"+ user +"/"+ repository+ "/commits";
        String response = requestGitHubApi(url);
        List<JSONObject> responseList = jsonHelper.toJsonObjectList(response);

        return responseList.stream()
                .map(CommitsMapper::convert)
                .collect(Collectors.toList());
    }

    public CommitDto getCommit(String owner, String repo, String sha) throws Exception{
        String url = "https://api.github.com/repos/" +owner+"/"+repo+"/commits/"+ sha;
        String response = requestGitHubApi(url);

        return CommitsMapper.convert(new JSONObject(response));
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


}
