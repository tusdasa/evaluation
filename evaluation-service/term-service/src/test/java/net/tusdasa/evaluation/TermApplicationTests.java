package net.tusdasa.evaluation;

import net.tusdasa.evaluation.service.TermService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TermApplicationTests {

	@Autowired
	private TermService termService;

	public TermApplicationTests(){
	}

	@Test
	void contextLoads() {
		termService.findAll();
	}

}
