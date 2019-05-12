package model.sqlite;

import helpers.Conversions;
import model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectSQLite extends SQLiteBase {

    public ProjectSQLite(){

        open();

        try{
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Projects (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "client TEXT," +
                    "banner INTEGER," +
                    "site INTEGER," +
                    "seo INTEGER," +
                    "grap INTEGER, " +
                    "title TEXT," +
                    "porder INTEGER," +
                    "delivery INTEGER," +
                    "boss TEXT, " +
                    "responsible TEXT," +
                    "description TEXT);");

            stm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

    }

    public void create(Project p){
        open();

        try{
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Projects VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");

            stm.setString(2, p.getClient());
            stm.setInt(3, Conversions.booleanToInteger(p.isBanner()));
            stm.setInt(4, Conversions.booleanToInteger(p.isSite()));
            stm.setInt(5, Conversions.booleanToInteger(p.isSeo()));
            stm.setInt(6, Conversions.booleanToInteger(p.isGrap()));
            stm.setString(7, p.getTitle());
            stm.setLong(8, p.getOrder().getTime() );
            stm.setLong(9, p.getDelivery().getTime() );
            stm.setString(10, p.getBoss());
            stm.setString(11, p.getResponsible());
            stm.setString(12, p.getDescription());

            stm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public List<Project> all(){
        ArrayList<Project> result = new ArrayList<>();

        open();

        try{
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Projects;");

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                Project p = new Project(
                        rs.getInt(1),
                        rs.getString(2),
                        Conversions.integerToBoolean(rs.getInt(3)),
                        Conversions.integerToBoolean(rs.getInt(4)),
                        Conversions.integerToBoolean(rs.getInt(5)),
                        Conversions.integerToBoolean(rs.getInt(6)),
                        rs.getString(7),
                        new java.util.Date(rs.getLong(8)),
                        new java.util.Date(rs.getLong(9)),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));

                result.add(p);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

        return result;
    }

    public void update(Project p){
        conn = open();

        try{
            PreparedStatement stm = conn.prepareStatement("UPDATE Projects SET "+
                    "client = ?," +
                    "banner = ?," +
                    "site = ?," +
                    "seo = ?," +
                    "grap = ?, " +
                    "title = ?," +
                    "porder = ?," +
                    "delivery = ?," +
                    "boss = ?, " +
                    "responsible = ?," +
                    "description = ? " +
                    "WHERE id = ?;");

            stm.setString(1, p.getClient());
            stm.setInt(2, Conversions.booleanToInteger(p.isBanner()));
            stm.setInt(3, Conversions.booleanToInteger(p.isSite()));
            stm.setInt(4, Conversions.booleanToInteger(p.isSeo()));
            stm.setInt(5, Conversions.booleanToInteger(p.isGrap()));
            stm.setString(6, p.getTitle());
            stm.setLong(7, p.getOrder().getTime() );
            stm.setLong(8, p.getDelivery().getTime() );
            stm.setString(9,p.getBoss());
            stm.setString(10, p.getResponsible());
            stm.setString(11, p.getDescription());
            stm.setInt(12, p.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void delete(Project p){
        conn = open();

        try{
            PreparedStatement stm = conn.prepareStatement("DELETE FROM Projects WHERE id = ?;");

            stm.setInt(1, p.getId());

            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public Project find(int pk){
        Project result = null;
        conn = open();

        try{
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Projects WHERE id = ?;");

            stm.setInt(1,pk);

            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                Project p = new Project(
                        rs.getInt(1),
                        rs.getString(2),
                        Conversions.integerToBoolean(rs.getInt(3)),
                        Conversions.integerToBoolean(rs.getInt(4)),
                        Conversions.integerToBoolean(rs.getInt(5)),
                        Conversions.integerToBoolean(rs.getInt(6)),
                        rs.getString(7),
                        new java.util.Date(rs.getLong(8)),
                        new java.util.Date(rs.getLong(9)),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));

                result = p;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close();

            return result;
        }
    }
}
