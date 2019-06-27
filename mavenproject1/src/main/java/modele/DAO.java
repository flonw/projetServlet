package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO 
{

	protected final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) 
        {
		this.myDataSource = dataSource;
	}

        /**
         * 
         * @return
         * @throws DAOException 
         */
        public List<DiscountEntity> ListeDesDiscount() throws DAOException 
        {
		List<DiscountEntity> result = new LinkedList<>(); // Liste vIde

                // La requete sql qui permet de récupérer toutes les réductions
		String requeteSQL = "SELECT * FROM DISCOUNT_CODE";
                
                
		try (
                        // obtenir la ocnnection à la BDD
                        Connection connection = myDataSource.getConnection();
                        
                        // préparation de la requete
			PreparedStatement stmt = connection.prepareStatement(requeteSQL)) {

                        // execution et traitement du retour
			try (
                                ResultSet rs = stmt.executeQuery()
                            ) 
                        {
                            
                                // pour chauqe résultat
				while (rs.next()) 
                                {
					// Récuoréer le code de la réduction
					char discount_code = rs.getString("DISCOUNT_CODE").charAt(0);
                                        
                                        // Récupérer la valeu de la réduction
					double discount_rate = rs.getDouble("RATE");
                                        
					// On créer l'objet réduction
					DiscountEntity c = new DiscountEntity(discount_code,discount_rate);
                                        
					// on l'enregistre dans la liste
					result.add(c);
				}
		}
		}  
                catch (SQLException ex) 
                {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
		return result;
	}
        
        /**
         * Permet d'ajouter un discount danl a BDD
         * @param discountCode, le code de la réduction sur un charactère
         * @param taux, le taux associé
         */
        public void addDiscount(String discountCode, double taux)
        {
            String sql = "INSERT INTO DISCOUNT_CODE (discount_code, rate) values (?,?)";
            
            try 
            (   
                Connection connection = myDataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql)
            ) 
            {
                // Définir la valeur du paramètre
                stmt.setString(1,discountCode);
                stmt.setDouble(2,taux);
                stmt.executeUpdate();
            }  
            catch (SQLException ex) 
            {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            }
        }
        
        public void updateDiscount(String discountCode, double taux)
        {
            String sql = "UPDATE DISCOUNT_CODE set rate = ? WHERE discount_code = ?";
            
            try 
            (   
                Connection connection = myDataSource.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql)
            ) 
            {
                // Définir la valeur du paramètre
                stmt.setDouble(1,taux);
                stmt.setString(2,discountCode);
                stmt.executeUpdate();
            }  
            catch (SQLException ex) 
            {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            }
        }
        
        public void deleteDiscount(String discountCode)
        {
            String sql = "DELETE FROM DISCOUNT_CODE WHERE discount_code = ?";
            
            try 
            (   
                Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
            ) 
            {
                        // Définir la valeur du paramètre
			stmt.setString(1,discountCode);
			stmt.executeUpdate();

            }  
            catch (SQLException ex) 
            {
		Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            }
        }
}
