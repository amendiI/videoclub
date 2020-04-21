package jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.context.Context;
import jdbc.model.Formation;

class DaoFormationJdbcImpl implements DaoFormation {

	@Override
	public void insert(Formation obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into formation(id,nom,date_formation) values(nextval('seq_formation'),?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getNom());
			if (obj.getDateFormation() != null) {
				ps.setDate(2, new Date(obj.getDateFormation().getTime()));
			} else {
				ps.setNull(2, Types.DATE);
			}

			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
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
	public Formation update(Formation obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update Formation set nom=?, date_formation=? where id=?")) {
			ps.setString(1, obj.getNom());
			if (obj.getDateFormation() != null) {
				ps.setDate(2, new Date(obj.getDateFormation().getTime()));
			} else {
				ps.setNull(2, Types.DATE);
			}
			ps.setInt(3, obj.getId());
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
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
	public void delete(Formation obj) {
		deleteById(obj.getId());
	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from formation where id=?")) {
			ps.setInt(1, key);
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
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
	public Optional<Formation> findByKey(Integer key) {
		Formation formation = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select * from formation where id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				formation = new Formation(rs.getInt("id"), rs.getString("nom"));
				formation.setDateFormation(rs.getDate("date_formation"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(formation);
	}

	@Override
	public List<Formation> findAll() {
		List<Formation> formations = new ArrayList<Formation>();
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from formation");
			while (rs.next()) {
				formations.add(new Formation(rs.getInt("id"), rs.getString("nom"), rs.getDate("date_formation")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Context.destroy();
		return formations;
	}

}
