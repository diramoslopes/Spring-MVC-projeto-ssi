$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var codigoAtendimento = button.data('id');
	var solicitanteAtendimento = button.data('solicitante')
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if(!action.endsWith('/')){
		action += '/';
	}
	
	form.attr('action', action + codigoAtendimento);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o atendimento do <strong>' + solicitanteAtendimento + '</strong>?');
	
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	
	$('#ip'). mask ('999.999.99.999');
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoCheck = $(event.currentTarget); 
		var urlCheck = botaoCheck.attr('href');
		
		var response = $.ajax({
			url: urlCheck,
			type: 'PUT'
		});
		
		response.done(function(e){
			var codigoCheck = botaoCheck.data('codigo');
			$('[data-role=' + codigoCheck + ']').html('<span class="label label-success">' + e + '</span>');
			botaoCheck.hide();
		});
		
		reponse.fail(function(e){
			console.log(e)
			alert("Erro realizar/resolver")
		})
		
		console.log('urlCheck', urlCheck)
		
	});
	
});