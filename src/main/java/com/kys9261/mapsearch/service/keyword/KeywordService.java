package com.kys9261.mapsearch.service.keyword;

import com.kys9261.mapsearch.model.keyword.PopularKeyword;
import com.kys9261.mapsearch.model.keyword.SearchKeyword;
import com.kys9261.mapsearch.repository.SearchKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class KeywordService {

    @Autowired
    private SearchKeywordRepository searchKeywordRepository;

    public List<SearchKeyword> getKeywordHistory(String userId) {
        checkNotNull(userId, "userId must be provided.");

        return searchKeywordRepository.findTop10ByUserIdOrderByCreateAtDesc(userId);
    }

    public List<PopularKeyword> getPopularKeyword() {
        List<Object[]> resultList = searchKeywordRepository.findTop10PopularKeywordOrderByCountDesc();

        List<PopularKeyword> list = new ArrayList<>();
        for(Object[] objectArr : resultList) {
            list.add(new PopularKeyword(String.valueOf(objectArr[0]), ((BigInteger) objectArr[1]).intValue()));
        }
        return list;
    }

    public SearchKeyword addKeywordHistory(SearchKeyword searchKeyword) {
        return searchKeywordRepository.save(searchKeyword);
    }
}
