package br.com.treino.festa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treino.festa.model.Convidado;

public class ConvidadoDAO {

	Connection connection;

	public ConvidadoDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Convidado> listar() {
		List<Convidado> convidados = new ArrayList<>();
		String sql = "SELECT * FROM CONVIDADO";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			ResultSet rst = pstm.getResultSet();
			while (rst.next()) {
				Convidado convidado = new Convidado(rst.getInt(1), rst.getString(2), rst.getInt(3));
				convidados.add(convidado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return convidados;
	}

	public void salvar(Convidado convidado) {
		String sql = "INSERT INTO convidado (nome, quantidade_acompanhantes) VALUES (?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, convidado.getNome());
			pstm.setInt(2, convidado.getQuantidadeAcompanhantes());
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarConvidado(int convidadoId) {

		String sql = "DELETE FROM convidado WHERE id = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, convidadoId);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizarConvidado(Convidado convidado) {
		String sql = "UPDATE convidado SET nome = ?, quantidade_acompanhantes = ? WHERE id = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, convidado.getNome());
			pstm.setInt(2, convidado.getQuantidadeAcompanhantes());
			pstm.setInt(3, convidado.getId());
			boolean foi = pstm.execute();
			System.out.println(foi);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Convidado loadConvidado(int convidadoId) {
		String sql = "Select nome, quantidade_acompanhantes from convidado WHERE id = ?";
		Convidado convidado = null;
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, convidadoId);
			pstm.execute();
			
			try (ResultSet rst = pstm.getResultSet()) {
				rst.next();
				String nome = rst.getString("nome");
				int quantidadeAcompanhantes = rst.getInt("quantidade_acompanhantes");
				convidado = new Convidado(convidadoId, nome, quantidadeAcompanhantes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convidado;
	}
}
