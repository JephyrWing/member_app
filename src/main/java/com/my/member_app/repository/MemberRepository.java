package com.my.member_app.repository;

import com.my.member_app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // extend로 받았기 때문에 이 annotation은 적어도 되고 안 적어도 된다.
//테이블과 연결할 Entity class를 지정, 해당 클래스의 PK의 타입을 적는다.
public interface MemberRepository extends JpaRepository<Member, Long> {
}
