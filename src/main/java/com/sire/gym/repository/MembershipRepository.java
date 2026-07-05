package com.sire.gym.repository;

import com.sire.gym.model.Membership;
import com.sire.gym.model.MembershipStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    @Query(value = "SELECT * FROM Membership m WHERE m.member_id = :memberId", nativeQuery = true)
    Optional<Membership> findByMemberId(Long memberId);

    @Query(
            value = "SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Membership m " +
                    "WHERE m.member_id = :memberId AND m.status = 'ACTIVE'",
            nativeQuery = true)
    boolean activeMembershipExists(Long memberId);

    Page<Membership> findByStatus(MembershipStatus status, Pageable pageable);

}
