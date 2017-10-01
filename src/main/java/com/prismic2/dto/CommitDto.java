package com.prismic2.dto;


import java.io.Serializable;

public class CommitDto implements Serializable {

    private String sha;
    private String committerName;
    private Integer committerId;
    private String message;


    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getCommitterName() {
        return committerName;
    }

    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    public Integer getCommitterId() {
        return committerId;
    }

    public void setCommitterId(Integer committerId) {
        this.committerId = committerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
