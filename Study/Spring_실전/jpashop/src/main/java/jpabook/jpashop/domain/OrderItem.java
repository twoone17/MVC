package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    //id 기본키
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //item
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") //order의 기본키를 joincolumn
    //joinColumns	현재 엔티티를 참조하는 외래키
    private Order order;

    //orderprice
    private int orderPrice; //주문가격

    //count
    private int count; //주문 수량
}
