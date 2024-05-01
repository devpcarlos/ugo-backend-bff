package com.ugo.entitys.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Review(int calification,
                     String comment
) {
}
