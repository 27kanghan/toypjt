package com.example.toypjt.board;


import com.example.toypjt.user.User;
import com.example.toypjt.user.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(BoardPostingRequestDto requestDto) {

        Board board = Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        boardRepository.save(board);

    }

    public void delete(BoardDeleteRequestDto requestDto) {

        Long boardId = requestDto.getBoardId();

        Optional<Board> board = boardRepository.findById(boardId);

        if(board.isPresent()){
            boardRepository.deleteById(boardId);
        }else{
            throw new IllegalArgumentException("잘못 된 요청입니다");
        }

    }




}
