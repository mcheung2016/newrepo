use healthcaresystemdb;

# Ticket #2.1

SELECT count(*) as TotoalPatients From patients;

SELECT count(*) as CompletedAppointments From appointments
WHERE Status ='Completed';

SELECT count(*) AS AsthmaCase FROM medicalrecords
WHERE Diagnosis = 'Asthma';