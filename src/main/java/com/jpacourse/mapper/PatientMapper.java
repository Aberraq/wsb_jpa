package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    private final VisitMapper visitMapper;

    public PatientMapper(VisitMapper visitMapper) {
        this.visitMapper = visitMapper;
    }

    public PatientTO mapToTO(PatientEntity entity) {
        if (entity == null) return null;

        PatientTO to = new PatientTO();
        to.setId(entity.getId());
        to.setFirstName(entity.getFirstName());
        to.setLastName(entity.getLastName());
        to.setInsured(entity.isInsured());

        List<VisitTO> visits = entity.getVisits()
                .stream()
                .map(visitMapper::mapToTO)
                .collect(Collectors.toList());

        to.setVisits(visits);
        return to;
    }

    public PatientEntity mapToEntity(PatientTO to) {
        throw new UnsupportedOperationException("Mapping from TO to entity not implemented.");
    }
}
