package com.ugo.services;

import com.ugo.dto.ReserveDetailsDto;
import com.ugo.dto.reserveRequestDto;
import com.ugo.entitys.Reserve;
import com.ugo.entitys.State;
import com.ugo.entitys.User;
import com.ugo.exceptions.ReserveException;
import com.ugo.repository.IReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReserveService implements IReserveService {

    @Autowired
    UserService userService;

    @Autowired
    IReserveRepository reserveRepository;

    @Autowired
    StateServiceImpl stateService;

    @Override
    public List<reserveRequestDto> findAll() {
        List<reserveRequestDto> ReserveDtoList = reserveRepository.findAll()
                .stream()
                .map(Reserve -> reserveRequestDto.builder()
                        .reserveDetails(Reserve.getReserveDetails())
                        .pax(Reserve.getPax())
                        .currency(Reserve.getCurrency())
                        .duration(Reserve.getDuration())
                        .experience(Reserve.getExperience())
                        .build())
                .toList();
        return ReserveDtoList;
    }

    @Override
    public reserveRequestDto FindById(Long Id) {
        Optional<Reserve> ReserveRequestDto = reserveRepository.findById(Id);
        if (ReserveRequestDto.isPresent()) {
            Reserve reserve = ReserveRequestDto.get();
            reserveRequestDto reserveRequest = reserveRequestDto.builder()
                    .duration(reserve.getDuration())
                    .pax(reserve.getPax())
                    .currency(reserve.getCurrency())
                    .experience(reserve.getExperience())
                    .build();
            return reserveRequest;
        } else {
            throw new ReserveException(500, "error");
        }
    }

    @Override
    public void save(ReserveDetailsDto reserveDTO) {
        State state = stateService.findById(reserveDTO.getState());
        User user = userService.findById(reserveDTO.getReserveOwner());
        if (
                reserveDTO.getCurrency().isBlank() &&
                        reserveDTO.getReserveOwner() == null &&
                        reserveDTO.getState() == null &&
                        reserveDTO.getDuration() == 0
        ) {
            throw new ReserveException(400, "Incorrect status");
        } else {

            reserveRepository.save(Reserve.builder()
                    .Duration(reserveDTO.getDuration())
                    .CreationDate(new Date(System.currentTimeMillis()))
                    .UpdateDate(new Date(System.currentTimeMillis()))
                    .ReserveOwner(user)
                    .state(state)
                    .floor(reserveDTO.getFloor())
                    .Currency(reserveDTO.getCurrency())
                    .experience(reserveDTO.getExperience())
                    .build()
            );
        }
    }

    @Override
    public void Update(Long Id, ReserveDetailsDto reserveDTO) {
        State state = stateService.findById(reserveDTO.getState());
        User user = userService.findById(reserveDTO.getReserveOwner());
        Optional<Reserve> reserveOptional = reserveRepository.findById(Id);
        if (reserveOptional.isPresent()) {
            reserveRepository.save(Reserve.builder()
                    .Duration(reserveDTO.getDuration())
                    .CreationDate(new Date(System.currentTimeMillis()))
                    .UpdateDate(new Date(System.currentTimeMillis()))
                    .ReserveOwner(user)
                    .state(state)
                    .floor(reserveDTO.getFloor())
                    .Currency(reserveDTO.getCurrency())
                    .experience(reserveDTO.getExperience())
                    .build());
        } else {
            throw new ReserveException(404, "Update unaccomplished");
        }

    }

    @Override
    public void DeleteById(Long Id) {
        if (Id != null) {
            reserveRepository.deleteById(Id);
        } else {
            throw new ReserveException(404, "Incorrect Id");
        }
    }
}



