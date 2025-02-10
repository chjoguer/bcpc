package com.bcpc.movement.repository;

import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.controller.dto.ReportDTO;
import com.bcpc.movement.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository  extends JpaRepository<Movement, Long> {
    @Query(value = "select * from movement m where status =1",nativeQuery = true)
    List<Movement> findAllMovements();

    @Query(value = "select * from movement m where status =1  and m.number_account = :accountNumber",nativeQuery = true)
    List<Movement> findBynumberAccount(@Param("accountNumber") String accountNumber);

    @Query(value = "select m.movement_at as movementAt, " +
            "       p.name as name, " +
            "       a.number_account as numberAccount, " +
            "       a.type_account as typeAccount, " +
            "       m.status as status, " +
            "       m.initial_amount as initialAmount, " +
            "       m.movement_amount as movementAmount, " +
            "       (m.initial_amount + m.movement_amount) as totalAmount " +
            "from person p " +
            "inner join account a on p.identification = a.identification " +
            "inner join movement m on m.number_account = a.number_account " +
            "where a.number_account = :accountNumber " +
            "  and m.movement_at::date between :startDate and :endDate" +
            "  order by m.movement_at desc",
            nativeQuery = true)
    List<ReportDTO> findMovementByAccountAndDate(@Param("accountNumber") String accountNumber,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);


    @Query(value = "select m.movement_at as movementAt, " +
            "       p.name as name, " +
            "       a.number_account as numberAccount, " +
            "       a.type_account as typeAccount, " +
            "       m.status as status, " +
            "       m.initial_amount as initialAmount, " +
            "       m.movement_amount as movementAmount, " +
            "       (m.initial_amount + m.movement_amount) as totalAmount " +
            "from person p " +
            "inner join account a on p.identification = a.identification " +
            "inner join movement m on m.number_account = a.number_account " +
            "where m.movement_at::date between :startDate and :endDate" +
            "  order by m.movement_at desc",
            nativeQuery = true)
    List<ReportDTO> findMovementsByDate(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

}
