# Write your MySQL query statement below
with salaryRank as (
    select salary, dense_rank() over(order by salary desc) as rnk
    from employee
)
select max(salary) as secondhighestsalary
from salaryRank
where rnk = 2;