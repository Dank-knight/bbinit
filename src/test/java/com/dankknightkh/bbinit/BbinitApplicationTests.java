package com.dankknightkh.bbinit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(args={"-p=training", "-c=noCommand"})
class BbinitApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true, org.hamcrest.Matchers.is(true));
	}

}
