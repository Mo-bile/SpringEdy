package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    //디카프리오가 여배우 캐스팅 하는 상황임
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    /*
    * 이게 설계가 잘 된거임
    * orderservice 입장에서는 몰라 discountPolicy 니가 알아서해
    * 결과만 나한테 알려줘 -> 단일체계원칙 준수함
    * */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);


        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
