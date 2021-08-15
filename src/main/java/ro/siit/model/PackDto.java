package ro.siit.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PackDto {

    private Long id;
    private String title;
    private String targetCity;
    private BigDecimal targetDistance;
    private BigDecimal value;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime deliveryData;

    public PackDto(){
    };

    public PackDto(Long id, String title, String targetCity, BigDecimal targetDistance, BigDecimal value, LocalDateTime deliveryData) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(String targetCity) {
        this.targetCity = targetCity;
    }

    public BigDecimal getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(BigDecimal targetDistance) {
        this.targetDistance = targetDistance;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDeliveryData() {
        return deliveryData;
    }

    public void setDeliveryData(LocalDateTime deliveryData) {
        this.deliveryData = deliveryData;
    }
}
