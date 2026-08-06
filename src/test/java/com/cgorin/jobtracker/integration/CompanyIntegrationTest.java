package com.cgorin.jobtracker.integration;

import com.cgorin.jobtracker.repository.CompanyRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CompanyIntegrationTest {
    @Mock
    private CompanyRepository companyRepository;
}
