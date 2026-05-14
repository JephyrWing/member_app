package com.my.member_app.controller;

import com.my.member_app.dto.MemberDto;
import com.my.member_app.dto.SearchDto;
import com.my.member_app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/view")
    public String showAllMember(Model model){
        List<MemberDto> dtoList = memberService.findAll();
        model.addAttribute("title", "회원정보");
        model.addAttribute("dtolist", dtoList);
        return "showMember";
    }

    @GetMapping("/insertForm")
    public String insertForm(Model model){
        model.addAttribute("dto", new MemberDto());
        return "insertMember";
    }

    @PostMapping("/insert")
    public String insertMember(@ModelAttribute("dto") MemberDto dto,
                               RedirectAttributes redirectAttributes){
        // redirect:/member/view는 /member/view를 get으로 다시 호출
        log.info("result : " + dto);
        memberService.insert(dto);
        // 성공 메시지를 RedirectAttribute에 담아 보낸다.
        redirectAttributes.addFlashAttribute("message", "등록이 완료되었습니다.");
        return "redirect:/member/view";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("deleteId") Long deleteId,
                         RedirectAttributes redirectAttributes){
        memberService.delete(deleteId);
        redirectAttributes.addFlashAttribute("message", "정상적으로 삭제되었습니다.");
        return "redirect:/member/view";
    }
    @GetMapping("/update")
    public String updateForm(@RequestParam("updateId") Long id, Model model,
                             RedirectAttributes redirectAttributes){
        MemberDto dto = memberService.findById(id);
        if(dto == null) {
            redirectAttributes.addFlashAttribute("message", "선택한 데이터가 없습니다.");
            return "redirect:/member/view";
        } else {
            model.addAttribute("dto", dto);
            return "updateMember";
        }
    }

    @PostMapping("update")
    public String update(@ModelAttribute("dto") MemberDto dto,
                         RedirectAttributes redirectAttributes){
        log.info("updatedDto:" + dto);
        memberService.insert(dto);
        redirectAttributes.addFlashAttribute("message", "정상적으로 수정되었습니다.");
        return "redirect:/member/view";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam("type")String type,
//                         @RequestParam("keyword")String keyword,
//                         Model model){
//        List<MemberDto> resultList = memberService.search(type, keyword);
//        model.addAttribute("dtolist", resultList);
//        return "showMember";
//    }

    @GetMapping("/search")
    public String search(SearchDto searchDto, Model model){
        List<MemberDto> resultList = memberService.search(searchDto.getType(), searchDto.getKeyword());
        model.addAttribute("dtolist", resultList);
        return "showMember";
    }
}
