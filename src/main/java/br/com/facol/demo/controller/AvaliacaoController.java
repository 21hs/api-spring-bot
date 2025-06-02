package br.com.facol.demo.controller;

import br.com.facol.demo.dto.AvaliacaoRequestDTO;
import br.com.facol.demo.dto.AvaliacaoResponseDTO;
import br.com.facol.demo.entity.AvaliacaoEntity;
import br.com.facol.demo.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/avaliacoes")
@Tag(name = "Avaliações", description = "Operações relacionadas às avaliações")
public class AvaliacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AvaliacaoController.class);
    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar nova avaliação")
    public ResponseEntity<AvaliacaoResponseDTO> criar(@RequestBody AvaliacaoRequestDTO dto,
                                                      UriComponentsBuilder uriBuilder) {
        logger.info("Recebendo avaliação: {}", dto);
        AvaliacaoEntity salvo = service.salvar(dto);
        AvaliacaoResponseDTO response = toResponseDTO(salvo);
        URI uri = uriBuilder.path("/avaliacoes/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar avaliações")
    public ResponseEntity<Page<AvaliacaoResponseDTO>> listar(@ParameterObject Pageable pageable) {
        Page<AvaliacaoResponseDTO> page = service.listarTodas(pageable).map(this::toResponseDTO);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar avaliação por ID")
    public ResponseEntity<AvaliacaoResponseDTO> buscar(@PathVariable Long id) {
        AvaliacaoEntity entity = service.buscarPorId(id);
        return ResponseEntity.ok(toResponseDTO(entity));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar avaliação")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable Long id,
                                                          @RequestBody AvaliacaoRequestDTO dto) {
        AvaliacaoEntity atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(toResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar avaliação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    private AvaliacaoResponseDTO toResponseDTO(AvaliacaoEntity entity) {
        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setId(entity.getId());
        dto.setUsuario(entity.getUsuario());
        dto.setAcademia(entity.getAcademia());
        dto.setNota(entity.getNota());
        dto.setComentario(entity.getComentario());
        return dto;
    }
}
