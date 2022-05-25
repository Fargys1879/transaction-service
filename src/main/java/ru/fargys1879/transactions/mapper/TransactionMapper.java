package ru.fargys1879.transactions.mapper;


import org.mapstruct.Mapper;
import ru.fargys1879.transactions.dto.TransactionDto;
import ru.fargys1879.transactions.model.Transaction;

@Mapper
public interface TransactionMapper {

    Transaction mapTransactionDto(TransactionDto dto);
}
