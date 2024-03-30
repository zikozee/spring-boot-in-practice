package com.sbip.ch08websocket.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 30 Mar, 2024
 */

@Data
@Builder
public class OutputMessage{
    private Instant time;
    private String content;
}
