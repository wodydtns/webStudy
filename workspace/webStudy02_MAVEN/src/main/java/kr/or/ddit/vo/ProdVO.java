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
@EqualsAndHashCode(of= {"prod_name","lprod_gu"})

@Alias("prodVO")
public class ProdVO implements Serializable{
	private String lprod_gu;
	private String prod_name;
	private String prod_insdate;
	private Integer prod_cost; 
	private Integer prod_price;
	private Integer prod_mileage;
	private String prod_buyer;
	private String lprod_nm;
	
}
