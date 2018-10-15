function mostrar(id) {
		if (id == "cnueva") {
			$("#nueva").show();
		}
		if (id != "cnueva") {
			$("#nueva").hide();
		}
	}
	$(document).ready(function(){
		$('#boton_append_1').click(function() {
			
			 var exp = /([A-Za-z0-9_])$/;
			
			if($("#nuevaCarpeta").val().length == 0){
				 alert("El nombre no puede ir vacio");
			}else 
			if($("#nuevaCarpeta").val() == " "){
				alert("El nombre no puede ir vacio");
			}else 
			if($("#nuevaCarpeta").val().length > 12){
				alert("El nombre no puede superar los 12 caracteres");
			}else
			if($("#nuevaCarpeta").val().match(exp)){
			$('.carpeta').prepend(
				"<option> /Carpeta_"+$("#nuevaCarpeta").val()+" </option> ");
				alert("Se creo carpeta [ "+$("#nuevaCarpeta").val()+ " ] con exito");
			}else {
				alert("El nombre no puede contemer caracteres especiales");
			}
			
		});
	});
	
	