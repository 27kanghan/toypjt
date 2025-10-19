package com.example.toypjt.board;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {


    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<String> write(@RequestBody BoardPostingRequestDto requestDto) {

        boardService.write(requestDto);

        return ResponseEntity.ok("Suc");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody BoardDeleteRequestDto requestDto){

        System.out.println(requestDto.getBoardId());
        boardService.delete(requestDto);

        return ResponseEntity.ok("done");
    }
}
