package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    //name
    private String name;
    //items
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    //다대다 관계에선 해소하는 테이블이 필요하다 : JoinTable 사용
    //왜 쓰지 말라면 이 그림말고 다른 데 사용이 안된다. 하지만 jpa에서 되긴한다
    private List<Item> items = new ArrayList<>();
    //parent
    //내 부모니까 many to one? 부모는 하나니까
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    //child
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
