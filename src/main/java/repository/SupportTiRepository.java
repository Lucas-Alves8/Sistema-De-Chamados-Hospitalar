package repository;

import models.SupportTi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupportTiRepository extends JpaRepository <SupportTi, UUID> {

    boolean existsByCpf(String cpf);
}
