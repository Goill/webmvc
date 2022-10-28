package com.spring.webmvc.board.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void builderTest() {
        //나머지만 넣고 보기 싫은데 생성자를 쓰면 정리하기 힘듬
        Board board = new Board.Builder()
                .title("제목")
                .boardNo(20L)
                .content("ㅎㅇㅎㅇ")
                .writer("ㅋㅋㅋㅋ")
                .build();


    }
}