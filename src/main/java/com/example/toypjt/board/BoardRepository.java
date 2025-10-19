package com.example.toypjt.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByBoardId(Long boardId);
    Optional<Board> deleteByBoardId(Long bardId);
}
