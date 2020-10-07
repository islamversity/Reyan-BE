package com.islamversity.reyan.reyan.domain.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "quran")
public class SurahDocument {
    @Id
    private String index;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<AyehDocument> ayehDocumentList;
}
