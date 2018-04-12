package jp.co.wangtora;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.wangtora.model.A;
import jp.co.wangtora.model.S;
import lombok.Data;

@RestController
public class HelloController {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	DozerBeanMapper mapper;

	@RequestMapping("/")
	String hello() {

		return "Hello World!";
	}

	@Data
	static class Result {

		private final int left;

		private final int right;

		private final long answer;
	}

	protected S dozermapper_604(A a) {

		return mapper.map(a, S.class, "case1");
	}

	// SQL sample
	@RequestMapping("calc")
	Result calc(@RequestParam int left, @RequestParam int right) {

		MapSqlParameterSource source = new MapSqlParameterSource()
				.addValue("left", left)
				.addValue("right", right);
		return jdbcTemplate.queryForObject("SELECT :left + :right AS answer", source,
				(rs, rowNum) -> new Result(left, right, rs.getLong("answer")));
	}
}
