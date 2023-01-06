package com.tripmate.domain.test.service;

import com.tripmate.domain.test.dao.TestDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private TestDAO testDAO;

    @Autowired
    public TestServiceImpl(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    @Override
    public String test() {
        return testDAO.test();
    }
}
