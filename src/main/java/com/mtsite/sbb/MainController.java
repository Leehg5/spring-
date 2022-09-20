package com.mtsite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    int increaseNum = -1;     // 지역함수로  increase 에들어간다 ( 1씩 증가 )

    @RequestMapping("/sbb")
    @ResponseBody
    public String index() {
        System.out.println("sbb");
        return "sbb";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type='number' name="age" placeholder="나이"/>
                    <input type="submit" value="page2로 POST방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(value = "age", defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>POST방식으로 옴</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(value = "age", defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>GET방식으로 옴</h1>
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

    @GetMapping("/minus") //빼기
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

    @GetMapping("/increase") //1씩증가
    @ResponseBody
    public int increase() {

        return increaseNum++;
    }
}
