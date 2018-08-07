package com.abubusoft.filmfinder.service.model;

import com.abubusoft.kripton.annotation.BindType;

import java.util.ArrayList;
import java.util.List;

@BindType
public class Search {
    public List<Film> getSearch() {
        return search;
    }

    public void setSearch(List<Film> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private List<Film> search;

    private String totalResults;

    private String response;
}
