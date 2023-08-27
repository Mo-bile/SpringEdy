package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야한다") //이렇게 티스트명 지정가능
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    //테스트는 성공테스트외 실패테스트도 만들어 보아야함
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야한다")
    void vip_x() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(0);

    }

}