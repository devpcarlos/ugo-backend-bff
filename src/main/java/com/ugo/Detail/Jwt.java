package com.ugo.Detail;

import org.springframework.beans.factory.annotation.Value;

public class Jwt {
    @Value("${SECURITY.JWT.KEY}")
    private String SecretKey;
    @Value("${SECURITY.JWT.USER.GENERATOR}")
    private String Generator;


}
