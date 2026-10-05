package com.KeyStone.FieldService2.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.Entity.Customer;
import com.KeyStone.FieldService2.Entity.Site;
import com.KeyStone.FieldService2.Entity.UserEntity;
import com.KeyStone.FieldService2.Entity.WorkOrder;
import com.KeyStone.FieldService2.Enum.WorkOrderStatus;
import com.KeyStone.FieldService2.Repository.WorkOrderRepository;

import jakarta.persistence.EntityManager;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private EntityManager entityManager;


    // =====================================================
    // CREATE WORK ORDER
    // =====================================================

    @Override
    public WorkOrder createWorkOrder(WorkOrder workOrder) {

        workOrder.setCode("WO-" + System.currentTimeMillis());

        if (workOrder.getStatus() == null) {
            workOrder.setStatus(WorkOrderStatus.NEW);
        }

        workOrder.setCreatedAt(LocalDateTime.now());
        workOrder.setUpdatedAt(LocalDateTime.now());

        // Customer
        if (workOrder.getCustomer() != null
                && workOrder.getCustomer().getId() != null) {

            Customer customer = entityManager.getReference(
                    Customer.class,
                    workOrder.getCustomer().getId()
            );

            workOrder.setCustomer(customer);
        }

        // Site
        if (workOrder.getSite() != null
                && workOrder.getSite().getId() != null) {

            Site site = entityManager.getReference(
                    Site.class,
                    workOrder.getSite().getId()
            );

            workOrder.setSite(site);
        }

        // Technician / Assignee
        if (workOrder.getTechnician() != null
                && workOrder.getTechnician().getId() != null) {

            UserEntity technician = entityManager.getReference(
                    UserEntity.class,
                    workOrder.getTechnician().getId()
            );

            workOrder.setTechnician(technician);
        }

        return workOrderRepository.save(workOrder);
    }


    // =====================================================
    // GET WORK ORDER BY ID
    // =====================================================

    @Override
    public WorkOrder getWorkOrder(Long id) {

        return workOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Work Order not found with id: " + id
                        )
                );
    }


    // =====================================================
    // GET ALL WORK ORDERS
    // =====================================================

    @Override
    public List<WorkOrder> getAllWorkOrders() {

        return workOrderRepository.findAll();
    }


    // =====================================================
    // GET BY CUSTOMER
    // =====================================================

    @Override
    public List<WorkOrder> getWorkOrdersByCustomer(Long customerId) {

        return workOrderRepository.findByCustomerId(customerId);
    }


    // =====================================================
    // GET BY TECHNICIAN
    // =====================================================

    @Override
    public List<WorkOrder> getWorkOrdersByTechnician(Long technicianId) {

        return workOrderRepository.findByTechnicianId(technicianId);
    }


    // =====================================================
    // GET BY STATUS
    // =====================================================

    @Override
    public List<WorkOrder> getWorkOrdersByStatus(
            WorkOrderStatus status) {

        return workOrderRepository.findByStatus(status);
    }


    // =====================================================
    // UPDATE WORK ORDER
    // =====================================================

    @Override
    public WorkOrder updateWorkOrder(
            Long id,
            WorkOrder workOrder) {

        WorkOrder existing = getWorkOrder(id);


        // -----------------------------
        // BASIC FIELDS
        // -----------------------------

        existing.setTitle(workOrder.getTitle());

        existing.setDescription(workOrder.getDescription());

        existing.setPriority(workOrder.getPriority());


        // -----------------------------
        // CUSTOMER
        // -----------------------------

        if (workOrder.getCustomer() != null
                && workOrder.getCustomer().getId() != null) {

            Long customerId =
                    workOrder.getCustomer().getId();

            Customer customer =
                    entityManager.getReference(
                            Customer.class,
                            customerId
                    );

            existing.setCustomer(customer);
        }


        // -----------------------------
        // SITE
        // -----------------------------

        if (workOrder.getSite() != null
                && workOrder.getSite().getId() != null) {

            Long siteId =
                    workOrder.getSite().getId();

            Site site =
                    entityManager.getReference(
                            Site.class,
                            siteId
                    );

            existing.setSite(site);
        }


        // -----------------------------
        // TECHNICIAN
        // -----------------------------

        if (workOrder.getTechnician() != null
                && workOrder.getTechnician().getId() != null) {

            Long technicianId =
                    workOrder.getTechnician().getId();

            UserEntity technician =
                    entityManager.getReference(
                            UserEntity.class,
                            technicianId
                    );

            existing.setTechnician(technician);
        }


        // -----------------------------
        // STATUS
        // -----------------------------

        if (workOrder.getStatus() != null) {

            existing.setStatus(
                    workOrder.getStatus()
            );
        }


        // -----------------------------
        // SLA DATE
        // -----------------------------

        if (workOrder.getSlaDueDate() != null) {

            existing.setSlaDueDate(
                    workOrder.getSlaDueDate()
            );
        }


        // -----------------------------
        // UPDATED TIME
        // -----------------------------

        existing.setUpdatedAt(
                LocalDateTime.now()
        );


        return workOrderRepository.save(existing);
    }


    // =====================================================
    // UPDATE STATUS
    // =====================================================

    @Override
    public WorkOrder updateWorkOrderStatus(
            Long id,
            WorkOrderStatus status) {

        WorkOrder existing = getWorkOrder(id);

        existing.setStatus(status);

        existing.setUpdatedAt(
                LocalDateTime.now()
        );

        return workOrderRepository.save(existing);
    }


    // =====================================================
    // DELETE WORK ORDER
    // =====================================================

    @Override
    public void deleteWorkOrder(Long id) {

        WorkOrder existing = getWorkOrder(id);

        workOrderRepository.delete(existing);
    }

}