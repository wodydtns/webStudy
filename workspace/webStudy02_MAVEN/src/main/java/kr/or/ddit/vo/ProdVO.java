package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

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
@EqualsAndHashCode(of= {"prod_id", "prod_name"})
@ToString(exclude = {"prod_detail"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("prodVO")
public class ProdVO implements Serializable {
	
	public ProdVO(String prod_id, String prod_name) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
	}
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String lprod_nm;
	private String prod_buyer;
	private String buyer_name;
	// has a 관계 (1:1)
	private BuyerVO buyer;
	private List<MemberVO> memberList;
	
	
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
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
}
