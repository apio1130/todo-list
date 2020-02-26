package com.todolist;

import com.todolist.domain.Member;
import com.todolist.service.MemberServiceCustom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ModuleApiApplication.class)
public class ModuleApiApplicationTests {

	@Autowired
	private MemberServiceCustom memberServiceCustom;

	@Test
	public void save() {
		Member member = new Member("kjkim", "test@email.com");
		Long id = memberServiceCustom.signup(member);
		log.info("saved Member : {}", member.getName());	
		assertThat(id, is(1L));
	}
}