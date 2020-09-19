package com.islamversity.reyan.reyan.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
public class Surah {
    @Id
    Integer index;

    String name;

    @DBRef
    List<Aye> ayat;


}
