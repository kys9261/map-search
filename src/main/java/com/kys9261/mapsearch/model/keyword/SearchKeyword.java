package com.kys9261.mapsearch.model.keyword;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class SearchKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @NonNull
    private Integer userSeq;

    @NonNull
    private String keyword;

    @NonNull
    private LocalDateTime createAt;
}
