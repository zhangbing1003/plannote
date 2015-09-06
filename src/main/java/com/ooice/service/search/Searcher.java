package com.ooice.service.search;

import com.ooice.model.search.SearchResult;


public interface Searcher {
    public SearchResult search(String keyword);
    public SearchResult search(String keyword, int page);
}
