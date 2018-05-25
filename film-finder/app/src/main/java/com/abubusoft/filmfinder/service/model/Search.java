package com.abubusoft.filmfinder.service.model;

import com.abubusoft.kripton.annotation.BindType;

import java.util.ArrayList;
import java.util.List;

@BindType
public class Search {
    private List<Film> Search;

    public List<Film> getSearch() {
        return this.Search;
    }

    public void setSearch(List<Film> Search) {
        this.Search = Search;
    }

    private String totalResults;

    public String getTotalResults() {
        return this.totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    private String Response;

    public String getResponse() {
        return this.Response;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }
}
