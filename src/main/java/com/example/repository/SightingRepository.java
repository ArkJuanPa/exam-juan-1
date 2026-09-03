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
        save(new Sighting(1, "775-225", "Its good", "Tucan", " Ramphastidae ", "Tree", "Amazonas", 2, 2, 1));
        save(new Sighting(2, "652-1563", "Its bad", "Crocodile", "Crocodylidae", "Lake", "SIERRA_NEVADA", 3, 7, 2));

    }
}
