$(function(){
    // Inicializando Combobox da Disciplina como oculto.
    $('select[name=cod_disciplina]').hide();
    
    // Desabilitando botão de pesquisar
    $('button[name=btnpesquisar]').attr('disabled', 'disabled');
    
    /* Ao clicar no combobox do curso é efetuado algumas pesquisas com ajax.
     * para preencher o combobox da disciplina conforme o curso selecionado.
     */
    $('select[name=cod_curso]').on('change',function(){
        var codigoDoCursoComboBox = $('select[name=cod_curso]').val();
		$.ajax({
			type: 'POST',
			url: 'relacaodisciplina',
			data: 'cod_curso=' +$('select[name=cod_curso]').val(),
			statusCode:{
				404: function(){
					alert("Erro 404 :o");
				},
				500: function(){
					alert("Erro no servidor");
				}
			},
			success: function(dados){
                $('select[name=cod_disciplina]').fadeIn(1000).show();
				$('select[name=cod_disciplina] option').remove();
				$('select[name=cod_disciplina]').append('<option value="-1">Selecione a disciplina</option>')
				var pegadados = dados.split(":");
				for(var i = 0; i < pegadados.length - 1; i++){
					var cod_disciplina = pegadados[i].split("-")[0];
					var nome_disciplina = pegadados[i].split("-")[1];
					$('select[name=cod_disciplina]').append(
                        '<option value="'+cod_disciplina+'">'+nome_disciplina+'</option>'
                    )
				}
			}
			
		});
	})
    
    /*
     * Ao selecionar o combobox da disicplina é verificado se foi de fato selecionado uma disciplina,
     * caso seja diferente de (-1) significa que não é a opção de "Selecione a Disciplina" então é 
     * liberado o botão de pesquisar, senão é bloqueado o botão de pesquisar.
     */
     $('select[name=cod_disciplina]').on('change',function(){
        var codigoDaDisciplinaComboBox = $('select[name=cod_disciplina]').val(); 
        if(codigoDaDisciplinaComboBox != -1){
            $('button[name=btnpesquisar]').removeAttr('disabled');
        }else{
            $('button[name=btnpesquisar]').attr('disabled', 'disabled');
        }		
	})
     
});
