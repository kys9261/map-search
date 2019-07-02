package com.kys9261.mapsearch.controller.keyword;

import com.kys9261.mapsearch.model.keyword.PopularKeyword;
import com.kys9261.mapsearch.model.keyword.SearchKeyword;
import com.kys9261.mapsearch.model.response.ApiResult;
import com.kys9261.mapsearch.security.JwtAuthenticationToken;
import com.kys9261.mapsearch.service.keyword.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/history")
    public ApiResult<List<SearchKeyword>> getKeywordHistory(
            @AuthenticationPrincipal JwtAuthenticationToken token) {
        String userId = (String) token.getPrincipal();
        return new ApiResult<>(keywordService.getKeywordHistory(userId));
    }

    @GetMapping("/popular")
    public ApiResult<List<PopularKeyword>> getPopularKeyword(
            @AuthenticationPrincipal JwtAuthenticationToken token) {
        return new ApiResult<>(keywordService.getPopularKeyword());
    }
}
