package com.npee.myproject.service;

import com.npee.myproject.entity.User;
import com.npee.myproject.entity.dto.RequestUserDto;
import com.npee.myproject.entity.dto.ResponseUserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // TODO: Entity가 준비되면 해당 Entity의 List를 반환하도록 수정
    // List<String> selectAllUsers();
    List<ResponseUserDto> selectAllUsers();
    ResponseUserDto selectUserByIdAndPassword(String id, String pw);
    ResponseUserDto updateUser(RequestUserDto requestUserDto);
    ResponseUserDto deleteUserByUserNo(Long userNo);
}
