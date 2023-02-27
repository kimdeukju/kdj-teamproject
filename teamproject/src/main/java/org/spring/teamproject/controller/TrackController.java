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
    public String itemList(Model model) {

        List<ItemDto> itemDtoList = itemService.itemList();

        model.addAttribute("itemDtoList", itemDtoList);

        return "pages/track/trackList";
    }
}

