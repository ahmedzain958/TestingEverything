package com.hogp.testingeverything.futurestudio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubClient {

    @GET("/users/ahmedzain958/repos")
    Call<List<GitHubRepo>> getUserRepos();
}
