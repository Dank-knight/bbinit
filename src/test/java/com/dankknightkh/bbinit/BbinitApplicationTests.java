package com.dankknightkh.bbinit;

import com.dankknightkh.bbinit.command.catalog.CommandCatalog;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class BbinitApplicationTests {

	@Mock
	private CommandCatalog commandCatalog;

	@InjectMocks
	private BbInitApplication bbInitApplication;


	@Test
	void contextLoads() {
		assertThat(bbInitApplication, is(notNullValue()));
	}

}
