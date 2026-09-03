package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.model.Sighting;

public class SightingRepository {

    private List<Sighting> sightings;

    public SightingRepository() {
        sightings = new ArrayList<>();
    }

    public void save(Sighting Sighting) {
        sightings.add(Sighting);
    }

    public List<Sighting> findAll() {
        return sightings;
    }

    public boolean existsById(int id) {
        for (Sighting Sighting : sightings) {
            if (Sighting.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Sighting searchById(int id) {
        for (Sighting Sighting : sightings) {
            if (Sighting.getId() == id) {
                return Sighting;
            }
        }
        return null;
    }

    public boolean existBySessionCode(String sessionCode) {
        for (Sighting Sighting : sightings) {
            if (Sighting.getSightingCode().equals(sessionCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean existByexpeditionId(int expeditionId) {
        for (Sighting Sighting : sightings) {
            if (Sighting.getExpeditionId() == expeditionId) {
                return true;
            }
        }
        return false;
    }

    public void initialize() {
        save(new Sighting(1, "SESSION1", "Alice", 1200, 45, "North America", 1));
        save(new Sighting(2, "SESSION2", "Bob", 850, 30, "Europe", 1));
        save(new Sighting(3, "SESSION3", "Charlie", 2100, 90, "South America", 1));
    }
}
