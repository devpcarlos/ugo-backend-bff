package com.ugo.services;

import com.ugo.dto.reserveDetailsDto;
import com.ugo.dto.reserveRequestDto;
import com.ugo.entitys.Reserve;
import com.ugo.entitys.State;
import com.ugo.entitys.User;
import com.ugo.exceptions.ReserveException;
import com.ugo.repository.IReserveRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ReserveService implements IReserveService {
    @Autowired
    UserService US;
    @Autowired
    IReserveRepository ReserveRepo;
    @Autowired
    StateServiceImpl SS;
    @Override
    public List<reserveRequestDto>FindAll(){
        List<reserveRequestDto>ReserveDtoList = ReserveRepo.findAll()
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
    public reserveRequestDto FindById(Long Id){
         Optional<Reserve>ReserveRequestDto = ReserveRepo.findById(Id);
         if (ReserveRequestDto.isPresent()){
             Reserve reserve = ReserveRequestDto.get();
             reserveRequestDto reserveRequest = reserveRequestDto.builder()
                     .duration(reserve.getDuration())
                     .pax(reserve.getPax())
                     .currency(reserve.getCurrency())
                     .experience(reserve.getExperience())
                     .build();
             return reserveRequest;
         }
         else {
             throw new ReserveException(500,"error");
         }
    }
    @Override
    public void Save(reserveDetailsDto reserveDTO, HttpServletRequest request){
        State state = SS.findById(reserveDTO.getState());
        User user = US.findById(reserveDTO.getReserveOwner());
        if(
                reserveDTO.getCurrency().isBlank() &&
                        reserveDTO.getReserveOwner()==null &&
                        reserveDTO.getState()==null &&
                        reserveDTO.getDuration()==0
        ){throw new ReserveException(400,"Incorrect status");}
            else {

                ReserveRepo.save(Reserve.builder()
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
    public void Update(Long Id,reserveDetailsDto reserveDTO){
        State state = SS.findById(reserveDTO.getState());
        User user = US.findById(reserveDTO.getReserveOwner());
        Optional<Reserve>reserveOptional=ReserveRepo.findById(Id);
        if(reserveOptional.isPresent())
        {
            ReserveRepo.save(Reserve.builder()
                    .Duration(reserveDTO.getDuration())
                    .CreationDate(new Date(System.currentTimeMillis()))
                    .UpdateDate(new Date(System.currentTimeMillis()))
                    .ReserveOwner(user)
                    .state(state)
                    .floor(reserveDTO.getFloor())
                    .Currency(reserveDTO.getCurrency())
                    .experience(reserveDTO.getExperience())
                    .build());
            }
        else {
            throw new ReserveException(404,"Update unaccomplished");
        }

    }
    @Override
    public void DeleteById(Long Id){
        if(Id!=null){ReserveRepo.deleteById(Id);}
        else {
            throw new ReserveException(404,"Incorrect Id");
        }
    }
}



