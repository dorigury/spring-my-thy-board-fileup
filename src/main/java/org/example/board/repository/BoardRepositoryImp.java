package org.example.board.repository;


import lombok.RequiredArgsConstructor;
import org.example.board.dto.BoardDTO;
import org.example.board.dto.BoardFileDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImp implements BoardRepository {

    private final SqlSessionTemplate sql;

    @Override
    public BoardDTO save(BoardDTO boardDto) {
        System.out.println("boardDto = " + boardDto);
        sql.insert("Board.save", boardDto);

        return boardDto;
    }

    @Override
    public List<BoardDTO> findAll() {
        System.out.println("BoardRepositoryImp.findAll");
        return sql.selectList("Board.findAll");

    }

    @Override
    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    @Override
    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);

    }

    @Override
    public void update(BoardDTO boardDto) {
        sql.update("Board.update", boardDto);

    }

    @Override
    public void saveFile(BoardFileDTO boardFileDto) {
        sql.insert("Board.saveFile", boardFileDto);
    }

    @Override
    public List<BoardFileDTO> findFile(Long boardId) {
        return sql.selectList("Board.findFile", boardId);
    }

    @Override
    public void delete(Long id) {
        sql.delete("Board.delete", id);

    }


}
