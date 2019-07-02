package com.kys9261.mapsearch.network;

import com.kys9261.mapsearch.model.kakao.location.KakaoApiResponse;
import com.kys9261.mapsearch.model.kakao.location.KakaoLocal;
import com.kys9261.mapsearch.network.endpoint.KakaoApiEndPoint;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiClient {

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dapi.kakao.com/")
            .build();

    public KakaoApiEndPoint kakaoApiEndPoint = retrofit.create(KakaoApiEndPoint.class);

    public KakaoApiResponse<KakaoLocal> getLocationList(String placeName, int page) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "KakaoAK "+"18ba80db41995fbb28a2ca2b2622908c");

        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("query", placeName);
        queryParam.put("page", String.valueOf(page));
        queryParam.put("size", "10");

        Call<KakaoApiResponse<KakaoLocal>> call = kakaoApiEndPoint.getLocationsList(headers, queryParam);
        return call.execute().body();
    }

}
