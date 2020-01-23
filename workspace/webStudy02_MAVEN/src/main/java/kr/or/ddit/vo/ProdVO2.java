package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor // 모든 생성자 받기
//@Builder
@EqualsAndHashCode(of= {"prod_id","prod_name"})
@ToString(exclude = {"prod_detail"})
//@Alias("prodVO")
public class ProdVO2 implements Serializable{
	
	public ProdVO2(String prod_id, String prod_name) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
	}
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String prod_buyer;
	private Integer prod_cost;
	private Integer prod_price;
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Integer prod_totalstock;
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
}
