package edu.tcc.v1.mensagemagendamento;

import edu.tcc.v1.consulta.Consulta;
import edu.tcc.v1.consulta.ConsultaServicoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MensagemAgendamentoImpl implements MensagemAgendamento {

    @Autowired
    private ConsultaServicoImpl consultaServico;

    @Override
    public String personalizarMensagemDeAgendamento(LocalDateTime dataAgendamento) {
        Consulta consulta = consultaServico.exibirConsultaPelaDataDeAgendamento(dataAgendamento);
        String cliente = consulta.getCliente().toString();
        String medico = consulta.getMedico().toString();
        int horas = consulta.getDataAgendamento().getHour();
        int minutos = consulta.getDataAgendamento().getMinute();
        int segundos = consulta.getDataAgendamento().getSecond();
        String horario = String.format("%d:%d:%d", horas, minutos, segundos);
        int dia = consulta.getDataAgendamento().getDayOfMonth();
        int mes = consulta.getDataAgendamento().getMonthValue();
        int ano = consulta.getDataAgendamento().getYear();
        String data = String.format("%d/%d/%d", dia, mes, ano, mes);
        return String.format("""
            Sr(a). %s, informamos que há uma consulta agendada às %s no dia %s com o Dr. %s.
        """, cliente, horario, data, medico);
    }

}
