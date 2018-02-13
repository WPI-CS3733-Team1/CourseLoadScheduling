package org.dselent.scheduling.server.controller;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.miscellaneous.EmailUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class EmailServiceTest {
	
	
	@Test
	public void testEmail() {
		EmailUtility.sendEmail("", "", "", "", "");
	}
	
}
