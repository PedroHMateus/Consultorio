
package utilitario;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;



    
public class Conectar {
    private static final String usuario="root";
    private static final String senha="";
    private static final String url="jdbc:mysql://localhost:3306/consultorio";
    
    
    public static Connection getConectar(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,senha);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao conectar"+ex.getMessage());
        }
        return con;
    }

}


