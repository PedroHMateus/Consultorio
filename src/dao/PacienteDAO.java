package dao;

import Mapeamento.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;




import javax.swing.JOptionPane;
import utilitario.Conectar;

public class PacienteDAO {

    public void cadastrar(Paciente p) {
        Connection con = (Connection) Conectar.getConectar();
        String sql = "INSERT INTO paciente (nome,cpf,email,datanasc,telefone,genero) VALUES(?,?,?,?,?,?)";
        try ( PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setString(4, p.getDatanasc());
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getGenero());
            smt.executeUpdate();
            smt.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " erro ao cadastrar:");

        }
    }

    public void atualizar(Paciente p) {
        Connection con = (Connection) Conectar.getConectar();
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, email = ?, datanasc = ?, telefone = ?, genero=? WHERE id_paciente = ?";
        try ( PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setString(4, p.getDatanasc());
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getGenero());
            smt.setInt(7, p.getId_paciente());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "erro ao atualizar o registro !");
        }

    }

    public void excluir(Paciente p) {
        Connection con = (Connection) Conectar.getConectar();
        String sql = "DELETAR FROM paciente WHERE id_paciente = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar"
                + p.getNome() + "?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try ( PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, p.getId_paciente());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "erro ao excluir paciente");
            }
        }}
      
    
   
      public List<Paciente> listarTodos(){
         Connection con = (Connection) Conectar.getConectar();
            List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER BY nome";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet resultado = smt.executeQuery();
                    while(resultado.next()){
                        Paciente p = new Paciente();
                        p.setId_paciente(resultado.getInt("id paciente"));
                        p.setNome(resultado.getString("nome"));
                        p.setCpf(resultado.getString("cpf"));
                        p.setEmail(resultado.getString("Email"));
                        p.setDatanasc(resultado.getString("datanasc"));
                        p.setTelefone(resultado.getString("telefone"));
                        p.setGenero(resultado.getString("genero"));
                        lista.add(p);   
                    }
                    smt.close();
                    con.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"erro ao buscar os registros");
        }
        return lista;   
        }
       
        }

 
