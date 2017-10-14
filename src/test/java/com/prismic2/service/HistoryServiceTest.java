package com.prismic2.service;


import com.prismic2.dao.commits.Commit;
import com.prismic2.dao.commits.CommitDao;
import com.prismic2.dao.repositories.Repository;
import com.prismic2.dao.repositories.RepositoryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryServiceTest {

    @Mock
    private RepositoryDao repositoryDao;

    @Mock
    private CommitDao commitDao;

    @InjectMocks
    private HistoryService historyService;

    @Test
    public void createRepository_should_called_save_method_of_repositoryDao(){

        // THEN
        historyService.createRepository("an url");

        // ASSERT
        Mockito.verify(repositoryDao, Mockito.times(1)).save(Mockito.any(Repository.class));
    }

    @Test
    public void createCommit_should_called_save_method_of_commitDao(){

        // THEN
        historyService.createCommit("an url");

        // ASSERT
        Mockito.verify(commitDao, Mockito.times(1)).save(Mockito.any(Commit.class));
    }
}
