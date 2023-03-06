package org.spring.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.ItemDto;
import org.spring.teamproject.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/trackAdd")
    public String addView(Model model) {
        model.addAttribute("itemDto", new ItemDto());

        return "/pages/track/trackInsert";
    }

    @PostMapping("/trackAdd")
    public String addmet(@Valid ItemDto itemDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/pages/track/trackInsert";
        }
        itemService.itemInsert(itemDto);

        return "redirect:/trackList";
    }

    // track목록
    @GetMapping("/trackList")
    public String itemList(Model model, @PageableDefault(page = 0,size = 10,sort = "no",
                        direction = Sort.Direction.DESC)Pageable pageable) {
        // 목록 페이징 처리
        Page<ItemDto> itemDtoPage=itemService.itemPage(pageable);
        // 제목으로 검색

        Long total=itemDtoPage.getTotalElements(); // 전체 레코드 수
        int bockNum=4;
        int nowPage= itemDtoPage.getNumber()+1 ;// 현재페이지-> boardList.getNumber()는 0부터 시작
        int startPage=Math.max(1,itemDtoPage.getNumber()-bockNum); //시작페이지 -> 기본이 최소 1페이지
        int endPage=itemDtoPage.getTotalPages(); // 마지막페이지

        model.addAttribute("itemDtoPage",itemDtoPage);
        model.addAttribute("total",total);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "pages/track/trackList";
    }
    @GetMapping("/trackList/search")
    public String search(@RequestParam (value = "search",required = false)String search,Model model){
        List<ItemDto> itemDtoPage=itemService.search(search);

        model.addAttribute("itemDtoPage",itemDtoPage);

        return "pages/track/trackList";
    }

    // track상세목록
//   @GetMapping("/trackDetail/{no}")
 //   public String trackDetail(@PathVariable("no") long no, Model model){
//        ItemDto dto=itemService.trackDetail(no);

//        if(dto!= null){
//            model.addAttribute("dto",dto);
//            return "pages/track/trackDetail";
//        }else{
//            return null;
//        }

//    }


 //track 상세목록 이지창
@GetMapping("/trackDetail/{itemNo}/{memberNo}")
    public String trackDetail(@PathVariable("itemNo") Long itemNo,@PathVariable("memberNo") Long memberNo, Model model){

        ItemDto dto=itemService.trackDetail(itemNo);
        MemberDto memberDto = cartService.memberDtoSearch(memberNo);
        if(dto!= null){
            model.addAttribute("dto",dto);
            model.addAttribute("member",memberDto);
        }

        return "pages/track/trackDetail";
    }


    // track수정
    @GetMapping("/trackUpdate/{no}")
    public String trackUpdate(@PathVariable("no") long no, Model model){
        ItemDto dto=itemService.trackUpdate(no);

        model.addAttribute("dto",dto);

        return "pages/track/trackUpdate";
    }

    // track 수정실행
    @PostMapping("/trackUpdate")
    public String trackUpdateOk(@ModelAttribute ItemDto itemDto)  {


        itemService.trackUpdateOk(itemDto);

        return "redirect:/trackList";
    }

    @GetMapping("/trackDelete/{no}")
    public String trackDelete(@PathVariable("no") long no){

        itemService.trackDelete(no);


        return "redirect:/trackList";

    }



}
























