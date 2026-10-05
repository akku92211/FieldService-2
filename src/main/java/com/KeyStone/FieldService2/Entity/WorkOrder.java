package com.KeyStone.FieldService2.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.KeyStone.FieldService2.Enum.WorkOrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "work_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true, length = 50)
    private String code = "WO-" + System.currentTimeMillis();


    private String title;

    private String description;

    private String priority;


    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @Column(name = "sla_due_date")
    private LocalDateTime slaDueDate;


    // =========================
    // CUSTOMER
    // =========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sites"})
    private Customer customer;


    // =========================
    // SITE
    // =========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "customer"})
    private Site site;


    // =========================
    // TECHNICIAN
    // =========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    @JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "handler",
        "password"
    })
    private UserEntity technician;


    // =========================
    // BEFORE CREATE
    // =========================

    @PrePersist
    public void beforeCreate() {

        if (status == null) {
            status = WorkOrderStatus.NEW;
        }

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }


    // =========================
    // GETTERS / SETTERS
    // =========================

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }


    public WorkOrderStatus getStatus() {
        return status;
    }


    public void setStatus(WorkOrderStatus status) {
        this.status = status;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public LocalDateTime getSlaDueDate() {
        return slaDueDate;
    }


    public void setSlaDueDate(LocalDateTime slaDueDate) {
        this.slaDueDate = slaDueDate;
    }


    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Site getSite() {
        return site;
    }


    public void setSite(Site site) {
        this.site = site;
    }


    public UserEntity getTechnician() {
        return technician;
    }


    public void setTechnician(UserEntity technician) {
        this.technician = technician;
    }

}