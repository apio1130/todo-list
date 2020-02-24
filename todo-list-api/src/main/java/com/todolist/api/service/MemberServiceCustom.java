package com.todolist.api.service;

import com.todolist.common.domain.Member;
import com.todolist.common.repository.MemberRepository;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceCustom {

    private MemberRepository memberRepository;

    public MemberServiceCustom(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // public Long signup (Member member) {
    //     return memberRepository.save(member).getId();
    // }
}