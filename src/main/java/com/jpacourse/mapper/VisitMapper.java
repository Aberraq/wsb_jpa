package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitMapper {

    public VisitTO mapToTO(VisitEntity entity) {
        if (entity == null) return null;

        VisitTO to = new VisitTO();
        to.setTime(entity.getTime());
        to.setDoctorFirstName(entity.getDoctor().getFirstName());
        to.setDoctorLastName(entity.getDoctor().getLastName());
        to.setTreatmentTypes(
                entity.getTreatments()
                        .stream()
                        .map(treatment -> treatment.getType().name())
                        .collect(Collectors.toList())
        );
        return to;
    }
}
