package org.spring.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.spring.teamproject.dto.ItemDto;
import org.spring.teamproject.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ItemService itemService;

    @GetMapping("/trackAdd")
    public String addView(Model model){
        model.addAttribute("itemDto",new ItemDto());

        return "/pages/pages/trackInsert";
    }

    @PostMapping("/trackAdd")
    public String addmet(@Valid ItemDto itemDto, BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            return "/pages/admin/trackInsert";
        }

        itemService.itemInsert(itemDto);

        return "redirect:/trackAdd";

    }


}
