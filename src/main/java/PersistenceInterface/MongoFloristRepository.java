package PersistenceInterface;

import florist.Florist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoFloristRepository implements FloristRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Florist findByName(String name) {
        return mongoTemplate.findById(name, Florist.class);
    }

    @Override
    public void save(Florist florist) {
        mongoTemplate.save(florist);
    }

    @Override
    public boolean existsByName(String name) {
        return mongoTemplate.exists(Query.query(Criteria.where("name").is(name)), Florist.class);
    }
}