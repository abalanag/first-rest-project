package ro.siit.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "packs")
public class Pack {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String targetCity;

    @Column
    private BigDecimal targetDistance;

    @Column
    private BigDecimal value;

    @Column
    private LocalDateTime deliveryData;

    public Pack() {
    }

    public Pack(Long id, String title, String targetCity, BigDecimal targetDistance, BigDecimal value, LocalDateTime deliveryData) {
        this.id = id;
        this.title = title;
        this.targetCity = targetCity;
        this.targetDistance = targetDistance;
        this.value = value;
        this.deliveryData = deliveryData;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTargetCity() {
        return targetCity;
    }

    public BigDecimal getTargetDistance() {
        return targetDistance;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getDeliveryData() {
        return deliveryData;
    }
}
