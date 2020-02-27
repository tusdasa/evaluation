package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.AcademicYearClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYear;
import org.springframework.stereotype.Component;

@Component
public class AcademicYearClientImpl implements AcademicYearClient {
    @Override
    public CommonResponse<AcademicYear> current() {
        return new CommonResponse<AcademicYear>().busy();
    }
}
