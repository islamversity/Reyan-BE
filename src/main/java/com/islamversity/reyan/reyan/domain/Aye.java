package com.islamversity.reyan.reyan.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Aye {
    @Id
    String index;

    String text;

    String bismillah;

    Integer surahIndex;


}
