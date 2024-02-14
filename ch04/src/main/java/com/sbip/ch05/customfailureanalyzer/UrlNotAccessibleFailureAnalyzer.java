package com.sbip.ch05.customfailureanalyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 21 Jan, 2024
 */

public class UrlNotAccessibleFailureAnalyzer extends AbstractFailureAnalyzer<UrlNotAccessibleException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, UrlNotAccessibleException cause) {
        return new FailureAnalysis("Unable to access the URL " + cause.getUrl(), "Validate the URL and ensure it is accessible", cause);
    }
}
