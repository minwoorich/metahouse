package com.multi.metahouse.domain.entity.point;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor // 모든 멤버 매개변수로 하는 생성자 생성
@RequiredArgsConstructor
@NoArgsConstructor	// 기본 생성자 생성
@Entity
@Table(name = "charge_point_info")
public class ChargedPointInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chargePointInfoId;	// id
	@NonNull
	private String userId;			// 유저 아이디
	@NonNull
	private int chargingPoint;		// 충전 포인트
	private String paymentMethod;	// 결제 방식
	@NonNull
	private int remainingPoint;		// 잔여 포인트
	@CreationTimestamp
	@NonNull
	private Date chargeDate;		// 충전한 시간
	
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
}
