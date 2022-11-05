
package dao;

import Mapeamento.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilitario.Conectar;


public class FuncionarioDAO {
    public void cadastrar(Funcionario r){
        Connection con = Conectar.getConectar();
        String sql = "INTERT INTO funcionario(nome,cpf,email,telefone,dataadmissao,senha) VALUES(?,?,?,?,?,?)";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, r.getNome());
            smt.setString(2, r.getCpf());
            smt.setString(3, r.getEmail());
            smt.setString(4, r.getTelefone());
            smt.setString(5, r.getDataadmissao());
            smt.setString(6, r.getSenha());
            smt.executeUpdate();
            smt.close();
            con.close();
           JOptionPane.showMessageDialog(null,"Cadastrado com sucesso.");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar o funcionário");
        }
    }
    
    public void atualizar(Funcionario r){
        Connection con = Conectar.getConectar();
        String sql = "UPDATE funcionario SET nome = ?, cpf =?,email=?,telefone=?,dataadmissao=? WHERE id_funcionario= ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, r.getNome());
            smt.setString(2, r.getCpf());
            smt.setString(3, r.getEmail());
            smt.setString(4, r.getTelefone());
            smt.setString(5, r.getDataadmissao());
            smt.setInt(6,r.getId_funcionario());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao atualizar o funcionário");
        }
        
    }
    
    public void excluir(Funcionario r){
        Connection con = Conectar.getConectar();
        String sql = "DELETE FROM funcionaro WHERE id_funcionario =?";
        int opcao = JOptionPane.showConfirmDialog(null, "deseja excluir o funcionario?"+r.getNome(),"Exclusao",JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt=con.prepareStatement(sql)){
            smt.setInt(1,r.getId_funcionario());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "erro ao excluir o funcionario");
        }
        }
        
        
}

    public List<Funcionario> listarTodos(){
        Connection con = Conectar.getConectar();
        List<Funcionario> listaFuncionario = new ArrayList<>();
        String sql = "SELECT * FROM funcionario ORDER BY nome ";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet resultado = smt.executeQuery();
            while(resultado.next()){
                Funcionario f = new Funcionario();
                f.setId_funcionario(resultado.getInt("id_funcionario"));
                f.setNome(resultado.getString("nome"));
                f.setCpf(resultado.getString("cpf"));
                f.setEmail(resultado.getString("email"));
                f.setTelefone(resultado.getString("telefone"));
                f.setDataadmissao(resultado.getString("dataadmissao"));
                listaFuncionario.add(f);
            }
           smt.close();
           con.close();
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar os funcionarios");
        }
        return listaFuncionario;
    }

    public Funcionario login(String text, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}