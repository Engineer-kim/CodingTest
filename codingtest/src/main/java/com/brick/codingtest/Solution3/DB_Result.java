package com.brick.codingtest.Solution3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DB_Result {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public List<String> getData() {
        List<String> results = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String query = """
            SELECT
                직원.emp_no AS "종업원 번호 (emp_no)",
                직원.first_name AS "이름 (first_name)",
                직원.last_name AS "성 (last_name)",
                직원.gender AS "성별 (gender)",
                직원.hire_date AS "고용일자 (hire_date)",
                부서.dept_name AS "부서 이름 (dept_name)",
                직급.title AS "직급(title)",
                MAX(급여.salary) AS "최대 급여 (max_salary)"
            FROM
                employees 직원
                JOIN dept_emp 사원정보 ON 직원.emp_no = 사원정보.emp_no
                JOIN departments 부서 ON 사원정보.dept_no = 부서.dept_no
                JOIN titles 직급 ON 직원.emp_no = 직급.emp_no
                JOIN salaries 급여 ON 직원.emp_no = 급여.emp_no
            WHERE
                사원정보.to_date = '9999-01-01'
                AND 직급.to_date = '9999-01-01'
                AND 급여.to_date = '9999-01-01'
                AND 직원.hire_date >= '2000-01-01'
            GROUP BY
                직원.emp_no, 직원.first_name, 직원.last_name, 직원.gender, 직원.hire_date, 부서.dept_name, 직급.title
            ORDER BY
                직원.emp_no;
            """;

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int empNo = resultSet.getInt("종업원 번호 (emp_no)");
                String firstName = resultSet.getString("이름 (first_name)");
                String lastName = resultSet.getString("성 (last_name)");
                String gender = resultSet.getString("성별 (gender)");
                String hireDate = resultSet.getString("고용일자 (hire_date)");
                String deptName = resultSet.getString("부서 이름 (dept_name)");
                String title = resultSet.getString("직급(title)");
                double maxSalary = resultSet.getDouble("최대 급여 (max_salary)");
                String resultString = String.format("Emp No: %d, Name: %s %s, Gender: %s, Hire Date: %s, Dept: %s, Title: %s, Max Salary: %.2f", empNo, firstName, lastName, gender, hireDate, deptName, title, maxSalary);
                results.add(resultString);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}