package br.com.treino.festa.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import br.com.treino.festa.dao.ConvidadoDAO;
import br.com.treino.festa.model.Convidado;

@WebServlet("/ConvidadosController")
public class ConvidadosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/festa")
	private DataSource dataSource;
	ConvidadoDAO convidadoDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			convidadoDAO = new ConvidadoDAO(dataSource.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");

		if (command == null) {
			command = "listar";
		}
		switch (command) {
		case "listar":
			listarConvidados(request, response);
			break;
		case "deletar":
			deletarConvidados(request, response);
			break;
		case "carregar":
			carregarConvidado(request, response);
			break;
		default:
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");

		switch (command) {
		case "salvar":
			salvarConvidado(request, response);
			break;
		case "atualizar":
			atualizarConvidado(request, response);
			break;
		default:
			listarConvidados(request, response);
			break;
		}
	}

	private void atualizarConvidado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		int quantidadeAcompanhantes = Integer.parseInt(request.getParameter("acompanhantes"));
		int convidadoId = Integer.parseInt(request.getParameter("convidadoId"));
		Convidado convidado = new Convidado(convidadoId, nome, quantidadeAcompanhantes);

		convidadoDAO.atualizarConvidado(convidado);

		listarConvidados(request, response);
	}

	private void carregarConvidado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int convidadoId = Integer.parseInt(request.getParameter("convidadoId"));

		Convidado convidado = convidadoDAO.loadConvidado(convidadoId);

		request.setAttribute("CONVIDADO", convidado);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/atualizar-convidado-form.jsp");
		dispatcher.forward(request, response);
	}

	private void deletarConvidados(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int convidadoId = Integer.parseInt(request.getParameter("convidadoId"));
		convidadoDAO.deletarConvidado(convidadoId);

		listarConvidados(request, response);
	}

	private void salvarConvidado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		int quantidadeAcompanhantes = Integer.parseInt(request.getParameter("acompanhantes"));

		Convidado convidado = new Convidado(nome, quantidadeAcompanhantes);

		convidadoDAO.salvar(convidado);

		listarConvidados(request, response);
	}

	private void listarConvidados(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Convidado> convidados = convidadoDAO.listar();

		request.setAttribute("convidados", convidados);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
