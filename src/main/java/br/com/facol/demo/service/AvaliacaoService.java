package br.com.facol.demo.service;

import br.com.facol.demo.dto.AvaliacaoRequestDTO;
import br.com.facol.demo.entity.AvaliacaoEntity;
import br.com.facol.demo.exception.NotFoundException;
import br.com.facol.demo.repository.AvaliacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }


    public Page<AvaliacaoEntity> listarTodas(Pageable pageable) {
        return repository.findAll(pageable); // não lança mais NotFoundException
    }

    public AvaliacaoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Avaliação com id " + id + " não encontrada"));
    }


    public AvaliacaoEntity salvar(AvaliacaoRequestDTO dto) {
        AvaliacaoEntity entity = toEntity(dto);
        return repository.save(entity);
    }


    public AvaliacaoEntity atualizar(Long id, AvaliacaoRequestDTO dto) {
        AvaliacaoEntity existente = buscarPorId(id);
        existente.setUsuario(dto.getUsuario());
        existente.setAcademia(dto.getAcademia());
        existente.setNota(dto.getNota());
        existente.setComentario(dto.getComentario());
        return repository.save(existente);
    }


    public void deletarPorId(Long id) {
        AvaliacaoEntity entity = buscarPorId(id);
        repository.delete(entity);
    }


    private AvaliacaoEntity toEntity(AvaliacaoRequestDTO dto) {
        AvaliacaoEntity entity = new AvaliacaoEntity();
        entity.setUsuario(dto.getUsuario());
        entity.setAcademia(dto.getAcademia());
        entity.setNota(dto.getNota());
        entity.setComentario(dto.getComentario());
        return entity;
    }
}
