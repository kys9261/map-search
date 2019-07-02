package com.kys9261.mapsearch.network.endpoint;

import com.kys9261.mapsearch.model.kakao.location.KakaoApiResponse;
import com.kys9261.mapsearch.model.kakao.location.KakaoLocal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface KakaoApiEndPoint {

    @GET("/v2/local/search/keyword.json")
    Call<KakaoApiResponse<KakaoLocal>> getLocationsList(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> queryParam);
}
