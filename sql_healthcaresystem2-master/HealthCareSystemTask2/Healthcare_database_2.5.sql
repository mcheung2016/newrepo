use healthcaresystemdb;

# Ticket #2.5


select TotalAmount, 
	case 
		when TotalAmount > 1000 then 'High'
        when TotalAmount between 500 and 1000 then 'Medium'
        else 'Low'
	end as BillCatagory
from billing;


select DateOfBirth,
	case
		WHEN (year(curdate()) - year(DateOfBirth)) < 18 then 'Minor'
        when (year(curdate()) - year(DateOfBirth)) between 18 and 65 then 'Adult'
        else 'Senior'
	end as AgeCatagory
    from patients;
    
    
    
    
    
    
    
    
    
    