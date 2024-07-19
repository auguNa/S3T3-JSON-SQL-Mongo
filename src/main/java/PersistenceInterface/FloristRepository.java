package PersistenceInterface;

import florist.Florist;

public interface FloristRepository {
    Florist findByName(String name);

    void save(Florist florist);

    boolean existsByName(String name);

}
