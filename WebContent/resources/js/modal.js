
function onevent(e){
                        
                        if(e.status==='begin'){
                            document.getElementById('tope').style.display='block';
                        }
                        if(e.status==='success') {
                                $(".efecto-01").fadeOut(1000,function(){
                                    $(this).fadeIn(1000);
                                });
                                
                        } 
                        if(e.status==='complete') {
                          
                          document.getElementById('tope').style.display='none';
                         
                        } 
                      }


$(document).ready(function(){
    
        /*    
                    $.datepicker.regional["es"] = {
                    closeText: 'Cerrar',
                    prevText: 'Ant',
                    nextText: 'Sig',
                    currentText: 'Hoy',
                    monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                    monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
                    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
                    dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
                    dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
                    weekHeader: 'Sm',
                    dateFormat: 'dd/mm/yy',
                    firstDay: 1,
                    isRTL: false,
                    showMonthAfterYear: false,
                    yearSuffix: ''
                    };
                    

                    $.datepicker.setDefaults($.datepicker.regional["es"]);
                    $(".fecha").datepicker({ dateFormat: "dd-mm-yy" });
                        
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       
      $('.pdf').click(function(e){
        
        archivo=$(this).attr("href");
        extension = (archivo.substring(archivo.lastIndexOf(".")+1)).toLowerCase(); 
        
        if(extension === "pdf"){
            e.preventDefault();
          
            $(".principal object").attr("type","application/pdf");
            $(".principal object").attr("data",archivo);
            $(".principal object").css("display","block");
            $(".principal .iconos-indice").css("display","none");
        }
     });
          
     $('.pdfCronograma').click(function(e){
        e.preventDefault();
       
        
       $("#principal object").attr("type","application/pdf");
       $("#principal object").attr("data",$(this).attr("href"));
       $("#principal object").css("display","block");
 
     });
    
    
    
    
    
 
 
    
     
    
     
	//parametros principales
           var contenidoHTML = 
                  ' <form method="post" action="SRegProducto" class="form-style-a" >'+
        ' <div class="font24 color-c sm-header" style="display:block;width:100%;padding:5px 0;text-align:center"> Actualizar Foto</div>'+
        ' <input type="hidden" name="txtNombre" style="width:55%" value="'+$("#codigo").val()+'" size=50 />'+
        ' <label style="width:30%;padding-left:10px" > Imagen en JPG:</label><input type="file" name="txtDireccion" id="txtDireccion" style="width:55%"  value="" size="50" /><br />'+
        '  <img id="imgSalida" src="resources/imagenes/icon-user.png" style="background:rgba(0,0,0,0.1);height:100px;margin:0 auto;display:block;box-shadow:1px 1px 1px rgba(0,0,0,0.4),-1px -1px 1px rgba(0,0,0,0.4)"/>'+
        '<div id="wrapper-button" style="margin:0 auto;text-align:center"> <input type="submit" value="Subir foto"  id="btnFotoper" style="width:30%" class="form-button"/>'+
        '<input type="button" value=Cerrar onclick="closeModal()" style="width:30%" id="btnClosemod" class="form-button"></div>'+
        ' </form>';
    
    
    
        
	
	 var ancho = 0; 
	var alto = 0;

	$('#button').click(function(){
         alto=210;
         ancho = 550;
		// fondo transparente
                // creamos un div nuevo, con dos atributos
		/*var bgdiv = $('<div>').attr({
					className: 'bgtransparent',
					id: 'bgtransparent'
					});
                */
                var bgdiv = $('<div id=bgtransparent>').css({
                                        position:"fixed",
                                        left:"0",
                                        top:"0",
                                        background:"#000",
                                        opacity:"0.6",
                                        filter:"alpha(opacity=60)" 
					});
                
                
                
                // agregamos nuevo div a la pagina
		$('body').append(bgdiv);
		
                // obtenemos ancho y alto de la ventana del explorer
		var wscr = $(window).width();
		var hscr = $(window).height();
		
		//establecemos las dimensiones del fondo
		$('#bgtransparent').css("width", wscr);
		$('#bgtransparent').css("height", hscr);
		
		
		// ventana modal
                // creamos otro div para la ventana modal y dos atributos
		/*var moddiv = $('<div>').attr({
					className: 'bgmodal',
					id: 'bgmodal'
					});	
		*/
               var moddiv = $('<div id=bgmodal>').css({position:"fixed", 
                                            //font-family:"arial",
                                           // font-size:"1em",
                                            border:"0.05em solid black",
                                            //overflow:"auto",
                                            background:"#ECF0F1"
					});	
		
               
               
                // agregamos div a la pagina
		$('body').append(moddiv);

                // agregamos contenido HTML a la ventana modal
		$('#bgmodal').append(contenidoHTML);
		
                // redimensionamos para que se ajuste al centro y mas
		$(window).resize();
                
                $('#txtDireccion').change(function(e) {
                    addImage(e); 
                });

                function addImage(e){
                   var file = e.target.files[0],
                   imageType = /image.*/;
                   if (!file.type.match(imageType))
                       return;

                   var reader = new FileReader();
                   reader.onload = fileOnload;
                   reader.readAsDataURL(file);
                }

                function fileOnload(e) {
                    var result=e.target.result;
                    $('#imgSalida').attr("src",result);
                    $('#imgSalida').css("height","150px");
                }
	});

	$(window).resize(function(){
		// dimensiones de la ventana del explorer 
		var wscr = $(window).width();
		var hscr = $(window).height();

		// estableciendo dimensiones de fondo
		$('#bgtransparent').css("width", wscr);
		$('#bgtransparent').css("height", hscr);
		
		// estableciendo tamaño de la ventana modal
		$('#bgmodal').css("width", ancho+'px');
		$('#bgmodal').css("min-height", alto+'px');
		
		// obtiendo tamaño de la ventana modal
		var wcnt = $('#bgmodal').width();
		var hcnt = $('#bgmodal').height();
		
		// obtener posicion central
		var mleft = ( wscr - wcnt ) / 2;
		var mtop = ( hscr - hcnt ) / 2;
		
		// estableciendo ventana modal en el centro
		$('#bgmodal').css("left", mleft+'px');
		$('#bgmodal').css("top", mtop+'px');
                
                
                //--------------- efecto de ingreso -----------------------
                $("#bgmodal").css("opacity",0).animate({"opacity":1},700);
	});
	  
       
	
        
        
            $('#modales').click(function(){
               // alert("entro");
               
                ancho = 700; 
                 alto = 300;
                var bgdiv = $('<div id=bgtransparent>').css({
                                        position:"fixed",
                                        left:"0",
                                        top:"0",
                                        background:"#000",
                                        opacity:"0.6",
                                       filter:"alpha(opacity=60)" 
					});
                
                
                
                // agregamos nuevo div a la pagina
		$('body').append(bgdiv);
                
                
                var wscr = $(window).width();
		var hscr = $(window).height();
		
		//establecemos las dimensiones del fondo
		$('#bgtransparent').css("width", wscr);
		$('#bgtransparent').css("height", hscr);
                
                $('#bgmodal').css({position:"fixed", 
                                            border:"0.05em solid black",
                                            overflow:"auto",                                            
                                            background:"#ECF0F1",
                                            'z-index':"500",
                                            display:"block"
		});	
		
		
		$('#bgtransparent').css("width", wscr);
		$('#bgtransparent').css("height", hscr);
		
		// estableciendo tamaño de la ventana modal
		$('#bgmodal').css("width", ancho+'px');
		$('#bgmodal').css("min-height", alto+'px');
		
		// obtiendo tamaño de la ventana modal
		var wcnt = $('#bgmodal').width();
		var hcnt = $('#bgmodal').height();
		
		// obtener posicion central
		var mleft = ( wscr - wcnt ) / 2;
		var mtop = ( hscr - hcnt ) / 2;
		
		// estableciendo ventana modal en el centro
		$('#bgmodal').css("left", mleft+'px');
		$('#bgmodal').css("top", mtop+'px');
                //--------------- efecto de ingreso -----------------------
                $("#bgmodal").css("opacity",0).animate({"opacity":1},700);
                
                
                
                $('#bgtransparent').click(function(){
                   $('#bgtransparent').remove();
                   $('#bgmodal').css({display:"none"});
                   
                });
                
                
                
                $('.cerrarModal').click(function(){
                   $('#bgtransparent').remove();
                   $('#bgmodal').css({display:"none"});
                   
                });
            });
 });
 

    function msg(texto,imagen){
                        
                          $("#msg-texto").text(texto);
                          $("#msg-img").css("background-image","url('../../resources/imagenes/mensaje/"+imagen+".png')");
                          $( "#msg" ).show( "bounce", { times:5, distance:100,  direction:'down' }, 2000 ).delay(2000).hide( "bounce", { times:1,  distance:100, direction:'down' }, 1000 );
                          
                          
 }

 
	
function closeModal(){
        // removemos divs creados
	$('#bgmodal').remove();
	$('#bgtransparent').remove();
}


  function EstadoBotones(bandera){
      
       $(".deshabilidado").attr("disabled",bandera);
       $("#btEditar").attr("disabled",!bandera);
       $("#Datos\\:btGuardar").attr("disabled",bandera);
       $("#btCancelar").attr("disabled",bandera);
       $("#btImprime").attr("disabled",!bandera);
 }
 
 
 
 



/*
function muestraReloj() {

var fechaHora = new Date();

var hora = 48-fechaHora.getHours();
var minuto = 59-fechaHora.getMinutes();
var segundo =59- fechaHora.getSeconds();



if (hora < 10) {
hora = "0" + hora;
}
if (minuto < 10) {
minuto = "0" + minuto;
}
if (segundo < 10) {
segundo = "0" + segundo;
}

document.getElementById("reloj").innerHTML = hora + ":" + minuto  + ":" + segundo ;

}
*/
window.onload = function() {
//setInterval(muestraReloj, 1000);
//setInterval(countdown('reloj'), 1000);
}



function Refrescar(url){  
   
    $("#principal object").attr("data","../Alumno/pdf/GUIA DE TRABAJOS DE INNOVACION.pdf");
    
    $("#principal object").css("display","block");

}

 function soloNumeros(e){
    
                         
                            var key = window.Event ? e.which : e.keyCode;
                         
                            return ((key >= 48 &&  key <= 57) || (key === 8) || (e.keyCode === 37) || (e.keyCode === 39) || (e.keyCode === 16) || (e.keyCode === 9) );
                    }
                    
                    
                    
                    
 function soloNumerosExecpcion(e, exepcion){
                         
                            var key = window.Event ? e.which : e.keyCode;                            
                            lista=exepcion.split("-");                         
                          
                            for (i=0;i<=lista.length-1;i++){
                                if(String.fromCharCode(key)===lista[i]){
                                    return true;
                                }
                            }
                            return ((key >= 48 &&  key <= 57) || (key === 8) || (e.keyCode === 37) || (e.keyCode === 39) || (e.keyCode === 16) || (e.keyCode === 9));
 }
                    
                    
                    
  function valorIncorecto(objecto,maxValor){           
       
        if(objecto.value > maxValor){
            msg("El valor ingresado es Incorecto","alerta");
            //alert("El valor ingresado es Incorecto");
           }
           
                
                            
}
                    



function regresiva(id){
  
    var fecha=new Date($("#frmNotas\\:caducidad").text());
    var hoy=new Date();
    var dias=0;
    var horas=0;
    var minutos=0;
    var segundos=0;

    if (fecha>hoy){
        var diferencia=(fecha.getTime()-hoy.getTime())/1000;
        dias=Math.floor(diferencia/86400);
        diferencia=diferencia-(86400*dias);
        horas=Math.floor(diferencia/3600);
        diferencia=diferencia-(3600*horas);
        minutos=Math.floor(diferencia/60);
        diferencia=diferencia-(60*minutos);
        segundos=Math.floor(diferencia);

        document.getElementById(id).innerHTML= dias + ' D&iacute;as, ' + horas + ' Horas, ' + minutos + ' Minutos, ' + segundos + ' Segundos';

        if (dias>0 || horas>0 || minutos>0 || segundos>0){
            $("#control-tiempo").attr("display","block");
            setTimeout("regresiva(\"" + id + "\")",1000);
      
        }
    }
    else{
   
        document.getElementById('tiempo').innerHTML= 'tiempo cumplido';
        
    }
}