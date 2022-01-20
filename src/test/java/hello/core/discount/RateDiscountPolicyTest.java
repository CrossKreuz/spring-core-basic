package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("Vip는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        Member vip = new Member(3L, "vip", Grade.VIP);
        int discount = discountPolicy.discount(vip, 10000);
        System.out.println("discount = " + discount);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("기본인은 0원 적용되어야 한다.")
    void vip_x() {
        Member vix = new Member(4L, "basic", Grade.BASIC);
        int discount = discountPolicy.discount(vix, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}