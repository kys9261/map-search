package com.kys9261.mapsearch.model.keyword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PopularKeyword {

    private String keyword;

    private int count;
}
