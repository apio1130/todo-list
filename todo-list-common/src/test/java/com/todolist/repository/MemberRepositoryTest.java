package com.todolist.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.todolist.domain.Member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원 등록 테스트")
    @Test
    public void add () {
        memberRepository.save(new Member("kjkim", "test@email.com"));
        Member saved = memberRepository.findById(1L).orElse(new Member());
        log.info("saved Member Info : {}", saved.getName());
        assertThat(saved.getName(), is("kjkim"));
    }
}