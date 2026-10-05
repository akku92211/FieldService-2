package com.KeyStone.FieldService2.Repository;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

	import com.KeyStone.FieldService2.Entity.WorkOrder;
	import com.KeyStone.FieldService2.Enum.WorkOrderStatus;

	@Repository
	public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

	    List<WorkOrder> findByCustomerId(Long customerId);

	    List<WorkOrder> findByTechnicianId(Long technicianId);

	    List<WorkOrder> findByStatus(WorkOrderStatus status);
	}

