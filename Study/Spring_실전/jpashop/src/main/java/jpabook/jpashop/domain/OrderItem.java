package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class OrderItem {

    //id
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //item
    private Item item;

    //order

    //orderprice

    //count
}
