package com.spring.webmvc.board.service;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

// 역할 : 컨트롤러와 레파지토리 사이 중간에서 잡다한 처리를 담당
@RequiredArgsConstructor //final 필드 초기화 생성자를 만듦 이거를 사용해 Autowired를 사용하지 않아도 주입된다.
@Service //기본적인 서비스의 bean 등록 방법
public class BoardService {

    // 여기에 @Autowired는 안하는게 낫다. 실행중에 만약 바뀐다면? 의존 객체를 안전하게 지키기위해
    // 생성자 주입을 사용한다.
    private final BoardRepository repository;

    //전체 조회 중간 처리
    public List<Board> getList() {
        List<Board> boardList = repository.findAll();

        // 게시물 제목 줄임 처리
        // 만약에 글제목이 6글자 이상이면 6글자까지만 보여주고 뒤에 ... 처리
        for (Board b : boardList) {
            String title = b.getTitle();
            if (title.length() > 6) {
                String shortTitle = title.substring(0, 6) + "...";
                b.setShortTitle(shortTitle);
            } else {
                b.setShortTitle(title);
            }
        }
        return boardList;
    }

    //상세 조회 중간처리
    public Board getDetail(Long boardNo) {
        Board board = repository.findOne(boardNo);
        return board;
    }

    //게시물 저장 중간 처리
    public boolean insert(Board board) {
        boolean flag = repository.save(board);
        return flag;
    }

    //게시물 수정 중간처리
    public boolean update(Board board) {
        boolean flag = repository.modify(board);
        return flag;
    }


    // 게시물 삭제 중간처리
    public boolean delete(Long boardNo) {
        boolean flag = repository.remove(boardNo);
        return flag;
    }

}
