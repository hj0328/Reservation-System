package kr.or.connect.reservation.domain.comment;

import kr.or.connect.reservation.domain.comment.dto.CommentDto;
import kr.or.connect.reservation.domain.comment.dto.CommentImageDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.domain.comment.CommentDaoSql.*;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<CommentDto> commentRowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);
	private RowMapper<CommentImageDto> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImageDto.class);

	public CommentDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CommentDto> selectComment(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(SELECT_COMMENT_BY_DISPLAY_ID, params, commentRowMapper);
	}

	public List<CommentImageDto> selectCommentImageByCommentId(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);

		return jdbc.query(SELECT_COMMENT_IMAGE_BY_COMMENT_ID, params, commentImageRowMapper);
	}
}
