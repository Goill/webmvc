package com.spring.webmvc.board.controller;

import com.spring.webmvc.board.domain.Board;
import com.spring.webmvc.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor // final을 위한 생성자 주입
@Controller // 컨트롤러 빈 등록
@RequestMapping("/board") // 공통 URL 진입점 설정
@Log4j2 //로그 라이브러리
public class BoardController {
    
    private final BoardService service;
    
    //게시물 목록 조회 요청 처리
    @GetMapping("/list")
    public String list(Model model) {
        int a = 10;
        List<Board> boardList = service.getList();
        log.info("/board/list GET! 요청 발생 - {}", a);
        System.out.println("요청");
        model.addAttribute("bList", boardList); //뷰로 보낼때 사용
        return "board/list";
        /* TRACE - 잡다한 자잘한 로그
         *  DEBUG - 개발단계의 디버깅
         *  INFO -정보
         *  WARN - 경고
         *  ERROR - 심각한 에러
         * */

    }
    // 주소 ? 뒤에 오는거 RequestParam
    // 주소 / PathVariable
    // @ 안에 Requestbody


    // 게시물 상세 조회 요청 처리
    @GetMapping("/content/{bno}")
    public String content(@PathVariable("bno") Long boardNo, Model model) {
        log.info("/board/content/{} get!!", boardNo);
        Board board = service.getDetail(boardNo); //Board에 저장

        model.addAttribute("b", board); // 모델에 저장해서 보여주기
        return "board/detail";
    }

    //게시물 쓰기 화면 요청
    @GetMapping("/write")
    public String write() {
        log.info("/board/write Get!");
        return "board/write";
    }

    // 게시물 등록 요청
    @PostMapping ("/write") //주소가 같아도 요청방식이 다르면 사용 가능.
    public String write(Board board, RedirectAttributes ra) { //등록은 post로
        log.info("board/write Post! - {}", board);

        boolean flag = service.insert(board);
        ra.addFlashAttribute("msg", "insert-success");
        return flag ? "redirect:/board/list" : "redirect:/"; // 포워딩으로 바로 보내면 안된다
        // redirect로 두개의 요청을 부탁한다.

    }
}
