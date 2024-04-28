package com.ugo.entitys.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Experience(String _id,
                         String name,
                         String type,
                         String description,
                         String country,
                         String province,
                         int price_min,
                         int price_max
) {
}