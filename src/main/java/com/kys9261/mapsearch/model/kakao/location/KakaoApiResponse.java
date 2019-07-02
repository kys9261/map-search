package com.kys9261.mapsearch.model.kakao.location;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KakaoApiResponse<T> {

    private List<T> documents;

    private KakaoMeta meta;
}
