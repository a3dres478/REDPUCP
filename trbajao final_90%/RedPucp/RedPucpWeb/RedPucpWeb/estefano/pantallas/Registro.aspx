<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Registro.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.Registro" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Registro - RedPUCP</title>

    <!-- bootstrap -->
    <link href="../../Content/bootstrap.min.css" rel="stylesheet" />
    <!-- fontawesome -->
    <link href="../../Content/font-awesome.min.css" rel="stylesheet" />

    <style>
        body {
            background-color: #f0f2f5;
            font-family: "Segoe UI", Arial, sans-serif;
        }

        .registro-container {
            max-width: 400px;
            margin: 70px auto;
            background: #fff;
            padding: 35px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.15);
            text-align: center;
        }

            .registro-container h1 {
                font-weight: bold;
                color: #0d6efd;
                margin-bottom: 10px;
            }

            .registro-container h5 {
                color: #555;
                margin-bottom: 25px;
            }

        .form-group {
            text-align: left;
            margin-bottom: 15px;
        }

        .form-control::placeholder {
            color: rgba(0, 0, 0, 0.35);
        }

        .btn-primary {
            width: 100%;
            border-radius: 25px;
            padding: 10px;
            font-weight: 500;
            background-color: #0d6efd;
            border: none;
        }

            .btn-primary:hover {
                background-color: #0b5ed7;
            }

        .login-link {
            display: block;
            margin-top: 15px;
            color: #0d6efd;
            text-decoration: none;
        }

            .login-link:hover {
                text-decoration: underline;
            }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="registro-container">
            <h1><i class="fa fa-hashtag"></i>RedPUCP</h1>
            <h5>Crear cuenta nueva</h5>

            <div class="form-group">
                <label for="txtNombre"><i class="fa fa-user"></i>Nombre completo</label>
                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" Placeholder="Ingresa tu nombre completo"></asp:TextBox>
            </div>

            <div class="form-group">
                <label for="txtEmail"><i class="fa fa-envelope"></i>Correo PUCP</label>
                <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" TextMode="Email" Placeholder="ejemplo@pucp.edu.pe"></asp:TextBox>
            </div>

            <div class="form-group">
                <label for="txtCodigo"><i class="fa fa-hashtag"></i>Codigo PUCP</label>
                <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control" Placeholder="20220880"></asp:TextBox>
            </div>

            <div class="form-group">
                <label for="txtContrasena"><i class="fa fa-lock"></i>Contraseña</label>
                <asp:TextBox ID="txtContrasena" runat="server" CssClass="form-control" TextMode="Password" Placeholder="Crea una contraseña segura"></asp:TextBox>
            </div>

            <div class="form-group">
                <label for="txtDescripcion"><i class="fa fa-comment"></i>Descripción (opcional)</label>
                <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="3" Placeholder="Cuéntanos algo sobre ti"></asp:TextBox>
            </div>

            <asp:Button ID="btnRegistrar" runat="server" Text="Registrarse" CssClass="btn btn-primary" OnClick="btnRegistrar_Click" />
            <a href="Login.aspx" class="login-link">¿Ya tienes una cuenta? Inicia sesión aquí</a>

             <asp:Label ID="lblError" runat="server" CssClass="text-danger"></asp:Label>
        </div>
    </form>
</body>
</html>
