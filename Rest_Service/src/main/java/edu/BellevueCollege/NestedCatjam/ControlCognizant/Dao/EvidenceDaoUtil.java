package edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ComplianceEvidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.EvidenceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EvidenceDaoUtil {
    private static final List<ComplianceEvidence> EVIDENCE = new ArrayList<>();

    private static long evidenceCount;

    public List<ComplianceEvidence> findAll() {
        return EVIDENCE;
    }

    public ComplianceEvidence save(ComplianceEvidence evidence) {
        if (evidence.getId() == null) {
            evidence.setId(UUID.randomUUID());
        }
        EVIDENCE.add(evidence);
        evidenceCount++;
        return evidence;
    }

    public Optional<ComplianceEvidence> findEvidence(UUID id) {
        for (ComplianceEvidence evidence : EVIDENCE) {
            if (evidence.getId().equals(id)) {
                return Optional.of(evidence);
            }
        }
        return Optional.empty();
    }

    public Optional<ComplianceEvidence> deleteById(UUID id) {
        Iterator<ComplianceEvidence> iterator = EVIDENCE.iterator();
        while (iterator.hasNext()) {
            ComplianceEvidence evidence = iterator.next();
            if (evidence.getId().equals(id)) {
                iterator.remove();
                return Optional.of(evidence);
            }
        }
        evidenceCount--;
        return Optional.empty();
    }

    public void updateById(ComplianceEvidence evidence) {
        if (evidence.getId() == null) {
            throw new EvidenceNotFoundException("Evidence ID is null");
        }
        for (ComplianceEvidence evidence1 : EVIDENCE) {
            if (evidence1.getId().equals(evidence.getId())) {
                evidence1.setEvidence(evidence.getEvidence());
                evidence1.setControlsImplemented(evidence.getControlsImplemented());
            }
        }
    }
}
