package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    @Autowired
//    public OrderServiceImpl(DiscountPolicy rateDiscountPolicy, MemberRepository memberRepository) {
//    public OrderServiceImpl(@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy, MemberRepository memberRepository) {
    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
//        this.discountPolicy = discountPolicy;
        this.discountPolicy = discountPolicy;
//        this.discountPolicy = rateDiscountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
