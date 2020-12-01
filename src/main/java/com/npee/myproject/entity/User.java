package com.npee.myproject.entity;

import com.npee.myproject.entity.common.BaseTime;
import com.npee.myproject.entity.dto.ResponseUserDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Builder
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
// TODO: intelliJ IDEA의 설정에서 에러 탐지 해제
// Editor -> Inspection -> Unresolved database references in annotations 체크 해제
@Table(name = "users")
@Slf4j
public class User extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = true)
    private String userName;

    public ResponseUserDto toResponseDto() {
        return new ResponseUserDto(this.userNo, this.userId, this.userName);
    }
}
