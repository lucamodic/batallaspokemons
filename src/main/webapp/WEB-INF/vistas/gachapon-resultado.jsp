<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/gachapon-resultado.css"></link>
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <title>Gachapon Resultado</title>
</head>
<body>

    <div class="header">
        <a href="gachapon" class="gachaponLogo" ><img class="gachapon" src="images/togepi.png" alt="gachaLogo"></a>
    </div>
    
    <div class="resultado">
        <img class="pokeballArriba pokeball animation" src="images/pokeballArriba${monedas}.png" alt="arriba">
        <img class="pokemon animation" src="images/sprites/${pokemon.nombre}/${pokemon.imagenFrente}" alt="pokemon">
        <img class="pokeballAbajo pokeball animation" src="images/pokeballAbajo100.png" alt="abajo">
    </div>
    <c:if test="${empty repetido}">
			<h1 class="congrats animation">¡Felicidades, ${pokemon.nombre}  es tu nuevo pokemon!</h1>
		</c:if>
    
    <c:if test="${not empty repetido}">
   			 <h1 class="congrats animation">${repetido}  ${pokemonedas}</h1>
		</c:if>


    <script  type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script type="text/javascript" src="js/gachapon-resultado.js"></script>
</body>
</html>
