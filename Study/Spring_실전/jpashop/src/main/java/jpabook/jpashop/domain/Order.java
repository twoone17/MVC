package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //객체를 새로 만들지 말라는 뜻, 생성자가 protected임, createOrder로 하라는거구나
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

    //==생성메서드==//

    /**
     * order 연관관계 걸고 상태랑 모든 것들이 정리가 된다
     * 생성하는 지점을 변경할때 이 로직만 변경하면 된다 !
     * 빌더 패턴인가 이게 ?
     * */
    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    /**
     * 주문 취소 CANCEL 버튼 누를때 다시 재고 올라가는것
     */
    public void cancel(){
        if(delivery.getStatus() ==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
