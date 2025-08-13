package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class SingletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletoBean.class);
        SingletoBean singletonBean1 = ac.getBean(SingletoBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        SingletoBean singletonBean2 = ac.getBean(SingletoBean.class);
        System.out.println("singletonBean2 = " + singletonBean2);


        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close(); // 종료
    }

    //@Scope("singleton")
    static class SingletoBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletoBean.init");
        }

        @PreDestroy
        public void destoty() {
            System.out.println("SingletoBean.destoty");
        }

    }

}
