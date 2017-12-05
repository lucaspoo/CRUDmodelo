<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Import JSTL-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Spring Famework -->
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//PT" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rapidonet - Gerenciador Usuario</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/estilo.css" />" />
    
<!-- Fontes do Google -->
<link href='http://fonts.googleapis.com/css?family=Anton' rel='stylesheet' type='text/css'>
    
<!-- Javascript do bootstrap -->
<script src="resources/js/jquery-1.11.2.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<!-- Estilos do Alertfy -->
<link rel="stylesheet" href="<c:url value="/resources/jquery/alertifyjs/alertify.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/jquery/alertifyjs/css/themes/default.min.css"/>" /> 
<script src="<c:url value="/resources/jquery/alertifyjs/alertify.min.js" />"></script>

<!-- Importação do arquivo Ajax -->
<script type="text/javascript" charset="utf-8"  src="<c:url value="/resources/jquery/ajax/ajax_gerenciadorusuario.js" />"></script> 

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/datatables/media/css/jquery.dataTables.css"/>">

    <!-- DataTables -->
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/jquery/datatables/media/js/jquery.dataTables.js"/>"></script>
<script>
    $(document).ready(function() {
    $.extend( $.fn.dataTable.defaults, {
            searching: false,
            ordering:  true,
            info: false
    });
             
    $('#tabelausuario').dataTable( {
        responsive: true,
        "scrollY":        "300",
        "scrollCollapse": true,
        "paging":         false, // Remove ocao de setar quantos itens mostrados
        //"bSort": false, // Removendo Ordenação
        
        "language": {
            "emptyTable":     "Sem dados para exibir",
            "lengthMenu": "Display _MENU_ records per page",
            "zeroRecords": "Nada encontrado para esse filtro",
            "info": "Mostrando pagina _PAGE_ de _PAGES_<br><br>",
            "infoEmpty": "Mostrando pagina 0 de 0 entradas",
            "infoFiltered": "(filtrado de _MAX_ gravações)<br><br>",
            "search":         "Procurar:"
        },
    } );
} );    
    
    </script>
    
<!-- Jquery Validator Form --> 
<script src="<c:url value="/resources/jquery/validator/jquery.validate.js" />"></script>
<script src="<c:url value="/resources/jquery/validator/views/gerenciadorusuario.js" />"></script> 
    
</head>
    
<body class="background-login">
    <div class="container-fluid">
        
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
        
        <c:if test="${not empty sucesso}">
            <script>
                alertify.success("${sucesso}");
            </script>
        </c:if>
             
        <!-- CABEÇALHO -->
        <header class="row">
            <jsp:include page="/resources/template/admin/menu.jsp">
                <jsp:param name="active" value="gerenciadorusuario"/>
            </jsp:include> 
        </header>
        
        <!-- CONTEÚDO -->
        <div class="row">
            <div role="main">
                <div class="col-md-12 col-md-offset-0">
		      
                    <!-- BREADCRUMB -->
                    <ul class="breadcrumb">
                       <li><a href="gerenciador">Home</a></li>
                      <li class="active">Gerenciador Usuario</li>
                    </ul>
                    
                    <form action="pesquisausuario" method="POST">
                        <label>
                            Pesquisar por: &nbsp; 
                        </label>
                        
                        <label class="radio-inline">
                            <input type="radio" name="opcaopesquisa" id="op_pesq_matricula" value="matricula" checked>Matricula
                        </label>
                        
                        <label class="radio-inline">
                            <input type="radio" name="opcaopesquisa" id="op_pesq_nome" value="nome">Nome
                        </label>
                             
                        
                        <div class="input-group">
                          <input type="number" class="form-control" name="matricula" id="search_matricula" placeholder="Matricula do usuario">
                          <input type="text" class="form-control" style="display:none" name="nome" id="search_nome" placeholder="Nome do Usuario">
                          <div class="input-group-btn">
                              <button type="submit" class="btn btn-info"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                    </form>
                    
                    <br>
                    
                    <table id="tabelausuario" class="table table-responsive table-bordered table-hover table-striped">
                        <thead>
                            <tr class="table-info">
                                <th>Matricula</th>
                                <th>Nome</th>
                                <th>Email</th>
                                <th>Permissao</th>
                                <th>Nova Senha</th>
                                <th>Editar</th>
                                <th>Excluir</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${listausuarios}" >
                            <tr class="active">
                                
                                <td class="text-center" name="tb_matricula">${usuario.matricula}</td>
                                
                                <td name="tb_nome">${usuario.nome}</td>
                                
                                <td name="tb_email">${usuario.email}</td>
                                
                                <td name="tb_permissao" id="${usuario.permissao}">${usuario.permissao}</td>
                                 
                                 <td>
                                    <button type="submit" class="btn btn-primary btn-sm"data-toggle="modal" data-target="#modalNovaSenha" name="novasenha">
                                        <span class="glyphicon glyphicon-lock"></span>
                                        <strong>Nova Senha</strong>
                                    </button>
                                </td>
                                
                                
                                <td>
                                    <button type="submit" class="btn btn-primary btn-sm"data-toggle="modal" data-target="#modalEditaUsuario" name="editarusuario">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        <strong>Editar</strong>
                                    </button>
                                </td> 
                                
                                <td>
                                    <button type="submit" class="btn btn-danger btn-sm" name="excluirusuario">
                                        <span class="glyphicon glyphicon-trash"></span>
                                         <strong>Excluir</strong>
                                    </button>
                                </td>
                                  
                            </tr>
                           </c:forEach> 
                        </tbody>
                    </table>
                
                    <div class="col-md-offset-9">
                        <br>
                        <button type="submit" class="btn btn-primary btn-sm form-control" data-toggle="modal" data-target="#modalAdicionaUsuario"/>
                        <span class="glyphicon glyphicon-plus-sign"></span>&nbsp;
                        <strong>Adicionar Novo Usuário</strong>
                        </button>
                    </div>
                    
                <br>
                <br>
                
            </div>
        </div>  
    </div>
        <!-- RODAPÉ  
        <footer class="row">
            <c:import url="/resources/template/admin/rodape.jsp"></c:import> 
        </footer>-->
    
        <!-- MODALS -->
        <c:import url="/resources/template/admin/modal/usuario/adicionausuario.jsp"></c:import>
        <c:import url="/resources/template/admin/modal/usuario/editausuario.jsp"></c:import> 
        <c:import url="/resources/template/admin/modal/usuario/alterasenha.jsp"></c:import> 
        
    <!--FIM DIV CONTAINER-->
    </div>
</body>
</html>