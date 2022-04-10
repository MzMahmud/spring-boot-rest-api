package com.moazmahmud.spring_boot_rest_api.order;

import com.moazmahmud.spring_boot_rest_api.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_id"))
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts;
}
