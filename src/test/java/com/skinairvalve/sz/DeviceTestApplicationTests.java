package com.skinairvalve.sz;

import com.skinairvalve.sz.entity.SzUser;
import com.skinairvalve.sz.service.ISzUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
@ActiveProfiles({"local"})
public class DeviceTestApplicationTests {
	protected final PodamFactory podamFactory =
			new PodamFactoryImpl();
	@Resource
	private ISzUserService szUserService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(szUserService);
		List<SzUser> userList = szUserService.list();
		Assertions.assertNotNull(userList);
	}
}
