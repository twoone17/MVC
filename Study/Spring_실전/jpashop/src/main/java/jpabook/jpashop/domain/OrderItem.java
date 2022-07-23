package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //객체를 새로 만들지 말라는 뜻, 생성자가 protected임,
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

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item,int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); //제품을 주문했으면 stock에서 하나를 까줘야함
        return orderItem;
    }

    //== 비즈니스 로직==//
    //재고를 늘리는게 목표
    public void cancel() {
        getItem().addStock(count);
    }


    //==조회 로직 ==//

    /**
     * 주문 상품 가격 조회회
     */
    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
}
