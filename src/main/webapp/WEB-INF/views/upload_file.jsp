<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<html>
<head>
<link href="/rnc/resources/css/styles.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="http://www.expertphp.in/js/jquery.form.js"></script>
</head>
<body>
	<div class="header">
		<div class="row margin-bottom-10">
			<div class="container col-md-4 left">
		    <img src="/rnc/resources/imagenes/MINSAL.png"/>
		  </div>
		  <div class="container col-md-4 text-center center">
		    <img src="/rnc/resources/imagenes/1.png"/>
		  </div>
		  <div class="container col-md-4 text-right right">
		    <img src="/rnc/resources/imagenes/2.png"/>
		  </div>			
		</div>
		<h3 class="text-center margin-bottom-30">Repositorio Nacional del Cáncer - Sistema de Cargas de Archivos CSV - Clínica Universidad Católica: <a href="http://redsalud.uc.cl/ucchristus/Hospital/ClinicaUC/">@UC-Christus</a></h3> 	
	</div>
 <div class="container margin-bottom-30">
    <div class="col-md-12">
      <div class="panel with-nav-tabs panel-primary">
        <div class="panel-heading">
          <ul class="nav nav-tabs">
            <li class="active"><a href="" data-toggle="tab" class="btn-9"><i class="fa fa-file-text" aria-hidden="true"></i>Carga de Archivos</a></li>
          </ul>
        </div>
            <div class="tab-pane fade in active" id="Tabs-Keempat">
              <div class="col-md-12">
                <form action = "file_upload" method = "post" enctype = "multipart/form-data">
                  <div class="form-group files color">
                    <label>Suba su archivo csv </label>
                    <input type="file" class="form-control" name="file">
                  </div>
                  <div class="col-md-12">
                    <button type = "submit" value = "Upload File" class="btn btn-labeled btn-success pull-right">
                      <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span>Guardar
                    </button>
                  </div>
                </form>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
  
<div class="container">   
    <div class="panel panel-success">
		  <div class="panel-heading" id="req">Manual de Procedimiento</div>
		  <div class="panel-body">
		    <ul>  
		    <h3>Para generar el archivo CSV debe:</h3>		
		    <li>
		      En documento excel debe seleccionar "Guardar Como:"
		    </li>
		    <li>
		      Al desplegar la ventana, debe seleccionar el lugar donde desea guardar el archivo
		    </li>
		    <li>
		     	A continuación debe seleccionar formato de archivo y seleccionar: "CSV codificado en UTF-8 (delimitado por comas)(.CSV)"
		    </li>
		    <li>
		     	En caso que se despliegue una ventana emergente, debe "Aceptar".
		    </li>
		  	</ul>
		  </div>
	</div>
 </div>

</body>

</html>

<!-- <html>
   <head>
		<title>File Uploading Form</title>
   </head>
   <body>
      <h3>File Upload:</h3>
      Select a file to upload: <br />
      <form action = "file_upload" method = "post"
         enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "50" />
         <br />
         <input type = "submit" value = "Upload File" />
      </form>
   </body>
</html> -->