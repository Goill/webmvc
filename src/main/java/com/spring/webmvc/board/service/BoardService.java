package com.spring.webmvc.board.service;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        processBoardList(boardList);
        return boardList;
    }

    private void processBoardList(List<Board> boardList) {
        for (Board b : boardList) {
            subStringTitle(b); // 만약에 글제목이 6글자 이상이면 6글자까지만 보여주고 뒤에 ... 처리
            convertDateFormat(b);
            isNewArticle(b); //신규 게시물 new마크 처리 (10분 이내 작성된 게시물)

        }
    }

    private void isNewArticle(Board b) {
        long redDate = b.getRegDate().getTime(); //게시물 작성시간(밀리초)
        long nowDate = System.currentTimeMillis(); // 현재 시간 (밀리초)

        long diff = nowDate - redDate; //작성후 지난 시간 (밀리초)
        long limit = 10 *60 * 1000; //밀리초니 * 1000 해줘야함 10분을 밀리초로 변환

        if(diff <= limit) {
            b.setNewArticle(true);
        }
    }

    private void convertDateFormat(Board b) {
        //날짜 포맷팅 처리
        Date regDate = b.getRegDate();
        SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd a  hh:mm");
        b.setPrettierDate(sdf.format(regDate));
    }

    private void subStringTitle(Board b) {
        // 게시물 제목 줄임 처리
        String title = b.getTitle();
        if (title.length() > 6) {
            String shortTitle = title.substring(0, 6) + "...";
            b.setShortTitle(shortTitle);
        } else {
            b.setShortTitle(title);
        }
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
