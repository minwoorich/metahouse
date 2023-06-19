package com.multi.metahouse.domain.dto.point;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("mypoint")
public class MyPointDTO {
	List<ChargedPointInfo> chargedConsumedInfoList;
	List<ConsumedPointInfo> consumedConsumedInfoList;
}
