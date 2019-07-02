package com.kys9261.mapsearch.repository;

import com.kys9261.mapsearch.model.keyword.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Integer> {

    @Query(value = "SELECT * FROM search_keyword WHERE user_seq = (SELECT seq FROM user where user_id = ?1) ORDER BY create_at DESC LIMIT 0,10", nativeQuery = true)
    List<SearchKeyword> findTop10ByUserIdOrderByCreateAtDesc(String userId);

    @Query(value = "SELECT KEYWORD, COUNT(KEYWORD) as count FROM SEARCH_KEYWORD GROUP BY KEYWORD ORDER BY COUNT DESC", nativeQuery = true)
    List<Object[]> findTop10PopularKeywordOrderByCountDesc();
}
