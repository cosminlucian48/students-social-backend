package com.example.studentssocial.constants;

public class SecurityConstant {
        public static final String[] PUBLIC_URL = {"/user/login", "/user/register","/h2-console-students/**","/user"}; // am facut toate url urile publice
//    public static final String[] PUBLIC_URL = {"/**"};
    //un array de url uri care o sa fie publice
    //login, h2, register
    /* public static final String[] PUBLIC_URLS = {"dashboard/user/login", "dashboard/user/register", "/user/resetpassword/**", "/user/image/**"}; //*/
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

    public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

    public static final String JWT_PREFIX = "Bearer ";

    public static final String JWT_TOKEN_HEADER = "Jwt-Token";

    public static final String SECRET_KEY = "secret";
    public static final String ACCESS_DENIED_MESSAGE = "Access is denied";


    public static final long JWT_EXPIRATION_PERIOD = 1000 * 60 * 60 * 10;
    public static final String DEFAULT_PROFILE_IMAGE = "data:image/jpeg;base64,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";



}
