package com.my.member_app.dto;

import com.my.member_app.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private int age;
    private String address;

    // DTO -> Entity로 변환하는 메서드(굉장히 많이 쓰이므로 메서드화)
    public static Member toEntity(MemberDto dto){
        //깡통 Member 생성 후 하나씩 넣어주고 리턴
        Member member = new Member();
        member.setId(dto.getId());
        member.setName(dto.getName());
        member.setAge(dto.getAge());
        member.setAddress(dto.getAddress());
        return member;
    }

    // Entity -> DTO 변환 메서드
    public static MemberDto toDto(Member member){
        MemberDto dto = new MemberDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setAge(member.getAge());
        dto.setAddress(member.getAddress());
        return dto;
    }

}
