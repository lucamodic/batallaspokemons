<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<c:url value="/css/historial-de-batallas.css"/>"
	rel="stylesheet">
<table class="table table-historial">
	<thead>
		<tr class="text-center align-middle">
			<th scope="col" class="table-text">Fecha</th>
			<th scope="col">Pokemons Usuario</th>
			<th scope="col">Pokemons Cpu</th>
			<th scope="col" class="table-text">Duraci�n</th>
			<th scope="col" class="table-text">Resultado</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${batallas}" var="batalla">
			<tr class='text-center align-middle trbody' id="batalla${batalla.id}">
				<td class="table-text"><fmt:formatDate pattern="dd-MM-yy"
						value="${batalla.fecha}" /></td>
				<td><c:forEach items="${batalla.pokemonsUsuario}"
						var="pokemonBatalla">
						<img
							class="img-pokemon<c:if test="${pokemonBatalla.debilitado}"> debilitado</c:if>"
							alt="${pokemonBatalla.pokemon.nombre}"
							src="<c:url value="/images/sprites/${pokemonBatalla.pokemon.nombre}/${pokemonBatalla.pokemon.imagenFrente}"/>">
					</c:forEach></td>
				<td><c:forEach items="${batalla.pokemonsCpu}"
						var="pokemonBatalla">
						<img
							class="img-pokemon<c:if test="${pokemonBatalla.debilitado}"> debilitado</c:if>"
							alt="${pokemonBatalla.pokemon.nombre}"
							src="<c:url value="/images/sprites/${pokemonBatalla.pokemon.nombre}/${pokemonBatalla.pokemon.imagenFrente}"/>">
					</c:forEach></td>
				<td class="table-text">${batalla.duracion}</td>
				<td class="table-text">${batalla.resultado}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="form-check form-switch switch">
	<input class="form-check-input" type="checkbox" id="switch"> <label
		class="form-check-label" for="switch">Ver pokemons debilitados</label>
</div>
<script>
	document.title = 'Historial de batallas';
</script>
<script src="<c:url value="/js/historial-de-batallas.js"/>"></script>


