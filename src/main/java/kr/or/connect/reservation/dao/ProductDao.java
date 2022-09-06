package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.CommentDto;
import kr.or.connect.reservation.dto.CommentImageDto;
import kr.or.connect.reservation.dto.DisplayInfoDto;
import kr.or.connect.reservation.dto.DisplayInfoImageDto;
import kr.or.connect.reservation.dto.ProductImageDto;
import kr.or.connect.reservation.dto.ProductItemDto;
import kr.or.connect.reservation.dto.ProductPriceDto;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductItemDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductItemDto.class);
	private RowMapper<CommentDto> commentRowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);
	private RowMapper<CommentImageDto> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImageDto.class);
	private RowMapper<DisplayInfoDto> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoDto.class);
	private RowMapper<DisplayInfoImageDto> displayInfoImageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImageDto.class);
	private RowMapper<ProductImageDto> proudcImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);
	private RowMapper<ProductPriceDto> proudcPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPriceDto.class);

	public ProductDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/*
	 * categoryId 인 상품에 대해 조회
	 */
	public List<ProductItemDto> selectProducts(Integer categoryId, Integer start) {
		String sql = new String(SELECT_PRODUCTS);

		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);

		List<ProductItemDto> productList = jdbc.query(sql, params, rowMapper);
		return productList;
	}

	/*
	 * 전체 상품에 대해 조회 (categoryId = 0)
	 */
	public List<ProductItemDto> selectAllProducts(Integer start) {
		String sql = new String(SELECT_ALL_PRODUCTS);

		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);

		List<ProductItemDto> productList = jdbc.query(sql, params, rowMapper);
		return productList;
	}


	public int selectProductCntById(int categoryId) {
		String sql = new String(SELECT_PRODUCTS_CNT_BY_ID);
		Map<String, Integer> params = new HashMap<>();

		if(categoryId == 0) {
			sql = sql.replace("category.id = :categoryId AND", "");
		} else {
			params.put("categoryId", categoryId);
		}
		Integer productCnt = jdbc.queryForObject(sql, params, Integer.class);
		if(productCnt == null) {
			return 0;
		}
		return productCnt;
	}

	public List<CommentDto> selectComment(int displayInfoId) {
		String sql = SELECT_COMMENT_BY_DISPLAY_ID;
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(sql, params, commentRowMapper);
	}

	public double selectAvgScore(int displayInfoId) {
		String sql = SELECT_COMMENT_AVG_SCORE_BY_DISPLAY_ID;
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		Double avgScore = jdbc.queryForObject(sql, params, Double.class);
		if(avgScore == null) {
			return 0;
		}
		return avgScore;
	}

	public List<CommentImageDto> selectCommentImageByCommentId(int commentId) {
		String sql = SELECT_COMMENT_IMAGE_BY_COMMENT_ID;

		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);

		return jdbc.query(sql, params, commentImageRowMapper);
	}

	public DisplayInfoDto selectDisplayInfo(int displayInfoId){
		String sql = SELECT_DISPLAYINFO_BY_DISPLAY_ID;
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(sql, params, displayInfoRowMapper);
	}

	public DisplayInfoImageDto selectDisplayInfoImage(int displayInfoId) {
		String sql = SELECT_DISPLAY_INFO_IMG_BY_DISPLAY_ID;
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(sql, params, displayInfoImageRowMapper);
	}

	public List<ProductImageDto> selectProductImage(int displayInfoId) {
		String sql = SELECT_PRODUCT_IMAGE_BY_DISPLAY_ID;
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(sql, params, proudcImageRowMapper);
	}

	public List<ProductPriceDto> selectProductPrice(int displayInfoId) {
		String sql = SELECT_PRODUCT_PRICE_BY_DISPLAY_ID;
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(sql, params, proudcPriceRowMapper);
	}
}
