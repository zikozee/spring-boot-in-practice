package com.sbip.ch04.customactuatorendpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 25 Jan, 2024
 */

@Configuration
public class CourseTrackerApplication {

    @Bean(name = "releaseNotes")
    public Collection<ReleaseNote> loadReleaseNotes() {
        ReleaseNote releaseNote1 = ReleaseNote.builder()
                .version("v1.2.1")
                .releaseDate(LocalDate.of(2021, 12, 30))
                .commitTag("a7d2ea3")
                .bugFixes(Set.of(
                        new ReleaseItem("SBIP-123", "The name of the matching-strategy property is incorrect in the action message of the failure analysis for a PatternParseException #28839"),
                        new ReleaseItem("SBIP-124", "ErrorPageSecurityFilter prevents deployment to a Servlet 3.1 compatible container #28790")))
                .build();

        ReleaseNote releaseNote2 = ReleaseNote.builder()
                .version("v1.2.0")
                .releaseDate(LocalDate.of(2021, 11, 20))
                .commitTag("44047f3")
                .newReleases(Set.of(new ReleaseItem("SBIP-125", "Support both kebab-case and camelCase as Spring init CLI Options #28138")))
                                .bugFixes(Set.of(new ReleaseItem("SBIP-126", "Profiles added using @ActiveProfiles have different precedence #28724")))
                                                .build();
        return new LinkedHashSet<>(Set.of(releaseNote1, releaseNote2));
    }
}
