<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400|Open+Sans' rel='stylesheet' type='text/css'>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand logo-login" href="#">RAPIDONET</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      

        <div class="navbar-text pull-right">
       
			<form id="formlogin_logar" action="j_spring_security_check" method="POST" class="form-inline" >
			  <div class="form-group form-group-sm">
			    <input type="text" name="j_username" class="form-control" placeholder="Matrícula" value="${param.usuario}">
			  </div>
			  <div class="form-group form-group-sm">
			    <input type="password" name="j_password" class="form-control form-control-sm" placeholder="Senha" value="${param.senha}">
			  </div>
			  <button type="submit" class="btn btn-login-menu btn-sm">Entrar</button>
			</form>       

      </div>

    
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>