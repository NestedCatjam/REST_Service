package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.EvidenceNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
public class EvidenceController {
    @Autowired
    EvidenceRepository evidenceRepository;

    @GetMapping("/api/evidence/get-evidence-by-id")
    public String getEvidenceById(@RequestParam String id) {
        try {
            for (Evidence evidence : evidenceRepository.findAll()) {
                if (evidence.getId() == (Long.parseLong(id))) {
                    byte[] bytes = toByteArray(evidence.getFile());
                    return Base64.encodeBase64String(bytes);
                } else {
                    throw new EvidenceNotFoundException("Evidence with id " + id + " not found");
                }
            }
        } catch (EvidenceNotFoundException | SQLException | IOException e) {
            return e.getMessage();
        }
        return "Evidence with id " + id + " not found";
    }

    @PostMapping("/api/evidence/post-evidence")
    @Transactional
    public String postEvidence(@RequestBody Evidence evidence) {
        try {
            evidenceRepository.save(evidence);
            return "Evidence saved";
        } catch (EvidenceNotFoundException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/api/evidence/delete-evidence")
    @Transactional
    public String deleteEvidence(@RequestParam String id) {
        try {
            if (evidenceRepository.findById(Long.valueOf(id)).isPresent()) {
                evidenceRepository.deleteById(Long.valueOf(id));
                return "Evidence deleted";
            } else {
                throw new ControlNotFoundException("Evidence with id " + id + " not found");
            }
        } catch (EvidenceNotFoundException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/api/evidence/update-evidence")
    @Transactional
    public String updateEvidence(@RequestParam String id, @RequestParam Evidence evidence) {
        try {
            if (evidenceRepository.findById(Long.valueOf(id)).isPresent()) {
                evidenceRepository.save(evidence);
                return "Evidence updated";
            } else {
                throw new ControlNotFoundException("Evidence with id " + id + " not found");
            }
        } catch (EvidenceNotFoundException e) {
            return e.getMessage();
        }
    }

    public static byte[] toByteArray(Blob fromBlob) throws SQLException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        InputStream is = fromBlob.getBinaryStream();
        while (true) {
            int data = is.read(buf);
            if (data == -1) {
                break;
            }
            baos.write(buf, 0, data);
        }
        return baos.toByteArray();
    }
}
