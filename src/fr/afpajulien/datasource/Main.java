package fr.afpajulien.datasource;

import javax.sql.DataSource;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        testDataSource();
    }

    private static void testDataSource() {
        DataSource ds;
        ds = DataSourceFactory.getMySQLDatSource();
        ResultSet rs;
        String query = "select * from film where length > ? limit ?";

        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            // Assignation des paramètres '?' dans la query
            ps.setInt(1, 170);
            ps.setInt(2, 15);

            // Exécution de la requête
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Titre : " + rs.getString("title")
                        + " | Duree : " + rs.getInt("length") + " min.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
