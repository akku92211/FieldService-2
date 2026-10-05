package com.KeyStone.FieldService2.Entity;

	import jakarta.persistence.*;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Entity
	@Table(name = "part_usage")
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class PartUsage {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "work_order_id", nullable = false)
	    private WorkOrder workOrder;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "part_id", nullable = false)
	    private Part part;

	    @Column(nullable = false)
	    private Integer quantity;
	}
