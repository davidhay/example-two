package com.ealanta.exampletwo;

public class GitInfo {
    private final String gitSha;

    public GitInfo(String gitSha) {
        this.gitSha = gitSha;
    }

    public String getGitSha() {
        return gitSha;
    }
}
