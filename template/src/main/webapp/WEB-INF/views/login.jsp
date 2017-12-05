<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Import JSTL-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login Template</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/estilo.css" />" />   
    
<!-- Estilos do Alertfy -->
<link rel="stylesheet" href="<c:url value="/resources/jquery/alertifyjs/alertify.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/jquery/alertifyjs/css/themes/default.min.css"/>" /> 
<script src="<c:url value="/resources/jquery/alertifyjs/alertify.min.js" />"></script>
    
<!-- Jquery Validator Form -->
    <script src="resources/js/jquery-1.11.2.min.js"></script>   
<script src="<c:url value="/resources/jquery/validator/jquery.validate.js" />"></script>
<script src="<c:url value="/resources/jquery/validator/views/login.js" />"></script>
    
<!-- Jquery para Loading Page -->    
<script src="<c:url value="/resources/jquery/modernizr/modernizr.js" />"></script>
 
<script>
	$(window).load(function() {
		// Animate loader off screen
		$(".loading-page").fadeOut(3000);
	});    
</script>     
    
</head>
<body class="background-login">
    <div class="container-fluid">
        
        <div class="loading-page"></div>
        
        <!-- MENSAGENS ALERTIFY-->
        <c:if test="${not empty param.error}">
            <script>
                alertify.error("${param.error}");
            </script>
        </c:if>
        
        <c:if test="${not empty erro}">
            <script>
                alertify.error("${erro}");
            </script>
        </c:if>
        
        <c:if test="${not empty param.sucesso}">
            <script>
                alertify.success("${param.sucesso}");
            </script>
        </c:if>
        
        <c:if test="${not empty sucesso}">
            <script>
                alertify.success("${sucesso}");
            </script>
        </c:if>
             
    
     <!-- CABEÇALHO -->
        <header class="row">
        <c:import url="/resources/template/publico/menu.jsp"></c:import> 
        </header>

        <!-- CONTEÚDO -->
        <div class="row">
                <div class="col-md-4 col-md-offset-4">
		          <form id="formlogin_cadastrar" action="<c:url value='/adicionausuariologin' />" method="POST">
                    <div class="panel-body">
                    <h3 class="text-center signup">
                        Cadastre-se Agora
                    </h3>
                    <form class="form form-signup" role="form">
                    <!-- Campo Matrícula -->
                     <div class="form-group">
                       <input type="text" class="form-control mat-input" placeholder="Matrícula" name="matricula"/>  
                     </div>

                     <!-- Campo Nome Completo -->
                     <div class="form-group">
                       <input type="text" class="form-control mat-input" placeholder="Nome completo" name="nome" id="add_nome"/>
                     </div>

                     <!-- Campo Email -->
                     <div class="form-group">
                       <input type="text" class="form-control mat-input" placeholder="Email" name="email"/>
                     </div>

                     <!-- Campo Senha -->
                     <div class="form-group">
                       <input type="password" class="form-control mat-input" placeholder="Senha" name="senha"/>
                       <input type="hidden" name="permissao"/>
                     </div>
                     <br>        
                     <button type="submit" class="btn btn-success btn-login center-block">Abrir Conta</button>
                     <!-- <a data-toggle="modal" data-target="#myModal"><h5 class="text-center">Esqueceu sua senha?</h5></a> -->    
                </form>
            </div>
    </div>
</div>
        <!-- RODAPÉ 
        <footer class="row">
         
        </footer>-->
    
        <!-- MODAL PARA RESETAR SENHA -->
        <c:import url="/resources/template/publico/resetarsenha.jsp"></c:import> 
        
    <!--FIM DIV CONTAINER-->
    </div>

    <!-- Javascript do bootstrap -->
    <script src="resources/js/bootstrap.min.js"></script>
</body>
    
</html>