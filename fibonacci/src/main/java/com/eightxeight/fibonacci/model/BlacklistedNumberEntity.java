package com.eightxeight.fibonacci.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name= "blacklisted")
@Getter
@Setter
public class BlacklistedNumberEntity {

    @Id
    private int number;
}
