use healthcaresystemdb;

# Ticket #2.2


SELECT sum(TotalAmount) as TotalBilled 
from billing;

SELECT sum(PaidAmount) as TotalPaid
from billing;

select sum(BalanceAmount) as TotalUnpaidBalance
from billing 
where PaymentStatus = 'Unpaid';




