use healthcaresystemdb;

# Ticket #2.3

select avg(TotalAmount) as AverageBilled
from billing;


select avg(year(curdate()) - year(DateOfBirth)) 
as  AverageAge
from patients;

select avg(PaidAmount) as AveragePaid
from billing
where PaymentStatus = 'Paid';

select avg(Dosage) as AverageDosage
from prescriptions;

