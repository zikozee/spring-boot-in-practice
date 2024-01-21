package com.sbip.ch04.customfailureanalyzer;

import lombok.Getter;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 21 Jan, 2024
 */

@Getter
public class UrlNotAccessibleException extends RuntimeException{

    private final String url;

    public UrlNotAccessibleException(String url) {
        this(url, null);
    }

    public UrlNotAccessibleException(String url, Throwable cause) {
        super("URL " + url + " is not accessible", cause);
        this.url = url;
    }
}
