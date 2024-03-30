package com.sbip.ch08rsocketintro;

import com.sbip.ch08rsocketintro.model.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class Ch08RsocketIntroApplicationTests {


	private static RSocketRequester requester;


	@BeforeAll
	public static void setUpOnce(@Autowired RSocketRequester.Builder builder, @LocalRSocketServerPort Integer port,
								 @Autowired RSocketStrategies rSocketStrategies) {

		requester = builder.tcp("localhost", port);
	}

	@Test
	public void testRequestResponse() {

		Mono<Course> courseMono = requester
				.route("request-response")
				.data(new Course("Spring"))
				.retrieveMono(Course.class);

		StepVerifier.create(courseMono)
				.consumeNextWith(course -> assertThat(course.getCourseName())
						.isEqualTo("Your course name: Spring"))
				.verifyComplete();
	}

	@Test
	public void testFireAndForget() {
		Mono<Void> courseMono = requester
				.route("fire-and-forget")
				.data(new Course("Spring"))
				.retrieveMono(Void.class);

		StepVerifier
				.create(courseMono)
				.verifyComplete();
	}

	@Test
	public void testRequestStream() {

		Flux<Course> courseFlux = requester
				.route("request-stream")
				.data(new Course("Spring"))
				.retrieveFlux(Course.class);

		StepVerifier.create(courseFlux)
				.consumeNextWith(course -> assertThat(course.getCourseName()).isEqualTo("Your course name: Spring. Response #0"))
										.expectNextCount(0)
										.consumeNextWith(course -> assertThat(course.getCourseName()).isEqualTo("Your course name: Spring. Response #1"))
																.thenCancel()
																.verify();
	}

	@Test
	public void testChannel() {
		Mono<Integer> setting1 = Mono.just(2).delayElement(Duration.ofSeconds(0));
		Mono<Integer> setting2 = Mono.just(1).delayElement(Duration.ofSeconds(3));
		Flux<Integer> settings = Flux.concat(setting1, setting2);
		Flux<Course> stream = requester.route("stream-stream").data(settings).retrieveFlux(Course.class);
				StepVerifier
						.create(stream)
						.consumeNextWith(course -> assertThat(course.getCourseName()).isEqualTo("Spring. Response #0"))
						.consumeNextWith(course -> assertThat(course.getCourseName()).isEqualTo("Spring. Response #0"))
						.thenCancel()
						.verify();
	}
}
