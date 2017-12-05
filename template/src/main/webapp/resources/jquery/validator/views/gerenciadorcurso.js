$(document).ready( function() {

// Verificando e convertendo para UPPERCASE do campo
$("#add_nome_curso,#edit_nome_curso").bind('keyup', function (e) {
    if (e.which >= 97 && e.which <= 122) {
        var newKey = e.which - 32;
        e.keyCode = newKey;
        e.charCode = newKey;
    }
    $("#add_nome_curso").val(($("#add_nome_curso").val()).toUpperCase());
    $("#edit_nome_curso").val(($("#edit_nome_curso").val()).toUpperCase());
});
    

    
// Validação do Formulário adicionar curso pelo Modal    
$("#formcurso_adicionar").validate({
    // Define as regras
    rules:{
      nome_curso:{
        required: true, minlength: 3
      }
    },
    // Define as mensagens de erro para cada regra
    messages:{
      nome_curso:{
        required: "O nome do curso não pode ser vazio",
        minlength: "O nome do curso deve conter, no mínimo, 3 caracteres"
      }
    }
  });

// Validação do Formulário editar curso pelo Modal
 $("#formcurso_editar").validate({
    // Define as regras
    rules:{
      nome_curso:{
        required: true, minlength: 3
      }
    },
    // Define as mensagens de erro para cada regra
    messages:{
      nome_curso:{
        required: "O nome do curso não pode ser vazio",
        minlength: "O nome do curso deve conter, no mínimo, 3 caracteres"
      }
    }
  });        
});

