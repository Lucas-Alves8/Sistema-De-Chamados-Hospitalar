package controller;

import controller.dto.CreateSupportTiDto;
import controller.dto.UpdateSupportTi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import models.SupportTi;
import org.springframework.web.bind.annotation.*;
import service.SupportTiService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SupportTiController {

    private final SupportTiService supportTiService;

    @PostMapping("/supportti")
    public SupportTi createSupportTi (@RequestBody @Valid CreateSupportTiDto createSupportTiDto) {
    return supportTiService.createSupportTi(
            createSupportTiDto.name(),
            createSupportTiDto.cpf(),
            createSupportTiDto.rg(),
            createSupportTiDto.age()
    );
    }
    @PutMapping("/supportti/update")
    public SupportTi updateSupportTi (@RequestBody @Valid UpdateSupportTi updateSupportTi) {
        return supportTiService.updateSupportTi(
                updateSupportTi.id(),
                updateSupportTi.name(),
                updateSupportTi.cpf(),
                updateSupportTi.rg(),
                updateSupportTi.age()
        );
    }

    @GetMapping("/supportti")
    public List<SupportTi> listAllSupportTi() {
        return supportTiService.listAllSupportTi();
    }

    @GetMapping("/supportti/{id}")
    public SupportTi listSupportTiById(@PathVariable UUID id) {
        return supportTiService.listSupportTiById(id);
    }

    @DeleteMapping("/supportti/{id}")
    public SupportTi deleteSupportTi(@PathVariable UUID id) {
        return supportTiService.deleteSupportTi(id);
    }
}
