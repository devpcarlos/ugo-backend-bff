package com.ugo.services;

import com.ugo.dto.StateDTO;
import com.ugo.entitys.State;
import com.ugo.exceptions.CustomerException;
import com.ugo.repository.IStateRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StateService implements IStateService {

    @Autowired
    private IStateRepository stateRepository;

    @Override
    public List<StateDTO> findAll() {
        List<StateDTO> stateDTOList = stateRepository.findAll()
                .stream()
                .map(state -> StateDTO.builder()
                        .id(state.getId())
                        .name(state.getName())
                        .created(state.getCreated())
                        .updated(state.getUpdated())
                        .build())
                .toList();
        return stateDTOList;
    }

    @Override
    public void save(StateDTO stateDTO, HttpServletRequest request) {
        if (stateDTO.getName().isBlank()) {
            throw new CustomerException(400, "INCORRECT STATUS");
        }
        stateRepository.save(State.builder()
                .name(stateDTO.getName())
                .created(new Date(System.currentTimeMillis()))
                .updated(new Date(System.currentTimeMillis()))
                .build());
    }

    @Override
    public StateDTO findById(Long id) {
        Optional<State> stateOptional = stateRepository.findById(id);
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            StateDTO stateDTO = StateDTO.builder()
                    .id(state.getId())
                    .name(state.getName())
                    .created(state.getCreated())
                    .updated(state.getUpdated())
                    .build();
            return stateDTO;
        } else {
            throw new CustomerException(404, "INCORRECT ID");
        }
    }

    @Override
    public void update(Long id, StateDTO stateDTO) {
        Optional<State> state = stateRepository.findById(id);
        if (state.isPresent()) {
            State s = state.get();
            stateRepository.save(State.builder()
                    .id(stateDTO.getId())
                    .name(stateDTO.getName())
                    .created(s.getCreated())
                    .updated(new Date(System.currentTimeMillis()))
                    .build());
        } else {
            throw new CustomerException(404, "ERROR UPDATE");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            stateRepository.deleteById(id);
        } else {
            throw new CustomerException(404, "ERROR DELETE");
        }
    }
}
