<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.Login" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="utf-8" />
    <title>Iniciar Sesión - RedPUCP</title>
    <link href="../../Content/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background: linear-gradient(to bottom right, #4b6cb7, #182848);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .login-container {
            background-color: white;
            padding: 40px 50px;
            border-radius: 15px;
            box-shadow: 0 5px 25px rgba(0, 0, 0, 0.3);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        .login-container h2 {
            margin-bottom: 25px;
            font-weight: 700;
            color: #182848;
        }

        .form-control::placeholder {
            color: rgba(0, 0, 0, 0.4);
        }

        .btn-primary {
            background-color: #4b6cb7;
            border: none;
            width: 100%;
            padding: 10px;
            border-radius: 8px;
        }

        .btn-primary:hover {
            background-color: #395a9e;
        }

        .register-link {
            margin-top: 15px;
            display: block;
        }

        .alert {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="login-container">
            <h2>RedPUCP</h2>
            <asp:Label ID="lblError" runat="server" CssClass="alert alert-danger" Visible="false"></asp:Label>

            <div class="form-group mb-3">
                <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" Placeholder="Correo electrónico"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvEmail" runat="server"
                    ControlToValidate="txtEmail"
                    ErrorMessage="El correo es obligatorio."
                    CssClass="text-danger" Display="Dynamic" />
            </div>

            <div class="form-group mb-3">
                <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control" TextMode="Password" Placeholder="Contraseña"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvPassword" runat="server"
                    ControlToValidate="txtPassword"
                    ErrorMessage="La contraseña es obligatoria."
                    CssClass="text-danger" Display="Dynamic" />
            </div>

            <asp:Button ID="btnLogin" runat="server" Text="Iniciar sesión" CssClass="btn btn-primary" OnClick="btnLogin_Click" />

            <a href="Registro.aspx" class="register-link">¿No tienes una cuenta? Regístrate aquí</a>
        </div>
    </form>
</body>
</html>