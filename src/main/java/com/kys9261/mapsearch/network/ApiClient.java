package com.kys9261.mapsearch.network;

import com.kys9261.mapsearch.model.kakao.location.KakaoApiResponse;
import com.kys9261.mapsearch.model.kakao.location.KakaoLocal;
import com.kys9261.mapsearch.network.endpoint.KakaoApiEndPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiClient {

    @Value("${kakao.api.url}")
    private String kakakoApiUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    private Retrofit retrofit;

    public KakaoApiEndPoint kakaoApiEndPoint;

    @PostConstruct
    public void init() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(kakakoApiUrl)
                .build();

        kakaoApiEndPoint = retrofit.create(KakaoApiEndPoint.class);
    }

    public KakaoApiResponse<KakaoLocal> getLocationList(String placeName, int page) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "KakaoAK "+kakaoApiKey);

        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("query", placeName);
        queryParam.put("page", String.valueOf(page));
        queryParam.put("size", "10");

        Call<KakaoApiResponse<KakaoLocal>> call = kakaoApiEndPoint.getLocationsList(headers, queryParam);
        return call.execute().body();
    }

}
