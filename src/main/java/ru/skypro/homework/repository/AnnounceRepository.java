package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Announce;

import java.util.List;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {
    List<Announce> findAllByPk(Long pk);
}