package com.kys9261.mapsearch.service;

import com.kys9261.mapsearch.model.keyword.PopularKeyword;
import com.kys9261.mapsearch.model.keyword.SearchKeyword;
import com.kys9261.mapsearch.service.keyword.KeywordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordServiceTest {

    @Autowired
    private KeywordService keywordService;

    private String userId = "testAccount1";

    private int userSeq = 1;

    private String keyword = "카카오프렌즈";

    private String popularKeyword = "한국카카오은행";

    private static boolean initIsDone = false;

    @Before
    public void init() {
        if (initIsDone) {
            return;
        }

        for(int i=0; i<10; i++) {
            keywordService.addKeywordHistory(new SearchKeyword(userSeq, popularKeyword, now()));
        }
        initIsDone = true;
    }

    @Test
    @Order(1)
    public void popularKeyword() {
        List<PopularKeyword> popularKeywords = keywordService.getPopularKeyword();

        assertThat(popularKeyword, is(popularKeywords.get(0).getKeyword()));
        assertThat(10, is(popularKeywords.get(0).getCount()));
    }

    @Test
    @Order(2)
    public void addKeywordHistory() {
        SearchKeyword searchKeyword = new SearchKeyword(userSeq, keyword, now());
        SearchKeyword addedSearchKeyword = keywordService.addKeywordHistory(searchKeyword);

        assertThat(searchKeyword.getKeyword(), is(addedSearchKeyword.getKeyword()));
    }

    @Test
    @Order(3)
    public void keywordHistory() {
        List<SearchKeyword> searchKeywords = keywordService.getKeywordHistory(userId);

        assertThat(userSeq, is(searchKeywords.get(0).getUserSeq()));
        assertThat(keyword, is(searchKeywords.get(0).getKeyword()));
    }
}
