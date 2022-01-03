package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address adress;

    @Enumerated(EnumType.STRING) // STRING으로 해주어야 그대로 들어간다, default = ORDINAL 순서인 숫자로 들어간다.
    private DeliveryStatus status; //READY, COMP

}
