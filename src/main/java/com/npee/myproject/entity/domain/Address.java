package com.npee.myproject.entity.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable // 값 타입
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // reflection 이나 proxy 등의 기술을 사용하기 위해서 기본 생성자가 필요하다.
public class Address {

    private String city;
    private String street;
    private String zipcode;
}

