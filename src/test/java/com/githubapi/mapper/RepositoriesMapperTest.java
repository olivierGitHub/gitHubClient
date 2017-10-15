package com.githubapi.mapper;

import com.githubapi.dto.RepositoryDto;
import com.githubapi.util.mapper.RepositoriesMapper;
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
public class RepositoriesMapperTest {

    private final String REPOSITORY_JSON_STRING = "{\"id\":33642092,\"name\":\"AngularMaterialProject\",\"full_name\":\"olivierGitHub/AngularMaterialProject\",\"owner\":{\"login\":\"olivierGitHub\",\"id\":6985323,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/6985323?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/olivierGitHub\",\"html_url\":\"https://github.com/olivierGitHub\",\"followers_url\":\"https://api.github.com/users/olivierGitHub/followers\",\"following_url\":\"https://api.github.com/users/olivierGitHub/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/olivierGitHub/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/olivierGitHub/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/olivierGitHub/subscriptions\",\"organizations_url\":\"https://api.github.com/users/olivierGitHub/orgs\",\"repos_url\":\"https://api.github.com/users/olivierGitHub/repos\",\"events_url\":\"https://api.github.com/users/olivierGitHub/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/olivierGitHub/received_events\",\"type\":\"User\",\"site_admin\":false},\"private\":false,\"html_url\":\"https://github.com/olivierGitHub/AngularMaterialProject\",\"description\":\"just a quick test of Angular Material\",\"fork\":false,\"url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject\",\"forks_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/forks\",\"keys_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/teams\",\"hooks_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/hooks\",\"issue_events_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/events\",\"assignees_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/tags\",\"blobs_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/languages\",\"stargazers_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/stargazers\",\"contributors_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/contributors\",\"subscribers_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/subscribers\",\"subscription_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/subscription\",\"commits_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/merges\",\"archive_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/downloads\",\"issues_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/olivierGitHub/AngularMaterialProject/deployments\",\"created_at\":\"2015-04-09T01:38:46Z\",\"updated_at\":\"2015-04-09T01:40:40Z\",\"pushed_at\":\"2015-04-09T01:40:39Z\",\"git_url\":\"git://github.com/olivierGitHub/AngularMaterialProject.git\",\"ssh_url\":\"git@github.com:olivierGitHub/AngularMaterialProject.git\",\"clone_url\":\"https://github.com/olivierGitHub/AngularMaterialProject.git\",\"svn_url\":\"https://github.com/olivierGitHub/AngularMaterialProject\",\"homepage\":null,\"size\":1104,\"stargazers_count\":0,\"watchers_count\":0,\"language\":\"HTML\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":0,\"mirror_url\":null,\"open_issues_count\":0,\"forks\":0,\"open_issues\":0,\"watchers\":0,\"default_branch\":\"master\"}";

    @Autowired
    private RepositoriesMapper repositoriesMapper;


    @Test
    public void convert_should_transform_a_JSONObject_into_a_RepositoryDto() throws JSONException {
        // GIVEN
        JSONObject jsonObject = new JSONObject(REPOSITORY_JSON_STRING);

        // THEN
        RepositoryDto repositoryDto = repositoriesMapper.convert(jsonObject);

        // ASSERT
        Assert.assertNotNull(repositoryDto);
        Assert.assertEquals(repositoryDto.getId(), new Integer("33642092"));
        Assert.assertEquals(repositoryDto.getName(), "AngularMaterialProject");
        Assert.assertEquals(repositoryDto.getOwner(), "olivierGitHub");
        Assert.assertEquals(repositoryDto.getCommitsUrl(), "http://localhost:3000/commits/olivierGitHub/AngularMaterialProject");
    }

    }
