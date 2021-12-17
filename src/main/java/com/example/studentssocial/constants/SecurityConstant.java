package com.example.studentssocial.constants;

public class SecurityConstant {
    public static final String[] PUBLIC_URL = {"/user/login", "/user/register","/h2-console-students","/user"}; // am facut toate url urile publice
    //un array de url uri care o sa fie publice
    //login, h2, register
    /* public static final String[] PUBLIC_URLS = {"dashboard/user/login", "dashboard/user/register", "/user/resetpassword/**", "/user/image/**"}; //*/
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

    public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

    public static final String JWT_PREFIX = "Bearer ";

    public static final String JWT_TOKEN_HEADER = "Jwt-Token";

    public static final String SECRET_KEY = "secret";
    public static final String ACCESS_DENIED_MESSAGE = "Access is denied";






    public static final long JWT_EXPIRATION_PERIOD = 1000*60*60*10;




}
