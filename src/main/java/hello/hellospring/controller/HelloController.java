package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //url 의미
    public String hello(Model model){
        //MVC할떄 모델임
        model.addAttribute("data", "hello");
        return "hello"; //템플릿 폴더의 hello 로 가라는 의미임
    }


}
