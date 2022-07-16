package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //orderItem의 order와 매핑,
    //persist(orderItemA) B C 까지 한 뒤에
    //persist(order)해야하는데 order만 하면 밑 코드는 지워도됨 이전에 한것들 전파한다.
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //orderDate
    private LocalDateTime orderDate;

    //status
    private OrderStatus status; // 주문상태 [ORDER , CANCEL]

    //==연관관계 편의 메서드==//
    /*
    Member member = new Member();
    Order order = new Order();

    member.getOrders().add(order);
    order.setMember(member);
    -> 이 두개를 묶는 메서드
     */
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
