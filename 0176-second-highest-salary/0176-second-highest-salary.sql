# Write your MySQL query statement below
SELECT DISTINCT max(salary) as SecondHighestSalary FROM Employee
where salary<(SELECT max(salary) FROM Employee);