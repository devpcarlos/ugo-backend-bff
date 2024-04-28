package com.ugo.services;
import com.ugo.dto.reserveDetailsDto;
import com.ugo.dto.reserveRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
public interface IReserveService {
    List<reserveRequestDto> FindAll();
    void Save(reserveDetailsDto reserveDTO, HttpServletRequest request);
    reserveRequestDto FindById(Long Id);
    void Update(Long Id,reserveDetailsDto reserveDTO);
    void DeleteById(Long id);

}
