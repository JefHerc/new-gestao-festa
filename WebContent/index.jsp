<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Gestão de festas</title>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<h1>Lista de convidados</h1>
		
			<a href="add-convidado-form.jsp"><button class="btn btn-primary mb-3">Adicionar</button></a>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Quantidade de convidados</th>
						<th colspan="2">Ação</th>					
					</tr>
				</thead>
				<tbody>
					<c:forEach var="convidado" items="${convidados}">
						<c:url var="deletar" value="ConvidadosController">
							<c:param name="command" value="deletar" />
							<c:param name="convidadoId" value="${convidado.id}"></c:param>
						</c:url>
						<c:url var="atualizar" value="ConvidadosController">
							<c:param name="command" value="carregar" />
							<c:param name="convidadoId" value="${convidado.id}"></c:param>
						</c:url>
						<tr>
							<td>${convidado.nome}</td>
							<td>${convidado.quantidadeAcompanhantes}</td>
							<td><a href="${atualizar}">Atualizar</a></td>
							<td><a href="${deletar}">Deletar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>