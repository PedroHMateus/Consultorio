/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Mapeamento.Consulta;
import Mapeamento.Funcionario;
import Mapeamento.Medico;
import Mapeamento.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilitario.Conectar;

public class ConsultaDAO {

    public void cadastrar(Consulta c) {
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO consulta(dataAtendimento,horario,id_paciente,id_medico,id_funcionario)VALUES(?,?,?,?,?)";
        try ( PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, c.getData());
            smt.setString(2, c.getHoras());
            smt.setInt(3, c.getPaciente().getId_paciente());
            smt.setInt(4, c.getMedico().getId_medico());
            smt.setInt(5, c.getFuncionario().getId_funcionario());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            smt.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar" + ex.getMessage());
        }}
        public void atualizar(Consulta c){
            Connection con = Conectar.getConectar();
            String sql = "UPDATE consulta SET dataAtendimento = ?, horario = ? , id_paciente = ?, id_medico = ?, id_funcionario = ? WHERE id_consulta = ?";
             try ( PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, c.getData());
            smt.setString(2, c.getHoras());
            smt.setInt(3, c.getPaciente().getId_paciente());
            smt.setInt(4, c.getMedico().getId_medico());
            smt.setInt(5, c.getFuncionario().getId_funcionario());
            smt.setInt(6,c.getId_consulta());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            smt.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + ex.getMessage());
        }         
        }
        public void excluir(Consulta c){
            Connection con = Conectar.getConectar();
            String sql = "DELETE FROM consulta WHERE id_consulta = ?";
            int opcao=JOptionPane.showConfirmDialog(null, "Deseja excluir a consulta ?", "Exclusao",JOptionPane.YES_NO_OPTION);
            if(opcao == JOptionPane.YES_OPTION){
                try(PreparedStatement smt = con.prepareStatement(sql)){
                    smt.setInt(1, c.getId_consulta());
                    smt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                    smt.close();
                    con.close();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro ao excluir a consulta"+ex);
                }
            }else{
                
            }
        }
        
        
    public List<Consulta> listarTodas(){
        Connection con = Conectar.getConectar();
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta INNER JOIN paciente ON paciente.id_paciente = consulta.id_paciente INNER JOIN medico ON medico.id_medico = consulta.id_medico INNER JOIN funcionario ON funcionario.id_funcionario = consulta.id_funcionario";
        
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet resultado = smt.executeQuery();
            while(resultado.next()){
                Consulta c = new Consulta();
                c.setId_consulta(resultado.getInt("consulta.id_consulta"));
                c.setData(resultado.getString("consulta.dataAtendimento"));
                c.setHoras(resultado.getString("consulta.horario"));
               Funcionario f = new Funcionario();
               f.setId_funcionario(resultado.getInt("funcionario.id_funcionario"));
               f.setNome(resultado.getString("funcionario.nome"));
               
               c.setFuncionario(f);
               
               Medico m = new Medico();
               m.setId_medico(resultado.getInt("medico.id_medico"));
               m.setNome(resultado.getString("medico.nome"));
               c.setMedico(m);
               
               Paciente p = new Paciente ();
               p.setId_paciente(resultado.getInt("paciente.id_paciente"));
               p.setNome(resultado.getString("paciente.nome"));
               c.setPaciente(p);
               
               lista.add(c);
               
               
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao lidar as consultas !");
        }
        return lista;
    
}

    public List<Consulta> bucaData(String datapesquisa){
        Connection con = Conectar.getConectar();
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta INNER JOIN paciente ON paciente.id_paciente = consulta.id_paciente INNER JOIN medico ON medico.id_medico = consulta.id_medico INNER JOIN funcionario ON funcionario.id_funcionario = consulta.id_funcionario WHERE consulta.dataAtendimento = ?";
        
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, datapesquisa);
            ResultSet resultado = smt.executeQuery();
            while(resultado.next()){
                Consulta c = new Consulta();
                c.setId_consulta(resultado.getInt("consulta.id_consulta"));
                c.setData(resultado.getString("consulta.dataAtendimento"));
                c.setHoras(resultado.getString("consulta.horario"));
               Funcionario f = new Funcionario();
               f.setId_funcionario(resultado.getInt("funcionario.id_funcionario"));
               f.setNome(resultado.getString("funcionario.nome"));
               
               c.setFuncionario(f);
               
               Medico m = new Medico();
               m.setId_medico(resultado.getInt("medico.id_medico"));
               m.setNome(resultado.getString("medico.nome"));
               c.setMedico(m);
               
               Paciente p = new Paciente ();
               p.setId_paciente(resultado.getInt("paciente.id_paciente"));
               p.setNome(resultado.getString("paciente.nome"));
               c.setPaciente(p);
               
               lista.add(c);
               
               
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao lidar as consultas !");
        }
        return lista;
    
}

}
