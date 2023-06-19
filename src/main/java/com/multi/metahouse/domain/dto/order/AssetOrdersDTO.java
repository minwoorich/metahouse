package com.multi.metahouse.domain.dto.order;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("assetOrder")
public class AssetOrdersDTO {
	private String order_id;
	private String asset_id;
	private String buyer_id2;
	private String order_number;
	private String asset_order_date;
}
