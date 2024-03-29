<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet" >
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

        <title>Roles</title>
    </head>
    <body>
        <div class="container">
            <h1>ROLES</h1>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="roles" />
            </jsp:include>
            <br>
            <a href="RolControlador?action=add" class="btn btn-primary btn-sm"><i class="fas fa-plus-circle"></i> Nuevo </a>
            <table class="table table-striped">
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${roles}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.descripcion}</td>
                    <td><a href="RolControlador?action=edit&id=${item.id}"><i class="fas fa-edit"></i></a></td>
                    <td><a href="RolControlador?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro ???'))">
                            <i class="fas fa-trash-alt"></i>
                        </a>
                    </td>
                </tr>                    
                </c:forEach>
            </table>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>

    </body>
</html>
