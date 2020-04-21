package Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import context.Context;
import model.Film;

public class DaoFilmJdbcImpl implements DaoFilm {

	@Override
	public void insert(Film obj) {
		try(PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into film(id,titre,dateDeSortie)"
				+ "values(nextval('seq_film'),?,?)",PreparedStatement.RETURN_GENERATED_KEYS)){
			ps.setString(1, obj.getTitre());
			if(obj.getDateDeSortie()!=null) {
				ps.setDate(2, new Date(obj.getDateDeSortie().getTime()));
			}else {
				ps.setNull(2, Types.DATE);
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				obj.setId(rs.getInt(1));
			}
			Context.getInstance().getConnection().commit();
			
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		Context.destroy();
		
	}

	@Override
	public Film update(Film obj) {
		try(PreparedStatement ps=Context.getInstance().getConnection().prepareStatement(
				"update film set titre=?, dateDeSortie=? where id=?")){
			
			ps.setString(1, obj.getTitre());
			ps.setDate(2, new Date(obj.getDateDeSortie().getTime()));
			ps.setInt(3,  obj.getId());
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();	
			
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		Context.destroy();
		return obj;
		
	}

	@Override
	public void delete(Film obj) {
		deleteById(obj.getId());
		
	}

	@Override
	public void deleteById(Integer key) {
		try(PreparedStatement ps=Context.getInstance().getConnection()
				.prepareStatement("delete from film where id=?")){
			ps.setInt(1,  key);
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}

		}
		Context.destroy();
		
	}

	@Override
	public Optional<Film> findByKey(Integer key) {
		Film f=null;
		try(PreparedStatement ps=Context.getInstance().getConnection()
				.prepareStatement("select * from film where id=?")){
			ps.setInt(1,  key);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				f = new Film(
					rs.getInt("id"), rs.getString("titre"), rs.getDate("dateDeSortie"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		
		return Optional.ofNullable(f);
		
	}

	@Override
	public List<Film> findAll() {
		List<Film> films= new ArrayList<Film>();
		Film f = null;
		try(Statement st=Context.getInstance().getConnection().createStatement()){
			ResultSet rs = st.executeQuery("select * from film");
			while(rs.next()) {
				
				f = new Film(rs.getInt("id"), rs.getString("titre"), rs.getDate("dateDeSortie"));
				films.add(f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return films;
		
	}

}
