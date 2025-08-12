package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") DI시 Qualifier의 mainDiscountPolicy로 매칭되는 걸 주입해준다.
//@Primary // 만약 DiscountPolicy의 여러개의 빈이 조회되면 @Primary붙은게 우선순위를 가진다.
@MainDiscountPolicy // 내가 직접만든 어노테이션, 문자열의 타입오류를 잡아줄수있다.
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
