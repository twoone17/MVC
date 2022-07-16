package jpabook.jpashop.domain.item;

import jpabook.jpashop.Exception.NotEnoughStockException;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item { //구현체를 가지고 할것이기떄문에 추상클래스

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    //상속관계 매핑을 해야함
    //name
    private String name;
    //price
    private int price;
    //stockquantity
    private int stockQuantity;
    //category
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //this에 있는 field 로직을 추가해주는게 좋다
    //변경할 일이 있으면 setter로 바꿔주는게 아닌, 비즈니스 로직을 통하여 변경해야한다.


    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
    * stock 감소
    * */

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
    }

    //dtype

    //artist

    //etc

    //autor

    //isbn

    //director

    //actor


}
