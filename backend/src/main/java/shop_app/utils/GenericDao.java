package shop_app.utils;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


public class GenericDao extends NamedParameterJdbcDaoSupport {

	protected String paramsToString(Object[] params) {
		StringBuilder sb = new StringBuilder(100);
		Object[] var3 = params;
		int var4 = params.length;

		for (int var5 = 0; var5 < var4; ++var5) {
			Object o = var3[var5];
			sb.append(o).append(", ");
		}

		return sb.substring(0, sb.length() - 2).toString();
	}
}