package com.multi.metahouse.domain.entity.asset;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @Column(name = "asset_id")
    private String assetId;
    @Column(name = "seller_id")
    private String sellerId;
    private String title;
    private String category1;
    @Column(name="category2_as")
    private String category2;
    private String description;
    private int price;
    private String mainImg;
    @CreationTimestamp
    private LocalDateTime assetDate;
    
    /////////////////////////////////////////////
    
    
    ////////////////////////////////////////////
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "assetId")
//    private AssetContentEntity assetContentEntity = new AssetContentEntity();
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assetId")
//    private List<AssetDetailImgEntity> assetDetailImgEntityList = new ArrayList<>();
    
}