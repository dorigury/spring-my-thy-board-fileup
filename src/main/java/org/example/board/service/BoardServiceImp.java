package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.dto.BoardDTO;
import org.example.board.dto.BoardFileDTO;
import org.example.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService{

    private final BoardRepository boardRepository;
    @Override
    public void save(BoardDTO boardDto) throws IOException {
        System.out.println("boardDto = " + boardDto);

        // 파일 첨부 여부 확인
        if(boardDto.getBoardFile().isEmpty()) {
            // 첨부 파일이 없는 경우
            boardDto.setFileAttached(0);
            boardRepository.save(boardDto);
        } else {
            // 첨부 파일이 있는 경우
            boardDto.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.save(boardDto);

            // 파일 업로드

            for (MultipartFile boardFile: boardDto.getBoardFile()) {
                // 파일 이름 가져오기
                String originalFileName = boardFile.getOriginalFilename();
                System.out.println("originalFilename = " + originalFileName);

                // 저장용 이름 만들기
                System.out.println(System.currentTimeMillis());
                String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
                System.out.println("storedFileName = " + storedFileName);

                // BoardFileDTO 셋팅
                BoardFileDTO boardFileDto = new BoardFileDTO();
                boardFileDto.setOriginalFileName(originalFileName);
                boardFileDto.setStoredFileName(storedFileName);
                boardFileDto.setBoardId(savedBoard.getId());

                // 파일 저장용 폴더에 파일 저장 처리
                String savePath = "D:/data/spring/2024/0408/files/" + storedFileName;
                boardFile.transferTo(new File(savePath));

                // 파일 정보 저장
                boardRepository.saveFile(boardFileDto);

            }



        }
    }

    @Override
    public List<BoardDTO> findAll() {

        System.out.println("BoardServiceImp.findAll");

        return boardRepository.findAll();
    }

    @Override
    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public void updateHits(Long id) {
        boardRepository.updateHits(id);

    }

    @Override
    public void update(BoardDTO boardDto) {
        boardRepository.update(boardDto);
    }

    @Override
    public void delete(Long id) {
        boardRepository.delete(id);

    }

    @Override
    public List<BoardFileDTO> findFile(Long id) {
        return boardRepository.findFile(id);
    }
}
