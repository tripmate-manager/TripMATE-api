package com.tripmate.domain.common;

public interface Const {
    String SERVICE_NAME             = "TripMATE";
    String TRIPMATE_DOMAIN          = "localhost:8080";
    String CERTIFICATION_EMAIL_PATH = "/members/emailConfirm.trip";
    String CERTIFICATION_EMAIL_URL  = "http://" + TRIPMATE_DOMAIN + CERTIFICATION_EMAIL_PATH;

    int SIGNIN_LIMIT_CNT            = 5;
    String MAIL_TEMPLATES_PATH      = "mailTemplates/";

    String Y = "Y";
    String N = "N";
}
