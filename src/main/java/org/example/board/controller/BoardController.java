package org.example.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.board.dto.BoardDTO;
import org.example.board.dto.BoardFileDTO;
import org.example.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {

        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDto) throws IOException {
        System.out.println("boardDto = " + boardDto);

        boardService.save(boardDto);

        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {

        List<BoardDTO> boardDtoList = boardService.findAll();

        System.out.println("boardDtoList = " + boardDtoList);

        model.addAttribute("boardList", boardDtoList);

        return "list";
    }

    @GetMapping("/list/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {

        // 조회수 처리
        boardService.updateHits(id);

        // 상세 정보 조회
        BoardDTO boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        if (boardDto.getFileAttached() == 1) {
            List<BoardFileDTO> boardFileDtoList = boardService.findFile(id);
            model.addAttribute("boardFileList", boardFileDtoList);
        }
        return "detail";
    }

    // 수정 화면
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        return "update";
    }

    // 수정 처리
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, BoardDTO boardDto) {
        boardService.update(boardDto);
        return "redirect:/list";
    }

    // 삭제 화면
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/list";
    }
}
