package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /*
    *   @ManyToOne(fetch = FetchType.Eager) 설정 시 n + 1 문제
    *   JPQL select o from order o; -> SQL select * from order 100 + 1(order)
    *   order의 결과가 100개 이면 거기에 속한 member_id를 다시 100번 select 를 날려서 n + 1 문제가 발생한다.
    * */

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;// 주문시간

    private OrderStatus status; //주문상태 [ORDER, CANCEL]
}
