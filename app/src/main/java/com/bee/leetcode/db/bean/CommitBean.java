package com.bee.leetcode.db.bean;

//提交记录接口中的Array中的字段

public class CommitBean {
    private String memory;
    private String commitResult;
    private String commitTime;
    private String runtime;

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCommitResult() {
        return commitResult;
    }

    public void setCommitResult(String commitResult) {
        this.commitResult = commitResult;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
