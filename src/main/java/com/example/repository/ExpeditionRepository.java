package com.example.repository;

import com.example.model.Expedition;

import java.util.ArrayList;
import java.util.Collection;

public class ExpeditionRepository {

    private Collection<Expedition> expeditions;

    public ExpeditionRepository() {
        expeditions = new ArrayList<>();
    }

    public Collection<Expedition> findAll() {
        return expeditions;
    }

    public void save(Expedition expedition) {
        expeditions.add(expedition);
    }

    public void deleteById(int expeditionId) {
        expeditions.removeIf(expedition -> expedition.getId() == expeditionId);
    }

    public boolean existById(int expeditionId) {
        return expeditions.stream().anyMatch(expedition -> expedition.getId() == expeditionId);
    }

    public Expedition searchById(int expeditionId) {
        return expeditions.stream()
                .filter(expedition -> expedition.getId() == expeditionId)
                .findFirst()
                .orElse(null);
    }

    public boolean existByCode(String code) {
        for (Expedition expedition : expeditions) {
            if (expedition.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void initialize() {
        save(new Expedition(1, "Amazon", "VG0001", "AMAZONIA", "KIASK", "Juan Martinez", "2022-10-12", "2022-12-10",
                "ACTIVE"));
        save(new Expedition(2, "Lili", "VG00541", "SIERRA_NEVADA", "DEFLU", "Pablo Rosero", "2026-02-18", "2026-09-7",
                "ACTIVE"));

    }

}
