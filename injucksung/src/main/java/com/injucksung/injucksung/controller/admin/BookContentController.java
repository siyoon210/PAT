package com.injucksung.injucksung.controller.admin;

import com.injucksung.injucksung.domain.BookContent;
import com.injucksung.injucksung.dto.BookContentForm;
import com.injucksung.injucksung.service.BookContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/book-contents")
public class BookContentController {
    private final BookContentService bookContentService;

    //책 목차 추가하기
    @PostMapping
    public String addBookContent(@ModelAttribute BookContentForm bookContentForm) {
        bookContentService.addBookContent(bookContentForm);
        return "redirect:/admin/books/" + bookContentForm.getBookId();
    }

    //책 목차 삭제하기
    @DeleteMapping("/{bookContentId}")
    public String deleteBookContent(@PathVariable Long bookContentId, @RequestParam("bookId") Long bookId) {
        bookContentService.deleteBookContent(bookContentId);
        return "redirect:/admin/books/" + bookId;
    }

    //책 목차 수정하기
//    @PutMapping("/{bookContentId}")
//    @ResponseBody
//    public BookContent modifyBookContent(@PathVariable Long bookContentId, @ModelAttribute BookContentForm bookContentForm) {
//        BookContent bookContent = bookContentService.modifyBookContent(bookContentForm, bookContentId);
//        return bookContent;
//    }

    @GetMapping("/{bookContentId}")
    @ResponseBody
    public BookContent modifyBookContent(@PathVariable Long bookContentId, @RequestParam(value = "sequenceDirection") String sequenceDirection) {
        System.out.println("방향은 : "+sequenceDirection);
        BookContent bookContent = bookContentService.getBookContent(1L);
        return bookContent;
    }
}
