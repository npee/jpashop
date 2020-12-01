package com.npee.myproject.controller.v1;

import com.npee.myproject.advice.exception.CustomUserNotExistsException;
import com.npee.myproject.config.response.CommonResult;
import com.npee.myproject.config.response.ListResult;
import com.npee.myproject.config.response.ResponseService;
import com.npee.myproject.config.response.SingleResult;
import com.npee.myproject.entity.User;
import com.npee.myproject.entity.dto.RequestUserDto;
import com.npee.myproject.entity.dto.ResponseUserDto;
import com.npee.myproject.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RestController
// @CrossOrigin(origins = {"http://127.0.0.1:3000"}) // TODO: 다른 포트와 통신해야 할 경우 CORS 이슈 체크
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1")
public class UserController {

    private final ResponseService responseService;
    private final UserServiceImpl userService;

    @ApiOperation(value = "회원 목록", notes = "회원 목록 불러오기")
    @GetMapping("/all-users")
    // TODO: Entity가 준비되면 CommonResult에서 상황에 맞게 SingleResult나 ListResult로 변경
    // TODO: Entity에 직접 접근하지 않기 위해 DTO 이용
    public ListResult<ResponseUserDto> getUsers() {
        return responseService.getListResult(userService.selectAllUsers());
    }

    @ApiOperation(value = "회원 검색", notes = "특정 회원 검색")
    @GetMapping("/users")
    public SingleResult<ResponseUserDto> getUser(@RequestParam String id,
                                      @RequestParam String pw) {
        return responseService.getSingleResult(userService.selectUserByIdAndPassword(id, pw));
    }

    @ApiOperation(value = "회원 추가/수정", notes = "회원 업데이트")
    @PostMapping("/users")
    public SingleResult<ResponseUserDto> setUser(@RequestBody RequestUserDto userDto) {
        return responseService.getSingleResult(userService.updateUser(userDto));
    }

    @ApiOperation(value = "회원 삭제", notes = "회원 삭제")
    @DeleteMapping("/users/{userNo}")
    public SingleResult<ResponseUserDto> deleteUser(@PathVariable Long userNo) {
        return responseService.getSingleResult(userService.deleteUserByUserNo(userNo));
    }

}
