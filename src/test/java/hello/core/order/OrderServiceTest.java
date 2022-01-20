package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    MemberService memberService ;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void vip_o() {
        Member vip = new Member(1L, "vio", Grade.VIP);
        memberService.join(vip);

        Order order_vip = orderService.createOrder(vip.getId(), "vip_product", 10000);
        Assertions.assertThat(order_vip.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void vip_x() {
        Member basic = new Member(2L, "vix", Grade.BASIC);
        memberService.join(basic);

        Order order_basic = orderService.createOrder(basic.getId(), "basic_product", 10000);
        Assertions.assertThat(order_basic.getDiscountPrice()).isEqualTo(0);
    }

}