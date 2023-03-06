package org.spring.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.MemberDto;
import org.spring.teamproject.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberService memberService;
    @GetMapping({"/", "", "/index"})                    //기본페이지설정
    public String index() {
        return "/pages/main";
    }

    @GetMapping("/join")                                //회원가입페이지 이동
    public String join(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/pages/member/join";
    }
    @PostMapping("/join")                               //form 받아 회원가입실행
    public String joinPost(@Valid MemberDto memberDto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "/pages/member/join";
        }
        memberService.insertMember(memberDto);
        System.out.println("회원가입 성공");
        return "redirect:/login";
    }
    @PostMapping("/emailChecked")                        //회원가입 email 중복체크버튼
    public @ResponseBody int nameChecked(
            @RequestParam String email) {
        int rs = memberService.findByUserNameDo(email);
        return rs;
    }
    @GetMapping("/login")                               //로그인
    public String login(Model model) {
        System.out.println("로그인 성공");
        return "/pages/member/login";
    }
}
