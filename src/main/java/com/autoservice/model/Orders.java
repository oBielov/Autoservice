package com.autoservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "orders", schema = "service")
public class Orders extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @Column(name = "issue")
    private String issue;

    @Column(name = "opened")
    private LocalDate opened;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Services> services;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Goods> goods;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "total")
    private Double total;

    @Column(name = "closed")
    private LocalDate closed;

    public Double getTotal(){
        if(customer.getOrders().size() > 0){
            return (calculateServicePrice()) - (calculateServicePrice()/100 * customer.getOrders().size()*2) +
                    (calculateGoodsPrice()) - (calculateGoodsPrice()/100 * customer.getOrders().size());
        }
        else if(customer.getOrders().isEmpty()) return 500.00;
        return calculateServicePrice() + calculateGoodsPrice();

    }

    private Double calculateServicePrice(){
        if(services.isEmpty()) return 0.0;
        return services.stream().filter(s -> s != null && s.getPrice() != null)
                .mapToDouble(Services::getPrice).sum();
    }

    private Double calculateGoodsPrice(){
        if(goods.isEmpty()) return 0.0;
        return goods.stream().filter(g -> g != null && g.getPrice() != null)
                .mapToDouble(Goods::getPrice).sum();
    }

}
