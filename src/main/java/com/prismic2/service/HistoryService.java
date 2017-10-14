package com.prismic2.service;


import com.prismic2.dao.commits.Commit;
import com.prismic2.dao.commits.CommitDao;
import com.prismic2.dao.repositories.Repository;
import com.prismic2.dao.repositories.RepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HistoryService {


    @Autowired
    private RepositoryDao repositoryDao;

    @Autowired
    private CommitDao commitDao;

    /**
     *
     * @param url
     */
    public void createRepository(String url){
        Repository repository = new Repository();
        repository.setUrl(url);

        repositoryDao.save(repository);
    }

    /**
     *
     * @param url
     */
    public void createCommit(String url){
        Commit commit = new Commit();
        commit.setUrl(url);

        commitDao.save(commit);
    }
}
