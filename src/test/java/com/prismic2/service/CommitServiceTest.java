package com.prismic2.service;


import com.prismic2.dto.CommitDto;
import com.prismic2.util.helper.JsonHelper;
import com.prismic2.util.helper.UrlHelper;
import com.prismic2.util.mapper.CommitsMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.HttpURLConnection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommitServiceTest {

    private final String JSON_RESPONSE = "[{\"sha\":\"1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"commit\":{\"author\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:42:27Z\"},\"committer\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:42:27Z\"},\"message\":\"readme modified\",\"tree\":{\"sha\":\"51805b6044d4c89b147cef96af26a97c535c7a50\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/trees/51805b6044d4c89b147cef96af26a97c535c7a50\"},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"comments_url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1/comments\",\"author\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/9d0c454bc2163d2d4e12419e444af3632ec141a1\"}]},{\"sha\":\"9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"commit\":{\"author\":{\"name\":\"olivierGitHub\",\"email\":\"jacques-olivier.coulibaly@soat.fr\",\"date\":\"2017-06-04T19:38:34Z\"},\"committer\":{\"name\":\"GitHub\",\"email\":\"noreply@github.com\",\"date\":\"2017-06-04T19:38:34Z\"},\"message\":\"Create README.md\",\"tree\":{\"sha\":\"21ab54a4786c52d9c63433a2619d32662336986c\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/trees/21ab54a4786c52d9c63433a2619d32662336986c\"},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/commits/9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"comments_url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/9d0c454bc2163d2d4e12419e444af3632ec141a1/comments\",\"author\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"web-flow\",\"id\":19864447,\"avatar_url\":\"https://avatars3.githubusercontent.com/u/19864447?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/web-flow\",\"html_url\":\"https://github.com/web-flow\",\"followers_url\":\"https://api.github.com/users/web-flow/followers\",\"following_url\":\"https://api.github.com/users/web-flow/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/web-flow/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/web-flow/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/web-flow/subscriptions\",\"organizations_url\":\"https://api.github.com/users/web-flow/orgs\",\"repos_url\":\"https://api.github.com/users/web-flow/repos\",\"events_url\":\"https://api.github.com/users/web-flow/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/web-flow/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"22428e8262e3324ea602602ef1d48da888a6fda2\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/22428e8262e3324ea602602ef1d48da888a6fda2\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/22428e8262e3324ea602602ef1d48da888a6fda2\"}]},{\"sha\":\"22428e8262e3324ea602602ef1d48da888a6fda2\",\"commit\":{\"author\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:27:36Z\"},\"committer\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:27:36Z\"},\"message\":\"first commit\",\"tree\":{\"sha\":\"7e68a2a72afaa91267cbfc78d8f2dd0b0fe310fa\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/trees/7e68a2a72afaa91267cbfc78d8f2dd0b0fe310fa\"},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/commits/22428e8262e3324ea602602ef1d48da888a6fda2\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/22428e8262e3324ea602602ef1d48da888a6fda2\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/22428e8262e3324ea602602ef1d48da888a6fda2\",\"comments_url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/22428e8262e3324ea602602ef1d48da888a6fda2/comments\",\"author\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[]}]";
    private final String COMMIT_JSON_STRING = "{\"sha\":\"1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"commit\":{\"author\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:42:27Z\"},\"committer\":{\"name\":\"olivier\",\"email\":\"olivier_coulibaly@yahoo.fr\",\"date\":\"2017-06-04T19:42:27Z\"},\"message\":\"readme modified\",\"tree\":{\"sha\":\"51805b6044d4c89b147cef96af26a97c535c7a50\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/trees/51805b6044d4c89b147cef96af26a97c535c7a50\"},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/git/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"comment_count\":0,\"verification\":{\"verified\":false,\"reason\":\"unsigned\",\"signature\":null,\"payload\":null}},\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/1e83bea2d19d5e937f6e9549be03c341aae627c1\",\"comments_url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/1e83bea2d19d5e937f6e9549be03c341aae627c1/comments\",\"author\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"url\":\"https://api.github.com/repos/olivierGitHub/react-hello-world/commits/9d0c454bc2163d2d4e12419e444af3632ec141a1\",\"html_url\":\"https://github.com/olivierGitHub/react-hello-world/commit/9d0c454bc2163d2d4e12419e444af3632ec141a1\"}]}";

    @Mock
    private JsonHelper jsonHelper;

    @Mock
    private UrlHelper urlHelper;

    @Spy
    private CommitsMapper commitsMapper;

    @InjectMocks
    private CommitService commitService;

    @Test
    public void getAllCommits_should_return_a_list_of_CommitDto() throws Exception {
        // GIVEN
        HttpURLConnection httpURLConnection = null;

        // WHEN
        Mockito.when(urlHelper.getConnection(Mockito.anyString())).thenReturn(httpURLConnection);
        Mockito.when(urlHelper.getResponse(Mockito.any())).thenReturn(JSON_RESPONSE);
        Mockito.when(jsonHelper.toJsonObjectList(JSON_RESPONSE)).thenCallRealMethod();

        // THEN
        List<CommitDto> allCommits = commitService.getAllCommits("olivier", "react");

        // ASSERT
        Assert.assertNotNull(allCommits);
        Assert.assertEquals(allCommits.size(), 3);
        Assert.assertEquals(allCommits.get(0).getSha(), "1e83bea2d19d5e937f6e9549be03c341aae627c1");
        Assert.assertEquals(allCommits.get(1).getSha(), "9d0c454bc2163d2d4e12419e444af3632ec141a1");
        Assert.assertEquals(allCommits.get(2).getSha(), "22428e8262e3324ea602602ef1d48da888a6fda2");
    }

    @Test
    public void getAllCommits_should_return_an_empty_list() throws Exception {
        // GIVEN
        HttpURLConnection httpURLConnection = null;

        // WHEN
        Mockito.when(urlHelper.getConnection(Mockito.anyString())).thenReturn(httpURLConnection);
        Mockito.when(urlHelper.getResponse(Mockito.any())).thenReturn(JSON_RESPONSE);
        Mockito.when(jsonHelper.toJsonObjectList(JSON_RESPONSE)).thenCallRealMethod();

        // THEN
        List<CommitDto> allCommits = commitService.getAllCommits("olivier", " ");

        // ASSERT
        Assert.assertNotNull(allCommits);
        Assert.assertEquals(allCommits.size(), 0);
    }

    @Test
    public void getCommit_should_return_a_CommitDto() throws Exception {
        // GIVEN
        HttpURLConnection httpURLConnection = null;

        // WHEN
        Mockito.when(urlHelper.getConnection(Mockito.anyString())).thenReturn(httpURLConnection);
        Mockito.when(urlHelper.getResponse(Mockito.any())).thenReturn(COMMIT_JSON_STRING);

        // THEN
        CommitDto commitDto = commitService.getCommit("olivier", "react", "sha");

        // ASSERT
        Assert.assertNotNull(commitDto);
        Assert.assertEquals(commitDto.getSha(), "1e83bea2d19d5e937f6e9549be03c341aae627c1");
    }

    @Test
    public void getCommit_should_return_null() throws Exception {
        // GIVEN
        HttpURLConnection httpURLConnection = null;

        // WHEN
        Mockito.when(urlHelper.getConnection(Mockito.anyString())).thenReturn(httpURLConnection);
        Mockito.when(urlHelper.getResponse(Mockito.any())).thenReturn(COMMIT_JSON_STRING);

        // THEN
        CommitDto commitDto = commitService.getCommit("olivier", "react", " ");

        // ASSERT
        Assert.assertNull(commitDto);
    }
}
