package com.KeyStone.FieldService2.Service;

	import java.util.List;

	import com.KeyStone.FieldService2.Entity.WorkOrder;
	import com.KeyStone.FieldService2.Enum.WorkOrderStatus;

	public interface WorkOrderService {

	    WorkOrder createWorkOrder(WorkOrder workOrder);

	    WorkOrder getWorkOrder(Long id);

	    List<WorkOrder> getAllWorkOrders();

	    List<WorkOrder> getWorkOrdersByCustomer(Long customerId);

	    List<WorkOrder> getWorkOrdersByTechnician(Long technicianId);

	    List<WorkOrder> getWorkOrdersByStatus(WorkOrderStatus status);

	    WorkOrder updateWorkOrder(Long id, WorkOrder workOrder);
	    
	    WorkOrder updateWorkOrderStatus(Long id, WorkOrderStatus status);

	    void deleteWorkOrder(Long id);
	}

