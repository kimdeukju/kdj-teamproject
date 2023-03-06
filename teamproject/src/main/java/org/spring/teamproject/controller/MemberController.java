package org.spring.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.MemberDto;
import org.spring.teamproject.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/mypage/{email}")
    public String membermypage(@PathVariable("email") String email, Model model) {


        MemberDto memberDto = memberService.memberDetail(email);

        model.addAttribute("member", memberDto);
        System.out.println("mypage입장성공1");

        return "/pages/member/mypage";
    }

    @GetMapping("/update/{email}")
    public String info(@PathVariable("email") String email, Model model) {

        MemberDto memberDto = memberService.memberDetail(email);

        model.addAttribute("member", memberDto);
        System.out.println("mypage입장성공1");

        return "/pages/member/update";
    }

    @PostMapping("/update")     //회원수정 실행
    public String updatePost(@ModelAttribute MemberDto memberDto) {

        memberService.updateOk(memberDto);
        System.out.println("회원수정 성공");
        return "redirect:/";
    }


    @GetMapping("/delete/{no}")
    public String delete(@PathVariable(value = "no") Long no) {
        int rs = memberService.deleteOk(no);

        if (rs == 1) {
            System.out.println("회원탈퇴 실패");
            return null;
        }
        System.out.println("회원탈퇴 성공");
        return "redirect:/logout";
    }
}
