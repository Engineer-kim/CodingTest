package com.brick.codingtest.Solution3.service;

import com.brick.codingtest.Solution3.dto.EmployeeResult;
import com.brick.codingtest.Solution3.mapper.DBResultHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
    private final DBResultHelper dbResultHelper;

    public DBService(DBResultHelper dbResultHelper) {
        this.dbResultHelper = dbResultHelper;
    }

    public List<EmployeeResult> getData() {
        return dbResultHelper.getData();
    }
}
