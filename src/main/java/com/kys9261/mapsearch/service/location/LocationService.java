package com.kys9261.mapsearch.service.location;

import com.kys9261.mapsearch.model.kakao.location.KakaoApiResponse;
import com.kys9261.mapsearch.model.kakao.location.KakaoLocal;
import com.kys9261.mapsearch.network.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class LocationService {

    @Autowired
    private ApiClient apiClient;

    public KakaoApiResponse<KakaoLocal> getLocationList(String placeName, int page) throws IOException {
        checkNotNull(placeName, "placeName must be provided.");
        checkArgument(page > 0, "page must be more than 0.");

        return apiClient.getLocationList(placeName, page);
    }

}
