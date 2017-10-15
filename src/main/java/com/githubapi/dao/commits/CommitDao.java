package com.githubapi.dao.commits;

import org.springframework.data.repository.CrudRepository;


public interface CommitDao extends CrudRepository<Commit, Integer> {
}
