package com.example.service;

import java.util.List;
import com.example.repository.ExpeditionRepository;
import com.example.repository.SightingRepository;
import com.example.model.Sighting;

public class SightingService {

    private ExpeditionRepository expeditionRepository;
    private SightingRepository sightingRepository;

    public SightingService(SightingRepository sightingRepository, ExpeditionRepository expeditionRepository) {
        this.expeditionRepository = expeditionRepository;
        this.sightingRepository = sightingRepository;

    }

    public boolean addsighting(Sighting sighting) {
        if (sighting == null
                || sighting.getSightingCode() == null
                || sighting.getName() == null
                || sighting.getSightingCode().length() > 5 && sighting.getSightingCode().length() < 20
                || expeditionRepository.existById(sighting.getId())
                || expeditionRepository.existByCode(sighting.getSightingCode())) {
            return false;
        }
        sightingRepository.save(sighting);
        return true;
    }

    public List<Sighting> getsightings() {
        return sightingRepository.findAll();
    }
}
