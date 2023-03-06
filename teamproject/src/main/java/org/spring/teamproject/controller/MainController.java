package org.spring.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.MemberDto;
import org.spring.teamproject.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @GetMapping({"/", "", "/index"})
    public String index() {
        return "/pages/main";
    }


    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/pages/member/join";
    }

    @PostMapping("/join")
    public String joinPost(@Valid MemberDto memberDto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "/pages/member/join";
        }
        memberService.insertMember(memberDto);

        System.out.println("회원가입 성공");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("로그인 성공1");
        return "/pages/member/login";
    }

}
