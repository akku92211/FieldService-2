package com.KeyStone.FieldService2.Entity;

	import java.math.BigDecimal;
	import java.time.LocalDateTime;

	import jakarta.persistence.*;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Entity
	@Table(name = "time_logs")
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class TimeLog {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "work_order_id", nullable = false)
	    private WorkOrder workOrder;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	    private UserEntity user;

	    @Column(name = "start_time", nullable = false)
	    private LocalDateTime startTime;

	    @Column(name = "end_time")
	    private LocalDateTime endTime;

	    @Column(precision = 5, scale = 2)
	    private BigDecimal hours;

	    private String description;
	}

