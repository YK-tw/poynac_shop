package by.poynac.shop.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderProductKey implements Serializable {

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "product_id")
    Long productId;

}
