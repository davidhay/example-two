package com.ealanta.exampletwo;

public class GitInfo {
    private String gitSha;

    public GitInfo(String gitSha) {
        this.gitSha = gitSha;
    }

    public GitInfo(){
    }

    public void setGitSha(String gitSha){
        this.gitSha = gitSha;
    }

    public String getGitSha() {
        return gitSha;
    }
}
