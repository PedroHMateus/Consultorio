/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Mapeamento.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilitario.Conectar;

/**
 *
 * @author PC
 */
public class MedicoDAO {

    public void cadastrar(Medico m) {
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO medico(nome,email,crm,telefone,especializacao) VALUES (?,?,?,?,?)";
        try ( PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, m.getNome());
            smt.setString(2, m.getEmail());
            smt.setString(3, m.getCrm());
            smt.setString(4, m.getTelefone());
            smt.setString(5, m.getEspecializacao());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "cadastrado com sucesso");
             smt.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "erro ao cadastro medico");

        }
    }

    public void atualizar(Medico m) {
        Connection con = Conectar.getConectar();
        String sql = "UPDATE medico SET nome = ?, email = ?, crm =?, telefone=?,especializacao = ? WHERE id_medico = ?";
        try ( PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, m.getNome());
            smt.setString(2, m.getEmail());
            smt.setString(3, m.getCrm());
            smt.setString(4, m.getTelefone());
            smt.setString(5, m.getEspecializacao());
            smt.setInt(6, m.getId_medico());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            smt.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "erro ao atualizar medico");

        }
    }
    public void excluir (Medico m){
        Connection con = Conectar.getConectar();
        String sql = "DELETE FROM medico WHERE id_medico=?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o médico"+m.getNome(),"Excluir meédico", JOptionPane.YES_NO_OPTION);
        if(opcao==JOptionPane.YES_OPTION){
            try (PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1, m.getId_medico());
                smt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso !");
                smt.close();
                con.close();
            }catch (Exception ex){
               JOptionPane.showMessageDialog(null, "Erro ao excluir o médico");
            }
        }
    }
    public List<Medico> listarTodos(){
       Connection con = Conectar.getConectar();
       List<Medico> ListaMedico = new ArrayList<>();
       String sql = "SELECT * FROM medico ORDER BY nome";
       try(PreparedStatement smt= con.prepareStatement(sql)){
           ResultSet resultado = smt.executeQuery();
           while (resultado.next()){
               Medico m = new Medico();
               m.setId_medico(resultado.getInt("id_medico"));
               m.setNome(resultado.getString("nome"));
               m.setEmail(resultado.getString("email"));
               m.setCrm(resultado.getString("crm"));
               m.setTelefone(resultado.getString("telefone"));
               m.setEspecializacao(resultado.getString("especializacao"));
               ListaMedico.add(m);
                       
           }
           smt.close();
           con.close();
       }catch (Exception ex){
          JOptionPane.showMessageDialog(null,"erro ao buscar os dados"); 
                  
       }
       return ListaMedico;
       
    }

}
