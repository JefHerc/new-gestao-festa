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
			<h1>Adicionar novo convidado</h1>
			<form action="ConvidadosController" method="post">
				<input type="hidden" name="command" value="salvar">
				<div>
					<label for="nome" class="mr- 3 form-label">Nome</label>				
					<input class="ml-3 form-control" type="text" name="nome" placeholder="Nome">
				</div>
				<div>
					<label for="nome" class="form-label">Quantidade de acompanhantes</label>				
					<input class="form-control"type="number" name="acompanhantes" placeholder="Acompanhantes">
				</div>
				<button type="submit" class="btn btn-primary mt-3">Adicionar</button>
			</form>
		</div>
	</body>
</html>