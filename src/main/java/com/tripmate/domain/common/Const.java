package com.tripmate.domain.common;

public interface Const {
    String SERVICE_NAME         = "TripMATE";
    String TRIPMATE_DOMAIN      = "localhost:8080";
    String JOIN_EMAIL_PATH      = "/members/signUp/emailConfirm.trip";
    String JOIN_EMAIL_URL       = "http://" + TRIPMATE_DOMAIN + JOIN_EMAIL_PATH;

    int SIGNIN_LIMIT_CNT        = 5;
    String MAIL_TEMPLATES_PATH       = "/src/main/resources/templates/";
}
