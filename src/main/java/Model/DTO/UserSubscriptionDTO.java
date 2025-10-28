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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author phant
 */
@Entity(name = "tblUserSubscription")
@Table(name = "tblUserSubscription")
public class UserSubscriptionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
    private UserDTO user;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "plan_id", nullable = false)
    private SubscriptionDTO subscription;

    @Column(name = "start_date", insertable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_active", insertable = false, updatable = false)
    private Boolean isActive;

    @Column(name = "hidden")
    private Boolean hidden = false;

    public UserSubscriptionDTO() {
    }

    public UserSubscriptionDTO(Integer subscriptionId, UserDTO user, SubscriptionDTO subscription, LocalDateTime startDate, LocalDateTime endDate, Boolean isActive) {
        this.subscriptionId = subscriptionId;
        this.user = user;
        this.subscription = subscription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Boolean getIsDeleted() {
        return hidden;
    }

    public void setIsDeleted(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public SubscriptionDTO getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionDTO subscription) {
        this.subscription = subscription;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
