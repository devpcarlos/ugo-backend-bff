package com.ugo.services;

import com.ugo.dto.ReserveDetailsDto;
import com.ugo.dto.reserveRequestDto;

import java.util.List;

public interface IReserveService {

    List<reserveRequestDto> findAll();

    void save(ReserveDetailsDto reserveDTO);

    reserveRequestDto FindById(Long Id);

    void Update(Long Id, ReserveDetailsDto reserveDTO);

    void DeleteById(Long id);

}
