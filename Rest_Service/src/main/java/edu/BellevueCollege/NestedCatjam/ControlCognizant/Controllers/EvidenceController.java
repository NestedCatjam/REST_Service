package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.exception.Auth0Exception;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.EvidenceNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.common.EvidenceRequest;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
public class EvidenceController {
    @Autowired
    private EvidenceRepository evidenceRepository;


    @Autowired private UserManagementService userManagementService;

    @Transactional
    @GetMapping("/api/v1/evidence/get")
    public ResponseEntity<Object> getEvidence(Authentication authentication, @RequestBody EvidenceRequest request) throws Auth0Exception {
        try {
            if (notInOrganization(authentication, request.getOrganizationId())) {
                throw new AccessDeniedException("The current user is not a member of the organization whose evidence is being requested.");
                // TODO: check for role
            }
            Evidence evidence = evidenceRepository.findByIdAndOrganizationID(request.getEvidenceId(), request.getOrganizationId());
            return ResponseEntity.ok().body(evidence);
        } catch (EvidenceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private boolean notInOrganization(Authentication authentication, String organizationID) throws Auth0Exception {
        return userManagementService.getOrganizations(authentication).getItems().stream().noneMatch(organization -> organization.getId().equals(organizationID));
    }

    @Transactional
    @GetMapping("/api/v1/evidence/get_all_by_organization")
    public ResponseEntity<Object> getAllEvidence(Authentication authentication, @RequestBody String organizationId) throws Auth0Exception {
        try {
            if (notInOrganization(authentication, organizationId)) {
                throw new AccessDeniedException("The current user is not a member of the organization whose evidence is being requested.");
            }
            List<Evidence> evidence = evidenceRepository.findAllByOrganizationID(organizationId);
            return ResponseEntity.ok().body(evidence);
        } catch (EvidenceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PostMapping("/api/v1/evidence/save")
    public ResponseEntity<Evidence> saveEvidence(Authentication authentication, @RequestBody Evidence evidence) {
        try {
            evidence.setContributorAuth0ID(authentication.getName());
            if (notInOrganization(authentication, evidence.getOrganizationID())) {
                throw new AccessDeniedException("The user is not a member of the organization on whose behalf evidence is being saved.");
            }
            evidenceRepository.save(evidence);
            return ResponseEntity.ok().body(evidence);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(evidence);
        }
    }
}
