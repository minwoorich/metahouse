package com.multi.metahouse.domain.entity.asset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assets")
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String assetId;
    private String sellerId;
    private String title;
    private String category1;
    @Column(name="category2_as")
    private String category2;
    private String description;
    private String price;
    private String mainImg;
    private String assetDate;
    private String assetHits;
}