package BanpoXi.Dong.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        System.out.println("hewllwe");
        model.addAttribute("data", "hello!!");
        return "hello";
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "Hello-temeplete";
    }


    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;//hello spring
    }



    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello  = new Hello();
        hello.setName(name);
        return hello;//문자가 아닌 객체를 넘긴다!
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;// + "Dong!";
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}