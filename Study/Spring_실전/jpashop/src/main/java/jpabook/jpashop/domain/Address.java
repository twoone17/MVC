package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//내장타입이기 떄문에
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipCode;
}
