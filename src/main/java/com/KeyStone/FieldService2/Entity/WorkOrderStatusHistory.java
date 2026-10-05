package com.KeyStone.FieldService2.Entity;

	import java.time.LocalDateTime;

	import jakarta.persistence.*;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Entity
	@Table(name = "work_order_status_history")
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class WorkOrderStatusHistory {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "work_order_id", nullable = false)
	    private WorkOrder workOrder;

	    @Column(name = "old_status", length = 30)
	    private String oldStatus;

	    @Column(name = "new_status", nullable = false, length = 30)
	    private String newStatus;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "changed_by", nullable = false)
	    private UserEntity changedBy;

	    @Column(name = "changed_at")
	    private LocalDateTime changedAt;
	}

