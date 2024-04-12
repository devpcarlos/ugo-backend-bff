package com.ugo.services;

import com.ugo.dto.ReserveDTO;
import com.ugo.dto.StateDTO;
import com.ugo.entitys.Reserve;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IReserveService {
    List<ReserveDTO> FindAll();
    void Save(ReserveDTO reserveDTO, HttpServletRequest request);
    ReserveDTO FindById(Long Id);
    void Update(Long Id,ReserveDTO reserveDTO);
    void DeleteById(Long id);

}
