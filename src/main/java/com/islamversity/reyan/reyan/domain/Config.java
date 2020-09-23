package com.islamversity.reyan.reyan.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Config {
    @Id
    Integer index;

    String version;

    String description;

    String forceUpdate;
}
