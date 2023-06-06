package backend_shop_app_test.UtilTest;

import java.sql.SQLException;
import java.util.Properties;

import javax.script.ScriptException;
import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

public class DatabaseUtils {

	
	public static DataSource defaultInMemoryDataSource(boolean loadTestDatabase) throws ScriptException, SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		if (loadTestDatabase) {
			ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("schema-h2.sql"));
		}
		return dataSource;
	}

	public static DataSource defaultInMemoryDataSourceSchema(boolean loadTestDatabase) throws ScriptException, SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		if (loadTestDatabase) {
			ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("schema-h2.sql"));
		}
		return dataSource;
	}
	
	public static DataSource dataSource(Properties properties) throws ScriptException, SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(properties.getProperty("spring.datasource.url"));
		dataSource.setUsername(properties.getProperty("spring.datasource.username"));
		dataSource.setPassword(properties.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
	public static NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
}
