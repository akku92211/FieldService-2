package com.KeyStone.FieldService2.controller;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;

	import com.KeyStone.FieldService2.Entity.WorkOrder;
	import com.KeyStone.FieldService2.Enum.WorkOrderStatus;
	import com.KeyStone.FieldService2.Service.WorkOrderService;

	@RestController
	@RequestMapping("/api/workorders")
	public class WorkOrderController {

	    @Autowired
	    private WorkOrderService workOrderService;

	    // Create Work Order
	    @PostMapping
	    public ResponseEntity<WorkOrder> createWorkOrder(
	            @RequestBody WorkOrder workOrder) {

	        return ResponseEntity.ok(workOrderService.createWorkOrder(workOrder));


	    }

	    // Get Work Order by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<WorkOrder> getWorkOrder(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                workOrderService.getWorkOrder(id)
	        );
	    }

	    // Get All Work Orders
	    @GetMapping
	    public ResponseEntity<List<WorkOrder>> getAllWorkOrders() {

	        return ResponseEntity.ok(
	                workOrderService.getAllWorkOrders()
	        );
	    }

	    // Get Work Orders by Customer
	    @GetMapping("/customer/{customerId}")
	    public ResponseEntity<List<WorkOrder>> getWorkOrdersByCustomer(
	            @PathVariable Long customerId) {

	        return ResponseEntity.ok(
	                workOrderService.getWorkOrdersByCustomer(customerId)
	        );
	    }

	    // Get Work Orders by Technician
	    @GetMapping("/technician/{technicianId}")
	    public ResponseEntity<List<WorkOrder>> getWorkOrdersByTechnician(
	            @PathVariable Long technicianId) {

	        return ResponseEntity.ok(
	                workOrderService.getWorkOrdersByTechnician(technicianId)
	        );
	    }

	    // Get Work Orders by Status
	    @GetMapping("/status")
	    public ResponseEntity<List<WorkOrder>> getWorkOrdersByStatus(
	            @RequestParam WorkOrderStatus status) {

	        return ResponseEntity.ok(
	                workOrderService.getWorkOrdersByStatus(status)
	        );
	    }

	    // Update Work Order
	    @PutMapping("/{id}")
	    public ResponseEntity<WorkOrder> updateWorkOrder(
	            @PathVariable Long id,
	            @RequestBody WorkOrder workOrder) {

	        return ResponseEntity.ok(
	                workOrderService.updateWorkOrder(id, workOrder)
	        );
	    }
	    
	    @PutMapping("/{id}/status")
	    public ResponseEntity<WorkOrder> updateWorkOrderStatus(
	            @PathVariable Long id,
	            @RequestParam WorkOrderStatus status) {

	        return ResponseEntity.ok(
	                workOrderService.updateWorkOrderStatus(id, status)
	        );
	    }

	    // Delete Work Order
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteWorkOrder(
	            @PathVariable Long id) {

	        workOrderService.deleteWorkOrder(id);

	        return ResponseEntity.ok("Work Order deleted successfully");
	    }
	

}
