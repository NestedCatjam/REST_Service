package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.exception.Auth0Exception;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.EvidenceNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.common.EvidenceRequest;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import javax.transaction.Transactional;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;


@RestController
public class EvidenceController {
    @Autowired
    private EvidenceRepository evidenceRepository;




    @Autowired private UserManagementService userManagementService;



    @Transactional
    @GetMapping(value = "/organizations/{organizationID}/nist_control/get/{controlID}/evidence", produces = "application/json")
    public List<Evidence> getEvidenceForBy(Authentication authentication, @PathVariable("organizationID") String organizationID, @PathVariable("controlID") long controlID) throws Auth0Exception {
        if (notInOrganization(authentication, organizationID)) {
            throw new AccessDeniedException("The user is not in the organization that they are trying to get evidence for.");
            // TODO: check for role
        }
        return evidenceRepository.findAllByOrganizationIDAndNistControlId(organizationID, controlID);
    }

    @org.springframework.transaction.annotation.Transactional
    @PostMapping("/organizations/{organizationID}/nist_control/get/{controlID}/evidence")
    public Evidence saveEvidence(Authentication authentication, @PathVariable("organizationID") String organizationID, @PathVariable("controlID") long controlID, @RequestPart("file") MultipartFile file) throws Auth0Exception, IOException, NoSuchAlgorithmException {
        if (notInOrganization(authentication, organizationID)) {
            throw new AccessDeniedException("The user is not in the organization that they are trying to post evidence on behalf of.");
            // TODO: check for role
        }

        var evidence = new Evidence();
        evidence.setNistControlId(controlID);
        evidence.setOrganizationID(organizationID);
        evidence.setContributorAuth0ID(authentication.getName());
        evidence.setDescription(file.getResource().getDescription());
        evidence.setName(file.getOriginalFilename());
        evidence.setType(file.getContentType());
        evidence.setBase64(Base64.encodeBase64String(file.getBytes()));
        final var digest = MessageDigest.getInstance("SHA-256");
        evidence.setChatid(evidence.getOrganizationID() + "-" + evidence.getContributorAuth0ID() + "-" +
                evidence.getNistControlId() + "-" + Base64.encodeBase64String(digest.digest(file.getBytes())) + "-" +
                RandomStringUtils.randomAlphanumeric(256, 512));

        evidence = evidenceRepository.save(evidence);
        return evidence;
    }

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
