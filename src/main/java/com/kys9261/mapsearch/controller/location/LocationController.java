package com.kys9261.mapsearch.controller.location;

import com.kys9261.mapsearch.model.kakao.location.KakaoApiResponse;
import com.kys9261.mapsearch.model.kakao.location.KakaoLocal;
import com.kys9261.mapsearch.model.keyword.SearchKeyword;
import com.kys9261.mapsearch.model.response.ApiResult;
import com.kys9261.mapsearch.security.JwtAuthenticationToken;
import com.kys9261.mapsearch.service.keyword.KeywordService;
import com.kys9261.mapsearch.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/list")
    public ApiResult<KakaoApiResponse<KakaoLocal>> getLocationList(
            @AuthenticationPrincipal JwtAuthenticationToken token,
            @RequestParam(value = "placeName", required = true) String placeName,
            @RequestParam(value = "page", defaultValue = "1") int page) throws IOException {

        keywordService.addKeywordHistory(new SearchKeyword(token.getUserSeq(), placeName, now()));
        return new ApiResult<>(locationService.getLocationList(placeName, page));
    }

}
