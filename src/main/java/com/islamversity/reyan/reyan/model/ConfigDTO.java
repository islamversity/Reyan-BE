package com.islamversity.reyan.reyan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigDTO {
    Integer index;

    String version;

    String description;

    String forceUpdate;

}
