package org.spring.teamproject.controller;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.spring.teamproject.dto.ItemDto;
import org.spring.teamproject.entity.ItemEntity;
import org.spring.teamproject.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrackController {

    private final ItemService itemService;

    @GetMapping("/trackAdd")
    public String addView(Model model) {
        model.addAttribute("itemDto", new ItemDto());

        return "/pages/track/trackInsert";
    }

    @PostMapping("/trackAdd")
    public String addmet(@Valid ItemDto itemDto, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "/pages/track/trackInsert";
        }
        itemService.itemInsert(itemDto);

        return "redirect:/trackList";
    }


    @GetMapping("/trackList")
    public String itemList(Model model, @PageableDefault(page = 0,size = 10,sort = "no",
                        direction = Sort.Direction.ASC)Pageable pageable) {
        // 목록 페이징 처리
        Page<ItemDto> itemDtoPage=itemService.itemPage(pageable);

        int nowPage= itemDtoPage.getPageable().getPageNumber()+1;
        int startPage=Math.max(nowPage-4,1);
        int endPage=Math.min(nowPage+5,itemDtoPage.getTotalPages());

        model.addAttribute("itemDtoPage",itemDtoPage);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "pages/track/trackList";
    }

    @GetMapping("/trackDetail/{no}")
    public String trackDetail(@PathVariable long no, Model model){
        ItemDto dto=itemService.trackDetail(no);

        if(dto!= null){
            model.addAttribute("dto",dto);
        }

        return "pages/track/trackDetail";
    }


}
























