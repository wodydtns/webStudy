package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원관리 Domain Layer (VO, DTO, Bean, Model)
 * mybatis를 이용해 여러 테입르을 조회하는 단계
 * situation : 한명의 회원 정보에 구매 기록을 포함해 보여주어야하는 상황(MEMBER: prod - 1:N - MEMBERVO has many ProdVO )
 * 한 건의 상품 정보에 거래처 정보를 포함 (PROD : buyer - 1:1 - PRODVO has a buyerVO)
 * 
 * 해결 과정
 * 1. 문제 해결에 필요한 테이블 선택
 * 2. 주 데이터를 가진 메인 테이블을 선택
 * 3. 테이블간의 관계 파악 - 1:N 구조 or 1:1 구조 - 1은 메인 테이블
 * 4. 테이블 간의 관계를 객체에 반영
 *  	1:1 -> has a로
 *  	1:N -> has many로
 * 5. xml에서 result를 resultType 대신에 resultMap을 사용
 * 		has a - association 으로 바인딩
 * 		has many - collection 으로 바인딩
 */
@Alias("memberVO") // Custom Alias
@Getter
@Setter
@EqualsAndHashCode(of = {"mem_id","mem_regno1","mem_regno2"})
@ToString
public class MemberVO implements Serializable{
	
	public MemberVO() {
		super();
	}
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	private int rn;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private transient String mem_regno1;
	private transient String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	
	//has many (member : prod -> 1:n 관계)
	private List<ProdVO> prodList;
	
}
