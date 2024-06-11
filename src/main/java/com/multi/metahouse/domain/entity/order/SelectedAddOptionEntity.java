package com.multi.metahouse.domain.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.multi.metahouse.domain.entity.project.AddOptionEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "selected_add_option")
public class SelectedAddOptionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "selected_add_option_id")
	private Long selectedAddOptionId;
	@Column(name="order_id")
	private Long orderId;
//	@Column(name="add_option_id")
//	private Long addOptionId;
	private String count;
	
	////////부모참조////////////
	//단방향
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="add_option_id")
	private AddOptionEntity addOptionId;
}
