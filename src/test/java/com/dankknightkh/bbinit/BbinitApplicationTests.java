package com.dankknightkh.bbinit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(args={"-p=training", "-c=noCommand"})
class BbinitApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true, is(true));
	}

}
