package com.multi.metahouse.domain.dto.asset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDetailImgDTO {
    private String asset_detail_img_id;
    private String asset_id;
    private String asset_detail_img_store_filename;
    private String asset_fileno;
}