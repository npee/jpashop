package com.npee.myproject.service;

import com.npee.myproject.advice.exception.CustomSigninFailedException;
import com.npee.myproject.advice.exception.CustomUserNotExistsException;
import com.npee.myproject.entity.User;
import com.npee.myproject.entity.dto.RequestUserDto;
import com.npee.myproject.entity.dto.ResponseUserDto;
import com.npee.myproject.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public List<ResponseUserDto> selectAllUsers() {
        // TODO: 데이터베이스에서 USER 정보 불러오기
        // findAll() 메서드는 메서드 체인으로 예외처리할 수 없다.
        return userJpaRepository.customUserSelect();

        // db 없을 때 에러메시지 확인
        // List<String> userList = new ArrayList<>();
        // if (userList.isEmpty()) throw new CustomUserNotExistsException();
        // else return userList;
    }

    @Override
    public ResponseUserDto selectUserByIdAndPassword(String id, String pw) {
        userJpaRepository.findByUserId(id).orElseThrow(CustomUserNotExistsException::new);
        return userJpaRepository.findByUserIdAndPassword(id, pw).orElseThrow(CustomSigninFailedException::new);
    }

    @Override
    public ResponseUserDto updateUser(RequestUserDto userDto) {
        return userJpaRepository.save(userDto.toEntity()).toResponseDto();
    }

    @Override
    public ResponseUserDto deleteUserByUserNo(Long userNo) {
        ResponseUserDto deletedUser = userJpaRepository.findByUserNo(userNo).orElseThrow(CustomUserNotExistsException::new);
        userJpaRepository.deleteById(userNo);
        return deletedUser;
    }
}
