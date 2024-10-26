package ddd.base.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

@Slf4j
public class StringTypeHandler extends BaseTypeHandler<Object> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
			ps.setString(i, JSON.toJSONString(parameter));
	}

	@Override
	public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
		try{
			return JSON.parseObject(rs.getString(columnName), List.class);
		}catch(JSONException e){
			//兼容历史数据
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("exepct '[', but ident,")){
				String value = rs.getString(columnName);
				if(StringUtils.isBlank(value)){
					return null;
				}
				List<String> list = new ArrayList<>();
				list.add(value);
				return list;
			}
			log.info("getNullableResultError context is  {},column is {}",rs.getString(columnName),columnName);
			throw e;
		}catch(Exception e){
		    log.info("getNullableResultError context is  {},column is {}",rs.getString(columnName),columnName);
		    throw e;
		}
	}

	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return JSON.parseObject(rs.getString(columnIndex), List.class);
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return JSON.parseObject(cs.getString(columnIndex), List.class);
	}

}
