package com.ensat.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cID ;
    private  String cname;

    @OneToMany(mappedBy = "category")
    @JsonIgnore // tham khảo thêm JsonBackReferences, JsonManagerReferences
    private List<Product> products;
}
