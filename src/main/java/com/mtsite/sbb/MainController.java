package com.mtsite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    int increaseNum = -1;

    @RequestMapping("/sbb") //메인사이트
    @ResponseBody
    public String index() {
        System.out.println("sbb");
        return "sbb";
    }

    @GetMapping("/page1") // 베이스 인풋으로 보내기전
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type='number' name="age" placeholder="나이"/>
                    <input type="submit" value="page2로 POST방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2") // 베이스 보내고 난후
    @ResponseBody
    public String showPage2Post(@RequestParam(value = "age", defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>POST방식으로 옴</h1>
                """.formatted(age);
    }


    @GetMapping("/plus") // 더하기
    @ResponseBody
    public String plus(@RequestParam(required = false) Integer a, @RequestParam(required = false) Integer b) {
        if( a == null ) {
            return "a를 입력해주세요.";
        }
        if( b == null ) {
            return "b를 입력해주세요.";
        }
        return String.valueOf(a + b);
    }

    @GetMapping("/minus") // 빼기
    @ResponseBody
    public String minus(@RequestParam(required = false) Integer a, @RequestParam(required = false) Integer b) {
        if( a == null ) {
            return "a를 입력해주세요.";
        }
        if( b == null ) {
            return "b를 입력해주세요.";
        }
        return String.valueOf(a - b);
    }

    @GetMapping("/increase") //1씩 증가
    @ResponseBody
    public int increase() {

        return increaseNum++;
    }

    @GetMapping("/servletPlus") // 더하기 다른 문제로도
    @ResponseBody
    public void showPlus(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));

        res.getWriter().append(a + b + "");
    }

    @GetMapping("gugudan") //구구단
    @ResponseBody
    public String gugudan(int dan, int limit) {
        // 이런 방식으로도 구현할 수 있다
//        final Integer finalDan = dan;
//        return IntStream.rangeClosed(1, limit)
//                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
//                .collect(Collectors.joining("<br>\n"));
        String result = "";
        for(int i = 1; i <= limit; i++) {
            result += dan + " * " + i + " = " + (dan *  i) + "<br/>\n";
        }
        return result;

    }

}