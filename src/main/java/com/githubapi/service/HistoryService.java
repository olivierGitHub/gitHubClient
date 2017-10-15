package com.githubapi.service;


import com.githubapi.dao.commits.Commit;
import com.githubapi.dao.commits.CommitDao;
import com.githubapi.dao.repositories.Repository;
import com.githubapi.dao.repositories.RepositoryDao;
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
        System.out.println("repo request saved");
    }

    /**
     *
     * @param url
     */
    public void createCommit(String url){
        Commit commit = new Commit();
        commit.setUrl(url);

        commitDao.save(commit);
        System.out.println("repo request saved");
    }
}
