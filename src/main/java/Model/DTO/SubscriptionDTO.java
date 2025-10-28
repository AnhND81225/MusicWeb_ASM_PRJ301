/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author phant
 */
@Entity(name = "tblSubscription")
@Table(name = "tblSubscription")
public class SubscriptionDTO {

    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planID;

    @Column(name = "name_subscription", nullable = false)
    private String nameSubscription;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "duration_in_days", nullable = false)
    private Integer durationDay;

    @Column(name = "description")
    private String description;

    @Column(name = "hidden")
    private Boolean hidden = false;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Integer planID, String nameSubscription, Integer price, Integer durationDay, String description) {
        this.planID = planID;
        this.nameSubscription = nameSubscription;
        this.price = price;
        this.durationDay = durationDay;
        this.description = description;
    }

    public Integer getPlanID() {
        return planID;
    }

    public void setPlanID(Integer planID) {
        this.planID = planID;
    }

    public String getNameSubscription() {
        return nameSubscription;
    }

    public void setNameSubscription(String nameSubscription) {
        this.nameSubscription = nameSubscription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(Integer durationDay) {
        this.durationDay = durationDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
