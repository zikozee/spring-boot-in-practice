package com.sbip.ch06.customactuatorendpoint;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 25 Jan, 2024
 */

@Getter
@Setter
@Builder
public class ReleaseNote {

    private String version;
    private LocalDate releaseDate;
    private String commitTag;
    private Set<ReleaseItem> newReleases;
    private Set<ReleaseItem> bugFixes;
}
