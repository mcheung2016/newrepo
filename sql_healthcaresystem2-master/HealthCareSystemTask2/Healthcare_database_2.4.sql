use healthcaresystemdb;

# Ticket #2.4

select min(appointmentDate) as EarliestAppointment, 
max(appointmentDate) as LatestAppointment
from appointments;

select min(TotalAmount) as LowestBilledAmount, 
max(TotalAmount) as HighestBilledAmount
from billing;

select min(Dosage) as MinimumDosage, max(Dosage) as MaximumDosage
from prescriptions;


select min(year(curdate()) - year(DateOfBirth)) as YoungestPatient,
max(year(curdate()) - year(DateOfBirth)) as OldestPatient
from patients;

