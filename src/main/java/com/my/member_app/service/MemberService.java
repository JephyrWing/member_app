package com.my.member_app.service;

import com.my.member_app.dto.MemberDto;
import com.my.member_app.entity.Member;
import com.my.member_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    //의존성 주입 : 필요한 컴포넌트(인스턴스)를 불러오는 작업
    //1. 첫 번째 주입방법(비추)
    //@Autowired
    //MemberRepository memberRepository;

    //2. 두 번째 주입방법(생성자 주입)
    //private final MemberRepository memberRepository;
    //
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}

    //3. 세 번째 주입방법
    private final MemberRepository memberRepository;
    //@RequiredArgsConstructor


    public List<MemberDto> findAll() {
        // Repository에서 필요한 정보를 가져온다.
        // 단, Repo는 Entity만 사용한다.
        // Entity List -> Dto List로 변환한 후 리턴
        List<Member> members = memberRepository.findAll();
        List<MemberDto> dtoList = new ArrayList<>();
        members.forEach(x -> dtoList.add(MemberDto.toDto(x)));
        return dtoList;
    }

}
