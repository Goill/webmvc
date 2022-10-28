<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="../include/static-head.jspf" %>

    <style>
        .board-list {
            width: 70%;
            margin: 0 auto;
        }

        .board-list .articles {
            margin: 250px auto 100px;
            border-collapse: collapse;
            font-size: 1.5em;
            border-radius: 10px;
        }

        .board-list .btn-write {
            /* background: orange; */
            text-align: right;
            position: relative;
            top: -70px;
        }

        header {
            background: #222;
            border-bottom: 1px solid #2c2c2c;
        }


    </style>
</head>

<body>

<div class="wrap">

    <%@ include file="../include/header.jspf" %>

    <div class="board-list">
        <table class="table table-dark table-striped table-hover articles">
            <tr>
                <th>번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>조회수</th>
                <th>작성시간</th>
            </tr>

            <c:forEach var="b" items="${bList}">
                <tr>
                    <td>${b.boardNo}</td>
                    <td>${b.writer}</td>
                    <td title="${b.title}">${b.shortTitle}</td>
                    <td>0</td>
                    <td>${b.regDate}</td>
                </tr>
            </c:forEach>
        </table>

        <div class="btn-write">
            <a class="btn btn-outline-danger btn-lg" href="/board/write">글쓰기</a> <%--a태그로 나가니 get방식--%>
        </div>
    </div>


    <%@ include file="../include/footer.jspf" %>

</div>

<script>
    //상세보기 요청 이벤트
    const $table = document.querySelector(".articles");

    $table.addEventListener('click', e => {


        if (!e.target.matches('.articles td')) return;

        console.log('tr 클릭됨! - ', e.target);

        let bn = e.target.parentElement.firstElementChild.textContent; // 부모를 찾고 그 다음 첫번째 자식에게 접근
        console.log('글번호: ' + bn);

        location.href = '/board/content/' + bn;
    });

    //게시물 등록 성공시 알림창 생성
    const msg = '${msg}';
    console.log(msg);

    if (msg === 'insert-success') {
        alert('게시물 등록에 성공했습니다!');
    }

</script>

</body>

</html>