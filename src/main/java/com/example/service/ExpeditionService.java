package com.example.service;

import java.util.Collection;

import com.example.model.Expedition;
import com.example.repository.ExpeditionRepository;
import com.example.repository.SightingRepository;

public class ExpeditionService {

    private ExpeditionRepository expeditionRepository;
    private SightingRepository sightingRepository;

    public ExpeditionService(ExpeditionRepository expeditionRepository,
            SightingRepository sightingRepository) {

        this.expeditionRepository = expeditionRepository;
        this.sightingRepository = sightingRepository;
    }

    public boolean addExpedition(Expedition expedition) {
        if (expedition == null
                || expedition.getCode() == null
                || expeditionRepository.existById(expedition.getId()) ||
                expeditionRepository.existByCode(expedition.getCode()) ||
                expedition.getCode().length() != 6 ||
                !"AVAILABLE".equalsIgnoreCase(expedition.getState())) {
            return false;

        }
        expeditionRepository.save(expedition);
        return true;
    }

    public boolean deleteExpedition(int expeditionId) {
        if (!expeditionRepository.existById(expeditionId) || sightingRepository.existByexpeditionId(expeditionId)) {
            return false;
        }
        expeditionRepository.deleteById(expeditionId);
        return true;
    }

    public Collection<Expedition> getExpeditions() {
        return expeditionRepository.findAll();
    }
}
