package jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.context.Context;
import jdbc.model.Civilite;
import jdbc.model.Formateur;
import jdbc.model.Formation;
import jdbc.model.Personne;
import jdbc.model.Stagiaire;

class DaoPersonneJdbcImpl implements DaoPersonne {

	@Override
	public void insert(Personne obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into personne(id,prenom,nom,civilite,type,experience,id_formation)values(nextval('seq_personne'),?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			if (obj.getCivilite() != null) {
				ps.setString(3, obj.getCivilite().toString());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}
			if (obj instanceof Formateur) {
				ps.setString(4, "f");
				if (((Formateur) obj).getExperience() != null) {
					ps.setInt(5, ((Formateur) obj).getExperience());
				} else {
					ps.setNull(5, Types.INTEGER);
				}
				ps.setNull(6, Types.INTEGER);
			} else if (obj instanceof Stagiaire) {
				ps.setString(4, "s");
				ps.setNull(5, Types.INTEGER);
				if (((Stagiaire) obj).getFormation() != null) {
					ps.setInt(6, ((Stagiaire) obj).getFormation().getId());
				} else {
					ps.setNull(6, Types.INTEGER);
				}
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
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
	public Personne update(Personne obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"update personne set prenom=?,nom=?,civilite=?,experience=?,id_formation=? where id=?")) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			if (obj.getCivilite() != null) {
				ps.setString(3, obj.getCivilite().toString());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}

			if (obj instanceof Formateur) {
				if (((Formateur) obj).getExperience() != null) {
					ps.setInt(4, ((Formateur) obj).getExperience());
				} else {
					ps.setNull(4, Types.INTEGER);
				}
				ps.setNull(5, Types.INTEGER);
			} else if (obj instanceof Stagiaire) {
				ps.setNull(4, Types.INTEGER);
				if (((Stagiaire) obj).getFormation() != null) {
					ps.setInt(5, ((Stagiaire) obj).getFormation().getId());
				} else {
					ps.setNull(5, Types.INTEGER);
				}
			}
			ps.setInt(6, obj.getId());
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
	public void delete(Personne obj) {
		deleteById(obj.getId());
	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from personne where id=?")) {
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
	public Optional<Personne> findByKey(Integer key) {
		Personne p = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select p.id as id,p.prenom as prenom,p.nom as nom,p.civilite ,"
						+ "p.type,p.experience,p.id_formation,f.nom as nomf,f.date_formation "
						+ "from personne p left join formation f on p.id_formation=f.id " + "where p.id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("type").equals("s")) {
					p = new Stagiaire(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
					if (rs.getInt("id_formation") != 0) {
						((Stagiaire) p).setFormation(new Formation(rs.getInt("id_formation"), rs.getString("nomf"),
								rs.getDate("date_formation")));
					}
				} else if (rs.getString("type").equals("f")) {
					p = new Formateur(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
					((Formateur) p).setExperience(rs.getInt("experience"));
				}
				if (rs.getString("civilite") != null) {
					p.setCivilite(Civilite.valueOf(rs.getString("civilite")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(p);
	}

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = new ArrayList<Personne>();
		Personne p = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select p.id as id,p.prenom as prenom,p.nom as nom,p.civilite ,"
					+ "p.type,p.experience,p.id_formation,f.nom as nomf,f.date_formation "
					+ "from personne p left join formation f on p.id_formation=f.id ");
			while (rs.next()) {
				if (rs.getString("type").equals("s")) {
					p = new Stagiaire(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
					if (rs.getInt("id_formation") != 0) {
						((Stagiaire) p).setFormation(new Formation(rs.getInt("id_formation"), rs.getString("nomf"),
								rs.getDate("date_formation")));
					}
				} else if (rs.getString("type").equals("f")) {
					p = new Formateur(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
					((Formateur) p).setExperience(rs.getInt("experience"));
				}
				if (rs.getString("civilite") != null) {
					p.setCivilite(Civilite.valueOf(rs.getString("civilite")));
				}
				personnes.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return personnes;
	}

	@Override
	public List<Personne> findByPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Formateur> findAllFormateur() {
		List<Formateur> formateurs = new ArrayList<Formateur>();
		Formateur f = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from personne where type='f'");
			while (rs.next()) {
				f = new Formateur(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
				f.setExperience(rs.getInt("experience"));

				if (rs.getString("civilite") != null) {
					f.setCivilite(Civilite.valueOf(rs.getString("civilite")));
				}
				formateurs.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return formateurs;
	}

}
