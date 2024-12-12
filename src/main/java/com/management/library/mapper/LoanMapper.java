package com.management.library.mapper;

import com.management.library.dto.loan.LoanDTO;
import com.management.library.entity.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, BookMapper.class})
public interface LoanMapper extends EntityMapper<LoanDTO, Loan> {
}