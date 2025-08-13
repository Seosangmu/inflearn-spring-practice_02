package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import jakarta.inject.Provider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    //private final MyLogger myLogger; // MyLogger는 request스코프이기 때문에 스프링 실행시점에서 주입오류가 떠버린다.
    //private final ObjectProvider<MyLogger> myLoggerProvider; // 그래서 Provider 사용
    private final MyLogger myLogger; //MyLogger를 상속받은 MyLogger의 가짜 객체가 주입됨
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String LogDemoController(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString(); // 이때 MyLogger가 빈생성,주입이 된다.(지연시킴)

        System.out.println("myLogger = " + myLogger.getClass());
        // MyLogger$$SpringCGLIB$$0 라는 프록시 클래스가 조회됨
        // 가짜 프록시 객체는 요청이오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.

        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testID");

        return "OK";
    }
}
