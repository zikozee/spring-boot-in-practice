package com.sbip.ch06.model;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

public record RecaptchaDto(boolean success, List<String> errors) {
}
