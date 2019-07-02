package com.kys9261.mapsearch.model.kakao.location;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KakaoMeta {

    @SerializedName("is_end")
    private boolean isEnd;

    @SerializedName("pageable_count")
    private int pageableCount;

    @SerializedName("same_name")
    private SameName sameName;

    @SerializedName("total_count")
    private int totalCount;

    @Getter
    @Setter
    public class SameName {

        private String keyword;

        private List<String> region;

        @SerializedName("selected_region")
        private String selectedRegion;
    }
}
