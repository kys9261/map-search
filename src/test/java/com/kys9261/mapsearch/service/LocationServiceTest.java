package com.kys9261.mapsearch.service;

import com.kys9261.mapsearch.model.kakao.location.KakaoApiResponse;
import com.kys9261.mapsearch.model.kakao.location.KakaoLocal;
import com.kys9261.mapsearch.service.location.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTest {

    @Autowired
    private LocationService service;

    @Test
    public void KakaoLocationApi() throws IOException {
        String placeName = "카카오프렌즈";
        KakaoApiResponse<KakaoLocal> kakaoLocationList = service.getLocationList(placeName, 1);

        assertThat(placeName, is(kakaoLocationList.getMeta().getSameName().getKeyword()));
    }
}
