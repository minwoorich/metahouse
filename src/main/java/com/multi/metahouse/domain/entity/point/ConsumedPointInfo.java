package com.multi.metahouse.domain.entity.point;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "consume_point_info")
public class ConsumedPointInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int consumePointInfoId;	// id
	@NonNull
	private String userId;			// 유저 아이디
	@NonNull
	private int consumingPoint;		// 사용 포인트
	private String consumeInfo;		// 사용처
	@NonNull
	private int remainingPoint;		// 잔여 포인트
	@CreationTimestamp
	private Date consumeDate;		// 사용된 시간
	
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
}
