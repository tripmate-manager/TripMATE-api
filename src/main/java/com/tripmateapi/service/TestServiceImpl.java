package com.tripmateapi.service;

import com.tripmateapi.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDAO testDAO;

    @Override
    public String test() {
        return testDAO.test();
    }
}
