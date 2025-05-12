package com.efexunn.notification.email;

public enum EmailTemplateName {
    SIMPLE_TEMPLATE("simple_mail_template"),
    LOGIN_INFO_TEMPLATE("login-info-template");

    private final String name;
    EmailTemplateName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
