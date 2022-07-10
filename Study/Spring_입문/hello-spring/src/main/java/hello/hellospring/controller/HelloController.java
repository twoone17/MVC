package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String helo(Model model){
        model.addAttribute("data","hello!");
        return "hello";
        //templete 밑에있는 hello를 찾고 Thymeleaf 템플릿 엔진이 처리한다
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template.html";
    }

    @GetMapping("hello-string")
    @ResponseBody //body부분에 내가 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //요청한 클라에 그대로 내려간다, view가 없다
    }

    //데이터를 내놓으려고 할때? 이럴때 api를 많이쓴다
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    //객체로 반환하면 json형태로 넘겨주게 된다
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
