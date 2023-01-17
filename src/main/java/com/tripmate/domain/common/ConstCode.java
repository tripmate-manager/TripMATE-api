package com.tripmate.domain.common;

public interface ConstCode {
    String DUPLICATION_CHECK_CODE = "MB005";
    String DUPLICATION_CHECK_MEMBER_ID = "10";
    String DUPLICATION_CHECK_NICK_NAME = "20";
    String DUPLICATION_CHECK_EMAIL = "30";

    String EMAIL_TYPE_CODE = "MB004";
    String EMAIL_TYPE_CODE_JOIN = "10";
    String EMAIL_TYPE_CODE_EMAIL_CHANGE = "20";

    String TRIPMATE_DOMAIN     = "http://" + "localhost:8080";
    String JOIN_EMAIL_PATH     = "/members/signUp/emailConfirm.trip";
}
