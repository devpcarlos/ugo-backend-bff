package com.ugo.services;

import com.ugo.dto.StateDTO;
import com.ugo.entitys.State;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IStateService {

    List<StateDTO> findAll();

    void save(StateDTO stateDTO, HttpServletRequest request);

    State findById(Long id);
    void update(Long id, StateDTO stateDTO);

    void deleteById(Long id);

}
