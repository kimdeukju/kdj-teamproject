package org.spring.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.ItemDto;
import org.spring.teamproject.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrackController {

    private final ItemService itemService;

    @GetMapping("/admin/trackAdd")
    public String addView(Model model) {
        model.addAttribute("itemDto", new ItemDto());

        return "/pages/admin/trackInsert";
    }

    @PostMapping("/trackAdd")
    public String addmet(@Valid ItemDto itemDto, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "/pages/admin/trackInsert";
        }
        itemService.itemInsert(itemDto);

        return "redirect:/trackAdd";
    }

    @GetMapping("/tracks")
    public String itemList(Model model) {

        List<ItemDto> itemDtoList = new ArrayList<>();

        model.addAttribute("itemDtoList", itemDtoList);

        return "itemList";
    }
}

