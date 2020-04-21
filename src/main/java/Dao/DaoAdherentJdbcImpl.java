package Dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import context.Context;
import model.Adherent;
import model.Adresse;
import model.Civilite;

public class DaoAdherentJdbcImpl implements DaoAdherent{

	@Override
	public void insert(Adherent obj) {

		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into adherent(id,prenom,nom,numero,rue,codePostal,ville,civilite) "
						+ "values(nextval('seq_formation'),?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			ps.setInt(3, obj.getAdresse().getNumero());
			ps.setString(4, obj.getAdresse().getRue());
			ps.setString(5, obj.getAdresse().getCodePostal());
			ps.setString(6, obj.getAdresse().getVille());
			ps.setString(7, obj.getCivilite().toString());
	


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
	public Adherent update(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(

				"update adherent set prenom=?, nom=?, numero=?, rue=?, codePostal=?,ville=?,civilite=? where id=?",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			ps.setInt(3, obj.getAdresse().getNumero());
			ps.setString(4, obj.getAdresse().getRue());
			ps.setString(5, obj.getAdresse().getCodePostal());
			ps.setString(6, obj.getAdresse().getVille());
			ps.setString(7, obj.getCivilite().toString());
			ps.setInt(8, obj.getId());


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
	public void delete(Adherent obj) {
		deleteById(obj.getId());

	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from adherent where id=?")) {
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
	public Optional<Adherent> findByKey(Integer key) {
		Adherent adherent = null;
		Adresse adresse=null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select * from adherent where id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				//ps.setString(7, obj.getCivilite().toString());
				
				adresse = new Adresse (rs.getInt("numero"),rs.getString("rue"), rs.getString("codepostal"), rs.getString("ville"));
				adherent = new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"),Civilite.valueOf(rs.getString("civilite")),adresse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(adherent);
	}

	@Override
	public List<Adherent> findAll() {
		List<Adherent> adherents = new ArrayList<Adherent>();
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from adherent");
			while (rs.next()) {
				Adresse adresse = new Adresse (rs.getInt("numero"),rs.getString("rue"), rs.getString("codepostal"), rs.getString("ville"));
				adherents.add(new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"),Civilite.valueOf(rs.getString("civilite")),adresse));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Context.destroy();
		return adherents;
	}



}
