package com.brick.codingtest.Solution3.mapper;

import com.brick.codingtest.Solution3.dto.EmployeeResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DBResultHelper {
    List<EmployeeResult> getData();
}