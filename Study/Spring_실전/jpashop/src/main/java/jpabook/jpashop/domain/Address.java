package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//내장타입이기 떄문에
//값이 변경이 되면 안된다
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipCode;

//jpa 스팩상 만들어놓는것
    protected Address() {
    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
