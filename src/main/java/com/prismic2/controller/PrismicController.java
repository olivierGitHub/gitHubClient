package com.prismic2.controller;

import com.prismic2.dto.CommitDto;
import com.prismic2.dto.RepositoryDto;
import com.prismic2.service.HistoryService;
import com.prismic2.service.CommitService;
import com.prismic2.service.RepositoryService;
import com.sun.xml.internal.ws.api.message.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PrismicController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private CommitService commitService;

    @Autowired
    private HistoryService historyService;

    /**
     *
     * @param user
     * @param request
     * @return
     */
    @GetMapping(value = "/repositories/{user}")
    public List<RepositoryDto> getAllRepositories(@PathVariable(value="user") String user, HttpServletRequest request) {


        try{
            CompletableFuture.runAsync(() -> {
                historyService.createRepository(request.getRequestURI());
            });
            System.out.println("all repos request will be saved");
            return repositoryService.getAllRepositories(user);
        }catch(Exception e){
            System.out.println(e);
        }
        return Collections.emptyList();
    }

    /**
     *
     * @param user
     * @param repository
     * @param request
     * @return
     */
    @GetMapping(value = "/repository/{user}/{repo}")
    public RepositoryDto getRepository(@PathVariable(value="user") String user,
                                              @PathVariable(value="repo") String repository,
                                              HttpServletRequest request) {
        try{
            CompletableFuture.runAsync(() -> {
                historyService.createRepository(request.getRequestURI());
            });
            System.out.println("single repo request will be saved");
            return repositoryService.getRepository(user, repository);
        }catch(Exception e){
            System.out.println("error repositories request");
        }
        return null;
    }

    /**
     *
     * @param user
     * @param repository
     * @param request
     * @return
     */
    @GetMapping(value = "/commits/{user}/{repo}")
    public List<CommitDto> getAllCommits(@PathVariable(value="user") String user,
                                         @PathVariable(value="repo") String repository,
                                         HttpServletRequest request) {
        try{
            CompletableFuture.runAsync(() -> {
                historyService.createCommit(request.getRequestURI());
            });
            System.out.println("all commits request will be saved");
            return commitService.getAllCommits(user, repository);
        }catch(Exception e){
            System.out.println("error commits request");
            System.out.println(e);
        }

        return null;
    }

    /**
     *
     * @param user
     * @param repository
     * @param sha
     * @param request
     * @return
     */
    @GetMapping(value = "/commit/{user}/{repo}/{sha}")
    public CommitDto getCommit(@PathVariable(value="user") String user,
                                       @PathVariable(value="repo") String repository,
                                       @PathVariable(value="sha") String sha,
                                       HttpServletRequest request) {
        try{
            CompletableFuture.runAsync(() -> {
                historyService.createCommit(request.getRequestURI());
            });
            System.out.println("single commit request will be saved");
            return commitService.getCommit(user, repository, sha);
        }catch(Exception e){
            System.out.println("error commit request");
        }
        return null;
    }


}
