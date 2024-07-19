package PersistenceInterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import florist.Florist;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;


    @Repository
    public class JsonFloristRepository implements FloristRepository {
        private final ObjectMapper objectMapper = new ObjectMapper();
        private final File file = new File("florists.json");

        @Override
        public Florist findByName(String name) {
            try {
                Florist[] florists = objectMapper.readValue(file, Florist[].class);
                for (Florist florist : florists) {
                    if (florist.getName().equals(name)) {
                        return florist;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void save(Florist florist) {
            try {
                Florist[] florists = objectMapper.readValue(file, Florist[].class);
                Florist[] updatedFlorists = new Florist[florists.length + 1];
                System.arraycopy(florists, 0, updatedFlorists, 0, florists.length);
                updatedFlorists[florists.length] = florist;
                objectMapper.writeValue(file, updatedFlorists);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean existsByName(String name) {
            return findByName(name) != null;
        }
    }

