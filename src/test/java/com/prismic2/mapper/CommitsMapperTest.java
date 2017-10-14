package com.prismic2.mapper;


import com.prismic2.dto.CommitDto;
import com.prismic2.util.mapper.CommitsMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommitsMapperTest {

    private final String COMMIT_JSON_STRING = "{\"sha\":\"1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"commit\":{\"author\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:42:27Z\"},\"committer\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:42:27Z\"},\"message\":\"readme modified\",\"tree\":{\"sha\":\"51805b6044d4c89b147cef96af26a97c535c7a50\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/trees/51805b6044d4c89b147cef96af26a97c535c7a50\"},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"comments_url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1/comments\",\"author\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/9d0c454bc2163d2d4e12419e444af3632ec141a1\"}]}";

    @Autowired
    CommitsMapper commitsMapper;


    @Test
    public void convert_should_transform_a_JSONObject_into_a_CommitDto() throws JSONException {
        // GIVEN
        JSONObject jsonObject = new JSONObject(COMMIT_JSON_STRING);

        // THEN
        CommitDto commitDto = commitsMapper.convert(jsonObject);

        // ASSERT
        Assert.assertNotNull(commitDto);
        Assert.assertEquals(commitDto.getSha(), "1e83bea2d19d5e937f6e9549be03c341aae627c1");
        Assert.assertEquals(commitDto.getCommitterName(), "olivier");
    }
}
