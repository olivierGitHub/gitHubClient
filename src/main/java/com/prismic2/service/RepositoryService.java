package com.prismic2.service;

import com.prismic2.dto.RepositoryDto;
import com.prismic2.util.helper.JsonHelper;
import com.prismic2.util.helper.UrlHelper;
import com.prismic2.util.mapper.RepositoriesMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    @Autowired
    private JsonHelper jsonHelper;

    @Autowired
    private UrlHelper urlHelper;

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    public List<RepositoryDto> getAllRepositories(String user) throws Exception {
        String url = "https://api.github.com/users/" + user +"/repos";
        StringBuffer response = requestGitHubApi(url);
        List<JSONObject> responseList = jsonHelper.toJsonObjectList(response);

        return responseList.stream()
                .map(RepositoriesMapper::convert)
                .collect(Collectors.toList());
    }

    public RepositoryDto getRepository(String owner, String repo) throws Exception{
        String url = "https://api.github.com/repos/" +owner+ "/" + repo;
        StringBuffer response = requestGitHubApi(url);

        return RepositoriesMapper.convert(new JSONObject(response.toString()));
    }


    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    private StringBuffer requestGitHubApi(String url) throws Exception {
        HttpURLConnection con = urlHelper.getConnection(url);

        return urlHelper.getResponse(con);
    }


}
