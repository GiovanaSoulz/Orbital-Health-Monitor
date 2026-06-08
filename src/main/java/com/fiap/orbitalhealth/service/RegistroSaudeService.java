
package com.fiap.orbitalhealth.service;

import com.fiap.orbitalhealth.entity.Alerta;
import com.fiap.orbitalhealth.entity.AlertaCritico;
import com.fiap.orbitalhealth.entity.AlertaModerado;
import com.fiap.orbitalhealth.repository.AlertaRepository;
import com.fiap.orbitalhealth.dto.RegistroSaudeRequestDTO;
import com.fiap.orbitalhealth.dto.RegistroSaudeResponseDTO;
import com.fiap.orbitalhealth.entity.PessoaMonitorada;
import com.fiap.orbitalhealth.entity.RegistroSaude;
import com.fiap.orbitalhealth.enums.RiscoSaude;
import com.fiap.orbitalhealth.exception.ResourceNotFoundException;
import com.fiap.orbitalhealth.repository.PessoaMonitoradaRepository;
import com.fiap.orbitalhealth.repository.RegistroSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroSaudeService {

    private final RegistroSaudeRepository repository;
    private final AlertaRepository alertaRepository;
    private final PessoaMonitoradaRepository pessoaRepository;

    public RegistroSaudeResponseDTO cadastrar(
            RegistroSaudeRequestDTO dto) {

        PessoaMonitorada pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pessoa não encontrada"));

        RegistroSaude registro = RegistroSaude.builder()
                .temperatura(dto.temperatura())
                .frequenciaCardiaca(dto.frequenciaCardiaca())
                .pressaoArterial(dto.pressaoArterial())
                .oxigenacao(dto.oxigenacao())
                .nivelDor(dto.nivelDor())
                .hidratacao(dto.hidratacao())
                .horasSono(dto.horasSono())
                .sintomas(dto.sintomas())
                .dataRegistro(LocalDateTime.now())
                .pessoaMonitorada(pessoa)
                .build();

        repository.save(registro);
        RiscoSaude risco = calcularRisco(registro);

        if (risco == RiscoSaude.CRITICO) {

            AlertaCritico alerta = new AlertaCritico();

            alerta.setMensagem(
                    "Paciente apresenta risco CRÍTICO");

            alerta.setAcaoEmergencial(
                    "Encaminhar imediatamente para atendimento");

            alertaRepository.save(alerta);
        }

        if (risco == RiscoSaude.ALTO) {

            AlertaModerado alerta = new AlertaModerado();

            alerta.setMensagem(
                    "Paciente apresenta risco ALTO");

            alerta.setRecomendacao(
                    "Monitorar sinais vitais constantemente");

            alertaRepository.save(alerta);
        }
        return converter(registro);
    }

    public List<RegistroSaudeResponseDTO> listarTodos() {

        return repository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public RegistroSaudeResponseDTO buscarPorId(
            Long id) {

        RegistroSaude registro = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Registro não encontrado"));

        return converter(registro);
    }

    public RegistroSaudeResponseDTO atualizar(
            Long id,
            RegistroSaudeRequestDTO dto) {

        RegistroSaude registro = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Registro não encontrado"));

        PessoaMonitorada pessoa =
                pessoaRepository.findById(dto.pessoaId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pessoa não encontrada"));

        registro.setTemperatura(dto.temperatura());
        registro.setFrequenciaCardiaca(dto.frequenciaCardiaca());
        registro.setPressaoArterial(dto.pressaoArterial());
        registro.setOxigenacao(dto.oxigenacao());
        registro.setNivelDor(dto.nivelDor());
        registro.setHidratacao(dto.hidratacao());
        registro.setHorasSono(dto.horasSono());
        registro.setSintomas(dto.sintomas());
        registro.setPessoaMonitorada(pessoa);

        repository.save(registro);

        return converter(registro);
    }

    public void deletar(
            Long id) {

        RegistroSaude registro = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Registro não encontrado"));

        repository.delete(registro);
    }

    private RiscoSaude calcularRisco(
            RegistroSaude registro) {

        if (registro.getOxigenacao() != null
                && registro.getOxigenacao() < 90) {

            return RiscoSaude.CRITICO;
        }

        if (registro.getTemperatura() != null
                && registro.getTemperatura() >= 39) {

            return RiscoSaude.ALTO;
        }

        if (registro.getFrequenciaCardiaca() != null
                && registro.getFrequenciaCardiaca() > 120) {

            return RiscoSaude.ALTO;
        }

        if (registro.getNivelDor() != null
                && registro.getNivelDor() >= 8) {

            return RiscoSaude.ALTO;
        }

        if (registro.getTemperatura() != null
                && registro.getTemperatura() >= 37.5) {

            return RiscoSaude.MODERADO;
        }

        return RiscoSaude.BAIXO;
    }

    private RegistroSaudeResponseDTO converter(
            RegistroSaude registro) {

        return new RegistroSaudeResponseDTO(
                registro.getId(),
                registro.getTemperatura(),
                registro.getFrequenciaCardiaca(),
                registro.getPressaoArterial(),
                registro.getOxigenacao(),
                registro.getNivelDor(),
                registro.getHidratacao(),
                registro.getHorasSono(),
                registro.getSintomas(),
                registro.getDataRegistro(),
                registro.getPessoaMonitorada().getId(),
                calcularRisco(registro)
        );
    }
}