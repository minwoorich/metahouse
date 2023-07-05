package com.multi.metahouse.domain.entity.project.jpadto;



import com.multi.metahouse.domain.entity.project.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectListDTO {
	private Long project_id;
	private String creator_id;
	private String title;
	private int min_price;
	private String category1;
	private String category2_pj;
	private String thumbnail;
	
	public ProjectListDTO fromEntity(ProjectEntity entity) {
		
		if(entity.getSingleEntity() != null) {
			min_price = entity.getSingleEntity().getPrice();
		}else {
			min_price = entity.getTripleEntity().getBasicPrice();
		}
		
		return ProjectListDTO.builder()
				.project_id(entity.getProjectId())
				.creator_id(entity.getCreatorId())
				.title(entity.getTitle())
				.min_price(min_price)
				.category1(entity.getCategory1())
				.category2_pj(entity.getCategory2Pj())
				.thumbnail(entity.getThumbnail())
				.build();
	}
}
