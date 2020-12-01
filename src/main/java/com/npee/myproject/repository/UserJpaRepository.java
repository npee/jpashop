package com.npee.myproject.repository;

import com.npee.myproject.entity.User;
import com.npee.myproject.entity.dto.ResponseUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * userJpaRepository
 * JpaRepository를 상속받은 인터페이스 생성
 * 제네릭으로 객체 타입(Entity)과 Long을 사용한다.
 * */
public interface UserJpaRepository extends JpaRepository<User, Long> {
    @Query("SELECT new com.npee.myproject.entity.dto.ResponseUserDto(u.userNo, u.userId, u.password) FROM User u WHERE u.userNo = :no")
    Optional<ResponseUserDto> findByUserNo(@Param("no") Long userNo);
    // Optional<User> findByUserId(String id);
    @Query("SELECT new com.npee.myproject.entity.dto.ResponseUserDto(u.userNo, u.userId, u.password) FROM User u WHERE u.userId = :id AND u.password = :pw")
    Optional<ResponseUserDto> findByUserIdAndPassword(@Param("id") String id, @Param("pw") String pw);

    @Query("SELECT new com.npee.myproject.entity.dto.ResponseUserDto(u.userNo, u.userId, u.password) FROM User u")
    List<ResponseUserDto> customUserSelect();

    @Query("SELECT new com.npee.myproject.entity.dto.ResponseUserDto(u.userNo, u.userId, u.password) FROM User u WHERE u.userId = :id")
    Optional<ResponseUserDto> findByUserId(String id);
}
