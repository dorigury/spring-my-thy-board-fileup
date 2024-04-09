package org.example.board.repository;

import org.example.board.dto.BoardDTO;
import org.example.board.dto.BoardFileDTO;

import java.util.List;

public interface BoardRepository {

    BoardDTO save(BoardDTO boardDto);

    List<BoardDTO> findAll();

    BoardDTO findById(Long id);

    void updateHits(Long id);

    void delete(Long id);

    void update(BoardDTO boardDto);

    void saveFile(BoardFileDTO boardFileDto);

    List<BoardFileDTO> findFile(Long boardId);


}
