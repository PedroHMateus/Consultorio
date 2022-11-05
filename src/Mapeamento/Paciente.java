
package Mapeamento;

public class Paciente {
    private int id_paciente;
    private String nome, email, cpf, genero, telefone, datanasc;

    public int getId_paciente() {
        return id_paciente;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }
    
}
