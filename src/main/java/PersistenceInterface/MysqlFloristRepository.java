package PersistenceInterface;

import florist.Florist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlFloristRepository implements FloristRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Florist findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM florists WHERE name = ?", new Object[]{name},
                (rs, rowNum) -> new Florist(rs.getString("name")));
    }

    @Override
    public void save(Florist florist) {
        jdbcTemplate.update("INSERT INTO florists (name) VALUES (?)", florist.getName());
    }

    @Override
    public boolean existsByName(String name) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM florists WHERE name = ?", new Object[]{name}, Integer.class);
        return count != null && count > 0;
    }
}


