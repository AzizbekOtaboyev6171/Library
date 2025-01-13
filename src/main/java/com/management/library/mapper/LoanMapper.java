package com.management.library.mapper;

import com.management.library.dto.loan.LoanDTO;
import com.management.library.entity.Loan;
import com.management.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, BookMapper.class})
public interface LoanMapper extends EntityMapper<LoanDTO, Loan> {
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedBy")
    LoanDTO toDTO(Loan loan);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "loanMapIdToUser")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy", qualifiedByName = "loanMapIdToUser")
    Loan toEntity(LoanDTO loanDTO);

    @Named("loanMapIdToUser")
    default User mapIdToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}