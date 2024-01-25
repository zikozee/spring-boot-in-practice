package com.sbip.ch04.customactuatorendpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.jmx.annotation.JmxEndpoint;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 25 Jan, 2024
 */

@Component
//@JmxEndpoint(id = "releaseNotes") // access via jmx (Java management Extensions)  jconsole > select PID > connect Mbeans
@Endpoint(id = "releaseNotes")
@RequiredArgsConstructor
public class ReleaseNoteEndpoint {

    private final Collection<ReleaseNote> releaseNotes;

    @ReadOperation
    public Iterable<ReleaseNote> releaseNotes(){
        return releaseNotes;
    }

    @ReadOperation
    public Object selectCourse(@Selector String version){
        Optional<ReleaseNote> releaseNoteOptional = releaseNotes
                .stream().filter(releaseNote -> releaseNote.getVersion().equalsIgnoreCase(version))
                .findFirst();

        if(releaseNoteOptional.isPresent()) return releaseNoteOptional.get();
        return "No Such release version exists: " + version;
    }

    @DeleteOperation
    public void removeReleaseVersion(@Selector String version){
        Optional<ReleaseNote> releaseNoteOptional = releaseNotes
                .stream().filter(releaseNote -> releaseNote.getVersion().equalsIgnoreCase(version))
                .findFirst();

        releaseNoteOptional.ifPresent(releaseNotes::remove);
    }
}
