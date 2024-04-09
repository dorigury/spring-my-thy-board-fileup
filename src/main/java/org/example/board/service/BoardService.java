package org.example.board.service;

import org.example.board.dto.BoardDTO;
import org.example.board.dto.BoardFileDTO;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    void save(BoardDTO boardDto) throws IOException;

    List<BoardDTO> findAll();

    BoardDTO findById(Long id);

    void updateHits(Long id);

    void update(BoardDTO boardDto);

    void delete(Long id);

    List<BoardFileDTO> findFile(Long id);

}
